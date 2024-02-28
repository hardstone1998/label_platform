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
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.service.IVerityTaskSysUserService;
import com.ruoyi.total.domain.InsertBatch;
import com.ruoyi.total.mapper.InsertBatchMapper;
import com.ruoyi.verity.mapper.VerityQaMapper;
import com.ruoyi.verity.service.IVerityQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
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

    @Value("${label.verity.qa-error-num}")
    public int qaErrorNum;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private InsertBatchMapper insertBatchMapper;

    /**
     * 查询extract
     *
     * @param id extract主键
     * @return extract
     */
    @Override
    public AsrResult1 selectVerityQaById(Long id)
    {
        AsrResult1 asrResult1 = verityQaMapper.selectAsrResult1ById(id);
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

        if (1L==asrResult1.getVerityUser()){
            List<AsrResult1> asrResult1s = verityQaMapper.selectAsrResult1List(asrResult1);
            return asrResult1s;
        }else {
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
     */
    @Transactional
    @Override
    public int updateVerityQa(AsrResult1 asrResult1)
    {
        System.out.println(asrResult1);
        AsrResult1 asrResult11 = new AsrResult1();
        asrResult11.setUpdateTime(DateUtils.getNowDate());
        asrResult11.setQa4(asrResult1.getQa1());
        asrResult11.setQa5(asrResult1.getQa2());
        asrResult11.setQa6(asrResult1.getQa6());
        SysUser sysUser = sysUserService.selectUserByUserName(asrResult1.getReqUser());
        asrResult11.setVerityUser(sysUser.getUserId());
        asrResult11.setVerityTime(DateUtils.getNowDate());
        asrResult11.setVerityFeedback(asrResult1.getVerityFeedback());
        AsrResult1 asrResult12 = verityQaMapper.selectAsrResult1ById(asrResult1.getId());
        int errNum = 0;
        int qaNum = 0;
        if (asrResult12.getQa1()!=null&&!"".equals(asrResult12.getQa1())){
            qaNum++;
            if (!asrResult12.getQa1().equals(asrResult11.getQa4()))
            errNum++;
        }
        if (asrResult12.getQa2()!=null&&!"".equals(asrResult12.getQa2())){
            qaNum++;
            if (!asrResult12.getQa2().equals(asrResult11.getQa5()))
                errNum++;
        }
        if (asrResult12.getQa3()!=null&&!"".equals(asrResult12.getQa3())){
            qaNum++;
            if (!asrResult12.getQa3().equals(asrResult11.getQa6()))
                errNum++;
        }
        qaNum++;
        if (asrResult1.getClazzId()!=asrResult12.getClazzId()){
            errNum++;
        }
        asrResult11.setIsPass(qaErrorNum>=errNum?1:0);
        asrResult11.setAccuracy((long) (100.0*(qaNum-errNum)/qaNum));
        asrResult11.setVerityClazzId(asrResult1.getClazzId());
        asrResult11.setId(asrResult1.getId());
        return verityQaMapper.updateAsrResult1(asrResult11);
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
