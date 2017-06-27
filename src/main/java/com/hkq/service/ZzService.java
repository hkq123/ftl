package com.hkq.service;

import com.hkq.entity.Flavorid;
import com.hkq.entity.Zz;
import com.hkq.util.PageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */
public interface ZzService {
    PageUtil<Zz> selectUser(PageUtil<Zz> pageUtil);

    List<Flavorid> selectZzFlavorList();

    List<Zz> selectZz();

    List<Zz> selectZzList(Zz zzWhere);

    int saveZzForm(Zz addZz);

    int updateZz(Zz zz1);
}
