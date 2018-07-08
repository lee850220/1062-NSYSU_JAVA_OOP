package myjava.homework;

import java.util.ArrayList;

public class Combination extends fourStarGame {
    public void checkOfWin() {
        boolean wins = true;
        ArrayList<Integer> user = getUserNums();
        ArrayList<Integer> win = getWinNums();
        for (int i = 0, j; i < win.size(); i++) {
            j = 0;
            for(; j < win.size(); j++) {
                if (user.get(j) == win.get(i)) break;
            }
            if (j == 4) {
                wins = false;
                break;
            }
        }
        if (wins) System.out.println("**You win!");
        else System.out.println("**You lose!");
    }
}
