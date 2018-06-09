package com.hd.api.service;

import com.hd.api.dto.DictDTO;
import com.hd.api.model.Dict;

import java.util.List;

public interface DictService extends BaseService<Dict> {

	/**
	 * 根据字典分类，获取字典
	 * 
	 * @param dictClassCode
	 * @return
	 */
	List<DictDTO> getByClassCode(String dictClassCode);

	/**
	 * 根据字典分类，获取字典
	 * 
	 * @param dictClassId
	 * @return
	 */
	public List<DictDTO> getByClassId(String dictClassId);

}
