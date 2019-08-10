package xyz.zhuoxuan.jinnuo.base.dao;


import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;

/**
 * Mapper基类
 *
 * @author peng.xy
 * @createDate 2018年5月20日 下午12:06:43
 */
public interface BaseMapper<T extends BaseEntity> {

    /**
     * 根据entityId删除记录
     *
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:09:10
     */
    Integer deleteByPrimaryKey(Integer entityId);

    /**
     * 新增entity
     *
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:09:57
     */
    Integer insert(T entity);

    /**
     * 动态新增entity
     *
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:12:06
     */
    Integer insertSelective(T entity);

    /**
     * 根据entityId查询
     *
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:13:27
     */
    T selectByPrimaryKey(Integer entityId);

    /**
     * 动态更新entity
     *
     * @param entity
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:17:40
     */
    Integer updateByPrimaryKeySelective(T entity);

    /**
     * 更新entity
     *
     * @author peng.xy
     * @createDate 2018年5月20日 下午12:18:21
     */
    Integer updateByPrimaryKey(T entity);
}
