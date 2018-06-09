package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.DictDTO;
import com.hd.api.model.DictClass;

import java.util.List;

public interface DictClassMapper extends BaseMapper<DictClass> {

	List<DictDTO> listDictByClassCode(String code);
}