package com.hd.api.baseDao;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用mapper
 * 
 * 不能与其他mapper放到同一个包下，扫描会出错
 * 
 * @author hoda
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T> {

}