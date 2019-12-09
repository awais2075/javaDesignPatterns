package _interface;

public interface ItemClickListener<Model> {

    void onItemClicked(Model model, int position);

    void onButtonClicked(String buttonId);
}
