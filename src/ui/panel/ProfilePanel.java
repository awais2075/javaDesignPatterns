package ui.panel;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProfilePanel extends BasePanel {


    private JLabel jLabel_profileIcon, jLabel_userName, jLabel_userEmail, jLabel_firstName;
    private JPanel jPanel;

    public ProfilePanel() {
        setSize(new Dimension(100, 100));
        setLayout(new BorderLayout());
        setBackground(Color.green);
        init();

    }

    @Override
    protected void initUi() {
        jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(100, 100));
        jPanel.setLayout(new FlowLayout());
        jPanel.setAlignmentX(CENTER_ALIGNMENT);
        jPanel.setAlignmentY(CENTER_ALIGNMENT);

        jLabel_profileIcon = new JLabel();
        jLabel_profileIcon.setIcon(new ImageIcon("src/iconUser.png"));

        jLabel_userName = new JLabel("User Name : " + User.getInstance().getUserName());
        jLabel_userEmail = new JLabel("User Email : " + User.getInstance().getEmail());
        jLabel_firstName = new JLabel("Full Name : " + User.getInstance().getFirstName()+" "+User.getInstance().getLastName());
    }

    @Override
    void addListeners() {

    }

    @Override
    void addWidgetsToPanel() {
        jPanel.add(jLabel_profileIcon);
        jPanel.add(jLabel_userName);
        jPanel.add(jLabel_userEmail);
        jPanel.add(jLabel_firstName);

        add(jPanel);
    }

    @Override
    public void updateModel(Object o, int position) {

    }

    @Override
    public void addModel(Object o) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
