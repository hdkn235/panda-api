package com.hd.api.service;

import com.hd.api.dto.DictDTO;
import com.hd.api.model.DictClass;

import java.util.List;

public interface DictClassService extends BaseService<DictClass> {

	/**
	 * 根据字典分类获取数据
	 * 
	 * @param code
	 * @return
	 */
	List<DictDTO> listDictByClassCode(String code);

}
