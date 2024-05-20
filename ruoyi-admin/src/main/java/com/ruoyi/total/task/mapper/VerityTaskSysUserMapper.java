package com.ruoyi.total.task.mapper;

import com.ruoyi.total.task.domain.VerityTaskSysUser;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-01-15
 */
public interface VerityTaskSysUserMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public VerityTaskSysUser selectVerityTaskSysUserById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<VerityTaskSysUser> selectVerityTaskSysUserList(VerityTaskSysUser verityTaskSysUser);

    /**
     * 新增【请填写功能名称】
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 结果
     */
    public int insertVerityTaskSysUser(VerityTaskSysUser verityTaskSysUser);

    /**
     * 修改【请填写功能名称】
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 结果
     */
    public int updateVerityTaskSysUser(VerityTaskSysUser verityTaskSysUser);


    public int updateVerityTaskSysUserByTaskIdAndLabelUserId(VerityTaskSysUser verityTaskSysUser);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteVerityTaskSysUserById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVerityTaskSysUserByIds(Long[] ids);
}
