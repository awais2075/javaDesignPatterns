package ui;

import database.DatabaseHandler;
import model.Leave;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveStatus {

    private List<Leave> leaveList = new ArrayList<>();
    private JFrame leaveStatusFrame;
    private BaseUi leaveStatusPanel;

    public LeaveStatus() {

        leaveList = getLeaveApplicationList();

        Utils.printError(leaveList.size() + " leave list size");

        leaveStatusFrame = Utils.getFrame("Leave Application", 500, 600);

        leaveStatusPanel = new LeaveStatusUi(leaveList);
        leaveStatusPanel.init();
        leaveStatusPanel.setLayout(new FlowLayout());
        leaveStatusFrame.getContentPane().add((leaveStatusPanel));
        leaveStatusFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        leaveStatusFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                leaveStatusFrame.setVisible(false);
                leaveStatusFrame.dispose();
            }
        });
        leaveStatusFrame.setLocationRelativeTo(null);
        leaveStatusFrame.setVisible(true);
    }

    private List<Leave> getLeaveApplicationList() {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT   b.*, a.first_name, a.last_name FROM users a, leave_table b ";
        if (!User.getInstance().getStatus().equals("admin")) {
            sqlQuery = sqlQuery + "WHERE b.user_id = " + User.getInstance().getUserId()+" AND a.user_id = " + User.getInstance().getUserId();
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
}
