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
        this.classPath = classPath.toArray(new Long[0]);
    }

    private JSONObject qaRelation;

    /** 标注任务所属人 */
    private String taskOwner;

    private Integer taskId;

    private Long clazzId;

    private Long labelUser;

    private Long verityUser;

    private Date labelTime;

    private Date verityTime;

    private Integer isPass;

    private String verityFeedback;

    private Long updateNum;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getQa1() {
        return qa1;
    }

    public void setQa1(String qa1) {
        this.qa1 = qa1;
    }

    public String getQa2() {
        return qa2;
    }

    public void setQa2(String qa2) {
        this.qa2 = qa2;
    }

    public String getQa3() {
        return qa3;
    }

    public void setQa3(String qa3) {
        this.qa3 = qa3;
    }

    public String getQa4() {
        return qa4;
    }

    public void setQa4(String qa4) {
        this.qa4 = qa4;
    }

    public String getQa5() {
        return qa5;
    }

    public void setQa5(String qa5) {
        this.qa5 = qa5;
    }



    public JSONObject getQaRelation() {
        return qaRelation;
    }

    public void setQaRelation(JSONObject qaRelation) {
        this.qaRelation = qaRelation;
    }

    public String getMarkresult() {
        return markresult;
    }

    public void setMarkresult(String markresult) {
        this.markresult = markresult;
    }



    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAudioPath(String audioPath)
    {
        this.audioPath = audioPath;
    }

    public String getAudioPath()
    {
        return audioPath;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setCuda(String cuda)
    {
        this.cuda = cuda;
    }

    public String getCuda()
    {
        return cuda;
    }
    public void setAudioTime(String audioTime)
    {
        this.audioTime = audioTime;
    }

    public String getAudioTime()
    {
        return audioTime;
    }
    public void setExecTime(String execTime)
    {
        this.execTime = execTime;
    }

    public String getExecTime()
    {
        return execTime;
    }
    public void setQaExtract(String qaExtract)
    {
        this.qaExtract = qaExtract;
    }

    public String getQaExtract()
    {
        return qaExtract;
    }
    public void setQaMark(String qaMark)
    {
        this.qaMark = qaMark;
    }

    public String getQaMark()
    {
        return qaMark;
    }
    public void setTaskOwner(String taskOwner)
    {
        this.taskOwner = taskOwner;
    }

    public String getTaskOwner()
    {
        return taskOwner;
    }

    @Override
    public String toString() {
        return "AsrResult1{" +
                "id=" + id +
                ", audioPath='" + audioPath + '\'' +
                ", result='" + result + '\'' +
                ", cuda='" + cuda + '\'' +
                ", audioTime='" + audioTime + '\'' +
                ", execTime='" + execTime + '\'' +
                ", qaExtract='" + qaExtract + '\'' +
                ", qa1='" + qa1 + '\'' +
                ", qa2='" + qa2 + '\'' +
                ", qa3='" + qa3 + '\'' +
                ", qa4='" + qa4 + '\'' +
                ", qa5='" + qa5 + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", qaSum='" + qaSum + '\'' +
                ", options=" + options +
                ", qaMark='" + qaMark + '\'' +
                ", markresult='" + markresult + '\'' +
                ", classp=" + classp +
                ", classPath=" + Arrays.toString(classPath) +
                ", qaRelation=" + qaRelation +
                ", taskOwner='" + taskOwner + '\'' +
                ", isMark='" + isMark + '\'' +
                '}';
    }
}
