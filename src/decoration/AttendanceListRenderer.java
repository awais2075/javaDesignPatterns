package decoration;


import model.Attendance;

import javax.swing.*;
import java.awt.*;

public class AttendanceListRenderer extends JPanel implements ListCellRenderer<Attendance> {

    private JLabel jLabel_attendanceIcon = new JLabel();
    private JLabel jLabel_userName = new JLabel();
    private JLabel jLabel_attendanceDate = new JLabel();


    public AttendanceListRenderer() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(jLabel_userName);
        panelText.add(jLabel_attendanceDate);
        add(jLabel_attendanceIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Attendance> list,
                                                  Attendance attendance, int index, boolean isSelected, boolean cellHasFocus) {

        String imagePath = "src/iconAttendance.png";
        jLabel_attendanceIcon.setIcon(new ImageIcon(imagePath));
        jLabel_userName.setText(attendance.getUserName());
        jLabel_attendanceDate.setText(attendance.getAttendanceDate().toString());


        // set Opaque to change background color of JLabel
        jLabel_userName.setOpaque(true);
        jLabel_attendanceDate.setOpaque(true);
        jLabel_attendanceIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            jLabel_userName.setBackground(list.getSelectionBackground());
            jLabel_attendanceDate.setBackground(list.getSelectionBackground());
            jLabel_attendanceIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());

        } else { // when don't select
            jLabel_userName.setBackground(list.getBackground());
            jLabel_attendanceDate.setBackground(list.getBackground());
            jLabel_attendanceIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}
