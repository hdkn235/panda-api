package com.hd.api.service;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * 
	 * @param record
	 * @return
	 */
	boolean delete(T record);

	/**
	 * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
	 * 
	 * @param ids
	 *            1,2,3
	 * @return
	 */
	boolean deleteByIds(String ids);

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * 
	 * @param record
	 * @return
	 */
	boolean insert(T record);

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * 
	 * @param id
	 * @return
	 */
	T get(String id);

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * 
	 * @param id
	 * @return
	 */
	T getOne(T record);

	/**
	 * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
	 * 
	 * @param ids
	 *            1,2,3
	 * @return
	 */
	List<T> listByIds(String ids);

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * 
	 * @param id
	 * @return
	 */
	List<T> list(T record);

	/**
	 * 查询全部结果
	 * 
	 * @param id
	 * @return
	 */
	List<T> listAll();

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * 
	 * @param id
	 * @return
	 */
	int countAll(T record);

	/**
	 * 根据主键更新属性不为null的值
	 * 
	 * @param record
	 * @return
	 */
	boolean update(T record);

}
