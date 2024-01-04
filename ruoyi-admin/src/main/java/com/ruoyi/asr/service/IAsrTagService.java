package com.ruoyi.asr.service;

import java.util.List;
import com.ruoyi.asr.domain.AsrTag;

/**
 * tagsService接口
 *
 * @author ruoyi
 * @date 2023-06-29
 */
public interface IAsrTagService
{
    /**
     * 查询tags
     *
     * @param id tags主键
     * @return tags
     */
    public AsrTag selectAsrTagById(Long id);

    /**
     * 查询tags列表
     *
     * @param asrTag tags
     * @return tags集合
     */
    public List<AsrTag> selectAsrTagList(AsrTag asrTag);

    /**
     * 新增tags
     *
     * @param asrTag tags
     * @return 结果
     */
    public int insertAsrTag(AsrTag asrTag);

    /**
     * 修改tags
     *
     * @param asrTag tags
     * @return 结果
     */
    public int updateAsrTag(AsrTag asrTag);

    /**
     * 批量删除tags
     *
     * @param ids 需要删除的tags主键集合
     * @return 结果
     */
    public int deleteAsrTagByIds(Long[] ids);

    /**
     * 删除tags信息
     *
     * @param id tags主键
     * @return 结果
     */
    public int deleteAsrTagById(Long id);
}
