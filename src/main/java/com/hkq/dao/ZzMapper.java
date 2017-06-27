package com.hkq.dao;

import com.hkq.entity.Zz;
import com.hkq.util.PageUtil;

import java.util.List;

public interface ZzMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Zz record);

    int insertSelective(Zz record);

    Zz selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Zz record);

    int updateByPrimaryKey(Zz record);

    int selectZzListCount(PageUtil<Zz> pageUtil);

    List<Zz> selectZzList(PageUtil<Zz> pageUtil);

    List<Zz> selectZz();


    List<Zz> selectWhereZz(Zz zzWhere);

    int insertZzForm(Zz addZz);

    int updateZz(Zz zz1);
}