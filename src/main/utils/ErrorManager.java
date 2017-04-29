package main.utils;

/**
 * Created by admin on 29.04.2017.
 */
public class ErrorManager {
    private String msg;

    public ErrorManager(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
