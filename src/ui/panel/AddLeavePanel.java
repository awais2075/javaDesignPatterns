package ui.panel;


import _interface.AddListener;
import model.Leave;
import org.jdatepicker.impl.JDatePickerImpl;
import util.DateLabelFormatter;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class AddLeavePanel extends BasePanel implements KeyListener {

    private JLabel jLabel_leaveDate, jLabel_leaveReason;
    private JTextField jTextField_leaveDate, jTextField_leaveReason;
    private JButton jButton_add, jButton_cancel;
    private JDatePickerImpl datePicker;
    private AddListener listener;
    private JFrame jFrame;

    public AddLeavePanel(JFrame jFrame, AddListener listener) {
        this.jFrame = jFrame;
        this.listener = listener;
        init();
    }

    @Override
    protected void initUi() {
        jLabel_leaveReason = new JLabel("Leave Reason:  ");
        jLabel_leaveDate = new JLabel("Leave Date:  ");

        jTextField_leaveDate = new JTextField(18);
        jTextField_leaveReason = new JTextField(18);


        jButton_add = Utils.getButton("Add", "jButton_add");
        jButton_cancel = Utils.getButton("Cancel", "jButton_cancel");
        datePicker = new JDatePickerImpl(Utils.getDatePanel(), new DateLabelFormatter());

        jButton_add.setEnabled(false);


    }

    @Override
    void addListeners() {
        jTextField_leaveDate.addKeyListener(this);
        jTextField_leaveReason.addKeyListener(this);

        jButton_add.addActionListener(this);
        jButton_cancel.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        add(jLabel_leaveDate);
        add(datePicker);


        add(jLabel_leaveReason);
        add(jTextField_leaveDate);

        add(jButton_add);
        add(jButton_cancel);
    }

    @Override
    public void updateModel(Object o, int position) {

    }

    @Override
    public void addModel(Object o) {

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (((JComponent) actionEvent.getSource()).getName()) {
            case "jButton_add":
                Date leaveDate = (Date) datePicker.getModel().getValue();
                String leaveReason = jTextField_leaveDate.getText();
                jTextField_leaveDate.setText("");
                jTextField_leaveReason.setText("");

                listener.onAdd(new Leave(leaveDate, leaveReason));
                break;
            case "jButton_cancel":
                listener.onCancel(jFrame);
                break;
            default:
                Utils.printError("Invalid ...");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        jButton_add.setEnabled((!jTextField_leaveDate.getText().isEmpty()));
    }
}
