package _interface;


import javax.swing.*;

public interface AddListener<Model> {

    void onAdd(Model model);

    void onCancel(JFrame jFrame);
}
