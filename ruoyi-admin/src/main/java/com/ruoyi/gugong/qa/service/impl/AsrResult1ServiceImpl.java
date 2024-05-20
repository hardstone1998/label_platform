package com.ruoyi.gugong.qa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.qa.domain.*;
import com.ruoyi.gugong.qa.mapper.AsrResult1Mapper;
import com.ruoyi.gugong.qa.mapper.Class1Mapper;
import com.ruoyi.gugong.qa.mapper.QaRelationMapper;
import com.ruoyi.gugong.qa.service.IAsrResult1Service;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.total.task.domain.AddVerityUser;
import com.ruoyi.total.tool.domain.LabelStatistics;
import com.ruoyi.total.total.domain.InsertBatch;
import com.ruoyi.total.total.mapper.InsertBatchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * extractService业务层处理
 *
 * @author ruoyi
 * @date 2023-07-13
 */
@Service
@Slf4j
public class AsrResult1ServiceImpl implements IAsrResult1Service
{
    @Autowired
    private AsrResult1Mapper asrResult1Mapper;

    @Autowired
    private QaRelationMapper qaRelationMapper;

    @Autowired
    private Class1Mapper class1Mapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private InsertBatchMapper insertBatchMapper;

    /**
     * 查询extract
     *
     * @param id extract主键
     * @return extract
     */
    @Override
    public AsrResult1 selectAsrResult1ById(Long id)
    {
        AsrResult1 asrResult1 = asrResult1Mapper.selectAsrResult1ById(id);
        InsertBatch insertBatch = insertBatchMapper.selectInsertBatchById(Long.valueOf(asrResult1.getInsertBatchId()!=null?asrResult1.getInsertBatchId():0));
        asrResult1.setFolder(insertBatch.getFolder());

        QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResult1.getId());
        if (qaRelation!=null){
            String s = JSONObject.toJSONString(qaRelation);
            JSONObject jsonObject = JSONObject.parseObject(s);
//            asrResult1.setQaRelation(jsonObject);
        }
        String qaMark = asrResult1.getQaMark();

