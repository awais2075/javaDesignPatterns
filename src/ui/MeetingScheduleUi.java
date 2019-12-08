package ui;

import _interface.AddListener;
import _interface.ItemClickListener;
import _interface.UpdateListener;
import database.DatabaseHandler;
import model.Meeting;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MeetingScheduleUi extends BaseUi implements ItemClickListener<Meeting>, ActionListener, UpdateListener<Meeting>, AddListener<Meeting> {

    private List<Meeting> meetingList;

    private JPanel jPanel;
    private JButton jButton_addNewMeeting;
    private DefaultListModel<Meeting> listModel;
    private JList<Meeting> jList;

    public MeetingScheduleUi(List<Meeting> meetingList) {
        this.meetingList = meetingList;


    }

    @Override
    void initUi() {
        jPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);
        jPanel.setLayout(borderLayout);
        jPanel.setPreferredSize(new Dimension(300, 500));
        jButton_addNewMeeting = new JButton("Add Meeting");
        jButton_addNewMeeting.setName("jButton_addNewMeeting");
        listModel = new DefaultListModel<>();

        for (Meeting meeting : meetingList) {
            listModel.addElement(meeting);

        }
        jList = new JList(listModel);
        jList.setCellRenderer(new MeetingScheduleRenderer(this));


        if (User.getInstance().getStatus().equals("admin")) {
            jButton_addNewMeeting.setVisible(true);
        } else {
            jButton_addNewMeeting.setVisible(false);
        }
    }

    @Override
    void addListeners() {
        jButton_addNewMeeting.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        jPanel.add(jButton_addNewMeeting, BorderLayout.NORTH);
        jPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
        add(jPanel);
    }

    @Override
    public void onItemClicked(Meeting meeting, int position) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (((JComponent) actionEvent.getSource()).getName()) {
            case "jButton_addNewMeeting":
                new AddMeeting(this);
                break;
        }
    }

    @Override
    public void onUpdate(Meeting meeting) {
    }

    private void addMeetingSchedule(Meeting meeting) {
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
    }

    @Override
    public void onAdd(Meeting meeting) {
        addMeetingSchedule(meeting);
        listModel.addElement(meeting);
    }

    @Override
    public void onCancel() {

    }
}
