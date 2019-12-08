package util;

import javax.swing.*;

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

    public static JFrame getFrame(String title, int width, int height) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle(title);
        jFrame.setSize(width, height);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }
}
