package ui.panel;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BasePanel<Model> extends JPanel implements ActionListener {

    protected abstract void initUi();

    abstract void addListeners();

    abstract void addWidgetsToPanel();

    //template method
    public final void init() {
        initUi();

        addListeners();

        addWidgetsToPanel();
    }

    public abstract void updateModel(Model model, int position);
    public abstract void addModel(Model model);
}
