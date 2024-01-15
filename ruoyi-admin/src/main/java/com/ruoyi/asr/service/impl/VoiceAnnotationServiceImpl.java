package com.ruoyi.asr.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.ruoyi.asr.domain.*;
import com.ruoyi.asr.mapper.AsrTagMapper;
import com.ruoyi.asr.mapper.AsrTagRelationMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qa.domain.ExportResJson;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.commons.io.output.AppendableOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asr.mapper.VoiceAnnotationMapper;
import com.ruoyi.asr.service.IVoiceAnnotationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标注Service业务层处理
 *
 * @author wrh
 * @date 2023-06-25
 */
@Service
public class VoiceAnnotationServiceImpl implements IVoiceAnnotationService
{
    @Autowired
    private VoiceAnnotationMapper voiceAnnotationMapper;
    @Autowired
    private AsrTagMapper asrTagMapper;

    @Autowired
    private AsrTagRelationMapper asrTagRelationMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 查询标注
     *
     * @param id 标注主键
     * @return 标注
     */
    @Override
    public VoiceAnnotation selectVoiceAnnotationById(Long id)
    {

        //查询所有的标签
        List<AsrTag> asrTags = asrTagMapper.selectAsrTagList(null);
        ArrayList<String> tags=new ArrayList<>();
        if (asrTags.size()>0){
            for (int i = 0; i < asrTags.size(); i++) {
                tags.add(asrTags.get(i).getTagName());
            }

        }
        String[] allTags = convertToStringArray(tags);


        //根据id查对应的标签内容
        //根据asrId查询标签
        HashSet<String> set = new HashSet<>();
        List<AsrTagRelation> asrTagRelations = asrTagRelationMapper.selectAsrTagRelByAsrId(id);
        if (asrTagRelations.size()>0){
            for (int i = 0; i < asrTagRelations.size(); i++) {
                AsrTag asrTag = asrTagMapper.selectAsrTagById(asrTagRelations.get(i).getTagId());
                set.add(asrTag.getTagName());
            }
        }

        String[] stringArray = convertToStringArray(set);

        if (stringArray.length>0){
            VoiceAnnotation voiceAnnotation = voiceAnnotationMapper.selectVoiceAnnotationById(id);
            voiceAnnotation.setDynamicTags(allTags);
            voiceAnnotation.setSelectTags(stringArray);
            return voiceAnnotation;
        }else {

            VoiceAnnotation voiceAnnotation = voiceAnnotationMapper.selectVoiceAnnotationById(id);
            voiceAnnotation.setDynamicTags(allTags);
            voiceAnnotation.setSelectTags(stringArray);
            return voiceAnnotation;
        }

    }


    public static String[] convertToStringArray(HashSet<String> hashSet) {
        String[] stringArray = new String[hashSet.size()];
        hashSet.toArray(stringArray);
        return stringArray;
    }
    public static String[] convertToStringArray(ArrayList<String> arrayList) {
        String[] stringArray = new String[arrayList.size()];
        arrayList.toArray(stringArray);
        return stringArray;
    }
    /**
     * 查询标注列表
     *
     * @param voiceAnnotation 标注
     * @return 标注
     */
    @Override
    public List<VoiceAnnotation> selectVoiceAnnotationList(VoiceAnnotation voiceAnnotation)
    {
        //查询所有标签名字
//        List<AsrTag> asrTags = asrTagMapper.selectAsrTagList(null);
//        Set<String> tags=new HashSet<>();
//        for (int i = 0; i < asrTags.size(); i++) {
//            tags.add(asrTags.get(i).getTagName());
//        }
//        String[] strings = tags.toArray(new String[tags.size()]);
//
        if ("admin".equals(voiceAnnotation.getTaskOwner())){

            return voiceAnnotationMapper.selectVoiceAnnotationList(voiceAnnotation);
        }else {

            return voiceAnnotationMapper.selectVoiceAnnotationListByOwner(voiceAnnotation);
        }

    }

    /**
     * 新增标注
     *
     * @param voiceAnnotation 标注
     * @return 结果
     */
    @Override
    public int insertVoiceAnnotation(VoiceAnnotation voiceAnnotation)
    {


        return voiceAnnotationMapper.insertVoiceAnnotation(voiceAnnotation);
    }

