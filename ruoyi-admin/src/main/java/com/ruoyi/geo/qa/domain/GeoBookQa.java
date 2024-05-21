package com.ruoyi.geo.qa.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 geo_book_qa
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoBookQa extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 标题id */
    @Excel(name = "标题id")
    private Long bookClassId;

    /** 标题内容 */
    @Excel(name = "标题内容")
    private String titleContent;

    /** 问答对1 */
    @Excel(name = "问答对1")
    private String qa1;

    /** 问答对2 */
    @Excel(name = "问答对2")
    private String qa2;

    /** 问答对3 */
    @Excel(name = "问答对3")
    private String qa3;

    /** 审核后的qa1 */
    @Excel(name = "审核后的qa1")
    private String verityQa1;

    /** 审核后的qa2 */
    @Excel(name = "审核后的qa2")
    private String verityQa2;

    /** 审核后的qa3 */
    @Excel(name = "审核后的qa3")
    private String verityQa3;

    /** 数据更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;

    /** 是否已标注 */
    @Excel(name = "是否已标注")
    private String isMark;

    /** 任务拥有者 */
    @Excel(name = "任务拥有者")
    private String taskOwner;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer taskId;

    /** 类别id */
    @Excel(name = "类别id")
    private Long clazzId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String labelUser;

    /** 标注时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "标注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date labelTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String verityUser;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verityTime;

    /** 是否通过。1通过，0不通过 */
    @Excel(name = "是否通过。1通过，0不通过")
    private Integer isPass;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String verityFeedback;

    /** 正确率*100 */
    @Excel(name = "正确率*100")
    private Long accuracy;

    /** 审核后分类 */
    @Excel(name = "审核后分类")
    private Long verityClazzId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer insertBatchId;

    private String bookClazz;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBookClassId(Long bookClassId)
    {
        this.bookClassId = bookClassId;
    }

    public Long getBookClassId()
    {
        return bookClassId;
    }
    public void setTitleContent(String titleContent)
    {
        this.titleContent = titleContent;
    }

    public String getTitleContent()
    {
        return titleContent;
    }
    public void setQa1(String qa1)
    {
        this.qa1 = qa1;
    }

    public String getQa1()
    {
        return qa1;
    }
    public void setQa2(String qa2)
    {
        this.qa2 = qa2;
    }

    public String getQa2()
    {
        return qa2;
    }
    public void setQa3(String qa3)
    {
        this.qa3 = qa3;
    }

    public String getQa3()
    {
        return qa3;
    }
    public void setVerityQa1(String verityQa1)
    {
        this.verityQa1 = verityQa1;
    }

    public String getVerityQa1()
    {
        return verityQa1;
    }
    public void setVerityQa2(String verityQa2)
    {
        this.verityQa2 = verityQa2;
    }

    public String getVerityQa2()
    {
        return verityQa2;
    }
    public void setVerityQa3(String verityQa3)
    {
        this.verityQa3 = verityQa3;
    }

    public String getVerityQa3()
    {
        return verityQa3;
    }
    public void setUpdataTime(Date updataTime)
    {
        this.updateTime = updataTime;
    }

    public Date getUpdataTime()
    {
        return updateTime;
    }
    public void setIsDelete(String isDelete)
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete()
    {
        return isDelete;
    }
    public void setIsMark(String isMark)
    {
        this.isMark = isMark;
    }

    public String getIsMark()
    {
        return isMark;
    }
    public void setTaskOwner(String taskOwner)
    {
        this.taskOwner = taskOwner;
    }

    public String getTaskOwner()
    {
        return taskOwner;
    }
    public void setTaskId(Integer taskId)
    {
        this.taskId = taskId;
    }

    public Integer getTaskId()
    {
        return taskId;
    }
    public void setClazzId(Long clazzId)
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId()
    {
        return clazzId;
    }
    public void setLabelUser(String labelUser)
    {
        this.labelUser = labelUser;
    }

    public String getLabelUser()
    {
        return labelUser;
    }
    public void setLabelTime(Date labelTime)
    {
        this.labelTime = labelTime;
    }

    public Date getLabelTime()
    {
        return labelTime;
    }
    public void setVerityUser(String verityUser)
    {
        this.verityUser = verityUser;
    }

    public String getVerityUser()
    {
        return verityUser;
    }
    public void setVerityTime(Date verityTime)
    {
        this.verityTime = verityTime;
    }

    public Date getVerityTime()
    {
        return verityTime;
    }
    public void setIsPass(Integer isPass)
    {
        this.isPass = isPass;
    }

    public Integer getIsPass()
    {
        return isPass;
    }
    public void setVerityFeedback(String verityFeedback)
    {
        this.verityFeedback = verityFeedback;
    }

    public String getVerityFeedback()
    {
        return verityFeedback;
    }
    public void setAccuracy(Long accuracy)
    {
        this.accuracy = accuracy;
    }

    public Long getAccuracy()
    {
        return accuracy;
    }
    public void setVerityClazzId(Long verityClazzId)
    {
        this.verityClazzId = verityClazzId;
    }

    public Long getVerityClazzId()
    {
        return verityClazzId;
    }
    public void setInsertBatchId(Integer insertBatchId)
    {
        this.insertBatchId = insertBatchId;
    }

    public Integer getInsertBatchId()
    {
        return insertBatchId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookClassId", getBookClassId())
            .append("titleContent", getTitleContent())
            .append("qa1", getQa1())
            .append("qa2", getQa2())
            .append("qa3", getQa3())
            .append("verityQa1", getVerityQa1())
            .append("verityQa2", getVerityQa2())
            .append("verityQa3", getVerityQa3())
            .append("createTime", getCreateTime())
            .append("updataTime", getUpdataTime())
            .append("isDelete", getIsDelete())
            .append("isMark", getIsMark())
            .append("taskOwner", getTaskOwner())
            .append("taskId", getTaskId())
            .append("clazzId", getClazzId())
            .append("labelUser", getLabelUser())
            .append("labelTime", getLabelTime())
            .append("verityUser", getVerityUser())
            .append("verityTime", getVerityTime())
            .append("isPass", getIsPass())
            .append("verityFeedback", getVerityFeedback())
            .append("accuracy", getAccuracy())
            .append("verityClazzId", getVerityClazzId())
            .append("insertBatchId", getInsertBatchId())
            .toString();
    }
}
