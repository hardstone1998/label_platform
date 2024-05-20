package com.ruoyi.total.total.mapper;

import com.ruoyi.total.total.domain.Answer;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-05
 */
public interface AnswerMapper
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
     * @return 类型集合
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAnswerById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerByIds(Long[] ids);
}
