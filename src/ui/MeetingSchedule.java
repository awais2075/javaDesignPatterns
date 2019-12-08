package ui;

import database.DatabaseHandler;
import model.Meeting;
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

public class MeetingSchedule {

    private List<Meeting> meetingList = new ArrayList<>();
    private JFrame meetingScheduleFrame;
    private BaseUi meetingSchedulePanel;

    public MeetingSchedule() {
        meetingList = getMeetingScheduleList();


        meetingScheduleFrame = Utils.getFrame("Meeting Schedule", 500, 600);

        meetingSchedulePanel = new MeetingScheduleUi(meetingList);
        meetingSchedulePanel.init();
        meetingSchedulePanel.setLayout(new FlowLayout());
        meetingScheduleFrame.getContentPane().add((meetingSchedulePanel));
        meetingScheduleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        meetingScheduleFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                meetingScheduleFrame.setVisible(false);
                meetingScheduleFrame.dispose();
            }
        });
        meetingScheduleFrame.setLocationRelativeTo(null);
        meetingScheduleFrame.setVisible(true);
    }

    private List<Meeting> getMeetingScheduleList() {
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
}
