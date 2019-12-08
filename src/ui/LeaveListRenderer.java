
package ui;


import _interface.ItemClickListener;
import model.Leave;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LeaveListRenderer extends JPanel implements ListCellRenderer<Leave> {

    private JLabel jLabel_leaveIcon = new JLabel();
    private JLabel jLabel_userName = new JLabel();
    private JLabel jLabel_leaveReason = new JLabel();
    private JLabel jLabel_leaveDate = new JLabel();

    private ItemClickListener itemClickListener;

    public LeaveListRenderer(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(jLabel_userName);
        panelText.add(jLabel_leaveReason);
        panelText.add(jLabel_leaveDate);
        add(jLabel_leaveIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Leave> list,
                                                  Leave leave, int index, boolean isSelected, boolean cellHasFocus) {

        String imagePath = "src/iconMeeting.png";
        jLabel_leaveIcon.setIcon(new ImageIcon(imagePath));
        jLabel_userName.setText(leave.getUserName());
        jLabel_leaveReason.setText(leave.getLeaveReason());
        jLabel_leaveDate.setText(leave.getLeaveDate().toString());


        switch (leave.getLeaveStatus()) {
            case "pending":
                jLabel_userName.setForeground(Color.red);
                break;
            case "approved":
                jLabel_userName.setForeground(Color.green);
                break;
        }
        // set Opaque to change background color of JLabel
        jLabel_userName.setOpaque(true);
        jLabel_leaveReason.setOpaque(true);
        jLabel_leaveDate.setOpaque(true);
        jLabel_leaveIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            jLabel_userName.setBackground(list.getSelectionBackground());
            jLabel_leaveReason.setBackground(list.getSelectionBackground());
            jLabel_leaveDate.setBackground(list.getSelectionBackground());
            jLabel_leaveIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());

            itemClickListener.onItemClicked(leave, list.getSelectedIndex());
        } else { // when don't select
            jLabel_userName.setBackground(list.getBackground());
            jLabel_leaveReason.setBackground(list.getBackground());
            jLabel_leaveDate.setBackground(list.getBackground());
            jLabel_leaveIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}