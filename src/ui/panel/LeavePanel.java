package ui.panel;

import _interface.ItemClickListener;
import decoration.LeaveListRenderer;
import model.Leave;
import model.User;
import util.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LeavePanel extends BasePanel<Leave> implements ListSelectionListener {

    private JButton jButton_addLeave;
    private JList<Leave> jList = new JList();

    private DefaultListModel<Leave> listModel = new DefaultListModel<>();

    private ItemClickListener listener;
    private List<Leave> leaveList;

    public LeavePanel(List leaveList, ItemClickListener listener) {
        this.leaveList = leaveList;
        this.listener = listener;
        init();
//        setVisible(false);
    }


    @Override
    protected void initUi() {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(5);
        setLayout(layout);

        listModel.clear();
        setPreferredSize(new Dimension(300, 500));

        jButton_addLeave = Utils.getButton("Add Leave", "jButton_addLeave");

        if (!User.getInstance().getStatus().equals("admin")) {
            jButton_addLeave.setVisible(true);
        } else {
            jButton_addLeave.setVisible(false);
        }


        for (Leave leave : leaveList) {
            listModel.addElement(leave);

        }

        jList.setModel(listModel);
        jList.setCellRenderer(new LeaveListRenderer());
    }

    @Override
    void addListeners() {
        jButton_addLeave.addActionListener(this);
        jList.addListSelectionListener(this);
    }

    @Override
    void addWidgetsToPanel() {

        add(jButton_addLeave, BorderLayout.NORTH);
        add(new JScrollPane(jList), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonId = ((JComponent) event.getSource()).getName();
        listener.onButtonClicked(buttonId);
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        Leave leave = jList.getSelectedValue();
        if (leave.getLeaveStatus().equals("pending")) {
            listener.onItemClicked(leave, jList.getSelectedIndex());
        }
    }

    public void updateModel(Leave leave, int position) {
        listModel.setElementAt(leave, position);
    }

    @Override
    public void addModel(Leave leave) {
        listModel.addElement(leave);
    }

}
