package _interface;

import model.Meeting;

public interface AddListener<Model> {

    void onAdd(Model model);

    void onCancel();
}
