package com.ruoyi.asr.domain;


import com.ruoyi.common.core.domain.BaseEntity;

public class CountData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //ASR总数据
    private Long AsrCountSum;
    //ASR已标注数据
    private Long AsrMarkedSum;
    //ASR未标注数据
    private Long AsrNotMarkedSum;
    //ASR已分配数据
    private Long AsrAllocationSum;
    //ASR未分配数据
    private Long AsrNotAllocationSum;


    //QA总数据
    private Long QACountSum;
    //QA已标注数据
    private Long QAMarkedSum;
    //QA未标注数据
    private Long QANotMarkedSum;
    //QA已分配数据
    private Long QAllocationSum;

    public Long getAsrNotAllocationSum() {
        return AsrNotAllocationSum;
    }

    public void setAsrNotAllocationSum(Long asrNotAllocationSum) {
        AsrNotAllocationSum = asrNotAllocationSum;
    }

    public Long getQANotAllocationSum() {
        return QANotAllocationSum;
    }

    public void setQANotAllocationSum(Long QANotAllocationSum) {
        this.QANotAllocationSum = QANotAllocationSum;
    }

    //QA未分配数据
    private Long QANotAllocationSum;

    public Long getAsrAllocationSum() {
        return AsrAllocationSum;
    }

    public void setAsrAllocationSum(Long asrAllocationSum) {
        AsrAllocationSum = asrAllocationSum;
    }

    public Long getQAllocationSum() {
        return QAllocationSum;
    }

    public void setQAllocationSum(Long QAllocationSum) {
        this.QAllocationSum = QAllocationSum;
    }



    public Long getAsrCountSum() {
        return AsrCountSum;
    }

    public void setAsrCountSum(Long asrCountSum) {
        AsrCountSum = asrCountSum;
    }

    public Long getAsrMarkedSum() {
        return AsrMarkedSum;
    }

    public void setAsrMarkedSum(Long asrMarkedSum) {
        AsrMarkedSum = asrMarkedSum;
    }

    public Long getAsrNotMarkedSum() {
        return AsrNotMarkedSum;
    }

    public void setAsrNotMarkedSum(Long asrNotMarkedSum) {
        AsrNotMarkedSum = asrNotMarkedSum;
    }

    public Long getQACountSum() {
        return QACountSum;
    }

    public void setQACountSum(Long QACountSum) {
        this.QACountSum = QACountSum;
    }

    public Long getQAMarkedSum() {
        return QAMarkedSum;
    }

    public void setQAMarkedSum(Long QAMarkedSum) {
        this.QAMarkedSum = QAMarkedSum;
    }

    public Long getQANotMarkedSum() {
        return QANotMarkedSum;
    }

    public void setQANotMarkedSum(Long QANotMarkedSum) {
        this.QANotMarkedSum = QANotMarkedSum;
    }





}
