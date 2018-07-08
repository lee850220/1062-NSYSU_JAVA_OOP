package myjava.homework_part2;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<String> id;

    ItemList() {
        id = new ArrayList<String>();
    }

    public void addItem(String str) {
        id.add(str);
    }

    public void remove(String str) {
        id.remove(str);
    }

    public void printList() {
        for (int i = 0; i < id.size(); i++) {
            System.out.println((i+1) + " : " + id.get(i));
        }
        System.out.println();
    }
}
