package com.ruoyi.task.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

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

    /** 标注用户 */
    private Long labelUserId;


    /** 发起用户 */
    @Excel(name = "发起用户")
    private String reqUser;

    /** 审核用户 */
    @Excel(name = "审核用户")
    private Long verityUserId;

    /** 审核数量 */
    @Excel(name = "审核用户名")
    private Long verityNum;

    /** 审核百分比 */
    @Excel(name = "审核百分比")
    private Double verityPercentage;
}
