package com.ruoyi.qa.domain;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.ArrayList;
import java.util.List;
@JSONType(orders = {"instruction","input","output","history"})
public class ExportResJson {

    private String instruction;
    private String input;
    private String output;
    private List<List<String>> history;

    public List<List<String>> getHistory() {
        return history;
    }

    public void setHistory(List<List<String>> history) {
        this.history = history;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    @Override
    public String toString() {
        return "ExportResJson{" +
                "instruction='" + instruction + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", history=" + history +
                '}';
    }
}
