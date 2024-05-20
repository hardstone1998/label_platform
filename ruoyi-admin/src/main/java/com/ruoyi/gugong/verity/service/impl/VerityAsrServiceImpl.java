package com.ruoyi.gugong.verity.service.impl;

import java.util.List;

import com.ruoyi.gugong.asr.domain.VoiceAnnotation;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.verity.mapper.VerityAsrMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.total.tool.LevenshteinDistance;
import com.ruoyi.total.total.domain.InsertBatch;
import com.ruoyi.total.total.mapper.InsertBatchMapper;
import com.ruoyi.gugong.verity.service.IVerityAsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-01-12
 */
@Service
public class VerityAsrServiceImpl implements IVerityAsrService
{
    @Autowired
    private VerityAsrMapper verityAsrMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private InsertBatchMapper insertBatchMapper;

    @Value("${label.verity.asr-error-rate}")
    public double errorRate;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public VoiceAnnotation selectVerityAsrById(Long id)
    {
        VoiceAnnotation voiceAnnotation = verityAsrMapper.selectVoiceAnnotationById(id);
        InsertBatch insertBatch = insertBatchMapper.selectInsertBatchById(Long.valueOf(voiceAnnotation.getInsertBatchId()!=null?voiceAnnotation.getInsertBatchId():0));
        voiceAnnotation.setFolder(insertBatch.getFolder());
        return voiceAnnotation;
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<VoiceAnnotation> selectVerityAsrList(VoiceAnnotation voiceAnnotation)
    {
        if (1L == voiceAnnotation.getVerityUser()){
            voiceAnnotation.setVerityUser(null);
            return verityAsrMapper.selectVoiceAnnotationList(voiceAnnotation);
        }else {
            return verityAsrMapper.selectVoiceAnnotationListByOwner(voiceAnnotation);
        }
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param voiceAnnotation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertVerityAsr(VoiceAnnotation voiceAnnotation)
    {
        voiceAnnotation.setCreateTime(DateUtils.getNowDate());
        return verityAsrMapper.insertVoiceAnnotation(voiceAnnotation);
    }

    /**
     * 审核逻辑
     *
     * @param voiceAnnotation 审核请求
     * @return 结果
     */
    @Transactional
    @Override
    public int updateVerityAsr(VoiceAnnotation voiceAnnotation)
    {
        voiceAnnotation.setUpdateTime(DateUtils.getNowDate());
        voiceAnnotation.setVerityTime(DateUtils.getNowDate());
        String labelUserName = voiceAnnotation.getLabelUserName();
        SysUser sysUser = sysUserMapper.selectUserByUserName(labelUserName);
        voiceAnnotation.setVerityUser(sysUser.getUserId());
        String afterText = voiceAnnotation.getAfterText();
        String verityText = voiceAnnotation.getVerityText();
        int accuracy = LevenshteinDistance.calculateDistance(afterText, verityText);
        voiceAnnotation.setAccuracy((long) accuracy);
        double accuracyRate = (1.0*accuracy)/afterText.length();
        voiceAnnotation.setIsPass(accuracyRate>errorRate? 0 : 1);
        return verityAsrMapper.updateVoiceAnnotation(voiceAnnotation);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteVerityAsrByIds(Long[] ids)
    {
        return verityAsrMapper.deleteVoiceAnnotationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteVerityAsrById(Long id)
    {
        return verityAsrMapper.deleteVoiceAnnotationById(id);
    }
}
