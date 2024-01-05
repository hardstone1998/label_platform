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
    private String isMask;

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

    public String[] getSelectTags() {
        return selectTags;
    }

    public void setSelectTags(String[] selectTags) {
        this.selectTags = selectTags;
    }

    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAudioPath(String audioPath)
    {
        this.audioPath = audioPath;
    }

    public String getAudioPath()
    {
        return audioPath;
    }
    public void setAudioName(String audioName)
    {
        this.audioName = audioName;
    }

    public String getAudioName()
    {
        return audioName;
    }
    public void setPreText(String preText)
    {
        this.preText = preText;
    }

    public String getPreText()
    {
        return preText;
    }
    public void setAfterText(String afterText)
    {
        this.afterText = afterText;
    }

    public String getAfterText()
    {
        return afterText;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }

    public String getIsUse()
    {
        return isUse;
    }
    public void setTaskOwner(String taskOwner)
    {
        this.taskOwner = taskOwner;
    }

    public String getTaskOwner()
    {
        return taskOwner;
    }
    public void setIsMask(String isMask)
    {
        this.isMask = isMask;
    }

    public String getIsMask()
    {
        return isMask;
    }

    @Override
    public String toString() {
        return "VoiceAnnotation{" +
                "id=" + id +
                ", audioPath='" + audioPath + '\'' +
                ", audioName='" + audioName + '\'' +
                ", preText='" + preText + '\'' +
                ", afterText='" + afterText + '\'' +
                ", isUse='" + isUse + '\'' +
                ", taskOwner='" + taskOwner + '\'' +
                ", isMask='" + isMask + '\'' +
                ", dynamicTags=" + Arrays.toString(dynamicTags) +
                ", selectTags=" + Arrays.toString(selectTags) +
                '}';
    }
}
