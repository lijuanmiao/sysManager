package com.cn.sysManager.service;

import com.cn.sysManager.common.Order;
import com.cn.sysManager.common.RollPage;
import org.apache.poi.ss.formula.functions.T;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2017-10-27.
 */
public interface IBaseService {
    /**
     * 增加记录
     * @param obj
     * @throws Exception
     */
    void addBasic(Object obj) throws Exception;

    /**
     * 修改记录
     * @param obj
     * @throws Exception
     */
    void modifyBasic(Object obj) throws Exception;

    /**
     * 删除记录
     * @param ids
     * @throws Exception
     */
    void delBasic(Integer ids) throws Exception;

    /**
     * 根据主键查询记录
     * @param seq
     * @return
     * @throws Exception
     */
    Object findObjByKey(Integer seq) throws Exception;

    /**
     * 根据条件查询记录
     * @param params
     * @return
     * @throws Exception
     */
    Object findObj(Map<String, Object> params) throws Exception;

    /**
     * 根据条件查询列表
     * @param params
     * @param order
     * @return
     * @throws Exception
     */
    List findListByParams(Map<String, Object> params, Order order) throws Exception;

   /**
     * 根据条件查询 ，返回记录总数
     * @param params
     * @return
     */
    Integer findCountByParams(Map<String, Object> params);

    /**
     * 根据条件查询 列表（分页查询）
     * @param params
     * @param order
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    RollPage<T> findListPageByParams(Map<String, Object> params, Order order, Integer pageNum, Integer pageSize);
}
