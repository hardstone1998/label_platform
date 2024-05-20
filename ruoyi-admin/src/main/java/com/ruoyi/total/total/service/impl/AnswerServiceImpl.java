package com.ruoyi.total.total.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.total.total.domain.Answer;
import com.ruoyi.total.total.mapper.AnswerMapper;
import com.ruoyi.total.total.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-05
 */
@Service
public class AnswerServiceImpl implements IAnswerService
{
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Answer selectAnswerById(Long id)
    {
        return answerMapper.selectAnswerById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param answer 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Answer> selectAnswerList(Answer answer)
    {
        return answerMapper.selectAnswerList(answer);
    }

    /**
     * 查询全部类型
     *
     * @return type集合
     */
    @Override
    public List<String> selectTypeAll() {
        return answerMapper.selectTypeAll();
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param answer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnswer(Answer answer)
    {
        answer.setCreateTime(DateUtils.getNowDate());
        return answerMapper.insertAnswer(answer);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param answer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnswer(Answer answer)
    {
        answer.setUpdateTime(DateUtils.getNowDate());
        return answerMapper.updateAnswer(answer);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAnswerByIds(Long[] ids)
    {
        return answerMapper.deleteAnswerByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAnswerById(Long id)
    {
        return answerMapper.deleteAnswerById(id);
    }
}
