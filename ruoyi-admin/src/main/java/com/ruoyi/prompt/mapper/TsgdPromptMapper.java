package com.ruoyi.prompt.mapper;

import com.ruoyi.prompt.domain.TsgdPrompt;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-29
 */
public interface TsgdPromptMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TsgdPrompt selectTsgdPromptById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TsgdPrompt> selectTsgdPromptList(TsgdPrompt tsgdPrompt);

    /**
     * 新增【请填写功能名称】
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 结果
     */
    public int insertTsgdPrompt(TsgdPrompt tsgdPrompt);

    /**
     * 修改【请填写功能名称】
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 结果
     */
    public int updateTsgdPrompt(TsgdPrompt tsgdPrompt);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTsgdPromptById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTsgdPromptByIds(Integer[] ids);
}
