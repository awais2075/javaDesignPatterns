package _interface;

import model.User;

public interface UpdateListener<Model> {
    void onUpdate(Model model);
}
