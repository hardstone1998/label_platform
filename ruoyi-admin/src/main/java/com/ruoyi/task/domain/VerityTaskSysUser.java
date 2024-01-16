package com.ruoyi.task.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 verity_task_sys_user
 *
 * @author ruoyi
 * @date 2024-01-15
 */
@Data
public class VerityTaskSysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 抽取数量 */
    @Excel(name = "抽取数量")
    private Long extractNum;


    /** 标注用户 */
    @Excel(name = "标注用户")
    private Long labelUserId;

    /** 审核用户 */
    @Excel(name = "审核用户")
    private Long verityUserId;

    /** 审核用户名 */
    @Excel(name = "审核用户名")
    private Long verityUserName;

    /** 请求用户名 */
    @Excel(name = "请求用户名")
    private Long reqUser;

}
