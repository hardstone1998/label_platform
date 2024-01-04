package com.ruoyi.qa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.qa.domain.*;
import com.ruoyi.qa.mapper.Class1Mapper;
import com.ruoyi.qa.mapper.QaRelationMapper;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qa.mapper.AsrResult1Mapper;
import com.ruoyi.qa.service.IAsrResult1Service;

/**
 * extractService业务层处理
 *
 * @author ruoyi
 * @date 2023-07-13
 */
@Service
public class AsrResult1ServiceImpl implements IAsrResult1Service
{
    @Autowired
    private AsrResult1Mapper asrResult1Mapper;

    @Autowired
    private QaRelationMapper qaRelationMapper;

    @Autowired
    private Class1Mapper class1Mapper;

    /**
     * 查询extract
     *
     * @param id extract主键
     * @return extract
     */
    @Override
    public AsrResult1 selectAsrResult1ById(Long id)
    {
        System.out.println("extract被调用"+id);
        AsrResult1 asrResult1 = asrResult1Mapper.selectAsrResult1ById(id);
        QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResult1.getId());
        if (qaRelation!=null){
            String s = JSONObject.toJSONString(qaRelation);
            JSONObject jsonObject = JSONObject.parseObject(s);
            asrResult1.setQaRelation(jsonObject);
        }
        String qaMark = asrResult1.getQaMark();

