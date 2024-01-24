package com.ruoyi.verity.service;

import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.qa.domain.AsrResult1;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-01-12
 */
public interface IVerityQaService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AsrResult1 selectVerityQaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param asrResult1 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AsrResult1> selectVerityQaList(AsrResult1 asrResult1);

    /**
     * 新增【请填写功能名称】
     *
     * @param asrResult1 【请填写功能名称】
     * @return 结果
     */
    public int insertVerityQa(AsrResult1 asrResult1);

    /**
     * 修改【请填写功能名称】
     *
     * @param asrResult1 【请填写功能名称】
     * @return 结果
     */
    public int updateVerityQa(AsrResult1 asrResult1);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteVerityQaByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteVerityQaById(Long id);
}
