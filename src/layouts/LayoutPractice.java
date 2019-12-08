package layouts;

import javax.swing.*;
import java.awt.*;

public class LayoutPractice extends JPanel {

    private JFrame jFrame;

    public static void main(String[] args) {
        new LayoutPractice();
    }

    public LayoutPractice() {

        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = getPanel(new Dimension(500, 500), new FlowLayout(FlowLayout.LEFT), Color.blue);

        JPanel imagePanel = getPanel(new Dimension(52, 52), new BorderLayout(), Color.green);
        imagePanel.add(getImageLabelIcon("src/iconUser.png"), BorderLayout.CENTER);

        JPanel contentPanel = getPanel(new Dimension(200, 52), new GridLayout(0,1), Color.orange);
        contentPanel.add(getLabel("Muhammad Awais Rashid"));
        contentPanel.add(getLabel("awais2075@gmail.com"));
        contentPanel.add(getLabel("pending"));

        jPanel.add(imagePanel);
        jPanel.add(contentPanel);
        jFrame.setSize(new Dimension((int) jPanel.getPreferredSize().getWidth(), (int) jPanel.getPreferredSize().getHeight()));
        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
    }

    private JLabel getLabel(String labelText) {
        JLabel jLabel = new JLabel(labelText);
        jLabel.setForeground(Color.white);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        return jLabel;
    }

    private JLabel getImageLabelIcon(String imageUrl) {
        JLabel imageLabelIcon = new JLabel();
        imageLabelIcon.setHorizontalAlignment(JLabel.CENTER);
        imageLabelIcon.setVerticalAlignment(JLabel.CENTER);
        imageLabelIcon.setIcon(new ImageIcon(imageUrl));

        return imageLabelIcon;
    }

    private JPanel getPanel(Dimension dimension, LayoutManager layoutManager, Color color) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(layoutManager);
        jPanel.setPreferredSize(dimension);
        jPanel.setBackground(color);
        return jPanel;

    }
}
