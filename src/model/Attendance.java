package model;

import java.util.Date;

public class Attendance {

    private int attendanceId;
    private Date attendanceDate;
    private String userName;

    public Attendance(int attendanceId, Date attendanceDate, String userName) {
        this.attendanceId = attendanceId;
        this.attendanceDate = attendanceDate;
        this.userName = userName;
    }

    public Attendance(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Attendance(Date attendanceDate, String userName) {
        this.attendanceDate = attendanceDate;
        this.userName = userName;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