        if (qaMark==null || qaMark.equals("")){
            asrResult1.setMarkresult(asrResult1.getQaExtract());

        }else {
            asrResult1.setMarkresult(asrResult1.getQaMark());
        }
        return asrResult1;
    }


    //递归查找所有子分类
    private List<Class1> getChildrens(Class1 root,List<Class1> all){
        List<Class1> collect = all.stream().filter((class11 -> {
            return class11.getClass1Id() == root.getId();
        })).map(class1->{
            class1.setChildren(getChildrens(class1,all));
            return class1;
        }).sorted((menu1,menu2)->{
            return (int) (menu1.getId()-menu2.getId());
        }).collect(Collectors.toList());

        return collect;
    }
    /**
     * 查询extract列表
     *
     * @param asrResult1 extract
     * @return extract
     */
    @Override
    public List<AsrResult1> selectAsrResult1List(AsrResult1 asrResult1)
    {

        if (asrResult1.getTaskOwner().equals("admin")){
            List<AsrResult1> asrResult1s = asrResult1Mapper.selectAsrResult1List(asrResult1);
            return asrResult1s;
        }else {
            System.out.println(asrResult1);
            return asrResult1Mapper.selectAsrResult1ListByOwner(asrResult1);
        }

    }

    /**
     * 新增extract
     *
     * @param asrResult1 extract
     * @return 结果
     */
    @Override
    public int insertAsrResult1(AsrResult1 asrResult1)
    {
        asrResult1.setCreateTime(DateUtils.getNowDate());
        return asrResult1Mapper.insertAsrResult1(asrResult1);
    }

    /**
     * 修改extract
     *
     * @param asrResult1 extract
     * @return 结果
     */
    @Override
    public int updateAsrResult1(AsrResult1 asrResult1)
    {
        asrResult1.setUpdateTime(DateUtils.getNowDate());
        String qa1 = asrResult1.getQa1();
        String qa2 = asrResult1.getQa2();
        String qa3 = asrResult1.getQa3();
        String qa_sum="";
        if (!qa1.equals("")){
            qa_sum+=qa1+"\n";
        }
        if (!qa2.equals("")){
            qa_sum+=qa2+"\n";
        }
        if(!qa3.equals("")){
            qa_sum+=qa3+"\n";
        }



        asrResult1.setQaSum(qa_sum);
        asrResult1.setIsMark("是");

        String qaExtract = asrResult1.getMarkresult();
        asrResult1.setQaMark(qaExtract);
        System.out.println("cuda结果是----------》"+asrResult1.getCuda());
        return asrResult1Mapper.updateAsrResult1(asrResult1);
    }

    /**
     * 批量删除extract
     *
     * @param ids 需要删除的extract主键
     * @return 结果
     */
    @Override
    public int deleteAsrResult1ByIds(Long[] ids)
    {
        return asrResult1Mapper.deleteAsrResult1ByIds(ids);
    }

    /**
     * 删除extract信息
     *
     * @param id extract主键
     * @return 结果
     */
    @Override
    public int deleteAsrResult1ById(Long id)
    {
        return asrResult1Mapper.deleteAsrResult1ById(id);
    }

    @Override
    public String selectAsrResult1JsonList(AsrResult1 asrResult1) {
        //选择标注后的数据。

        List<AsrResult1> asrResult1s = asrResult1Mapper.selectAsrResult1ListByJson(asrResult1);
        ArrayList<ExportResJson> exportResJsons = new ArrayList<ExportResJson>();



        for (int i = 0; i < asrResult1s.size(); i++) {
            String qa1 = asrResult1s.get(i).getQa1();
            String qa2 = asrResult1s.get(i).getQa2();
            String qa3 = asrResult1s.get(i).getQa3();

            //根据asrResult1的id查询是否存在关联关系。

            if (qa1 !=null && !qa1.equals("")){
                ExportResJson exportResJsonQ1 = new ExportResJson();
                int customerAskIndex = qa1.indexOf("客户问：");
                int customerServiceReplyIndex = qa1.indexOf("客服回答：");
                if (customerAskIndex != -1 && customerServiceReplyIndex != -1) {
                    String customerQuestion = qa1.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa1.substring(customerServiceReplyIndex + 5).trim();
                    exportResJsonQ1.setInstruction(customerQuestion);
                    exportResJsonQ1.setInput("");
                    exportResJsonQ1.setHistory(new ArrayList<>());
                    exportResJsonQ1.setOutput(serviceReply);
                    exportResJsons.add(exportResJsonQ1);

                } else {
                    System.out.println("---------未找到有效的客户问和客服回答内容。"+"当前id"+ asrResult1s.get(i).getId());
                }
            }
            if (qa2 !=null && !qa2.equals("")){
                ExportResJson exportResJsonQ2 = new ExportResJson();
                Long asrResId = asrResult1s.get(i).getId();
                QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResId);
                //先检查qa2和qa1有没有关联，有关联的话将qa1加入histoty
                int customerAskIndex = qa2.indexOf("客户问：");
                int customerServiceReplyIndex = qa2.indexOf("客服回答：");
                if (customerAskIndex != -1 && customerServiceReplyIndex != -1) {
                    String customerQuestion = qa2.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa2.substring(customerServiceReplyIndex + 5).trim();

                    if (qaRelation!=null){
                        if (qaRelation.getQa1() !=null && qaRelation.getQa2()!=null && qaRelation.getQa3()==null){
                            exportResJsonQ2.setInstruction(customerQuestion);
                            exportResJsonQ2.setOutput(serviceReply);
                            exportResJsonQ2.setInput("");
                            String qa12 = qaRelation.getQa1();
                            int customer2= qa12.indexOf("客户问：");
                            int customerService2 = qa12.indexOf("客服回答：");
                            if (customer2 != -1 && customerService2 != -1) {
                                String customerQuestion1 = qa12.substring(customer2 + 4, customerService2).trim();
                                String serviceReply1 = qa12.substring(customerService2 + 5).trim();
                                ArrayList<String> objects = new ArrayList<>();
                                objects.add(customerQuestion1);
                                objects.add(serviceReply1);
                                ArrayList<List<String>> lists2 = new ArrayList<>();
                                lists2.add(objects);
                                exportResJsonQ2.setHistory(lists2);
                                exportResJsons.add(exportResJsonQ2);
                            }
                        }
                    }else {
                        exportResJsonQ2.setInstruction(customerQuestion);
                        exportResJsonQ2.setOutput(serviceReply);
                        exportResJsonQ2.setInput("");
                        exportResJsonQ2.setHistory(new ArrayList<>());
                        exportResJsons.add(exportResJsonQ2);
                    }

                } else {
                    System.out.println("未找到有效的客户问和客服回答内容。");
                }
            }
            if (qa3 !=null && !qa3.equals("")){
                ExportResJson exportResJsonQ3 = new ExportResJson();
                int customerAskIndex = qa3.indexOf("客户问：");
                int customerServiceReplyIndex = qa3.indexOf("客服回答：");
                Long asrResId = asrResult1s.get(i).getId();
                QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResId);

                if (customerAskIndex != -1 && customerServiceReplyIndex != -1) {
                    String customerQuestion = qa3.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa3.substring(customerServiceReplyIndex + 5).trim();
                    if (qaRelation!=null){
                        if (qaRelation.getQa1() !=null && qaRelation.getQa2()!=null && qaRelation.getQa3()!=null){
                            String qa31 = qaRelation.getQa1();
                            String qa32 = qaRelation.getQa2();
                            int customerAskIndex31 = qa31.indexOf("客户问：");
                            int customerServiceReplyIndex31 = qa31.indexOf("客服回答：");
                            int customerAskIndex32 = qa32.indexOf("客户问：");
                            int customerServiceReplyIndex32 = qa32.indexOf("客服回答：");
                            exportResJsonQ3.setInstruction(customerQuestion);
                            exportResJsonQ3.setOutput(serviceReply);
                            exportResJsonQ3.setInput("");
                            if (customerAskIndex31 != -1 && customerServiceReplyIndex31 != -1) {
                                String customerQuestion1 = qa31.substring(customerAskIndex31 + 4, customerServiceReplyIndex31).trim();
                                String serviceReply1 = qa31.substring(customerServiceReplyIndex31 + 5).trim();
                                String customerQuestion32 = qa32.substring(customerAskIndex32 + 4, customerServiceReplyIndex32).trim();
                                String serviceReply32 = qa32.substring(customerServiceReplyIndex32 + 5).trim();

                                ArrayList<String> objects = new ArrayList<>();
                                objects.add(customerQuestion1);
                                objects.add(serviceReply1);
                                ArrayList<String> objects2 = new ArrayList<>();
                                objects2.add(customerQuestion32);
                                objects2.add(serviceReply32);
                                ArrayList<List<String>> lists2 = new ArrayList<>();
                                lists2.add(objects);
                                lists2.add(objects2);
                                exportResJsonQ3.setHistory(lists2);
                                exportResJsons.add(exportResJsonQ3);
                            }

                        }

                    }else {
                        exportResJsonQ3.setInstruction(customerQuestion);
                        exportResJsonQ3.setOutput(serviceReply);
                        exportResJsonQ3.setInput("");
                        exportResJsonQ3.setHistory(new ArrayList<>());
                        exportResJsons.add(exportResJsonQ3);
                    }



                } else {
                    System.out.println("未找到有效的客户问和客服回答内容。");
                }
            }
        }


        //String s = JSON.toJSONString(exportResJsons);

        String s = JSONObject.toJSONString(exportResJsons);

        return s;


        //return null;
    }

    @Override
    public ClassP getOptions() {

        //查询3级分类
        List<Class1> class1s = class1Mapper.selectClass1List(null);
        List<Class1> level1Menus = class1s.stream().filter(class1 ->
                class1.getClass1Id() == null
        ).map((menu)->{
            menu.setChildren(getChildrens(menu,class1s));
            return menu;
        }).sorted((menu1,menu2)->{
            return (int) (menu1.getId() - menu2.getId());
        }).collect(Collectors.toList());

        ClassP classP = new ClassP();
        classP.setOptions(level1Menus);
        return classP;
    }
}
