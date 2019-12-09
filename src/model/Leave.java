package model;

import java.util.Date;

public class Leave {

    private int leaveId;
    private Date leaveDate;
    private String leaveReason;
    private String leaveStatus;
    private String userName;

    public Leave(int leaveId, Date leaveDate, String leaveReason, String leaveStatus, String userName) {
        this.leaveId = leaveId;
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
        this.leaveStatus = leaveStatus;
        this.userName = userName;
    }

    public Leave(Date leaveDate, String leaveReason, String leaveStatus) {
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
        this.leaveStatus = leaveStatus;
    }

    public Leave(Date leaveDate, String leaveReason) {
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
    }

    public Leave(Date leaveDate, String leaveReason, String leaveStatus, String userName) {
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
        this.leaveStatus = leaveStatus;
        this.userName = userName;
    }



    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
