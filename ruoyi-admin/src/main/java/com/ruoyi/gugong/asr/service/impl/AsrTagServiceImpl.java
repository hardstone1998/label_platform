package com.ruoyi.gugong.asr.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.asr.domain.AsrTag;
import com.ruoyi.gugong.asr.mapper.AsrTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gugong.asr.service.IAsrTagService;

/**
 * tagsService业务层处理
 *
 * @author ruoyi
 * @date 2023-06-29
 */
@Service
public class AsrTagServiceImpl implements IAsrTagService
{
    @Autowired
    private AsrTagMapper asrTagMapper;

    /**
     * 查询tags
     *
     * @param id tags主键
     * @return tags
     */
    @Override
    public AsrTag selectAsrTagById(Long id)
    {
        return asrTagMapper.selectAsrTagById(id);
    }

    /**
     * 查询tags列表
     *
     * @param asrTag tags
     * @return tags
     */
    @Override
    public List<AsrTag> selectAsrTagList(AsrTag asrTag)
    {
        return asrTagMapper.selectAsrTagList(asrTag);
    }

    /**
     * 新增tags
     *
     * @param asrTag tags
     * @return 结果
     */
    @Override
    public int insertAsrTag(AsrTag asrTag)
    {
        asrTag.setCreateTime(DateUtils.getNowDate());
        try{
            asrTag.setIsDeleted("否");
            return asrTagMapper.insertAsrTag(asrTag);
        }catch (Exception e){
            System.out.println("该标签已经存在");
            return 0;
        }
    }

    /**
     * 修改tags
     *
     * @param asrTag tags
     * @return 结果
     */
    @Override
    public int updateAsrTag(AsrTag asrTag)
    {
        return asrTagMapper.updateAsrTag(asrTag);
    }

    /**
     * 批量删除tags
     *
     * @param ids 需要删除的tags主键
     * @return 结果
     */
    @Override
    public int deleteAsrTagByIds(Long[] ids)
    {
        return asrTagMapper.deleteAsrTagByIds(ids);
    }

    /**
     * 删除tags信息
     *
     * @param id tags主键
     * @return 结果
     */
    @Override
    public int deleteAsrTagById(Long id)
    {
        return asrTagMapper.deleteAsrTagById(id);
    }
}
