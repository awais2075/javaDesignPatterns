package ui.panel;

import _interface.ItemClickListener;
import decoration.AttendanceListRenderer;
import model.Attendance;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AttendancePanel extends BasePanel<Attendance> implements ListSelectionListener {

    private JList<Attendance> jList = new JList();

    private DefaultListModel<Attendance> listModel = new DefaultListModel<>();

    private ItemClickListener listener;
    private List<Attendance> attendanceList;

    public AttendancePanel(List attendanceList, ItemClickListener listener) {
        this.attendanceList = attendanceList;
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


        for (Attendance attendance : attendanceList) {
            listModel.addElement(attendance);

        }

        jList.setModel(listModel);
        jList.setCellRenderer(new AttendanceListRenderer());
    }

    @Override
    void addListeners() {
        jList.addListSelectionListener(this);
    }

    @Override
    void addWidgetsToPanel() {

        add(new JScrollPane(jList), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonId = ((JComponent) event.getSource()).getName();
        listener.onButtonClicked(buttonId);
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        Attendance attendance = jList.getSelectedValue();

    }

    public void updateModel(Attendance attendance, int position) {
        listModel.setElementAt(attendance, position);
    }

    @Override
    public void addModel(Attendance attendance) {
        listModel.addElement(attendance);
    }

}
