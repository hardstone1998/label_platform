package com.ruoyi.task.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 【请填写功能名称】对象 verity_task_sys_user
 *
 * @author ruoyi
 * @date 2024-01-15
 */
@Data
public class VerityTaskAllocationReq extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private Long id;

    /** 任务id */
    private Long taskId;

    /** 任务类型 */
    private Integer taskClazz;

    /** 标注用户 */
    @NotBlank
    private Long labelUserId;


    /** 发起用户 */
    @Excel(name = "发起用户")
    private String reqUser;

    /** 审核用户 */
    @Excel(name = "审核用户")
    @NotBlank
    private Long verityUserId;

    /** 审核数量 */
    @Excel(name = "审核数量")
    @NotBlank
    private Long verityNum;

    /** 审核百分比 */
    @Excel(name = "审核百分比")
    private Double verityPercentage;
}
