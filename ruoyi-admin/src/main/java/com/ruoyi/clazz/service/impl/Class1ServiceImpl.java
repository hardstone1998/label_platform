package com.ruoyi.clazz.service.impl;

import java.util.List;

import com.ruoyi.clazz.service.IClass1Service;
import com.ruoyi.qa.domain.Class1;
import com.ruoyi.qa.mapper.Class1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-15
 */
@Service
public class Class1ServiceImpl implements IClass1Service
{
    @Autowired
    private Class1Mapper class1Mapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Class1 selectClass1ById(Long id)
    {
        return class1Mapper.selectClass1ById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param class1 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Class1> selectClass1List(Class1 class1)
    {
        return class1Mapper.selectClass1List(class1);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param class1 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClass1(Class1 class1)
    {
        return class1Mapper.insertClass1(class1);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param class1 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClass1(Class1 class1)
    {
        return class1Mapper.updateClass1(class1);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClass1ByIds(Long[] ids)
    {
        return class1Mapper.deleteClass1ByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClass1ById(Long id)
    {
        return class1Mapper.deleteClass1ById(id);
    }
}
