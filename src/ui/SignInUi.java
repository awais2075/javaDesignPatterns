package ui;

import _interface.SignInListener;
import javafx.scene.control.DatePicker;
import model.User;
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
import java.util.Properties;

public class SignInUi extends BaseUi implements ActionListener, KeyListener {

    private JLabel jLabel_email, jLabel_password, jLabel_status;
    private JTextField jTextField_email, jTextField_password;
    private JButton jButton_signIn, jButton_signUp;

    private SignInListener listener;

    public SignInUi(SignInListener listener) {
        this.listener = listener;
    }

    @Override
    void initUi() {
        jLabel_email = new JLabel("Email :           ");
        jLabel_password = new JLabel("Password :  ");
        jTextField_email = new JTextField(15);
        jTextField_password = new JPasswordField(15);

        jLabel_status = new JLabel("Enter Your Email and Password");
        jButton_signIn = new JButton("SignIn");
        jButton_signUp = new JButton("SignUp");

        jButton_signIn.setEnabled(false);


        jButton_signIn.setName("jButton_signIn");
        jButton_signUp.setName("jButton_signUp");
    }

    @Override
    void addListeners() {
        jTextField_email.addKeyListener(this);
        jTextField_password.addKeyListener(this);
        jButton_signIn.addActionListener(this);
        jButton_signUp.addActionListener(this);
    }

    @Override
    void addWidgetsToPanel() {
        add(jLabel_email);
        add(jTextField_email);

        add(jLabel_password);
        add(jTextField_password);

        add(jLabel_status);
        add(jButton_signIn);
        add(jButton_signUp);

    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (((JComponent) actionEvent.getSource()).getName()) {
            case "jButton_signIn":
                User.getInstance().setEmail(jTextField_email.getText());
                User.getInstance().setPassword(jTextField_password.getText());

                jTextField_email.setText("");
                jTextField_password.setText("");

                listener.onSignIn();
                break;
            case "jButton_signUp":
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
        jButton_signIn.setEnabled((jTextField_email.getText().trim().matches(Utils.EMAIL_PATTERN) && jTextField_password.getText().length() > 6));

    }


}
