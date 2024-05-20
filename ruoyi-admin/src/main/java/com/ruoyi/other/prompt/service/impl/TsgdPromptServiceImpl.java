package com.ruoyi.other.prompt.service.impl;

import java.util.List;

import com.ruoyi.other.prompt.mapper.TsgdPromptMapper;
import com.ruoyi.other.prompt.service.ITsgdPromptService;
import com.ruoyi.other.prompt.domain.TsgdPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-29
 */
@Service
public class TsgdPromptServiceImpl implements ITsgdPromptService
{
    @Autowired
    private TsgdPromptMapper tsgdPromptMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TsgdPrompt selectTsgdPromptById(Integer id)
    {
        return tsgdPromptMapper.selectTsgdPromptById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TsgdPrompt> selectTsgdPromptList(TsgdPrompt tsgdPrompt)
    {
        return tsgdPromptMapper.selectTsgdPromptList(tsgdPrompt);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTsgdPrompt(TsgdPrompt tsgdPrompt)
    {
        return tsgdPromptMapper.insertTsgdPrompt(tsgdPrompt);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tsgdPrompt 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTsgdPrompt(TsgdPrompt tsgdPrompt)
    {
        return tsgdPromptMapper.updateTsgdPrompt(tsgdPrompt);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTsgdPromptByIds(Integer[] ids)
    {
        return tsgdPromptMapper.deleteTsgdPromptByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTsgdPromptById(Integer id)
    {
        return tsgdPromptMapper.deleteTsgdPromptById(id);
    }
}
