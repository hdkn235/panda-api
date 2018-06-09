package com.hd.api.service.impl;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected BaseMapper<T> mapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(String id) {
		return mapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(T record) {
		return mapper.delete(record) > 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean insert(T record) {
		return mapper.insertSelective(record) > 0;
	}

	@Override
	public T get(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(T record) {
		return mapper.updateByPrimaryKeySelective(record) > 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteByIds(String ids) {
		return mapper.deleteByIds(ids) > 0;
	}

	@Override
	public T getOne(T record) {
		return mapper.selectOne(record);
	}

	@Override
	public List<T> listByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	@Override
	public List<T> list(T record) {
		return mapper.select(record);
	}

	@Override
	public List<T> listAll() {
		return mapper.selectAll();
	}

	@Override
	public int countAll(T record) {
		return mapper.selectCount(record);
	}

}
