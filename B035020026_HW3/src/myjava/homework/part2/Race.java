package myjava.homework.part2;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private int RULE;

    public Race(int rule) {
        this.RULE = rule;
    }

    public void start() {
        final int MAX = 51, MIN = 0;
        final ArrayList<String> suit = new ArrayList<String>(Arrays.asList("_Spade_", "_Heart_", "_Diamond_", "_Club_"));
        final String[] num = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        int me = -1, com = -1;
        String me_s, com_s;

        me = (int)(Math.random() * ((MAX - MIN + 1) + MIN)); //取MIN~MAX之間的整數
        do {
            com = (int) (Math.random() * ((MAX - MIN + 1) + MIN));
        } while (me == com);

        me_s = suit.get(me / 13); com_s = suit.get(com / 13);
        me %= 13; com %= 13;
        System.out.println("Your hand is " + me_s + num[me]);
        System.out.println("Com's hand is " + com_s + num[com]);

        if (me > com) {
            System.out.println(me_s + num[me] + " is bigger than " + com_s + num[com]);
            System.out.println("So, you win!");
        } else if (me < com) {
            System.out.println(me_s + num[me] + " is smaller than " + com_s + num[com]);
            System.out.println("So, Com win!");
        } else {
            if (suit.indexOf(me_s) < suit.indexOf(com_s)) {
                System.out.println(me_s + num[me] + " is bigger than " + com_s + num[com]);
                System.out.println("So, you win!");
            } else {
                System.out.println(me_s + num[me] + " is smaller than " + com_s + num[com]);
                System.out.println("So, Com win!");
            }
        }
    }
}
