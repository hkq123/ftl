package com.hkq.controller;

import com.alibaba.fastjson.JSON;
import com.hkq.entity.Flavorid;
import com.hkq.entity.Img;
import com.hkq.entity.Zz;
import com.hkq.service.ZzService;
import com.hkq.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/5/30.
 */
@Controller
@RequestMapping("zz")
public class ZzAction {

    @Autowired
    private ZzService zzService;

    List<Img> imgList = new ArrayList<>();

    //修改
    @RequestMapping(value="updateZz",method=RequestMethod.POST)
    @ResponseBody
    public ReturnJson updateZz(String zz){
        ReturnJson rj = new ReturnJson();
        Zz zz1 = JSON.parseObject(zz, Zz.class);
        int intZz = zzService.updateZz(zz1);

        return rj;
    }


    //条件导出excel
    @RequestMapping(value = "exprotZzWhereList",method=RequestMethod.POST)
    public void exprotZzWhereList(HttpServletResponse response,Zz zzWhere){
        List<Zz> listZz = zzService.selectZzList(zzWhere);

        HSSFWorkbook exclWork = new HSSFWorkbook();
        CellStyle setBorder = exclWork.createCellStyle();

        setBorder.setFillForegroundColor((short) 11);// 设置背景色
        setBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setBorder.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet zzSheet = exclWork.createSheet("粽子");
        CellRangeAddress cra = new CellRangeAddress(0,1,5,7);
        zzSheet.addMergedRegion(cra);
        HSSFRow rowTop = zzSheet.createRow(0);
        HSSFCell cell = rowTop.createCell(5);
        cell.setCellValue("粽子列表");
        cell.setCellStyle(setBorder);

        HSSFRow row = zzSheet.createRow(2);

        String[] arrayStr = new String[]{"粽子品牌", "粽子产地", "粽子价格", "粽子生产日期", "粽子口味"};
        for (int i = 0; i < arrayStr.length; i++) {
            row.createCell(i + 2).setCellValue(arrayStr[i]);
        }

        int start = 3;
        for (int k = 0; k < listZz.size(); k++) {
            HSSFRow zzSheetRow = zzSheet.createRow(start);
            zzSheetRow.createCell(2).setCellValue(listZz.get(k).getZbrand());
            zzSheetRow.createCell(3).setCellValue(listZz.get(k).getZorigin());
            zzSheetRow.createCell(4).setCellValue(listZz.get(k).getZprice());
            zzSheetRow.createCell(5).setCellValue(listZz.get(k).getZdateinproduced());
            zzSheetRow.createCell(6).setCellValue(listZz.get(k).getFlavor().getFname());
            start++;
        }

        ServletOutputStream os = null;
        try {
            response.reset();
            //不同文件下载不同MIME类型
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + "ZzList.xls");
            //响应浏览器输出流
            os = response.getOutputStream();

            //写出excl
            exclWork.write(os);
            //存放路径
            File f = new File("D:\\testZzList.xls");
            //创建file文件
            f.createNewFile();
            exclWork.write(f);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    os = null;
                }
            }
            if (null != exclWork) {
                try {
                    exclWork.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    exclWork = null;
                }
            }
        }
    }

    //导出excel
    @RequestMapping("exportZzList")
    public void exportZzList(HttpServletResponse response){
        List<Zz> listZz = zzService.selectZz();

        HSSFWorkbook exclWork = new HSSFWorkbook();
        CellStyle setBorder = exclWork.createCellStyle();

        setBorder.setFillForegroundColor((short) 11);// 设置背景色
        setBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setBorder.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet zzSheet = exclWork.createSheet("粽子");
        CellRangeAddress cra = new CellRangeAddress(0,1,5,7);
        zzSheet.addMergedRegion(cra);
        HSSFRow rowTop = zzSheet.createRow(0);
        HSSFCell cell = rowTop.createCell(5);
        cell.setCellValue("粽子列表");
        cell.setCellStyle(setBorder);

        HSSFRow row = zzSheet.createRow(2);

        String[] arrayStr = new String[]{"粽子品牌", "粽子产地", "粽子价格", "粽子生产日期", "粽子口味"};
        for (int i = 0; i < arrayStr.length; i++) {
            row.createCell(i + 2).setCellValue(arrayStr[i]);
        }

        int start = 3;
        for (int k = 0; k < listZz.size(); k++) {
            HSSFRow zzSheetRow = zzSheet.createRow(start);
            zzSheetRow.createCell(2).setCellValue(listZz.get(k).getZbrand());
            zzSheetRow.createCell(3).setCellValue(listZz.get(k).getZorigin());
            zzSheetRow.createCell(4).setCellValue(listZz.get(k).getZprice());
            zzSheetRow.createCell(5).setCellValue(listZz.get(k).getZdateinproduced());
            zzSheetRow.createCell(6).setCellValue(listZz.get(k).getFlavor().getFname());
            start++;
        }

        ServletOutputStream os = null;
        try {
            response.reset();
            //不同文件下载不同MIME类型
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + "ZzList.xls");
            //响应浏览器输出流
            os = response.getOutputStream();

            //写出excl
            exclWork.write(os);
            //存放路径
            File f = new File("D:\\testZzList.xls");
            //创建file文件
            f.createNewFile();
            exclWork.write(f);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    os = null;
                }
            }
            if (null != exclWork) {
                try {
                    exclWork.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    exclWork = null;
                }
            }
        }

    }

    //新增
    @RequestMapping(value = "saveZzForm",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson saveZzForm(Zz addZz){
        ReturnJson rj = new ReturnJson();

        addZz.setImgList(imgList);
        int i = zzService.saveZzForm(addZz);
        if(i==1){
            rj.setMsg("新增粽子成");
            rj.setSuccess(true);

        }else {
            rj.setMsg("新增粽子失败");
            rj.setSuccess(false);
        }
        imgList.clear();

        return rj;
    }


    //图片上传
    @RequestMapping(value="uploadZzImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadUserImg(HttpServletRequest request, @RequestParam("iuuidname") MultipartFile file){
        String showImg = null;
        if(null != file){
            String imgStr = file.getOriginalFilename();

            String imgPath = UploadFileUtil.uploadFile(request, file, "uploadImgPath");

              Img zzImg = new Img();
            zzImg.setIname(imgStr);
            zzImg.setIuuidname(imgPath);

            imgList.add(zzImg);
            System.out.print(zzImg);

            String uploadPath = PropertiesUtil.getProperties("uploadImgPath");

            showImg = uploadPath+"/"+imgPath;
        }
        return showImg;
    }
    //新增跳转
    @RequestMapping("showZzForm")
    public String showZzForm(){
        return "addZz";
    }
    //查品味
    @RequestMapping("selectZzFlavorList")
    @ResponseBody
    public List<Flavorid> selectZzFlavorList(){
        List<Flavorid> listFlav = zzService.selectZzFlavorList();
        return listFlav;
    }


    //分页列表
    @RequestMapping(value = "queryZz",method = RequestMethod.POST)
    @ResponseBody
    public DataGridJson queryZz(int page,int rows ,PageUtil<Zz> pageUtil){
        DataGridJson dgj = new DataGridJson();

        pageUtil.setCpage(page);
        pageUtil.setPageSize(rows);

        pageUtil =  zzService.selectUser(pageUtil);

        dgj.setTotal(pageUtil.getTotalCount());
        dgj.setRows(pageUtil.getList());
        return dgj;
    }

    @RequestMapping("toZz")
    public String toZz(){
        return "ZzList";
    }



}
