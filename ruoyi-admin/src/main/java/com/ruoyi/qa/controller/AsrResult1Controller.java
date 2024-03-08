package com.ruoyi.qa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.qa.domain.Class1;
import com.ruoyi.qa.domain.ClassP;
import com.ruoyi.qa.mapper.Class1Mapper;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.qa.service.IAsrResult1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * extractController
 *
 * @author ruoyi
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/qa/extract")
public class AsrResult1Controller extends BaseController
{
    @Autowired
    private IAsrResult1Service asrResult1Service;

    @Autowired
    private Class1Mapper class1Mapper;

    /**
     * 查询extract列表
     */
//    @PreAuthorize("@ss.hasPermi('qa:extract:list')")
    @GetMapping("/list")
    public TableDataInfo list(AsrResult1 asrResult1)
    {
        startPage();
        List<AsrResult1> list = asrResult1Service.selectAsrResult1List(asrResult1);
        return getDataTable(list);
    }


    @GetMapping("/options")
    public AjaxResult options(AsrResult1 asrResult1)
    {
        ClassP classp = asrResult1Service.getOptions();
        return success(classp);
    }

    /**
     * 导出extract列表
     */
    @PreAuthorize("@ss.hasPermi('qa:extract:export')")
    @Log(title = "extract", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AsrResult1 asrResult1)
    {
//        String jsonData = asrResult1Service.selectAsrResult1JsonList(asrResult1);
        String jsonData = asrResult1Service.selectAsrResult1JsonList2(asrResult1);
        String formattedJsonData = formatJson(jsonData);
        try (FileWriter fileWriter = new FileWriter("data.json")) {
            fileWriter.write(formattedJsonData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("导出异常");
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=data.json");
        try (FileInputStream fis = new FileInputStream(new File("data.json"))) {
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("导出异常");
        }
    }
    private String formatJson(String jsonData) {
        Object jsonObjectOrArray = JSON.parse(jsonData,Feature.OrderedField);
        return JSON.toJSONString(jsonObjectOrArray, SerializerFeature.PrettyFormat);
    }



    /**
     * 获取extract详细信息
     */
//    @PreAuthorize("@ss.hasPermi('qa:extract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        //回显分类
        AsrResult1 asrResult1 = asrResult1Service.selectAsrResult1ById(id);

        List<Long> paths = new ArrayList<>();
        if(asrResult1.getCuda()!=null && !"0".equals(asrResult1.getCuda()) && !"".equals(asrResult1.getCuda())) {
            Long clas = Long.parseLong(asrResult1.getCuda());
            List<Long> parentPath = findParentPath(clas, paths);
            Collections.reverse(parentPath);
            asrResult1.setClassPath(parentPath);
        }else if (asrResult1.getClazzId()!=null && asrResult1.getClazzId()!=0L){
            List<Long> parentPath = findParentPath(asrResult1.getClazzId(), paths);
            Collections.reverse(parentPath);
            asrResult1.setClassPath(parentPath);
        }
        return success(asrResult1);
    }


    private List<Long> findParentPath(Long clasId, List<Long> paths){
        //1.收集当前节点id
        paths.add(clasId);
        Class1 class1 = class1Mapper.selectClass1ById(clasId);
        if (class1.getClass1Id() !=null){
            findParentPath(class1.getClass1Id(),paths);
        }
        return paths;

    }

    /**
     * 新增extract
     */
    @PreAuthorize("@ss.hasPermi('qa:extract:add')")
    @Log(title = "extract", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AsrResult1 asrResult1)
    {
        return toAjax(asrResult1Service.insertAsrResult1(asrResult1));
    }

    /**
     * 修改extract
     */
//    @PreAuthorize("@ss.hasPermi('qa:extract:edit')")
    @Log(title = "extract", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AsrResult1 asrResult1)
    {

        return toAjax(asrResult1Service.updateAsrResult1(asrResult1));
    }

    /**
     * 删除extract
     */
//    @PreAuthorize("@ss.hasPermi('qa:extract:remove')")
    @Log(title = "extract", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(asrResult1Service.deleteAsrResult1ByIds(ids));
    }
}
