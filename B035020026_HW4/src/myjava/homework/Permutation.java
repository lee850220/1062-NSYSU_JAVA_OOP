package myjava.homework;

import java.awt.*;
import java.util.ArrayList;

// Generalization 泛化關係(繼承)
public class Permutation extends fourStarGame {
    public void checkOfWin() {
        boolean wins = true;
        ArrayList<Integer> user = getUserNums();
        ArrayList<Integer> win = getWinNums();
        for (int i = 0; i < win.size(); i++) {
            if (user.get(i) != win.get(i)) {
                wins = false;
                break;
            }
        }
        if (wins) System.out.println("**You win!");
        else System.out.println("**You lose!");
    }
}
