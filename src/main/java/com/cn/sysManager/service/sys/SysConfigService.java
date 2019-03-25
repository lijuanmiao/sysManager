package com.cn.sysManager.service.sys;

import com.cn.sysManager.mapper.TSysConfigMapper;
import com.cn.sysManager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**系统参数
 * Created by lijm on 2018-10-23.
 */
@Service
public class SysConfigService extends BaseService implements ISysConfigService{

    @Autowired
    private TSysConfigMapper sysConfigMapper;


    @Autowired
    void setSysConfigMapper(TSysConfigMapper tSysConfigMapper) {
        this.sysConfigMapper = tSysConfigMapper;
        super.baseMapper = tSysConfigMapper;
    }
}
