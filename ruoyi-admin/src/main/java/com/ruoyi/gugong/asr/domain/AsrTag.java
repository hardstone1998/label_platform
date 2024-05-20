package com.ruoyi.gugong.asr.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 asr_tag
 *
 * @author ruoyi
 * @date 2023-06-29
 */
public class AsrTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;



    /** 标签名字 */
    @Excel(name = "标签名字")
    private String tagName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

    /** 添加人 */
    @Excel(name = "添加人")
    private String createUser;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public String getTagName()
    {
        return tagName;
    }
    public void setIsDeleted(String isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted()
    {
        return isDeleted;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tagName", getTagName())
                .append("isDeleted", getIsDeleted())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .toString();
    }
}
