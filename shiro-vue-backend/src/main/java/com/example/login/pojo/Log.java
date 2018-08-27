package com.example.login.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SYS_LOG")
public class Log {
    @Id
    @Column(name = "LOG_ID")
    private String logId;

    @Column(name = "LOG_USER")
    private String logUser;

    @Column(name = "LOG_TIME")
    private LocalDateTime logTime;

    @Column(name = "LOG_IP")
    private String logIp;

    @Column(name = "LOG_ACTION")
    private String logAction;

    /**
     * @return LOG_ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * @return LOG_USER
     */
    public String getLogUser() {
        return logUser;
    }

    /**
     * @param logUser
     */
    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    /**
     * @return LOG_TIME
     */
    public LocalDateTime getLogTime() {
        return logTime;
    }

    /**
     * @param logTime
     */
    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    /**
     * @return LOG_IP
     */
    public String getLogIp() {
        return logIp;
    }

    /**
     * @param logIp
     */
    public void setLogIp(String logIp) {
        this.logIp = logIp == null ? null : logIp.trim();
    }

    /**
     * @return LOG_ACTION
     */
    public String getLogAction() {
        return logAction;
    }

    /**
     * @param logAction
     */
    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }
}