package ui.panel;

import _interface.SignInListener;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignUpPanel extends BasePanel implements ActionListener, KeyListener {

    private JLabel jLabel_username, jLabel_firstName, jLabel_lastName, jLabel_email, jLabel_password, jLabel_address, jLabel_phoneNumber, jLabel_designation;
    private JTextField jTextField_username, jTextField_firstName, jTextField_lastName, jTextField_email, jTextField_password, jTextField_address, jTextField_phoneNumber, jTextField_designation;
    private JButton jButton_signUp, jButton_signIn;

    private SignInListener listener;

    public SignUpPanel(SignInListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (((JComponent) actionEvent.getSource()).getName()) {
            case "jButton_signIn":
                listener.onSignIn();
                break;
            case "jButton_signUp":
                String userName = jTextField_username.getText();
                String firstName = jTextField_firstName.getText();
                String lastName = jTextField_lastName.getText();
                String email = jTextField_email.getText();
                String address = jTextField_address.getText();
                String phoneNumber = jTextField_phoneNumber.getText();
                String designation = jTextField_designation.getText();
                String password = jTextField_password.getText();

                User.getInstance().setUserName(userName);
                User.getInstance().setFirstName(firstName);
                User.getInstance().setLastName(lastName);
                User.getInstance().setEmail(email);
                User.getInstance().setAddress(address);
                User.getInstance().setPhoneNumber(phoneNumber);
                User.getInstance().setDesignation(designation);
                User.getInstance().setPassword(password);
                listener.onSignUp();
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
        jButton_signUp.setEnabled((!jTextField_username.getText().trim().isEmpty() && !jTextField_firstName.getText().trim().isEmpty() && !jTextField_lastName.getText().trim().isEmpty() &&
                !jTextField_address.getText().trim().isEmpty() && !jTextField_phoneNumber.getText().trim().isEmpty() && !jTextField_designation.getText().trim().isEmpty()
                && jTextField_email.getText().trim().matches(Utils.EMAIL_PATTERN) && jTextField_password.getText().length() > 6));

    }

    @Override
    protected void initUi() {
        jLabel_username = new JLabel("Username : ");
        jLabel_firstName = new JLabel("First Name : ");
        jLabel_lastName = new JLabel("Last Name : ");
        jLabel_email = new JLabel("Email :           ");
        jLabel_address = new JLabel("Address :     ");
        jLabel_phoneNumber = new JLabel("Contact :      ");
        jLabel_designation = new JLabel("Designation:");
        jLabel_password = new JLabel("Password :  ");


        jTextField_username = new JTextField(15);
        jTextField_firstName = new JTextField(15);
        jTextField_lastName = new JTextField(15);
        jTextField_email = new JTextField(15);
        jTextField_address = new JTextField(15);
        jTextField_phoneNumber = new JTextField(15);
        jTextField_designation = new JTextField(15);
        jTextField_password = new JPasswordField(15);

        jTextField_username.addKeyListener(this);
        jTextField_firstName.addKeyListener(this);
        jTextField_lastName.addKeyListener(this);
        jTextField_email.addKeyListener(this);
        jTextField_address.addKeyListener(this);
        jTextField_phoneNumber.addKeyListener(this);
        jTextField_designation.addKeyListener(this);
        jTextField_password.addKeyListener(this);

        jButton_signIn = new JButton("SignIn");
        jButton_signUp = new JButton("SignUp");

        jButton_signIn.setName("jButton_signIn");
        jButton_signUp.setName("jButton_signUp");

        jButton_signUp.setEnabled(false);
    }

    @Override
    void addListeners() {
        jButton_signIn.addActionListener(this);
        jButton_signUp.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {

        add(jLabel_username);
        add(jTextField_username);

        add(jLabel_firstName);
        add(jTextField_firstName);

        add(jLabel_lastName);
        add(jTextField_lastName);

        add(jLabel_email);
        add(jTextField_email);

        add(jLabel_address);
        add(jTextField_address);

        add(jLabel_phoneNumber);
        add(jTextField_phoneNumber);

        add(jLabel_designation);
        add(jTextField_designation);

        add(jLabel_password);
        add(jTextField_password);

        add(jButton_signUp);
        add(jButton_signIn);
    }

    @Override
    public void updateModel(Object o, int position) {

    }

    @Override
    public void addModel(Object o) {

    }
}
