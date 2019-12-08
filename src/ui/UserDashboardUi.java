package ui;

import _interface.SignInListener;
import _interface.UpdateListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboardUi extends BaseUi implements ActionListener {

    private JButton jButton_viewMeetingSchedule, jButton_viewLeaveStatus, jButton_signOut;
    private SignInListener listener;
    private UpdateListener updateListener;

    public UserDashboardUi(SignInListener listener, UpdateListener updateListener) {
        this.listener = listener;
        this.updateListener = updateListener;
    }

    @Override
    void initUi() {
        jButton_signOut = new JButton("SignOut");
        jButton_signOut.setName("jButton_signOut");

        jButton_viewMeetingSchedule = new JButton("Meeting Schedule");
        jButton_viewMeetingSchedule.setName("jButton_viewMeetingSchedule");

        jButton_viewLeaveStatus = new JButton("View Leave Status");
        jButton_viewLeaveStatus.setName("jButton_viewLeaveStatus");


    }

    @Override
    void addListeners() {
        jButton_signOut.addActionListener(this);
        jButton_viewMeetingSchedule.addActionListener(this);
        jButton_viewLeaveStatus.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        add(jButton_viewMeetingSchedule);
        add(jButton_viewLeaveStatus);
        add(jButton_signOut);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (((JComponent) event.getSource()).getName()) {
            case "jButton_signOut":
                listener.onSignOut();
                break;
            case "jButton_viewMeetingSchedule":
                new MeetingSchedule();
                break;
            case "jButton_viewLeaveStatus":
            case "jButton_viewLeaveRequest":
                new LeaveStatus();
                break;
        }
    }
}
