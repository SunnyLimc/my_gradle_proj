package me.limc.demo.servlet;

import java.util.Date;

public class CalcLog {

    private static int sid = 0;

    private int logId;
    private String content;
    private Date calcTime;
    private String ipAddr;

    public CalcLog() {
        synchronized (CalcLog.class) {
            sid++;
            this.logId = sid;
        }
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(Date calcTime) {
        this.calcTime = calcTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @Override
    public String toString() {
        return "CalcLog [logId="
                + logId
                + ", content="
                + content
                + ", calcTime="
                + calcTime
                + ", ipAddr="
                + ipAddr
                + "]";
    }
}
