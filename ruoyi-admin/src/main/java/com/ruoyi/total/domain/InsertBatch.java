package com.ruoyi.total.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 insert_batch
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Data
public class InsertBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 插入批次id */
    private Long id;

    /** 插入名称 */
    @Excel(name = "插入名称")
    private String name;

    /** 项目名 */
    @Excel(name = "项目名")
    private String projectName;

    /** 任务类型，0-asr，关联voice_annotation，1-qa管理asr_result_1 */
    @Excel(name = "任务类型，0-asr，关联voice_annotation，1-qa管理asr_result_1")
    private Long taskType;

    private String folder;
}
