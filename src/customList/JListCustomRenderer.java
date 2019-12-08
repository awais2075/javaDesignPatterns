package customList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JListCustomRenderer extends JFrame {
    private int width = 350;
    private int height = 200;
    private JList<Book> listBook;
 
    public JListCustomRenderer() {
        // add main panel
        add(createMainPanel());
        // set display
        setTitle("JLIstCustomRenderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        panel.add(new JScrollPane(listBook = createListBooks()),
                BorderLayout.CENTER);
        return panel;
    }
 
    private JList<Book> createListBooks() {
        // create List model
        DefaultListModel<Book> model = new DefaultListModel<>();
        // add item to model
        model.addElement(new Book("C/C++ Programming", "A", "iconUser"));
        model.addElement(new Book("Java Programming", "B", "iconUser"));
        model.addElement(new Book("C# Programming", "C", "iconUser"));
        model.addElement(new Book("IOS Programming", "D", "iconUser"));
        model.addElement(new Book("Windows Phone Programming", "E", "iconUser"));
        model.addElement(new Book("Android Programming", "F", "iconUser"));
        // create JList with model
        JList<Book> list = new JList<Book>(model);
        list.setCellRenderer(new BookRenderer());
        return list;
    }
 
    public static void main(String[] args) {
        new JListCustomRenderer();
    }
}