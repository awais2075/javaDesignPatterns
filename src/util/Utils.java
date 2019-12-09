package util;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {

    public static void printInfo(String info) {
        System.out.println(info);
    }

    public static void printError(String error) {
        System.err.println(error);
    }

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static JFrame getFrame(String title, int width, int height, int closeOperationId) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle(title);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(closeOperationId);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    public static JButton getButton(String buttonText, String buttonName) {
        JButton jButton = new JButton(buttonText);
        jButton.setName(buttonName);
        return jButton;
    }

    public static JDatePanelImpl getDatePanel() {
        UtilDateModel model = new UtilDateModel();
        model.setDate(2019, 8, 24);
        model.setSelected(true);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        return datePanel;
    }

    public static Date getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));
        return date;
    }

    public static void showDialog(Component component, String title, String message, int messageType) {
        JOptionPane.showMessageDialog(component, message, title, messageType);
    }
}
