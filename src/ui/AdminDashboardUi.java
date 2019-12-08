package ui;

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

public class AdminDashboardUi extends BaseUi implements ListSelectionListener, ActionListener {

    private List<User> userList;
    private SignInListener listener;
    private UpdateListener updateListener;
    private DefaultListModel<User> listModel;
    private JButton jButton_addMeetingSchedule;
    private JButton jButton_viewLeaveRequests;
    private JButton jButton_signOut;
    private JList<User> jList;


    private JPanel jPanel;
    private JPanel topBarPanel;
    public AdminDashboardUi(SignInListener listener, UpdateListener updateListener, List<User> userList) {
        this.listener = listener;
        this.updateListener = updateListener;
        this.userList = userList;

        setLayout(new BorderLayout());
    }

    @Override
    void initUi() {
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        topBarPanel = new JPanel(new FlowLayout());

        jButton_addMeetingSchedule = new JButton("Add Meeting Schedule");
        jButton_addMeetingSchedule.setName("jButton_addMeetingSchedule");

        jButton_viewLeaveRequests = new JButton("View Leave Requests");
        jButton_viewLeaveRequests.setName("jButton_viewLeaveRequests");

        jButton_signOut = new JButton("SignOut");
        jButton_signOut.setName("jButton_signOut");


        listModel = new DefaultListModel<>();

        for (User user : userList) {
            listModel.addElement(user);
        }

        //create the list
        jList = new JList<>(listModel);
    //    jList.setCellRenderer(new AdminDashboardRenderer());
        jList.setSelectedIndex(0);


    }

    @Override
    void addListeners() {

        jButton_signOut.addActionListener(this);
        jList.addListSelectionListener(this);

    }

    @Override
    void addWidgetsToPanel() {
        /*topBarPanel.add(jButton_addMeetingSchedule);
        topBarPanel.add(jButton_viewLeaveRequests);
        topBarPanel.add(jButton_signOut);

        jPanel.add(topBarPanel,BorderLayout.NORTH);

        jPanel.add(new JScrollPane(jList ),BorderLayout.CENTER);

        add(jPanel);*/
        add(new JButton("EAST"),BorderLayout.EAST);
        add(new JButton("WEST"),BorderLayout.WEST);
        add(new JButton("NORTH"),BorderLayout.NORTH);
        add(new JButton("SOUTH"),BorderLayout.SOUTH);
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

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (((JComponent) event.getSource()).getName()) {
            case "jButton_signOut":
                listener.onSignOut();
                break;
        }
    }

}
