package com.hd.api.utils.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtil {

	/**
	 * 取得指定单元格行和列
	 * 
	 * @param keyMap
	 *            所有单元格行、列集合
	 * @param key
	 *            单元格标识
	 * @return 0：列 1：行（列表型数据不记行，即1无值）
	 */
	public static int[] getPos(JSONArray keyArray, String key) {
		int[] ret = new int[2];
		JSONObject obj = new JSONObject();
		for (int i = 0; i < keyArray.length(); i++) {
			if (keyArray.getJSONObject(i).has("startcell"))
				continue;
			if (keyArray.getJSONObject(i).getString("key").equals(key)) {
				obj = keyArray.getJSONObject(i);
				break;
			}
		}
		String val = (String) obj.getString("y");

		if (val == null || val.length() == 0)
			return ret;
		if (obj.has("x"))
			ret[0] = Integer.parseInt(obj.getString("x"));
		else
			ret[0] = 0;
		ret[1] = Integer.parseInt(obj.getString("y"));

		return ret;
	}

	/**
	 * 取对应格子的值
	 * 
	 * @param sheet
	 * @param rowNo
	 *            行
	 * @param cellNo
	 *            列
	 * @return
	 * @throws IOException
	 */
	public static String getCellValue(Sheet sheet, int rowNo, int cellNo) {
		String cellValue = null;
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				DecimalFormat df = new DecimalFormat("0");
				cellValue = getCutDotStr(df.format(cell.getNumericCellValue()));
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				cellValue = cell.getStringCellValue();
			}
			if (cellValue != null) {
				cellValue = cellValue.trim();
			}
		} else {
			cellValue = null;
		}
		return cellValue;
	}

	/**
	 * 取整数
	 * 
	 * @param srcString
	 * @return
	 */
	private static String getCutDotStr(String srcString) {
		String newString = "";
		if (srcString != null && srcString.endsWith(".0")) {
			newString = srcString.substring(0, srcString.length() - 2);
		} else {
			newString = srcString;
		}
		return newString;
	}

	/**
	 * 读数据模板
	 * 
	 * @param 模板地址
	 * @throws IOException
	 */
	public static JSONArray getTemplateFile(String templateFileName) throws IOException {
		FileInputStream fis = new FileInputStream(templateFileName);

		Workbook wbPartModule = null;
		if (templateFileName.endsWith(".xlsx")) {
			wbPartModule = new XSSFWorkbook(fis);
		} else if (templateFileName.endsWith(".xls")) {
			wbPartModule = new HSSFWorkbook(fis);
		}
		int numOfSheet = wbPartModule.getNumberOfSheets();
		JSONArray keyArray = new JSONArray();
		for (int i = 0; i < numOfSheet; i++) {
			Sheet sheet = wbPartModule.getSheetAt(i);
			JSONArray sheetArray = readSheet(sheet);
			keyArray.put(sheetArray);
		}
		fis.close();
		return keyArray;
	}

	/**
	 * 读模板数据的样式值置等信息
	 * 
	 * @param keyMap
	 * @param sheet
	 */
	private static JSONArray readSheet(Sheet sheet) {
		JSONArray array = new JSONArray();
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();

		int startLine = -1;
		for (int j = firstRowNum; j <= lastRowNum; j++) {
			Row rowIn = sheet.getRow(j);
			if (rowIn == null) {
				continue;
			}

			int firstCellNum = rowIn.getFirstCellNum();
			int lastCellNum = rowIn.getLastCellNum();

			for (int k = firstCellNum; k <= lastCellNum; k++) {
				// Cell cellIn = rowIn.getCell((short) k);
				Cell cellIn = rowIn.getCell(k);
				if (cellIn == null) {
					continue;
				}

				int cellType = cellIn.getCellType();
				if (Cell.CELL_TYPE_STRING != cellType) {
					continue;
				}
				String cellValue = cellIn.getStringCellValue();
				if (cellValue == null) {
					continue;
				}
				cellValue = cellValue.trim();
				// 单元格填充
				if (cellValue.length() > 2 && cellValue.substring(0, 2).equals("<%")) {
					String key = "cell_" + Integer.toString(j) + "_" + k;
					String keyPos = Integer.toString(k) + "," + Integer.toString(j);
					String para = cellValue.substring(2, cellValue.length());
					JSONObject obj = new JSONObject();
					obj.put("key", key);
					obj.put("para", para);
					obj.put("pos", keyPos);
					obj.put("y", Integer.toString(k));
					obj.put("x", Integer.toString(j));
					obj.put("style", cellIn.getCellStyle());
					array.put(obj);
				}
				// 循环填充
				else if (cellValue.length() >= 3 && cellValue.substring(0, 3).equals("<!%")) {
					startLine = j;
					if (k == firstCellNum) {
						JSONObject obj = new JSONObject();
						obj.put("startcell", Integer.toString(j));
						array.put(obj);
					}
					String key = "cell_" + Integer.toString(j) + "_" + k;
					String para = "";
					if (cellValue.length() > 3)
						para = cellValue.substring(3, cellValue.length());
					JSONObject obj = new JSONObject();
					obj.put("key", key);
					obj.put("para", para);
					obj.put("y", Integer.toString(k));
					obj.put("style", cellIn.getCellStyle());
					array.put(obj);
				}
			}

			if (startLine == j) {
				for (int m = firstCellNum; m < lastCellNum; m++) {
					Cell cellIn = rowIn.getCell(m);
					String cellValue = cellIn.getStringCellValue();
					if (cellValue == null || cellValue.equals("")) {
						String key = "cell_" + Integer.toString(j) + "_" + m;
						JSONObject obj = new JSONObject();
						obj.put("key", key);
						obj.put("para", "");
						obj.put("y", Integer.toString(m));
						obj.put("style", cellIn.getCellStyle());
						array.put(obj);
					}
				}
			}
		}
		return array;
	}

	/**
	 * 获取格式，不适于循环方法中使用，wb.createCellStyle()次数超过4000将抛异常
	 * 
	 * @param keyMap
	 * @param key
	 * @return
	 */
	public static CellStyle getStyle(JSONArray keyArray, String key, Workbook wb) {
		CellStyle cellStyle = null;
		JSONObject obj = new JSONObject();
		for (int i = 0; i < keyArray.length(); i++) {
			if (keyArray.getJSONObject(i).has("startcell"))
				continue;
			if (keyArray.getJSONObject(i).getString("key").equals(key)) {
				obj = keyArray.getJSONObject(i);
				break;
			}
		}
		cellStyle = (CellStyle) obj.get("style");
		// 当字符超出时换行
		// cellStyle.setWrapText(true);
		CellStyle newStyle = wb.createCellStyle();
		newStyle.cloneStyleFrom(cellStyle);
		return newStyle;
	}

	/**
	 * Excel单元格输出
	 * 
	 * @param sheet
	 * @param row
	 *            行
	 * @param cell
	 *            列
	 * @param value
	 *            值
	 * @param cellStyle
	 *            样式
	 */
	public static void setValue(Sheet sheet, int row, int cell, Object value, CellStyle cellStyle) {
		Row rowIn = sheet.getRow(row);
		if (rowIn == null) {
			rowIn = sheet.createRow(row);
		}
		Cell cellIn = rowIn.getCell(cell);
		if (cellIn == null) {
			cellIn = rowIn.createCell(cell);
		}
		if (cellStyle != null) {
			// 修复产生多超过4000 cellStyle 异常
			// CellStyle newStyle = wb.createCellStyle();
			// newStyle.cloneStyleFrom(cellStyle);
			cellIn.setCellStyle(cellStyle);
		}
		// 对时间格式进行单独处理
		if (value == null) {
			cellIn.setCellValue("");
		} else {
			if (isCellDateFormatted(cellStyle)) {
				cellIn.setCellValue((Date) value);
			} else {
				cellIn.setCellValue(new XSSFRichTextString(value.toString()));
			}
		}
	}

	/**
	 * 根据表格样式判断是否为日期格式
	 * 
	 * @param cellStyle
	 * @return
	 */
	public static boolean isCellDateFormatted(CellStyle cellStyle) {
		if (cellStyle == null) {
			return false;
		}
		int i = cellStyle.getDataFormat();
		String f = cellStyle.getDataFormatString();

		return org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
	}

	/**
	 * 适用于导出的数据Excel格式样式重复性较少 不适用于循环方法中使用
	 * 
	 * @param wbModule
	 * @param sheet
	 * @param pos
	 *            模板文件信息
	 * @param startCell
	 *            开始的行
	 * @param value
	 *            要填充的数据
	 * @param cellStyle
	 *            表格样式
	 */
	public static void createCell(Workbook wbModule, Sheet sheet, JSONArray keyArray, int startCell, Object value,
			String cellStyle) {
		int[] excelPos = getPos(keyArray, cellStyle);
		setValue(sheet, startCell, excelPos[0], value, getStyle(keyArray, cellStyle, wbModule));
	}

	/**
	 * 功能：拷贝sheet 实际调用 copySheet(targetSheet, sourceSheet, targetWork,
	 * sourceWork, true)
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 */
	public static void copySheet(Sheet targetSheet, Sheet sourceSheet, Workbook targetWork, Workbook sourceWork)
			throws Exception {
		if (targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException(
				"调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
		}
		copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true);
	}

	/**
	 * 功能：拷贝sheet
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 * @param copyStyle
	 *            boolean 是否拷贝样式
	 */
	public static void copySheet(Sheet targetSheet, Sheet sourceSheet, Workbook targetWork, Workbook sourceWork,
			boolean copyStyle) throws Exception {

		if (targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException(
				"调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
		}

		// 复制源表中的行
		int maxColumnNum = 0;

		Map styleMap = (copyStyle) ? new HashMap() : null;

		Drawing patriarch = targetSheet.createDrawingPatriarch(); // 用于复制注释
		for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet.getLastRowNum(); i++) {
			Row sourceRow = sourceSheet.getRow(i);
			Row targetRow = targetSheet.createRow(i);

			if (sourceRow != null) {
				copyRow(targetRow, sourceRow, targetWork, sourceWork, patriarch, styleMap);
				if (sourceRow.getLastCellNum() > maxColumnNum) {
					maxColumnNum = sourceRow.getLastCellNum();
				}
			}
		}

		// 复制源表中的合并单元格
		mergerRegion(targetSheet, sourceSheet);

		// 设置目标sheet的列宽
		for (int i = 0; i <= maxColumnNum; i++) {
			targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
		}

		targetSheet.getPrintSetup().setLandscape(sourceSheet.getPrintSetup().getLandscape());
		targetSheet.getPrintSetup().setPaperSize(sourceSheet.getPrintSetup().getPaperSize());
	}

	/**
	 * 功能：拷贝row
	 * 
	 * @param targetRow
	 * @param sourceRow
	 * @param styleMap
	 * @param targetWork
	 * @param sourceWork
	 * @param targetPatriarch
	 */
	public static void copyRow(Row targetRow, Row sourceRow, Workbook targetWork, Workbook sourceWork,
			Drawing targetPatriarch, Map styleMap) throws Exception {
		if (targetRow == null || sourceRow == null || targetWork == null || sourceWork == null
				|| targetPatriarch == null) {
			throw new IllegalArgumentException(
				"调用PoiUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork、targetPatriarch都不能为空，故抛出该异常！");
		}

		// 设置行高
		targetRow.setHeight(sourceRow.getHeight());

		for (int i = sourceRow.getFirstCellNum(); i <= sourceRow.getLastCellNum(); i++) {
			Cell sourceCell = sourceRow.getCell(i);
			Cell targetCell = targetRow.getCell(i);

			if (sourceCell != null) {
				if (targetCell == null) {
					targetCell = targetRow.createCell(i);
				}

				// 拷贝单元格，包括内容和样式
				copyCell(targetCell, sourceCell, targetWork, sourceWork, styleMap);

				// 拷贝单元格注释
				// copyComment(targetCell,sourceCell,targetPatriarch);
			}
		}
	}

	/**
	 * 功能：拷贝cell，依据styleMap是否为空判断是否拷贝单元格样式
	 * 
	 * @param targetCell
	 *            不能为空
	 * @param sourceCell
	 *            不能为空
	 * @param targetWork
	 *            不能为空
	 * @param sourceWork
	 *            不能为空
	 * @param styleMap
	 *            可以为空
	 */
	public static void copyCell(Cell targetCell, Cell sourceCell, Workbook targetWork, Workbook sourceWork,
			Map styleMap) {
		if (targetCell == null || sourceCell == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException(
				"调用PoiUtil.copyCell()方法时，targetCell、sourceCell、targetWork、sourceWork都不能为空，故抛出该异常！");
		}

		// 处理单元格样式
		if (styleMap != null) {
			if (targetWork == sourceWork) {
				CellStyle cs = sourceCell.getCellStyle();
				targetCell.setCellStyle(cs);
			} else {
				String stHashCode = "" + sourceCell.getCellStyle().hashCode();
				CellStyle targetCellStyle = (CellStyle) styleMap.get(stHashCode);
				if (targetCellStyle == null) {
					targetCellStyle = targetWork.createCellStyle();
					targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
					styleMap.put(stHashCode, targetCellStyle);
				}

				targetCell.setCellStyle(targetCellStyle);
			}
		}

		// 处理单元格内容
		switch (sourceCell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			targetCell.setCellValue(sourceCell.getRichStringCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			targetCell.setCellValue(sourceCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			targetCell.setCellType(Cell.CELL_TYPE_BLANK);
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			targetCell.setCellValue(sourceCell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			targetCell.setCellFormula(sourceCell.getCellFormula());
			break;
		default:
			break;
		}
	}

	/**
	 * 功能：清理sheet，依据styleMap是否为空判断是否拷贝单元格样式
	 * 
	 * @param sheet
	 *            不能为空 待清空的sheet
	 * @param startLine
	 *            不能为空 从第几行开始清空该sheet
	 */
	public static void clearSheet(Sheet sheet, int startLine) {
		if (sheet == null) {
			throw new IllegalArgumentException("调用PoiUtil.clearSheet()方法时，sheet不能为空，故抛出该异常！");
		}

		int firstRowNum = startLine;
		int lastRowNum = sheet.getLastRowNum();

		for (int i = firstRowNum; i <= lastRowNum; i++) {
			Row rowIn = sheet.getRow(i);
			if (rowIn == null) {
				continue;
			}

			int firstCellNum = rowIn.getFirstCellNum();
			int lastCellNum = rowIn.getLastCellNum();
			for (int k = firstCellNum; k <= lastCellNum; k++) {
				Cell cellIn = rowIn.getCell(k);
				if (cellIn == null)
					continue;
				cellIn.setCellValue("");
			}
		}
	}

	/**
	 * 功能：拷贝comment
	 * 
	 * @param targetCell
	 * @param sourceCell
	 * @param targetPatriarch
	 */
	/*
	 * public static void copyComment(HSSFCell targetCell,HSSFCell
	 * sourceCell,HSSFPatriarch targetPatriarch)throws Exception{ if(targetCell
	 * == null || sourceCell == null || targetPatriarch == null){ throw new
	 * IllegalArgumentException(
	 * "调用PoiUtil.copyCommentr()方法时，targetCell、sourceCell、targetPatriarch都不能为空，故抛出该异常！"
	 * ); }
	 * 
	 * //处理单元格注释 HSSFComment comment = sourceCell.getCellComment(); if(comment
	 * != null){ HSSFComment newComment = targetPatriarch.createComment(new
	 * HSSFClientAnchor()); newComment.setAuthor(comment.getAuthor());
	 * newComment.setColumn(comment.getColumn());
	 * newComment.setFillColor(comment.getFillColor());
	 * newComment.setHorizontalAlignment(comment.getHorizontalAlignment());
	 * newComment.setLineStyle(comment.getLineStyle());
	 * newComment.setLineStyleColor(comment.getLineStyleColor());
	 * newComment.setLineWidth(comment.getLineWidth());
	 * newComment.setMarginBottom(comment.getMarginBottom());
	 * newComment.setMarginLeft(comment.getMarginLeft());
	 * newComment.setMarginTop(comment.getMarginTop());
	 * newComment.setMarginRight(comment.getMarginRight());
	 * newComment.setNoFill(comment.isNoFill());
	 * newComment.setRow(comment.getRow());
	 * newComment.setShapeType(comment.getShapeType());
	 * newComment.setString(comment.getString());
	 * newComment.setVerticalAlignment(comment.getVerticalAlignment());
	 * newComment.setVisible(comment.isVisible());
	 * targetCell.setCellComment(newComment); } }
	 */
	/**
	 * 功能：复制原有sheet的合并单元格到新创建的sheet
	 * 
	 * @param sheetCreat
	 * @param sourceSheet
	 */
	public static void mergerRegion(Sheet targetSheet, Sheet sourceSheet) throws Exception {
		if (targetSheet == null || sourceSheet == null) {
			throw new IllegalArgumentException("调用PoiUtil.mergerRegion()方法时，targetSheet或者sourceSheet不能为空，故抛出该异常！");
		}

		for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
			CellRangeAddress oldRange = sourceSheet.getMergedRegion(i);
			CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
				oldRange.getFirstColumn(), oldRange.getLastColumn());
			targetSheet.addMergedRegion(newRange);
		}
	}

}