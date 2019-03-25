package com.cn.sysManager.service;

import com.cn.sysManager.mapper.TOpUserBasicMapper;
import com.cn.sysManager.models.TOpUserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-09-18.
 */
@Service
public class OpUserBasicService implements IOpuserBasicService {

    @Autowired
    private TOpUserBasicMapper basicMapper;


    @Override
    public TOpUserBasic getOpUserBasic(String no){

        return  basicMapper.selectByPrimaryKey(Integer.parseInt(no));
    }

    @Override
    public List<TOpUserBasic> getAll(Map<String,Object> params){
        return basicMapper.selectListByParams(params,0,10000,null);
    }

    @Override
    @Transactional
    public  void addBatchUser(List<TOpUserBasic> tList){
        basicMapper.insertBatch(tList);

        if(tList!=null && tList.size()>0){

            List<TOpUserBasic> upList = new ArrayList<TOpUserBasic>();
            for(TOpUserBasic up:tList){

                TOpUserBasic basic = new TOpUserBasic();
                basic.setId(up.getId());
                basic.setUpIp(String.format("%0" + 8 + "d", up.getId()));
                upList.add(basic);
            }
            basicMapper.updateBatch(upList);
        }
    }
}