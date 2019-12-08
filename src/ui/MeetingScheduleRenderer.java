package ui;


import _interface.ItemClickListener;
import model.Meeting;

import javax.swing.*;
import java.awt.*;

public class MeetingScheduleRenderer extends JPanel implements ListCellRenderer<Meeting> {

    private JLabel jLabel_meetingIcon = new JLabel();
    private JLabel jLabel_meetingTitle = new JLabel();
    private JLabel jLabel_meetingAgenda = new JLabel();
    private JLabel jLabel_meetingDate = new JLabel();

    private ItemClickListener itemClickListener;

    public MeetingScheduleRenderer(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(jLabel_meetingTitle);
        panelText.add(jLabel_meetingAgenda);
        panelText.add(jLabel_meetingDate);
        add(jLabel_meetingIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Meeting> list,
                                                  Meeting meeting, int index, boolean isSelected, boolean cellHasFocus) {

        String imagePath = "src/iconMeeting.png";
        jLabel_meetingIcon.setIcon(new ImageIcon(imagePath));
        jLabel_meetingTitle.setText(meeting.getMeetingTitle());
        jLabel_meetingAgenda.setText(meeting.getMeetingAgenda());
        jLabel_meetingDate.setText(meeting.getMeetingDate().toString());

        // set Opaque to change background color of JLabel
        jLabel_meetingTitle.setOpaque(true);
        jLabel_meetingAgenda.setOpaque(true);
        jLabel_meetingDate.setOpaque(true);
        jLabel_meetingIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            jLabel_meetingTitle.setBackground(list.getSelectionBackground());
            jLabel_meetingAgenda.setBackground(list.getSelectionBackground());
            jLabel_meetingDate.setBackground(list.getSelectionBackground());
            jLabel_meetingIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());

            itemClickListener.onItemClicked(meeting, list.getSelectedIndex());
        } else { // when don't select
            jLabel_meetingTitle.setBackground(list.getBackground());
            jLabel_meetingAgenda.setBackground(list.getBackground());
            jLabel_meetingDate.setBackground(list.getBackground());
            jLabel_meetingIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}