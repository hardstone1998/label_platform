package com.ruoyi.asr.service;

import java.util.List;

import com.ruoyi.asr.domain.CountData;
import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.task.domain.AddVerityUser;

/**
 * 标注Service接口
 *
 * @author wrh
 * @date 2023-06-25
 */
public interface IVoiceAnnotationService
{
    /**
     * 查询标注
     *
     * @param id 标注主键
     * @return 标注
     */
    public VoiceAnnotation selectVoiceAnnotationById(Long id);

    /**
     * 查询标注列表
     *
     * @param voiceAnnotation 标注
     * @return 标注集合
     */
    public List<VoiceAnnotation> selectVoiceAnnotationList(VoiceAnnotation voiceAnnotation);

    /**
     * 新增标注
     *
     * @param voiceAnnotation 标注
     * @return 结果
     */
    public int insertVoiceAnnotation(VoiceAnnotation voiceAnnotation);

    /**
     * 修改标注
     *
     * @param voiceAnnotation 标注
     * @return 结果
     */
    public int updateVoiceAnnotation(VoiceAnnotation voiceAnnotation);

    /**
     * 修改标注
     *
     * @param voiceAnnotation 标注
     * @return 结果
     */
    public int updateVoiceAnnotationByTaskAndLabelUser(VoiceAnnotation voiceAnnotation);

    /**
     * 修改标注 增加
     *
     * @param addVerityUser 标注
     * @return 结果
     */
    public int addVerity(AddVerityUser addVerityUser);

    /**
     * 修改标注 减少
     *
     * @param addVerityUser 标注
     * @return 结果
     */
    public int subtractVerity(AddVerityUser addVerityUser);

    /**
     * 批量删除标注
     *
     * @param ids 需要删除的标注主键集合
     * @return 结果
     */
    public int deleteVoiceAnnotationByIds(Long[] ids);

    /**
     * 删除标注信息
     *
     * @param id 标注主键
     * @return 结果
     */
    public int deleteVoiceAnnotationById(Long id);

    CountData selectVoiceAnnotationData();

    String selectVoiceAnnotationJsonList(VoiceAnnotation voiceAnnotation,String filePath);

    int selectVoiceAnnotationCount(VoiceAnnotation voiceAnnotation);

    int selectVoiceAnnotationVerityCount(VoiceAnnotation voiceAnnotation);

    Double selectVoiceAnnotationWordAccuracy(VoiceAnnotation voiceAnnotation);

//    selectVoiceAnnotationRecall
    int selectVoiceAnnotationRecall(VoiceAnnotation voiceAnnotation);
}
