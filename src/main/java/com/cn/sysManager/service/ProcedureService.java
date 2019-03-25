package com.cn.sysManager.service;

import com.cn.sysManager.mapper.TProcedureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lijm on 2018-10-22.
 */
@Service
public class ProcedureService implements IProcedureService {


    @Autowired
    private TProcedureMapper procedureMapper;

    public void addBasicTemp(Map<String,Object> params){
        procedureMapper.selectProData(params);
    }
}