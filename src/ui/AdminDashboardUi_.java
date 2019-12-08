package ui;

import _interface.ItemClickListener;
import _interface.SignInListener;
import _interface.UpdateListener;
import model.User;
import util.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminDashboardUi_ extends BaseUi implements ActionListener, ListSelectionListener, ItemClickListener<User> {

    private JPanel jPanel, topBarPanel, contentPanel;
    private JButton jButton_viewMeetingSchedule, jButton_viewLeaveRequest, jButton_signOut;
    private JList<User> jList;
    private DefaultListModel<User> listModel;
    private SignInListener listener;
    private UpdateListener updateListener;
    private List<User> userList;


    public AdminDashboardUi_(SignInListener listener, UpdateListener updateListener, List<User> userList) {
        this.listener = listener;
        this.updateListener = updateListener;
        this.userList = userList;
    }

    @Override
    void initUi() {
        jPanel = new JPanel(new BorderLayout());
        topBarPanel = new JPanel(new FlowLayout());
        jButton_signOut = new JButton("SignOut");
        jButton_signOut.setName("jButton_signOut");

        jButton_viewMeetingSchedule = new JButton("Meeting Schedule");
        jButton_viewMeetingSchedule.setName("jButton_viewMeetingSchedule");

        jButton_viewLeaveRequest = new JButton("Leave Request");
        jButton_viewLeaveRequest.setName("jButton_viewLeaveRequest");

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));

        jList = getUserDataList();
    }

    @Override
    void addListeners() {
        jButton_signOut.addActionListener(this);
        jButton_viewMeetingSchedule.addActionListener(this);
        jButton_viewLeaveRequest.addActionListener(this);
        jList.addListSelectionListener(this);

    }

    @Override
    void addWidgetsToPanel() {

        topBarPanel.add(jButton_viewMeetingSchedule);
        topBarPanel.add(jButton_viewLeaveRequest);
        topBarPanel.add(jButton_signOut);

        contentPanel.add(new JScrollPane(jList));
        jPanel.add(topBarPanel, BorderLayout.NORTH);

        jPanel.add(contentPanel, BorderLayout.CENTER);
        add(jPanel);
    }


    private JList getList() {
        DefaultListModel<String> modelList = new DefaultListModel<>();
        for (int i = 0; i < 100; i++) {
            modelList.addElement("List Item : " + (i + 1));
        }
        JList<String> jList = new JList<>(modelList);
        jList.setLayoutOrientation(JList.VERTICAL);
        return jList;
    }

    private JList getUserDataList() {
        listModel = new DefaultListModel<>();

        for (User user : userList) {
            listModel.addElement(user);
        }


        //create the list
        jList = new JList<>(listModel);
        jList.setCellRenderer(new AdminDashboardRenderer(this));
        jList.setLayoutOrientation(JList.VERTICAL);


        return jList;
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
            case "jButton_viewLeaveRequest":
                new LeaveStatus();
                break;
        }
    }

    @Override
    public void onItemClicked(User user, int position) {

        Utils.printInfo(user.getEmail());
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        Utils.printError(e.getLastIndex() + "\t" + e.getFirstIndex());
        //listModel.setElementAt(new User(), jList.getSelectedIndex());

        String message;
        String newStatus;
        User selectedUser = jList.getSelectedValue();
        int index = jList.getSelectedIndex();
        switch (selectedUser.getStatus()) {
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
                selectedUser.setStatus(newStatus);
                updateListener.onUpdate(selectedUser);
                listModel.setElementAt(selectedUser, index);

                break;
            default:
                Utils.printError("No");
                break;
        }

    }


}
