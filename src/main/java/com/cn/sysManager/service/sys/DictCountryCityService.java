package com.cn.sysManager.service.sys;

import com.cn.sysManager.mapper.TDictCountryCityMapper;
import com.cn.sysManager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lijm on 2018-10-19.
 */

@Service
public class DictCountryCityService extends BaseService implements IDictCountryCityService {


    @Autowired
    private TDictCountryCityMapper dictCountryCityMapper;


    @Autowired
    void setInvestMapper(TDictCountryCityMapper tDictCountryCityMapper) {
        this.dictCountryCityMapper = tDictCountryCityMapper;
        super.baseMapper = tDictCountryCityMapper;
    }


}
