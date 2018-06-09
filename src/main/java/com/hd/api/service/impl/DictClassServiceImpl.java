package com.hd.api.service.impl;

import com.hd.api.dao.DictClassMapper;
import com.hd.api.dto.DictDTO;
import com.hd.api.model.DictClass;
import com.hd.api.service.DictClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictClassServiceImpl extends BaseServiceImpl<DictClass> implements DictClassService {

	@Autowired
	private DictClassMapper dcMapper;

	@Override
	@Cacheable(value = "dict", key = "methodName + '.' + #code")
	public List<DictDTO> listDictByClassCode(String code) {
		return dcMapper.listDictByClassCode(code);
	}

}
