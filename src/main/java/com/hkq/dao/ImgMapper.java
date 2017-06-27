package com.hkq.dao;

import com.hkq.entity.Img;

import java.util.List;

public interface ImgMapper {
    int deleteByPrimaryKey(Short iid);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Short iid);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    int insertBatchZzImage(List<Img> imgList);
}