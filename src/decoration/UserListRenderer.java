package decoration;

import model.User;

import javax.swing.*;
import java.awt.*;

public class UserListRenderer extends JPanel implements ListCellRenderer<User> {

    private JLabel jLabel_userIcon = new JLabel();
    private JLabel jLabel_userName = new JLabel();
    private JLabel jLabel_email = new JLabel();
    private JLabel jLabel_status = new JLabel();


    public UserListRenderer( ) {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(jLabel_userName);
        panelText.add(jLabel_email);
        panelText.add(jLabel_status);
        add(jLabel_userIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list,
                                                  User user, int index, boolean isSelected, boolean cellHasFocus) {

        String imagePath = "src/iconUser.png";
        jLabel_userIcon.setIcon(new ImageIcon(imagePath));
        jLabel_userName.setText(user.getFirstName()+" "+user.getLastName());
        jLabel_email.setText(user.getEmail());
        jLabel_status.setText(user.getStatus());

        switch (user.getStatus()) {
            case "pending":
                jLabel_status.setForeground(Color.orange);
                break;
            case "registered":
                jLabel_status.setForeground(Color.green);
                break;
            case "unregistered":
                jLabel_status.setForeground(Color.red);
                break;

        }
        // set Opaque to change background color of JLabel
        jLabel_userName.setOpaque(true);
        jLabel_email.setOpaque(true);
        jLabel_status.setOpaque(true);
        jLabel_userIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            jLabel_userName.setBackground(list.getSelectionBackground());
            jLabel_email.setBackground(list.getSelectionBackground());
            jLabel_status.setBackground(list.getSelectionBackground());
            jLabel_userIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());

        } else { // when don't select
            jLabel_userName.setBackground(list.getBackground());
            jLabel_email.setBackground(list.getBackground());
            jLabel_status.setBackground(list.getBackground());
            jLabel_userIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}