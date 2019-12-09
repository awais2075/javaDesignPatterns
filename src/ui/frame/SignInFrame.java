package ui.frame;

import _interface.SignInListener;
import database.DatabaseHandler;
import ui.panel.BasePanel;
import ui.panel.SignInPanel;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInFrame implements SignInListener {

    private JFrame signInFrame;

    public SignInFrame() {
        signInFrame = Utils.getFrame("SignInFrame Screen", 250, 300,JFrame.EXIT_ON_CLOSE);


        BasePanel signInPanel = new SignInPanel(this);
        signInPanel.init();

        signInPanel.setLayout(new FlowLayout());
        signInFrame.getContentPane().add(signInPanel);
        signInFrame.setResizable(false);
        signInFrame.setLocationRelativeTo(null);
        signInFrame.setVisible(true);
    }

    private boolean isUserExists(String userEmail, String password) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, userEmail);
            statement.setString(2, password);
            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                User.getInstance().setUserId(userId);
                User.getInstance().setUserName(userName);
                User.getInstance().setFirstName(firstName);
                User.getInstance().setLastName(lastName);
                User.getInstance().setEmail(email);
                User.getInstance().setStatus(status);

                return true;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Strategy Pattern
     */

    @Override
    public void onSignIn() {
        if (isUserExists(User.getInstance().getEmail(), User.getInstance().getPassword())) {
            switch (User.getInstance().getStatus()) {
                case "pending":
                    JOptionPane.showMessageDialog(signInFrame,
                            "Your Registration is in Process",
                            "Pending",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "registered":
                    signInFrame.setVisible(false);
                    new DashboardFrame();
/*                    JOptionPane.showMessageDialog(signInFrame,
                            "Your Registration is Successful",
                            "Registered",
                            JOptionPane.PLAIN_MESSAGE);*/
                    break;
                case "unregistered":
                    JOptionPane.showMessageDialog(signInFrame,
                            "Your Registration is Suspended",
                            "Unregistered",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case "admin":
                    signInFrame.dispose();
                    new DashboardFrame();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(signInFrame,
                    "Either Credentials are Invalid or User is not Registered",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onSignUp() {
        signInFrame.dispose();
        new SignUpFrame();
    }

    @Override
    public void onSignOut() {

    }
}
