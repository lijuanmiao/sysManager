package com.cn.sysManager.mapper;

import com.cn.sysManager.models.TNotice;

public interface TNoticeMapper extends IBaseMapper<TNotice>{
    int deleteByPrimaryKey(Integer nid);

    int insert(TNotice record);

    int insertSelective(TNotice record);

    TNotice selectByPrimaryKey(Integer nid);

    int updateByPrimaryKeySelective(TNotice record);

    int updateByPrimaryKey(TNotice record);
}