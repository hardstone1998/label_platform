package com.ruoyi.total.task.service;

import com.ruoyi.total.task.domain.Unallocation;

import java.util.List;

/**
 * VIEWService接口
 *
 * @author ruoyi
 * @date 2024-01-16
 */
public interface IUnallocationService
{
    /**
     * 查询VIEW
     *
     * @param clazzId VIEW主键
     * @return VIEW
     */
    public Unallocation selectUnallocationByClazzId(Long clazzId);

    /**
     * 查询VIEW列表
     *
     * @param unallocation VIEW
     * @return VIEW集合
     */
    public List<Unallocation> selectUnallocationList(Unallocation unallocation);

    /**
     * 新增VIEW
     *
     * @param unallocation VIEW
     * @return 结果
     */
    public int insertUnallocation(Unallocation unallocation);

    /**
     * 修改VIEW
     *
     * @param unallocation VIEW
     * @return 结果
     */
    public int updateUnallocation(Unallocation unallocation);

    /**
     * 批量删除VIEW
     *
     * @param clazzIds 需要删除的VIEW主键集合
     * @return 结果
     */
    public int deleteUnallocationByClazzIds(Long[] clazzIds);

    /**
     * 删除VIEW信息
     *
     * @param clazzId VIEW主键
     * @return 结果
     */
    public int deleteUnallocationByClazzId(Long clazzId);
}
