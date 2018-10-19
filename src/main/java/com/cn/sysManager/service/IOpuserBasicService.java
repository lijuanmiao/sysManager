package com.cn.sysManager.service;

import com.cn.sysManager.models.TOpUserBasic;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-09-18.
 */
public interface IOpuserBasicService {//extends IBaseMapper<TOpUserBasic>{


     TOpUserBasic getOpUserBasic(String no);

     List<TOpUserBasic> getAll(Map<String,Object> params);

     void addBatchUser(List<TOpUserBasic> tList);


}
