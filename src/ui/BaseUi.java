package ui;

import javax.swing.*;

public abstract class BaseUi extends JPanel {
    abstract void initUi();

    abstract void addListeners();

    abstract void addWidgetsToPanel();

    //template method
    public final void init() {
        initUi();

        addListeners();

        addWidgetsToPanel();
    }
}
