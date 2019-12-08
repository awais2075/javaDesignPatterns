package ui;

import _interface.SignInListener;
import database.DatabaseHandler;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp implements SignInListener {

    private JFrame signUpFrame;

    public SignUp() {
        signUpFrame = Utils.getFrame("SignUp Screen", 300, 300);


        BaseUi signUpPanel = new SignUpUi(this);
        signUpPanel.init();

        signUpPanel.setLayout(new FlowLayout());
        signUpFrame.getContentPane().add(signUpPanel);
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setResizable(false);
        signUpFrame.setVisible(true);
    }

    /**
     * Strategy Pattern
     */

    @Override
    public void onSignIn() {
        signUpFrame.setVisible(false);
        new SignIn();
    }

    @Override
    public void onSignUp() {
        if (isRegistrationSuccessful()) {
            JOptionPane.showMessageDialog(signUpFrame,
                    "Your're Successfully Registered \n After admin approval you'll be able to access your portal",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(signUpFrame,
                    "Account with this email already exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isRegistrationSuccessful() {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "INSERT INTO users(user_name, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?)";
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, User.getInstance().getUserName());
            statement.setString(2, User.getInstance().getFirstName());
            statement.setString(3, User.getInstance().getLastName());
            statement.setString(4, User.getInstance().getEmail());
            statement.setString(5, User.getInstance().getPassword());
            result = DatabaseHandler.getInstance().insertData(statement);
            Utils.printInfo(result + "");
        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
        return result == 1;
    }

    @Override
    public void onSignOut() {

    }
}
