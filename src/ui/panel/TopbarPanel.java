package ui.panel;

import _interface.ItemClickListener;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TopbarPanel extends BasePanel<String> {

    private JButton jButton_profile, jButton_user, jButton_leave, jButton_meeting, jButton_attendance, jButton_signOut;


    private ItemClickListener listener;

    public TopbarPanel(ItemClickListener listener) {
        this.listener = listener;

        init();
    }

    @Override
    protected void initUi() {
        jButton_profile = Utils.getButton("Profile", "jButton_profile");
        jButton_user = Utils.getButton("User", "jButton_user");
        jButton_leave = Utils.getButton("Leave", "jButton_leave");
        jButton_meeting = Utils.getButton("Meeting", "jButton_meeting");
        jButton_attendance = Utils.getButton("Attendance", "jButton_attendance");
        jButton_signOut = Utils.getButton("SignOut", "jButton_signOut");


        jButton_profile.setVisible(false);
        jButton_attendance.setVisible(false);
        jButton_user.setVisible(false);

        if (User.getInstance().getStatus().equals("admin")) {
            jButton_user.setVisible(true);
            jButton_attendance.setVisible(true);
        } else {
            jButton_profile.setVisible(true);
        }
    }

    @Override
    void addListeners() {
        jButton_profile.addActionListener(this);
        jButton_leave.addActionListener(this);
        jButton_meeting.addActionListener(this);
        jButton_attendance.addActionListener(this);
        jButton_signOut.addActionListener(this);
        jButton_user.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        add(jButton_profile);
        add(jButton_user);
        add(jButton_leave);
        add(jButton_meeting);
        add(jButton_attendance);
        add(jButton_signOut);
    }

    @Override
    public void updateModel(String s, int position) {

    }

    @Override
    public void addModel(String s) {

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonId = ((JComponent) event.getSource()).getName();
        listener.onButtonClicked(buttonId);
    }
}
