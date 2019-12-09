package ui.panel;

import _interface.ItemClickListener;
import decoration.UserListRenderer;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserPanel extends BasePanel<User> implements ListSelectionListener {

    private JList<User> jList = new JList<>();

    private DefaultListModel<User> listModel = new DefaultListModel<>();

    private ItemClickListener listener;
    private List<User> userList;

    public UserPanel(List userList, ItemClickListener listener) {
        this.userList = userList;
        this.listener = listener;

        init();
//        setVisible(false);

    }


    @Override
    protected void initUi() {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(5);
        setLayout(layout);

        setPreferredSize(new Dimension(300, 500));


        listModel.removeAllElements();
        listModel = new DefaultListModel<>();
        for (User user : userList) {
            listModel.addElement(user);

        }
        jList.setModel(listModel);
        jList.setCellRenderer(new UserListRenderer());

    }

    @Override
    void addListeners() {
        jList.addListSelectionListener(this);
    }

    @Override
    void addWidgetsToPanel() {

        add(new JScrollPane(jList), BorderLayout.CENTER);
    }

    @Override
    public void updateModel(User user, int position) {
        listModel.setElementAt(user, position);
    }

    @Override
    public void addModel(User user) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonId = ((JComponent) event.getSource()).getName();
        listener.onButtonClicked(buttonId);
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {

        listener.onItemClicked(jList.getSelectedValue(), jList.getSelectedIndex());
    }


}
