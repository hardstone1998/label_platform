package com.ruoyi.gugong.qa.mapper;

import com.ruoyi.gugong.qa.domain.Class1;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface Class1Mapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Class1 selectClass1ById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param class1 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Class1> selectClass1List(Class1 class1);

    /**
     * 新增【请填写功能名称】
     *
     * @param class1 【请填写功能名称】
     * @return 结果
     */
    public int insertClass1(Class1 class1);

    /**
     * 修改【请填写功能名称】
     *
     * @param class1 【请填写功能名称】
     * @return 结果
     */
    public int updateClass1(Class1 class1);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteClass1ById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClass1ByIds(Long[] ids);

    Long[] findClassPath(String cuda);
}
