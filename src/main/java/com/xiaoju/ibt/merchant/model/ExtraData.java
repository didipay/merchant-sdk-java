package com.xiaoju.ibt.merchant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-17
 */
public class ExtraData {

    @JsonProperty("terminal_id")
    private int terminalId;

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        return "ExtraData{" +
                "terminalId=" + terminalId +
                '}';
    }
}
