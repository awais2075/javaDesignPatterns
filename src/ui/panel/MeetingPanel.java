package ui.panel;

import _interface.ItemClickListener;
import decoration.MeetingListRenderer;
import model.Meeting;
import model.User;
import util.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MeetingPanel extends BasePanel<Meeting> implements ListSelectionListener {

    private JButton jButton_addMeeting;
    private JList<Meeting> jList = new JList<>();

    private DefaultListModel<Meeting> listModel = new DefaultListModel<>();

    private ItemClickListener listener;
    private List<Meeting> meetingList;

    public MeetingPanel(List meetingList, ItemClickListener listener) {
        this.meetingList = meetingList;
        this.listener = listener;

        init();
//        setVisible(false);

    }


    @Override
    protected void initUi() {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(5);
        setLayout(layout);

        setPreferredSize(new Dimension(300, 500));

        jButton_addMeeting = Utils.getButton("Add Meeting", "jButton_addMeeting");
        if (User.getInstance().getStatus().equals("admin")) {
            jButton_addMeeting.setVisible(true);
        } else {
            jButton_addMeeting.setVisible(false);
        }

        listModel.removeAllElements();
        listModel = new DefaultListModel<>();
        for (Meeting meeting : meetingList) {
            listModel.addElement(meeting);

        }
        jList.setModel(listModel);
        jList.setCellRenderer(new MeetingListRenderer());

    }

    @Override
    void addListeners() {
        jButton_addMeeting.addActionListener(this);
        jList.addListSelectionListener(this);
    }

    @Override
    void addWidgetsToPanel() {

        add(jButton_addMeeting, BorderLayout.NORTH);
        add(new JScrollPane(jList), BorderLayout.CENTER);
    }

    @Override
    public void updateModel(Meeting meeting, int position) {
        listModel.setElementAt(meeting, position);
    }

    @Override
    public void addModel(Meeting meeting) {
        listModel.addElement(meeting);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonId = ((JComponent) event.getSource()).getName();
        listener.onButtonClicked(buttonId);
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {

        listener.onItemClicked(jList.getSelectedValue(), jList.getSelectedIndex());
    }


}
