package ui;

import _interface.AddListener;
import model.Meeting;
import util.Utils;

import javax.swing.*;
import java.awt.*;

public class AddMeeting implements AddListener<Meeting> {

    private JFrame addMeetingFrame;
    private AddListener listener;
    public AddMeeting(AddListener listener) {
        this.listener = listener;
        addMeetingFrame = Utils.getFrame("Add Meeting", 250, 300);


        BaseUi addMeetingPanel = new AddMeetingUi(this);
        addMeetingPanel.init();

        addMeetingPanel.setLayout(new FlowLayout());
        addMeetingFrame.getContentPane().add(addMeetingPanel);
        addMeetingFrame.setResizable(false);
        addMeetingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addMeetingFrame.setLocationRelativeTo(null);
        addMeetingFrame.setVisible(true);

    }

    @Override
    public void onAdd(Meeting meeting) {
//        addMeetingFrame.setVisible(false);
        listener.onAdd(meeting);
    }

    @Override
    public void onCancel() {
        addMeetingFrame.setVisible(false);
    }
}
