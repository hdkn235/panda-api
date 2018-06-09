package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.DictDTO;
import com.hd.api.model.Dict;
import com.hd.api.model.DictClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictMapper extends BaseMapper<Dict> {

	/**
	 * 根据字典Code获取字典信息
	 * 
	 * @param code
	 * @return
	 */
	Dict getByCode(String code);

	/**
	 * 根据字典分类及字典Code获取字典信息
	 * 
	 * @param dictClassCode
	 * @param dictCode
	 * @return
	 */
	Dict getByClassAndCode(@Param("dictClassCode") String dictClassCode, @Param("dictCode") String dictCode);

	List<DictDTO> getByClassCode(String dictClassCode);

	List<DictDTO> getByClassId(String dictClassId);

	DictClass getDictClassByDictCode(String code);

	Map<String, Object> getAreaTypeByCity(String city);
}