package com.ruoyi.asr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * relation对象 asr_tag_relation
 * 
 * @author ruoyi
 * @date 2023-06-29
 */
public class AsrTagRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联表主键 */
    private Long id;

    /** 标注id */
    @Excel(name = "标注id")
    private Long asrId;

    /** tag_id */
    @Excel(name = "tag_id")
    private Long tagId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAsrId(Long asrId) 
    {
        this.asrId = asrId;
    }

    public Long getAsrId() 
    {
        return asrId;
    }
    public void setTagId(Long tagId) 
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("asrId", getAsrId())
            .append("tagId", getTagId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
