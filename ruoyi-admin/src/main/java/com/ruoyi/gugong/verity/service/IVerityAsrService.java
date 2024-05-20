package com.ruoyi.gugong.verity.service;

import com.ruoyi.gugong.asr.domain.VoiceAnnotation;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-01-12
 */
public interface IVerityAsrService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public VoiceAnnotation selectVerityAsrById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<VoiceAnnotation> selectVerityAsrList(VoiceAnnotation voiceAnnotation);

    /**
     * 新增【请填写功能名称】
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 结果
     */
    public int insertVerityAsr(VoiceAnnotation voiceAnnotation);

    /**
     * 修改【请填写功能名称】
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 结果
     */
    public int updateVerityAsr(VoiceAnnotation voiceAnnotation);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteVerityAsrByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteVerityAsrById(Long id);
}
