package com.ruoyi.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 unallocation
 *
 * @author ruoyi
 * @date 2024-01-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unallocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别id */
    @Excel(name = "类别id")
    private Long clazzId;

    /** 类别名 */
    @Excel(name = "类别名")
    private String label;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private Long num;

    /** asr或Qa */
    @Excel(name = "asr或Qa")
    private String asrOrQa;
}
