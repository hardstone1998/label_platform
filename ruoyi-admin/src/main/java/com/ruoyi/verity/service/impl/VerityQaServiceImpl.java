package com.ruoyi.verity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qa.domain.*;
import com.ruoyi.qa.mapper.AsrResult1Mapper;
import com.ruoyi.qa.mapper.Class1Mapper;
import com.ruoyi.qa.mapper.QaRelationMapper;
import com.ruoyi.qa.service.IAsrResult1Service;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.verity.mapper.VerityQaMapper;
import com.ruoyi.verity.service.IVerityQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * extractService业务层处理
 *
 * @author ruoyi
 * @date 2023-07-13
 */
@Service
public class VerityQaServiceImpl implements IVerityQaService
{
    @Autowired
    private VerityQaMapper verityQaMapper;

    @Autowired
    private QaRelationMapper qaRelationMapper;


    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询extract
     *
     * @param id extract主键
     * @return extract
     */
    @Override
    public AsrResult1 selectVerityQaById(Long id)
    {
        System.out.println("extract被调用"+id);
        AsrResult1 asrResult1 = verityQaMapper.selectAsrResult1ById(id);
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
    public List<AsrResult1> selectVerityQaList(AsrResult1 asrResult1)
    {

        if (asrResult1.getTaskOwner().equals("admin")){
            List<AsrResult1> asrResult1s = verityQaMapper.selectAsrResult1List(asrResult1);
            return asrResult1s;
        }else {
            System.out.println(asrResult1);
            return verityQaMapper.selectAsrResult1ListByOwner(asrResult1);
        }

    }

    /**
     * 新增extract
     *
     * @param asrResult1 extract
     * @return 结果
     */
    @Override
    public int insertVerityQa(AsrResult1 asrResult1)
    {
        asrResult1.setCreateTime(DateUtils.getNowDate());
        return verityQaMapper.insertAsrResult1(asrResult1);
    }

    /**
     * 修改extract
     *
     * @param asrResult1 extract
     * @return 结果
     * todo
     */
    @Transactional
    @Override
    public int updateVerityQa(AsrResult1 asrResult1)
    {
        System.out.println("asrResult1:"+asrResult1);
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
        String taskOwner = asrResult1.getTaskOwner();
        SysUser sysUser = sysUserMapper.selectUserByUserName(taskOwner);
        asrResult1.setLabelUser(sysUser.getUserId());
        asrResult1.setLabelTime(DateUtils.getNowDate());
        asrResult1.setClazzId(Long.valueOf(asrResult1.getCuda()));
        System.out.println("cuda结果是----------》"+asrResult1.getCuda());
        return verityQaMapper.updateAsrResult1(asrResult1);
    }

    /**
     * 批量删除extract
     *
     * @param ids 需要删除的extract主键
     * @return 结果
     */
    @Override
    public int deleteVerityQaByIds(Long[] ids)
    {
        return verityQaMapper.deleteAsrResult1ByIds(ids);
    }

    /**
     * 删除extract信息
     *
     * @param id extract主键
     * @return 结果
     */
    @Override
    public int deleteVerityQaById(Long id)
    {
        return verityQaMapper.deleteAsrResult1ById(id);
    }

}
