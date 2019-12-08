package ui;

import _interface.SignInListener;
import _interface.UpdateListener;
import database.DatabaseHandler;
import model.User;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dashboard implements SignInListener, UpdateListener<User> {

    private JFrame dashboardFrame;
    private BaseUi dashboardPanel;

    public Dashboard() {

        dashboardFrame = Utils.getFrame(User.getInstance().getEmail(), 500, 600);
        if (!User.getInstance().getStatus().equals("admin")) {
            dashboardPanel = new UserDashboardUi(this, this);
        } else {
            dashboardPanel = new AdminDashboardUi_(this, this, getUsers());
            dashboardPanel = new AdminDashboardUi_(this, this, getUsers());
//            dashboardPanel = new AdminDashboardUi_();
        }
        dashboardPanel.init();
        dashboardPanel.setLayout(new FlowLayout());
        dashboardFrame.getContentPane().add((dashboardPanel));
//        dashboardFrame.setResizable(false);
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setVisible(true);
    }

    private List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "SELECT * FROM users WHERE email != ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "admin@mail.com");
            ResultSet resultSet = DatabaseHandler.getInstance().viewData(statement);

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                userList.add(new User(userId, userName, firstName, lastName, email, status));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return userList;
    }

    @Override
    public void onSignIn() {

    }

    @Override
    public void onSignUp() {

    }

    @Override
    public void onSignOut() {
        dashboardFrame.dispose();
        new SignIn();
    }

    @Override
    public void onUpdate(User user) {
        Connection connection = DatabaseHandler.getInstance().getConnection();
        String sqlQuery = "UPDATE users SET status = ? WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, user.getStatus());
            statement.setInt(2, user.getUserId());
            int result = DatabaseHandler.getInstance().updateData(statement);
        } catch (SQLException exception) {
            Utils.printError(exception.getMessage());
        }
    }
}
