package ui;

import _interface.AddListener;
import _interface.ItemClickListener;
import _interface.UpdateListener;
import database.DatabaseHandler;
import model.Leave;
import model.User;
import util.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LeaveStatusUi extends BaseUi implements ItemClickListener<Leave>, ListSelectionListener, ActionListener, UpdateListener<Leave>, AddListener<Leave> {

    private List<Leave> leaveList;


    private JPanel jPanel;
    private JButton jButton_addLeaveRequest;
    private DefaultListModel<Leave> listModel;
    private JList<Leave> jList;

    public LeaveStatusUi(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }

    @Override
    void initUi() {
        jPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);
        jPanel.setLayout(borderLayout);
        jPanel.setPreferredSize(new Dimension(300, 500));
        jButton_addLeaveRequest = new JButton("Add Leave Request");
        jButton_addLeaveRequest.setName("jButton_addLeaveRequest");
        listModel = new DefaultListModel<>();

        for (Leave leave : leaveList) {
            listModel.addElement(leave);

        }
        jList = new JList(listModel);
        jList.setCellRenderer(new LeaveListRenderer(this));


        if (!User.getInstance().getStatus().equals("admin")) {
            jButton_addLeaveRequest.setVisible(true);
        } else {
            jButton_addLeaveRequest.setVisible(false);
        }
    }

    @Override
    void addListeners() {
        jButton_addLeaveRequest.addActionListener(this);
        jList.addListSelectionListener(this);

    }

    @Override
    void addWidgetsToPanel() {
        jPanel.add(jButton_addLeaveRequest, BorderLayout.NORTH);
        jPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
        add(jPanel);
    }

    @Override
    public void onItemClicked(Leave leave, int position) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (((JComponent) actionEvent.getSource()).getName()) {
            case "jButton_addLeaveRequest":
//                new AddLeaveRequest(this);
                break;
        }
    }

    @Override
    public void onUpdate(Leave leave) {
    }

    private void addLeave(Leave leave) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "INSERT INTO leave(leave_date, reason, user_id) VALUES (?, ?, ?)";
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDate(1, new java.sql.Date(leave.getLeaveDate().getTime()));
            statement.setString(2, leave.getLeaveReason());
            statement.setInt(3, User.getInstance().getUserId());
            result = DatabaseHandler.getInstance().insertData(statement);


        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
    }

    @Override
    public void onAdd(Leave leave) {
        addLeave(leave);
        listModel.addElement(leave);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String message;
        String newStatus;
        Leave leave = jList.getSelectedValue();
        int index = jList.getSelectedIndex();
        switch (leave.getLeaveStatus()) {
            case "pending":
                message = "Do You want to approve this leave request";
               if (User.getInstance().getStatus().equals("admin")) {
                   showDialog(leave, index, message);
               }
                break;
            default:
                message = "Invalid Message";
                break;
        }
    }

    private void showDialog(Leave leave, int index, String message) {
        switch (JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION)) {
            case 0:
                Utils.printInfo("YES");
                leave.setLeaveStatus("approved");
//                updateListener.onUpdate(selectedUser);
                listModel.setElementAt(leave, index);

                break;
            default:
                Utils.printError("No");
                break;
        }

    }
}
