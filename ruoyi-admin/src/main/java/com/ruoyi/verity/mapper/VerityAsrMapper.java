package com.ruoyi.verity.mapper;

import com.ruoyi.asr.domain.VoiceAnnotation;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-01-12
 */
public interface VerityAsrMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public VoiceAnnotation selectVoiceAnnotationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<VoiceAnnotation> selectVoiceAnnotationList(VoiceAnnotation voiceAnnotation);


    public List<VoiceAnnotation> selectVoiceAnnotationListByOwner(VoiceAnnotation voiceAnnotation);

    /**
     * 新增【请填写功能名称】
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 结果
     */
    public int insertVoiceAnnotation(VoiceAnnotation voiceAnnotation);

    /**
     * 修改【请填写功能名称】
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 结果
     */
    public int updateVoiceAnnotation(VoiceAnnotation voiceAnnotation);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteVoiceAnnotationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVoiceAnnotationByIds(Long[] ids);
}