        if (qaMark==null || qaMark.equals("")){
            asrResult1.setMarkresult(asrResult1.getQaExtract());

        }else {
            asrResult1.setMarkresult(asrResult1.getQaMark());
        }
        return asrResult1;
    }

    @Override
    public LabelStatistics selectAsrResult1Count(AsrResult1 asrResult1) {
        return asrResult1Mapper.selectAsrResult1Count(asrResult1);
    }


    //递归查找所有子分类
    private List<Class1> getChildrens(Class1 root, List<Class1> all){
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
    @Transactional
    @Override
    public int updateAsrResult1(AsrResult1 asrResult1)
    {
        asrResult1.setUpdateTime(DateUtils.getNowDate());
        String qa1 = asrResult1.getQa1();
        String qa2 = asrResult1.getQa2();
        String qa3 = asrResult1.getQa3();
        String qa_sum="";
        if (qa1!=null&&!qa1.equals("")){
            qa_sum+=qa1+"\n";
        }
        if (qa2!=null&&!qa2.equals("")){
            qa_sum+=qa2+"\n";
        }
        if(qa3!=null&&!qa3.equals("")){
            qa_sum+=qa3+"\n";
        }

        asrResult1.setQaSum(qa_sum);
        asrResult1.setIsMark("是");

        String qaExtract = asrResult1.getMarkresult();
        asrResult1.setQaMark(qaExtract);
        String labelUserName = asrResult1.getLabelUserName();
        SysUser sysUser = sysUserMapper.selectUserByUserName(labelUserName);
        asrResult1.setLabelUser(sysUser.getUserId());
        asrResult1.setLabelTime(DateUtils.getNowDate());
        if (asrResult1.getClazzId()!=null) asrResult1.setCuda(asrResult1.getClazzId().toString());
        asrResult1.setLabelTime(DateUtils.getNowDate());
        return asrResult1Mapper.updateAsrResult1(asrResult1);
    }

    @Override
    public int updateAsrResult1ByTaskAndLabelUser(AsrResult1 asrResult1) {
        return asrResult1Mapper.updateAsrResult1ByTaskAndLabelUser(asrResult1);
    }

    @Override
    public int updateAsrResult1VerityUser(AsrResult1 asrResult1) {
        return asrResult1Mapper.updateAsrResult1VerityUser(asrResult1);
    }

    @Override
    public int addVerity(AddVerityUser addVerityUser) {
        return asrResult1Mapper.addVerityUser(addVerityUser);
    }

    @Override
    public int subtractVerity(AddVerityUser addVerityUser) {
        return asrResult1Mapper.subtractVerity(addVerityUser);
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

    /**
     * 导出方式2，自动关联qa1和qa2。同时qa1和qa2也会独立导出
     *
     * @param asrResult1 筛选信息
     * @return 结果
     */
    @Override
    public String selectAsrResult1JsonList2(AsrResult1 asrResult1) {
        List<AsrResult1> asrResult1s = asrResult1Mapper.selectAsrResult1ListByJson(asrResult1);
        ArrayList<ExportResJson> exportResJsons = new ArrayList<ExportResJson>();
        for (AsrResult1 a:asrResult1s) {
            String qa1 = null;
            String qa2 = null;
            String qa3 = null;
//            如果审核过，则使用审核过的qa对
            if (a.getQa4()!=null){
                qa1 = a.getQa4();
                qa2 = a.getQa5();
                qa3 = a.getQa6();
            }else {
                qa1 = a.getQa1();
                qa2 = a.getQa2();
                qa3 = a.getQa3();
            }
//            加入前面的qa对如果为空，则进行前移的机制
            if ((qa1==null||qa1=="")&&(qa2==null||qa2=="")&&(qa3==null||qa3=="")){
                continue;
            }
            for (int i = 0; i < 2; i++) {
                if (qa1==null||qa1==""){
                    qa1 = qa2;
                    qa2 = qa3;
                }
            }
            ExportResJson exportResJsonQ1 = new ExportResJson();
//            添加qa1
            if (qa1!=null&&qa1!="") {
                qa1 = qa1.replace("客户：", "客户问：").replace("客服：", "客服回答：").replace("客服答：", "客服回答：").replace("客户询问：", "客户问：");
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
                    log.error("qa1未找到有效的客户问和客服回答内容。当前id:{}",a.getId() );
                }
            }
//            添加qa2
            if (qa2 !=null && !qa2.equals("")) {
                ExportResJson exportResJsonQ2 = new ExportResJson();
                qa2 = qa2.replace("客户：", "客户问：").replace("客服：", "客服回答：").replace("客服答：", "客服回答：").replace("客户询问：", "客户问：");
                //先检查qa2和qa1有没有关联，有关联的话将qa1加入history
                int customerAskIndex = qa2.indexOf("客户问：");
                int customerServiceReplyIndex = qa2.indexOf("客服回答：");
                if (customerAskIndex != -1 && customerServiceReplyIndex != -1) {
                    String customerQuestion = qa2.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa2.substring(customerServiceReplyIndex + 5).trim();
                    exportResJsonQ2.setInstruction(customerQuestion);
                    exportResJsonQ2.setOutput(serviceReply);
                    exportResJsonQ2.setInput("");
                    String qa12 = qa1;
                    int customer2 = qa12.indexOf("客户问：");
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
                } else {
                    log.error("qa2未找到有效的客户问和客服回答内容。当前id:{}",a.getId() );
                }
            }

            if (qa3 !=null && !qa3.equals("")){
                ExportResJson exportResJsonQ3 = new ExportResJson();
                qa3 = qa3.replace("客户：", "客户问：").replace("客服：","客服回答：").replace("客服答：","客服回答：").replace("客户询问：","客户问：");
                int customerAskIndex = qa3.indexOf("客户问：");
                int customerServiceReplyIndex = qa3.indexOf("客服回答：");
                if (customerAskIndex > -1 && customerServiceReplyIndex > -1) {
                    String customerQuestion = qa3.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa3.substring(customerServiceReplyIndex + 5).trim();
                    String qa31 = qa1;
                    String qa32 = qa2;
                    int customerAskIndex31 = qa31.indexOf("客户问：");
                    int customerServiceReplyIndex31 = qa31.indexOf("客服回答：");
                    int customerAskIndex32 = qa32.indexOf("客户问：");
                    int customerServiceReplyIndex32 = qa32.indexOf("客服回答：");
//                    if (customerServiceReplyIndex32<customerAskIndex32)continue;
                    exportResJsonQ3.setInstruction(customerQuestion);
                    exportResJsonQ3.setOutput(serviceReply);
                    exportResJsonQ3.setInput("");
                    if (customerAskIndex31 != -1 && customerServiceReplyIndex31 != -1&&customerAskIndex32 != -1 && customerServiceReplyIndex32 != -1) {
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
                } else {
                    log.error("qa3未找到有效的客户问和客服回答内容。当前id:{}",a.getId() );
                }
            }
        }
        String s = JSONObject.toJSONString(exportResJsons);

        return s;
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
                qa1 = qa1.replace("客户：", "客户问：").replace("客服：","客服回答：").replace("客服答：","客服回答：").replace("客户询问：","客户问：");
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
                    log.error("qa1未找到有效的客户问和客服回答内容。当前id:{}" );
                }
            }
            if (qa2 !=null && !qa2.equals("")){
                ExportResJson exportResJsonQ2 = new ExportResJson();
                qa2 = qa2.replace("客户：", "客户问：").replace("客服：","客服回答：").replace("客服答：","客服回答：").replace("客户询问：","客户问：");
                Long asrResId = asrResult1s.get(i).getId();
                System.out.println(asrResId);
                QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResId);
//                QaRelation qaRelation = new QaRelation();
                qaRelation.setQa1(qa1);
                qaRelation.setQa2(qa2);
                qaRelation.setQa2(qa3);
                //先检查qa2和qa1有没有关联，有关联的话将qa1加入history
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
//                            String qa12 = qaRelation.getQa1();
                            String qa12 = qa1;
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
                    log.error("qa2未找到有效的客户问和客服回答内容。当前id:{}");
                }
            }
            if (qa3 !=null && !qa3.equals("")){
                ExportResJson exportResJsonQ3 = new ExportResJson();
                qa3 = qa3.replace("客户：", "客户问：").replace("客服：","客服回答：").replace("客服答：","客服回答：").replace("客户询问：","客户问：");
                int customerAskIndex = qa3.indexOf("客户问：");
                int customerServiceReplyIndex = qa3.indexOf("客服回答：");
                Long asrResId = asrResult1s.get(i).getId();
                QaRelation qaRelation = qaRelationMapper.selectQaRelationByASRId(asrResId);
//                QaRelation qaRelation = new QaRelation();
                qaRelation.setQa1(qa1);
                qaRelation.setQa2(qa2);
                qaRelation.setQa2(qa3);
                if (customerAskIndex != -1 && customerServiceReplyIndex != -1) {
//                    System.out.println(customerAskIndex);
//                    System.out.println(customerServiceReplyIndex);
                    String customerQuestion = qa3.substring(customerAskIndex + 4, customerServiceReplyIndex).trim();
                    String serviceReply = qa3.substring(customerServiceReplyIndex + 5).trim();
                    if (qaRelation!=null){
                        if (qaRelation.getQa1() !=null && qaRelation.getQa2()!=null && qaRelation.getQa3()!=null){
//                            String qa31 = qaRelation.getQa1();
//                            String qa32 = qaRelation.getQa2();
                            String qa31 = qa1;
                            String qa32 = qa2;
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
//
                    }else {
                        exportResJsonQ3.setInstruction(customerQuestion);
                        exportResJsonQ3.setOutput(serviceReply);
                        exportResJsonQ3.setInput("");
                        exportResJsonQ3.setHistory(new ArrayList<>());
                        exportResJsons.add(exportResJsonQ3);
                    }



                } else {
                    log.error("qa3未找到有效的客户问和客服回答内容。当前id:{}" );
                }
            }
        }
        //String s = JSON.toJSONString(exportResJsons);
        String s = JSONObject.toJSONString(exportResJsons);
        return s;
        //return null;
    }

    /**
     * 查询分类信息
     *
     * @return 结果
     */
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
