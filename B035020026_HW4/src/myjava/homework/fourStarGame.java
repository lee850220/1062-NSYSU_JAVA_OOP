package myjava.homework;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class fourStarGame {
    private ArrayList<Integer> userNums; // store 4 user input nums
    private ArrayList<Integer> winNums; // store 4 lucky nums

    public fourStarGame() {
        userNums = new ArrayList<Integer>();
        winNums = new ArrayList<Integer>();
    }

    // store nums from input
    public void generateUserNums() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.length() == 7) {
            for (int i = 0; i < 4; i++) {
                char t = input.charAt(i*2);
                if (t < '0' || t > '9') break;
                else userNums.add(Integer.parseInt(String.valueOf(input.charAt(i*2))));
            }
        }
    }

    public void generateWinNums() {
        for (int i = 0, newNum; i < 4; i++) {
            boolean fin = true;
            // check dup nums
            do {
                int j;
                newNum = (int) (Math.random() * 10);
                for (j = 0; j < winNums.size(); j++) {
                    if (newNum == winNums.get(j)) break;
                }
                if (j == winNums.size()) fin = false;
            } while (fin);
            winNums.add(newNum);
        }
    }

    public ArrayList<Integer> getUserNums() {
        return userNums;
    }

    public ArrayList<Integer> getWinNums() {
        return winNums;
    }
}
