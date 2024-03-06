package com.ruoyi.total.service;

import com.ruoyi.total.domain.Answer;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-03-05
 */
public interface IAnswerService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Answer selectAnswerById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param answer 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Answer> selectAnswerList(Answer answer);

    /**
     * 查询全部类型
     *
     * @return type集合
     */
    public List<String> selectTypeAll();

    /**
     * 新增【请填写功能名称】
     *
     * @param answer 【请填写功能名称】
     * @return 结果
     */
    public int insertAnswer(Answer answer);

    /**
     * 修改【请填写功能名称】
     *
     * @param answer 【请填写功能名称】
     * @return 结果
     */
    public int updateAnswer(Answer answer);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAnswerByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAnswerById(Long id);
}
