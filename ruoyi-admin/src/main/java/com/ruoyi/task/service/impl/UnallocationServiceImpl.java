package com.ruoyi.task.service.impl;

import java.util.List;

import com.ruoyi.task.domain.Unallocation;
import com.ruoyi.task.mapper.UnallocationMapper;
import com.ruoyi.task.service.IUnallocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * VIEWService业务层处理
 *
 * @author ruoyi
 * @date 2024-01-16
 */
@Service
public class UnallocationServiceImpl implements IUnallocationService
{
    @Autowired
    private UnallocationMapper unallocationMapper;

    /**
     * 查询VIEW
     *
     * @param clazzId VIEW主键
     * @return VIEW
     */
    @Override
    public Unallocation selectUnallocationByClazzId(Long clazzId)
    {
        return unallocationMapper.selectUnallocationByClazzId(clazzId);
    }

    /**
     * 查询VIEW列表
     *
     * @param unallocation VIEW
     * @return VIEW
     */
    @Override
    public List<Unallocation> selectUnallocationList(Unallocation unallocation)
    {
        return unallocationMapper.selectUnallocationList(unallocation);
    }

    /**
     * 新增VIEW
     *
     * @param unallocation VIEW
     * @return 结果
     */
    @Override
    public int insertUnallocation(Unallocation unallocation)
    {
        return unallocationMapper.insertUnallocation(unallocation);
    }

    /**
     * 修改VIEW
     *
     * @param unallocation VIEW
     * @return 结果
     */
    @Override
    public int updateUnallocation(Unallocation unallocation)
    {
        return unallocationMapper.updateUnallocation(unallocation);
    }

    /**
     * 批量删除VIEW
     *
     * @param clazzIds 需要删除的VIEW主键
     * @return 结果
     */
    @Override
    public int deleteUnallocationByClazzIds(Long[] clazzIds)
    {
        return unallocationMapper.deleteUnallocationByClazzIds(clazzIds);
    }

    /**
     * 删除VIEW信息
     *
     * @param clazzId VIEW主键
     * @return 结果
     */
    @Override
    public int deleteUnallocationByClazzId(Long clazzId)
    {
        return unallocationMapper.deleteUnallocationByClazzId(clazzId);
    }
}
