package customList;

import javax.swing.*;
import java.awt.*;

public class BookRenderer extends JPanel implements ListCellRenderer<Book> {

    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();

    public BookRenderer() {
        setLayout(new BorderLayout(5, 5));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lbName);
        panelText.add(lbAuthor);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list,
                                                  Book book, int index, boolean isSelected, boolean cellHasFocus) {

        String imagePath = "src/"+book.getIconName() + ".png";
        lbIcon.setIcon(new ImageIcon(imagePath));
        lbName.setText(book.getName());
        lbAuthor.setText(book.getAuthor());
        lbAuthor.setForeground(Color.blue);

        // set Opaque to change background color of JLabel
        lbName.setOpaque(true);
        lbAuthor.setOpaque(true);
        lbIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            lbName.setBackground(list.getSelectionBackground());
            lbAuthor.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbName.setBackground(list.getBackground());
            lbAuthor.setBackground(list.getBackground());
            lbIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }


}