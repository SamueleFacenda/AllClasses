package all.comparable;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Piatto> menu;
    public Menu(){
        menu=new ArrayList<>();
    }
    public void push(Piatto in){
        if(!menu.contains(in)){
            menu.add(in);
            menu.sort(new ComparePiatti());
        }
    }
    public void pop(Piatto in){
        menu.remove(in);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                '}';
    }
}
