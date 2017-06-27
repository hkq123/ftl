package com.hkq.dao;

import com.hkq.entity.Flavorid;

import java.util.List;

public interface FlavoridMapper {
    int deleteByPrimaryKey(Short flavorid);

    int insert(Flavorid record);

    int insertSelective(Flavorid record);

    Flavorid selectByPrimaryKey(Short flavorid);

    int updateByPrimaryKeySelective(Flavorid record);

    int updateByPrimaryKey(Flavorid record);

    List<Flavorid> selectZzFlavorList();
}