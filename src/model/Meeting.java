package model;

import java.util.Date;

public class Meeting {

    private int meetingId;
    private String meetingTitle;
    private Date meetingDate;
    private String meetingAgenda;

    public Meeting(int meetingId, String meetingTitle, Date meetingDate, String meetingAgenda) {
        this.meetingId = meetingId;
        this.meetingTitle = meetingTitle;
        this.meetingDate = meetingDate;
        this.meetingAgenda = meetingAgenda;
    }

    public Meeting(String meetingTitle, Date meetingDate, String meetingAgenda) {
        this.meetingTitle = meetingTitle;
        this.meetingDate = meetingDate;
        this.meetingAgenda = meetingAgenda;
    }


    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public Date  getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingAgenda() {
        return meetingAgenda;
    }

    public void setMeetingAgenda(String meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }
}