    /**
     * 修改标注
     *
     * @param voiceAnnotation 标注
     * @return 结果
     */
    @Override
    @Transactional
    public int updateVoiceAnnotation(VoiceAnnotation voiceAnnotation)
    {
        voiceAnnotation.setIsMark("是");
        voiceAnnotation.setLabelTime(DateUtils.getNowDate());
        String labelUserName = voiceAnnotation.getLabelUserName();
        SysUser sysUser = sysUserMapper.selectUserByUserName(labelUserName);
        voiceAnnotation.setLabelUser(sysUser.getUserId());


        if(voiceAnnotation.getAfterText()==null){
            voiceAnnotation.setAfterText(voiceAnnotation.getPreText());
        }

        //获取该内容的标签
        String[] selectTags = voiceAnnotation.getSelectTags();

        //先删除所有改标签关联信息，再将该标签添加关联表中
//        asrTagRelationMapper.deleteTagRelByAsrId(voiceAnnotation.getId());
//        ArrayList<Long> tagIds = new ArrayList<>();
        //根据名字获取id
//        for (String selectTag : selectTags) {
//            AsrTag asrTag = asrTagMapper.selectTagByTagName(selectTag);
//            tagIds.add(asrTag.getId());
//        }



        //循环插入
//        for (int i = 0; i < tagIds.size(); i++) {
//            AsrTagRelation asrTagRelation1 = new AsrTagRelation();
//            asrTagRelation1.setAsrId(voiceAnnotation.getId());
//            asrTagRelation1.setTagId(tagIds.get(i));
//            asrTagRelation1.setCreateTime(DateUtils.getNowDate());
//            asrTagRelationMapper.insertAsrTagRelation(asrTagRelation1);
//        }
        voiceAnnotation.setUpdateTime(DateUtils.getNowDate());

        return voiceAnnotationMapper.updateVoiceAnnotation(voiceAnnotation);
    }

    /**
     * 批量删除标注
     *
     * @param ids 需要删除的标注主键
     * @return 结果
     */
    @Override
    public int deleteVoiceAnnotationByIds(Long[] ids)
    {
        return voiceAnnotationMapper.deleteVoiceAnnotationByIds(ids);
    }

    /**
     * 删除标注信息
     *
     * @param id 标注主键
     * @return 结果
     */
    @Override
    public int deleteVoiceAnnotationById(Long id)
    {
        return voiceAnnotationMapper.deleteVoiceAnnotationById(id);
    }

    @Override
    public CountData selectVoiceAnnotationData() {

        int asrSum = voiceAnnotationMapper.getASRSum();
        int asrMarkedSum = voiceAnnotationMapper.getAsrMarkedSum();
        int asrAllocationSum = voiceAnnotationMapper.getAsrAllocationSum();
        int qaCountSum = voiceAnnotationMapper.getQACountSum();
        int qaMarkedSum = voiceAnnotationMapper.getQAMarkedSum();
        int qAllocationSum = voiceAnnotationMapper.getQAllocationSum();

        int asrNotMarkedSum = asrSum - asrMarkedSum;
        int qaNotMarkedSum = qaCountSum - qaMarkedSum;

        CountData countData = new CountData();
        countData.setAsrCountSum((long) asrSum);
        countData.setAsrMarkedSum((long) asrMarkedSum);
        countData.setAsrAllocationSum((long) asrAllocationSum);
        countData.setAsrNotMarkedSum((long) asrNotMarkedSum);
        countData.setQACountSum((long) qaCountSum);
        countData.setQAMarkedSum((long) qaMarkedSum);
        countData.setQANotMarkedSum((long) qaNotMarkedSum);
        countData.setQAllocationSum((long) qAllocationSum);
        return countData;
    }

    @Override
    public String selectVoiceAnnotationJsonList(VoiceAnnotation voiceAnnotation,String filePath) {
        List<VoiceAnnotation> voiceAnnotations = voiceAnnotationMapper.selectVoiceAnnotationList(voiceAnnotation);

        ArrayList<ExportASRResJson> exportResJsons = new ArrayList<ExportASRResJson>();
        for (int i = 0; i < voiceAnnotations.size(); i++) {
            VoiceAnnotation voiceAnnotation1 = voiceAnnotations.get(i);
            String audioName = voiceAnnotation1.getAudioName();
            String afterText = voiceAnnotation1.getAfterText();
            if(afterText==null||afterText.length()==0||audioName==null||audioName.length()==0)continue;
            ExportASRResJson exportASRResJson = new ExportASRResJson();
            String audioPath = "";
            if ('/'==filePath.charAt(filePath.length()-1)){
                audioName = filePath+audioName;
            }else {
                audioName = filePath + "/" +audioName;
            }

            exportASRResJson.setAudio(new ExportASRResJson.Audio(audioName,afterText));
            exportResJsons.add(exportASRResJson);
        }

        return JSONObject.toJSONString(exportResJsons);
    }

    @Override
    public int selectVoiceAnnotationCount(VoiceAnnotation voiceAnnotation) {
        return voiceAnnotationMapper.selectVoiceAnnotationCount(voiceAnnotation);
    }
}
