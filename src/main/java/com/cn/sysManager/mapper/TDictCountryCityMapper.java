package com.cn.sysManager.mapper;

import com.cn.sysManager.models.TDictCountryCity;

public interface TDictCountryCityMapper extends IBaseMapper<TDictCountryCity>{
    int deleteByPrimaryKey(Integer id);

    int insert(TDictCountryCity record);

    int insertSelective(TDictCountryCity record);

    TDictCountryCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDictCountryCity record);

    int updateByPrimaryKey(TDictCountryCity record);
}