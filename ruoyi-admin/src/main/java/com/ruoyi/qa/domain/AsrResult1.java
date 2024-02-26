package com.ruoyi.qa.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * extract对象 asr_result_1
 *
 * @author ruoyi
 * @date 2023-07-13
 */
@Data
public class AsrResult1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 音频路径 */
    @Excel(name = "音频路径")
    private String audioPath;

    /** 执行结果 */
    @Excel(name = "执行结果")
    private String result;

    /** 显卡id */
    private String cuda;

    /** 音频长度，单位：s */
    private String audioTime;

    /** 执行时间，单位：s */
    private String execTime;

    /** 6B提取后的qa信息 */
    @Excel(name = "6B提取后的qa信息")
    private String qaExtract;

    private String qa1;
    private String qa2;
    private String qa3;
    private String qa4;
    private String qa5;

    private String qa6;
    private String isDelete;
    private String qaSum;

    public String getVerityText() {
        String verityText = "";
        if (qa4!=null&&qa4!="")verityText+=qa4;
        if (qa5!=null&&qa5!="")verityText+=qa5;
        if (qa6!=null&&qa6!="")verityText+=qa6;
        return verityText;
    }

    private List<Class1> options;

    public List<Class1> getOptions() {
        return options;
    }

    public void setOptions(List<Class1> options) {
        this.options = options;
    }

    /** 对6B提取后的qa信息进行标注 */
    @Excel(name = "对6B提取后的qa信息进行标注")
    private String qaMark;

    private String markresult;

    private List<ClassP> classp;

    private Long[] classPath;


    public Long[] getClassPath() {
        return classPath;
    }

    public void setClassPath(List<Long> classPath) {
        if (classPath==null){
            this.classPath = null;
        }else {
            this.classPath = classPath.toArray(new Long[0]);
        }

    }

//    private JSONObject qaRelation;

    /** 标注任务所属人 */
    private String taskOwner;

    private Long taskId;

    private Long clazzId;

    private Long labelUser;

    private Long verityUser;

    private Date labelTime;

    private Date verityTime;

    private Integer isPass;

    private String verityFeedback;

    private Long updateNum;

    private Long accuracy;

    private Long verityClazzId;

    private String reqUser;
    public String getQaSum() {
        return qaSum;
    }

    public void setQaSum(String qaSum) {
        this.qaSum = qaSum;
    }

    public String getIsMark() {
        return isMark;
    }

    public void setIsMark(String isMark) {
        this.isMark = isMark;
    }

    private String isMark;

    private String labelUserName;

    private Integer insertBatchId;

}
