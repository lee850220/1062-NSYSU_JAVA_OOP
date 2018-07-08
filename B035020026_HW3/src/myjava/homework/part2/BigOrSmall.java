package myjava.homework.part2;

import java.util.Scanner;

public class BigOrSmall {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        Race race;
        while (true) {
            System.out.println("Which game you want? (You can input big or small to play, or input exit to quit.");
            input = scan.next();
            if (input.equals("exit")) {
                System.out.println("Game Over!!");
                return;
            } else if (input.equals("big")  || input.equals("small")) {
                if (input.equals("big")) race = new Race(RaceKind.BIG);
                else race = new Race(RaceKind.SMALL);
                race.start();
            } else {
                System.out.println("You have error input. The game is failed!");
            }
        }
    }
}
