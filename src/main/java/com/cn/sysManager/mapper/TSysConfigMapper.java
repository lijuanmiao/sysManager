package com.cn.sysManager.mapper;

import com.cn.sysManager.models.TSysConfig;

public interface TSysConfigMapper extends IBaseMapper<TSysConfig>{
    int deleteByPrimaryKey(Integer id);

    int insert(TSysConfig record);

    int insertSelective(TSysConfig record);

    TSysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSysConfig record);

    int updateByPrimaryKey(TSysConfig record);
}