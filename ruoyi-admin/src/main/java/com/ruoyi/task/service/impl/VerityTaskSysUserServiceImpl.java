package com.ruoyi.task.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.task.domain.VerityTaskSysUser;
import com.ruoyi.task.mapper.VerityTaskSysUserMapper;
import com.ruoyi.task.service.IVerityTaskSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class VerityTaskSysUserServiceImpl implements IVerityTaskSysUserService
{
    @Autowired
    private VerityTaskSysUserMapper verityTaskSysUserMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public VerityTaskSysUser selectVerityTaskSysUserById(Long id)
    {
        return verityTaskSysUserMapper.selectVerityTaskSysUserById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<VerityTaskSysUser> selectVerityTaskSysUserList(VerityTaskSysUser verityTaskSysUser)
    {
        return verityTaskSysUserMapper.selectVerityTaskSysUserList(verityTaskSysUser);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertVerityTaskSysUser(VerityTaskSysUser verityTaskSysUser)
    {
        verityTaskSysUser.setCreateTime(DateUtils.getNowDate());
        return verityTaskSysUserMapper.insertVerityTaskSysUser(verityTaskSysUser);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param verityTaskSysUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateVerityTaskSysUser(VerityTaskSysUser verityTaskSysUser)
    {
        verityTaskSysUser.setUpdateTime(DateUtils.getNowDate());
        return verityTaskSysUserMapper.updateVerityTaskSysUser(verityTaskSysUser);
    }

    @Override
    public int updateVerityTaskSysUserByTaskIdAndLabelUserId(VerityTaskSysUser verityTaskSysUser) {
        verityTaskSysUser.setUpdateTime(DateUtils.getNowDate());
        return verityTaskSysUserMapper.updateVerityTaskSysUserByTaskIdAndLabelUserId(verityTaskSysUser);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteVerityTaskSysUserByIds(Long[] ids)
    {
        return verityTaskSysUserMapper.deleteVerityTaskSysUserByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteVerityTaskSysUserById(Long id)
    {
        return verityTaskSysUserMapper.deleteVerityTaskSysUserById(id);
    }
}
