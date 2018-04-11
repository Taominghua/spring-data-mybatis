package com.enterprise.data.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>  需要操作的数据对象
 * @param <PK> 数据对象主键类型，一般为Integer
 * @author tommy
 */
public interface CrudMapper<T, PK extends Serializable> {


    /**
     * 插入一个新的对象
     *
     * @param record 对应的记录
     * @return 返回影响的行数，成功返回 1
     */
    int insert(T record);

    /**
     * 根据条件插入一个新的对象
     *
     * @param record 对应的记录
     * @return 返回影响的行数，成功返回 1
     */
    int insertSelective(T record);

    /**
     * 批量插入一组新的对象
     *
     * @param list 对应的记录列表
     * @return 返回影响的行数，成功返回 1
     */
    int batchInsert(List<T> list);

    /**
     * 根据id删除对象
     *
     * @param id
     * @return 返回影响的行数，0为删除失败。
     */
    int deleteById(PK id);

    /**
     * 根据id更新对象
     *
     * @param record
     * @return 返回影响的行数，失败返回0
     */
    int updateById(T record);

    /**
     * 根据id更新非空字段
     *
     * @param record
     * @return 返回影响的行数，失败返回0
     */
    int updateBySelective(T record);

    /**
     * 根据id查询对象
     *
     * @param id
     * @return 不存在则返回null
     */
    T getById(PK id);

    /**
     * 取出所有记录，大表禁用
     *
     * @return 如果表记录为空，返回空队列（list对象非null，是一个size为 0 的list）。
     */
    List<T> list();

    /**
     * 根据对象条件查询，取出所有记录
     *
     * @param record 对象条件
     * @return 返回列表
     */
    List<T> listByEntityWhere(T record);

}
