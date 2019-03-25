package com.cn.sysManager.mapper;

import com.cn.sysManager.models.TOpUserBasic;
import java.util.List;

/**用户基本信息-后台
 * Created by lijm on 2017-10-27.
 */
public interface TOpUserBasicMapper extends IBaseMapper<TOpUserBasic> {


    //批量新增
    int insertBatch(List<TOpUserBasic> user);

    //批量更新
    int updateBatch(List<TOpUserBasic> opUserBasics);

}