package com.ruoyi.asr.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Arrays;
import java.util.Date;

/**
 * 标注对象 voice_annotation
 *
 * @author wrh
 * @date 2023-06-25
 */
@Data
public class VoiceAnnotation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 音频所在路径 */
    @Excel(name = "音频所在路径")
    private String audioPath;

    /** 音频名字 */
    @Excel(name = "音频名字")
    private String audioName;

    /** 标注前文本内容 */
    @Excel(name = "标注前文本内容")
    private String preText;

    /** 标注后文本内容 */
    @Excel(name = "标注后文本内容")
    private String afterText;

    /** 是否使用该音频。0使用 ，1舍弃 */
    @Excel(name = "是否使用该音频。0使用 ，1舍弃")
    private String isUse;

    /** 任务所属人 */
    @Excel(name = "任务所属人")
    private String taskOwner;

    /** 是否已标注。0未标注，1已标注 */
    @Excel(name = "是否已标注。0未标注，1已标注")
    private String isMark;

    private String[] dynamicTags;

    private String[] selectTags;

    private Integer taskId;

    private Long clazzId;

    private Long labelUser;

    private Long verityUser;

    private Date labelTime;

    private Date verityTime;

    private Integer isPass;

    private String verityFeedback;

    private Long updateNum;

    /** 字错率 */
    @Excel(name = "字错率")
    private String cer;

    /** 标签备注 */
    @Excel(name = "标签备注")
    private String tags;

    /** 音频长度 */
    @Excel(name = "音频长度")
    private String audioTime;

    /** 文字长度 */
    @Excel(name = "文字长度")
    private String audioLenth;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String verity;

    /** 正确率 */
    @Excel(name = "正确率")
    private Long accuracy;

    /** 审核后文本 */
    @Excel(name = "审核后文本")
    private String verityText;
}
