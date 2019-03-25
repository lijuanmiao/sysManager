package com.cn.sysManager.service;

import com.cn.sysManager.common.Order;
import com.cn.sysManager.common.RollPage;
import com.cn.sysManager.mapper.IBaseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2017-12-12.
 */
@Service
public class BaseService implements IBaseService {

    protected Integer pageSizeDefault = 20;

    protected IBaseMapper baseMapper;

    void setBaseMapper(IBaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public  void addBasic(Object obj){
        baseMapper.insertSelective(obj);
    }

    @Override
    public void modifyBasic(Object obj){
        baseMapper.updateByPrimaryKeySelective(obj);
    }


    @Override
    public void delBasic(Integer ids){
        baseMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public Object findObjByKey(Integer seq){

       return baseMapper.selectByPrimaryKey(seq);
    }

    @Override
    public  Object findObj(Map<String, Object> params){

        return baseMapper.selectByParams(params);
    }

    @Override
    public List findListByParams(Map<String, Object> params, Order order){
        return baseMapper.selectListByParams(params,null,null,order==null?"":order.toString());
    }

    @Override
    public Integer findCountByParams(Map<String, Object> params){
        return baseMapper.selectCountByParams(params);
    }

    @Override
    public RollPage<T> findListPageByParams(Map<String, Object> params, Order order, Integer pageNum, Integer pageSize){

        Integer recordSum= baseMapper.selectCountByParams(params);
        RollPage<T> rollPage=new RollPage<T>();

        String orderParams = (order==null)?null:order.toString();
        rollPage.setRecordSum(recordSum);

        if(pageSize == null){
            rollPage.setPageSize(pageSizeDefault);
        }else{
            rollPage.setPageSize(pageSize);
        }
        pageNum = (pageNum==null)?1:pageNum;
        rollPage.setPageNum(pageNum);
        Integer pageOffset = (rollPage.getPageNum()-1)*rollPage.getPageSize();
        if(recordSum>0){
            //代表存在记录
            List<T> list = baseMapper.selectListByParams(params,pageOffset,pageSize,orderParams);
            rollPage.setRecordList(list);
        }else{
            rollPage.setRecordList(new ArrayList<T>());
        }
        return rollPage;
    }
}
