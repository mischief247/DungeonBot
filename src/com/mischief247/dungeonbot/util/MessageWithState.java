package com.mischief247.dungeonbot.util;

public class MessageWithState {
    private String msg;
    private boolean isError;
    public MessageWithState(String msg, boolean isError){
        this.msg=   msg;
        this.isError = isError;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isError() {
        return isError;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
