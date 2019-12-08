package ui;


import _interface.AddListener;
import model.Meeting;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateLabelFormatter;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Properties;

public class AddMeetingUi extends BaseUi implements ActionListener, KeyListener {

    private JLabel jLabel_meetingTitle, jLabel_meetingDate, jLabel_meetingAgenda;
    private JTextField jTextField_meetingTitle, jTextField_meetingDate;
    private JTextField jTextField_meetingAgenda;
    private JButton jButton_add, jButton_cancel;
    private JDatePickerImpl datePicker;
    private AddListener listener;

    public AddMeetingUi(AddListener listener) {
        this.listener = listener;
    }

    @Override
    void initUi() {
        jLabel_meetingTitle = new JLabel("Meeting Title :");
        jLabel_meetingDate = new JLabel("Meeting Date:  ");
        jLabel_meetingAgenda = new JLabel("Meeting Agenda:  ");

        jTextField_meetingTitle = new JTextField(15);
        jTextField_meetingDate = new JTextField(15);
        jTextField_meetingAgenda = new JTextField(18);


        jButton_add = new JButton("Add");
        jButton_cancel = new JButton("Cancel");

        jButton_add.setEnabled(false);


        jButton_add.setName("jButton_add");
        jButton_cancel.setName("jButton_cancel");
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

        UtilDateModel model = new UtilDateModel();
        model.setDate(2019, 8, 24);
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        add(datePicker);

        add(jLabel_meetingAgenda);
        add(jTextField_meetingAgenda);
        add(jButton_add);
        add(jButton_cancel);

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
                listener.onCancel();
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
