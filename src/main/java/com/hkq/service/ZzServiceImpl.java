package com.hkq.service;

import com.hkq.dao.FlavoridMapper;
import com.hkq.dao.ImgMapper;
import com.hkq.dao.ZzMapper;
import com.hkq.entity.Flavorid;
import com.hkq.entity.Img;
import com.hkq.entity.Zz;
import com.hkq.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */
@Service
public class ZzServiceImpl implements ZzService{
    @Autowired
    private ZzMapper zzMapper;

    @Autowired
    private FlavoridMapper flavoridMapper;
    @Autowired
    private ImgMapper imgMapper;
    //条件分页
    @Override
    public PageUtil<Zz> selectUser(PageUtil<Zz> pageUtil) {
        int totalCount = zzMapper.selectZzListCount(pageUtil);

        List<Zz> userList =  zzMapper.selectZzList(pageUtil);
        pageUtil.setTotalCount(totalCount);
        pageUtil.setList(userList);

        return pageUtil;
    }
//查品味
    @Override
    public List<Flavorid> selectZzFlavorList() {
        return flavoridMapper.selectZzFlavorList();
    }
    //查粽子
    @Override
    public List<Zz> selectZz() {
        return zzMapper.selectZz();
    }
    //条件查粽子
    @Override
    public List<Zz> selectZzList(Zz zzWhere) {
        return zzMapper.selectWhereZz(zzWhere);
    }
    //添加
    @Override
    public int saveZzForm(Zz addZz) {
        int i = zzMapper.insertZzForm(addZz);
        BigDecimal addZzId = addZz.getId();
        List<Img> imgList = addZz.getImgList();
        for (Img img : imgList) {
            img.setId(addZzId);
        }
        int i1 = imgMapper.insertBatchZzImage(imgList);
        return i;
    }
    //修改
    @Override
    public int updateZz(Zz zz1) {
        return zzMapper.updateZz(zz1);
    }
}
