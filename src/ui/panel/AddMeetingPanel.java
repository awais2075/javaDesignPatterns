package ui.panel;


import _interface.AddListener;
import model.Meeting;
import org.jdatepicker.impl.JDatePickerImpl;
import util.DateLabelFormatter;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class AddMeetingPanel extends BasePanel implements KeyListener {

    private JLabel jLabel_meetingTitle, jLabel_meetingDate, jLabel_meetingAgenda;
    private JTextField jTextField_meetingTitle, jTextField_meetingDate;
    private JTextField jTextField_meetingAgenda;
    private JButton jButton_add, jButton_cancel;
    private JDatePickerImpl datePicker;
    private AddListener listener;
    private JFrame jFrame;

    public AddMeetingPanel(JFrame jFrame, AddListener listener) {
        this.jFrame = jFrame;
        this.listener = listener;
        init();
    }

    @Override
    protected void initUi() {
        jLabel_meetingTitle = new JLabel("Meeting Title :");
        jLabel_meetingDate = new JLabel("Meeting Date:  ");
        jLabel_meetingAgenda = new JLabel("Meeting Agenda:  ");

        jTextField_meetingTitle = new JTextField(15);
        jTextField_meetingDate = new JTextField(15);
        jTextField_meetingAgenda = new JTextField(18);


        jButton_add = Utils.getButton("Add", "jButton_add");
        jButton_cancel = Utils.getButton("Cancel", "jButton_cancel");
        datePicker = new JDatePickerImpl(Utils.getDatePanel(), new DateLabelFormatter());

        jButton_add.setEnabled(false);


    }

    @Override
    void addListeners() {
        jTextField_meetingTitle.addKeyListener(this);
        jTextField_meetingDate.addKeyListener(this);
        jTextField_meetingAgenda.addKeyListener(this);

        jButton_add.addActionListener(this);
        jButton_cancel.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        add(jLabel_meetingTitle);
        add(jTextField_meetingTitle);

        add(jLabel_meetingDate);
        add(datePicker);

        add(jLabel_meetingAgenda);
        add(jTextField_meetingAgenda);
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
                String meetingTitle = jTextField_meetingTitle.getText();
                Date meetingDate = (Date) datePicker.getModel().getValue();
                String meetingAgenda = jTextField_meetingAgenda.getText();

                jTextField_meetingTitle.setText("");
                jTextField_meetingDate.setText("");
                jTextField_meetingAgenda.setText("");

                listener.onAdd(new Meeting(meetingTitle, meetingDate, meetingAgenda));
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
        jButton_add.setEnabled((!jTextField_meetingTitle.getText().isEmpty() && !jTextField_meetingAgenda.getText().isEmpty()));
    }
}
