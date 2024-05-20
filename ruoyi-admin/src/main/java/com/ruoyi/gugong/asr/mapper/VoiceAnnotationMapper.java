package com.ruoyi.gugong.asr.mapper;

import java.util.List;
import com.ruoyi.gugong.asr.domain.VoiceAnnotation;
import com.ruoyi.total.task.domain.AddVerityUser;
import com.ruoyi.total.tool.domain.LabelStatistics;

/**
 * 标注Mapper接口
 *
 * @author wrh
 * @date 2023-06-25
 */
public interface VoiceAnnotationMapper
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


    public List<VoiceAnnotation> selectVoiceAnnotationListByOwner(VoiceAnnotation voiceAnnotation);

    public LabelStatistics selectVoiceAnnotationCount(VoiceAnnotation voiceAnnotation);

    public int selectVoiceAnnotationVerityCount(VoiceAnnotation voiceAnnotation);

    public Double selectVoiceAnnotationWordAccuracy(VoiceAnnotation voiceAnnotation);

    public int selectVoiceAnnotationRecall(VoiceAnnotation voiceAnnotation);


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

    public int addVerityUser(AddVerityUser addVerityUser);

    public int subtractVerity(AddVerityUser addVerityUser);

    public int updateVoiceAnnotationByClazzId(VoiceAnnotation voiceAnnotation);


//    updateVoiceAnnotationVerityUser
    public int updateVoiceAnnotationVerityUser(VoiceAnnotation voiceAnnotation);

    public int updateVoiceAnnotationByTaskAndLabelUser(VoiceAnnotation voiceAnnotation);

    /**
     * 删除标注
     *
     * @param id 标注主键
     * @return 结果
     */
    public int deleteVoiceAnnotationById(Long id);

    /**
     * 批量删除标注
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVoiceAnnotationByIds(Long[] ids);


    public int getASRSum();

    public int getAsrMarkedSum();


    public int getAsrAllocationSum() ;
//
    public int getQACountSum();
    public int getQAMarkedSum();
//
    public int getQAllocationSum();



}
