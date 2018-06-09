package com.hd.api.service.impl;

import com.hd.api.dao.DictMapper;
import com.hd.api.dto.DictDTO;
import com.hd.api.model.Dict;
import com.hd.api.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

	@Autowired
	private DictMapper dMapper;

	@Override
	@Cacheable(value = "dict", key = "methodName + '.' + #dictClassCode")
	public List<DictDTO> getByClassCode(String dictClassCode) {
		return dMapper.getByClassCode(dictClassCode);
	}

	@Override
	@Cacheable(value = "dict", key = "methodName + '.' + #dictClassId")
	public List<DictDTO> getByClassId(String dictClassId) {
		return dMapper.getByClassId(dictClassId);
	}

}
