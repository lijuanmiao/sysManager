package com.cn.sysManager.service;

import com.cn.sysManager.mapper.TNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-09-18.
 */
@Service
public class NoticeService implements INotictService{


    @Autowired
    private TNoticeMapper noticeMapper;

    @Override
    public List getNotice(){
      Map<String,Object> params = new HashMap<String,Object>();
      return noticeMapper.selectListByParams(params,null,null,null);
    }
}
