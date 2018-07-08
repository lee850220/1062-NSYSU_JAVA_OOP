package myjava.homework;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args)  {
        String opt;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Choose your Four Star Game type : (1:Combination 2:Permutation 3: Exit)");
            opt = scan.nextLine();
            if (opt.length() > 1 || opt.charAt(0) < '0' || opt.charAt(0) > '9') System.out.println("Wrong input, try again.");
            else {
                ArrayList<Integer> win;
                switch (Integer.parseInt(opt)) {
                    case 1:
                        Combination comb = new Combination();
                        comb.generateWinNums();
                        win = comb.getWinNums();
                        System.out.print("Win numbers : ");
                        for (int i = 0; i < win.size(); i++) {
                            System.out.print(win.get(i) + " ");
                        }
                        System.out.print("\nInput four user numbers : ");
                        comb.generateUserNums();
                        if (comb.getUserNums().size() != 4) System.out.println("Wrong input, try again.");
                        else comb.checkOfWin();
                        break;
                    case 2:
                        Permutation perm = new Permutation();
                        perm.generateWinNums();
                        win = perm.getWinNums();
                        System.out.print("Win numbers : ");
                        for (int i = 0; i < win.size(); i++) {
                            System.out.print(win.get(i) + " ");
                        }
                        System.out.print("\nInput four user numbers : ");
                        perm.generateUserNums();
                        if (perm.getUserNums().size() != 4) System.out.println("Wrong input, try again.");
                        else perm.checkOfWin();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
    }
}
