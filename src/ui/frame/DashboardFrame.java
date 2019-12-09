package ui.frame;

import _interface.AddListener;
import _interface.ItemClickListener;
import database.DatabaseHandler;
import model.Attendance;
import model.Leave;
import model.Meeting;
import model.User;
import ui.panel.*;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashboardFrame extends JFrame implements ItemClickListener, AddListener {

    private BasePanel meetingPanel;
    private BasePanel leavePanel;
    private BasePanel userPanel;
    private ProfilePanel profilePanel;
    private BasePanel attendancePanel;

    private JFrame addMeetingFrame;
    private JFrame addLeaveFrame;

    private JPanel jPanel;
    private CardLayout cardLayout;

    public DashboardFrame() {

        setTitle("Dashboard");
        setLayout(new FlowLayout());
        setSize(new Dimension(450, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        meetingPanel = new MeetingPanel(getMeetingList(), this);
        leavePanel = new LeavePanel(getLeaveList(), this);
        userPanel = new UserPanel(getUserList(), this);
        attendancePanel = new AttendancePanel(getAttendanceList(), this);
        profilePanel = new ProfilePanel();

        add(new TopbarPanel(this));
        jPanel = new JPanel();
        cardLayout = new CardLayout();
        jPanel.setLayout(cardLayout);

        if (User.getInstance().getStatus().equals("admin")) {
            jPanel.add(userPanel, "userPanel");
            jPanel.add(attendancePanel, "attendancePanel");
        } else {
            jPanel.add(profilePanel, "profilePanel");
        }
        jPanel.add(leavePanel, "leavePanel");
        jPanel.add(meetingPanel, "meetingPanel");
        add(jPanel);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        if (!User.getInstance().getStatus().equals("admin")) {
            if (isAttendanceMarked(new Attendance(Utils.getCurrentDate()))) {

                Utils.showDialog(this, "Attendance", "Your Attendance for today is Marked", JOptionPane.INFORMATION_MESSAGE);
                Utils.printError("Your Attendance for today is Marked");
            } else {
                Utils.printError("Your Attendance for today is already Marked");
                Utils.showDialog(this, "Attendance", "Your Attendance for today is already Marked", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void onItemClicked(Object object, int position) {
        switch (object.getClass().getSimpleName()) {
            case "Meeting":
                Meeting meeting = (Meeting) object;
                Utils.printError(meeting.getMeetingTitle());

                break;
            case "User":
                User user = (User) object;
                showUserDialog(user, position);
                break;
            case "Leave":
                Leave leave = (Leave) object;
                String message = "Do You want to approve this leave request";
                if (User.getInstance().getStatus().equals("admin")) {
                    showLeaveDialog(leave, position, message);
                }
                break;

        }

    }

    private void showUserDialog(User user, int position) {
        String message;
        String newStatus;
        switch (user.getStatus()) {
            case "registered":
                message = "Do You want to suspend this account";
                newStatus = "unregistered";
                break;
            case "unregistered":
                message = "Do You want to cancel suspension of this account";
                newStatus = "registered";
                break;
            case "pending":
                message = "Do You want to approve this account";
                newStatus = "registered";
                break;
            default:
                message = "invalid case";
                newStatus = "invalid case";
                break;

        }
        switch (JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION)) {
            case 0:
                Utils.printInfo("YES");
                user.setStatus(newStatus);

                if (isUserStatusUpdated(user)) {
                    userPanel.updateModel(user, position);
                }

                break;
            default:
                Utils.printError("No");
                break;
        }
    }


    private void showLeaveDialog(Leave leave, int index, String message) {
        switch (JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION)) {
            case 0:
                Utils.printInfo("YES");
                leave.setLeaveStatus("approved");

                if (isLeaveStatusUpdated(leave)) {
                    leavePanel.updateModel(leave, index);
                }
                break;
            default:
                Utils.printError("No");
                break;
        }

    }

    @Override
    public void onButtonClicked(String buttonId) {
        switch (buttonId) {
            case "jButton_profile":
                Utils.printError("Profile Panel");
                cardLayout.show(jPanel, "profilePanel");
                break;
            case "jButton_user":
                cardLayout.show(jPanel, "userPanel");
//                userPanel.setVisible(true);
                Utils.printError("User Panel");
                break;
            case "jButton_leave":
                cardLayout.show(jPanel, "leavePanel");
//                leavePanel.setVisible(true);
                Utils.printError("Leave Panel");
                break;
            case "jButton_meeting":
                cardLayout.show(jPanel, "meetingPanel");
//                meetingPanel.setVisible(true);
                Utils.printError("Meeting Panel");
                break;
            case "jButton_attendance":
                cardLayout.show(jPanel, "attendancePanel");
                Utils.printError("Attendance Panel");
                break;
            case "jButton_signOut":
                Utils.printError("SignOut");
                dispose();
                new SignInFrame();
                break;
            case "jButton_addMeeting":
                Utils.printError("Add Meeting");
                addMeetingFrame = Utils.getFrame("Add Meeting", 250, 300, JFrame.DISPOSE_ON_CLOSE);
                addMeetingFrame.add(new AddMeetingPanel(addMeetingFrame, this));
                addMeetingFrame.setVisible(true);
                break;
            case "jButton_addLeave":
                Utils.printError("Add Leave");

                addLeaveFrame = Utils.getFrame("Add Leave", 250, 300, JFrame.DISPOSE_ON_CLOSE);
                addLeaveFrame.add(new AddLeavePanel(addLeaveFrame, this));
                addLeaveFrame.setVisible(true);
                break;
        }
    }

    @Override
    public void onAdd(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Meeting":
                Meeting meeting = (Meeting) object;
                Utils.printError("Meeting Panel Yes");
                if (isMeetingAdded(meeting)) {
                    addMeetingFrame.dispose();
                    meetingPanel.addModel(meeting);
                }
                break;

            case "Leave":
                Utils.printError("Leave Panel Yes");
                Leave leave = (Leave) object;

                if (isLeaveAdded(leave)) {
                    leave.setLeaveStatus("pending");
                    leave.setUserName(User.getInstance().getFirstName()+" "+User.getInstance().getLastName());
                    addLeaveFrame.dispose();
                    leavePanel.addModel(leave);
                }
                break;
        }

    }

    @Override
    public void onCancel(JFrame jFrame) {
        jFrame.dispose();
    }

    /*DatabaseOperation*/
    private List<Meeting> getMeetingList() {
        List<Meeting> meetingList = new ArrayList<>();
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT * FROM meeting";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int meetingId = resultSet.getInt(1);
                String meetingTitle = resultSet.getString(2);
                Date meetingDate = resultSet.getDate(3);
                String meetingAgenda = resultSet.getString(4);
                meetingList.add(new Meeting(meetingId, meetingTitle, meetingDate, meetingAgenda));
            }
        } catch (
                SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return meetingList;
    }

    private List<Leave> getLeaveList() {
        List<Leave> leaveList = new ArrayList<>();
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT   b.*, a.first_name, a.last_name FROM users a, leave_table b ";
        if (!User.getInstance().getStatus().equals("admin")) {
            sqlQuery = sqlQuery + "WHERE b.user_id = " + User.getInstance().getUserId() + " AND a.user_id = " + User.getInstance().getUserId();
        } else {
            sqlQuery = sqlQuery + "WHERE b.user_id = a.user_id";
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int leaveId = resultSet.getInt(1);
                Date leaveDate = resultSet.getDate(2);
                String leaveReason = resultSet.getString(3);
                String leaveStatus = resultSet.getString(5);
                String firstName = resultSet.getString(6);
                String lastName = resultSet.getString(7);
                leaveList.add(new Leave(leaveId, leaveDate, leaveReason, leaveStatus, firstName + " " + lastName));
            }
        } catch (
                SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return leaveList;
    }

    private List<Attendance> getAttendanceList() {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT   b.*, a.first_name, a.last_name FROM users a, attendance b ";
        if (!User.getInstance().getStatus().equals("admin")) {
            sqlQuery = sqlQuery + "WHERE b.user_id = " + User.getInstance().getUserId() + " AND a.user_id = " + User.getInstance().getUserId();
        } else {
            sqlQuery = sqlQuery + "WHERE b.user_id = a.user_id";
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int attendanceId = resultSet.getInt(1);
                Date attendanceDate = resultSet.getDate(2);
                int userId = resultSet.getInt(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                attendanceList.add(new Attendance(attendanceId, attendanceDate, firstName + " " + lastName));
            }
        } catch (
                SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return attendanceList;
    }

    private List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT * FROM users WHERE email != ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "admin@mail.com");
            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                userList.add(new User(userId, userName, firstName, lastName, email, status));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return userList;
    }

    private boolean isUserStatusUpdated(User user) {
        int result = -1;
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "UPDATE users SET status = ? WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, user.getStatus());
            statement.setInt(2, user.getUserId());
            result = DatabaseHandler.getInstance().updateData(statement);
        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result != -1;
    }

    private boolean isLeaveStatusUpdated(Leave leave) {
        int result = -1;
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "UPDATE leave_table SET status = ? WHERE leave_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, leave.getLeaveStatus());
            statement.setInt(2, leave.getLeaveId());
            result = DatabaseHandler.getInstance().updateData(statement);
        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result != -1;
    }

    private boolean isMeetingAdded(Meeting meeting) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "INSERT INTO meeting(meeting_title, meeting_date, meeting_agenda) VALUES (?, ?, ?)";
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, meeting.getMeetingTitle());
            statement.setDate(2, new java.sql.Date(meeting.getMeetingDate().getTime()));
            statement.setString(3, meeting.getMeetingAgenda());
            result = DatabaseHandler.getInstance().insertData(statement);


        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result != -1;
    }

    private boolean isLeaveAdded(Leave leave) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "INSERT INTO leave_table(leave_date, reason, user_id) VALUES (?, ?, ?)";
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDate(1, new java.sql.Date(leave.getLeaveDate().getTime()));
            statement.setString(2, leave.getLeaveReason());
            statement.setInt(3, User.getInstance().getUserId());
            result = DatabaseHandler.getInstance().insertData(statement);


        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result != -1;
    }

    private boolean isAttendanceMarked(Attendance attendance) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "INSERT INTO attendance(attendance_date, user_id) VALUES (?, ?)";
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDate(1, new java.sql.Date(attendance.getAttendanceDate().getTime()));
            statement.setInt(2, User.getInstance().getUserId());
            result = DatabaseHandler.getInstance().insertData(statement);


        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result != -1;
    }
}
