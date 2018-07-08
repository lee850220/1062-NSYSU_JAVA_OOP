package myjava.homework;

import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {

        SudokuPuzzle puzzle = new SudokuPuzzle();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Sudoku.");

        while (true) {
            String input;
            int opt, row, col, value;
            System.out.println(puzzle.toString());
            while (true) {
                if (puzzle.isFull() && puzzle.checkPuzzle()) {
                    System.out.println("Finished!");
                    return;
                }
                System.out.println("Select the options. (1)Fill a number (2)Show allowed number (3)Reset game (4)Exit");
                input = scan.nextLine();
                if (input.length() > 1 || input.charAt(0) < '1' || input.charAt(0) > '4') {
                    System.out.println("Wrong input.\n");
                } else break;
            }

            opt = (input.charAt(0) - '0');
            switch(opt) {
                case 1:
                    System.out.println("Input \"Row\" \"Col\" \"Value\" .");
                    input = scan.nextLine();
                    if (input.length() < 5 || input.charAt(1) != ' ' || input.charAt(3) != ' ' ||
                        input.charAt(0) < '1' || input.charAt(0) > '9' ||
                        input.charAt(2) < '1' || input.charAt(2) > '9' ||
                        input.charAt(4) < '1' || input.charAt(4) > '9') {
                        System.out.println("Wrong input.\n");
                        break;
                    }
                    row = (int) input.charAt(0) - '0';
                    col = (int) input.charAt(2) - '0';
                    value = (int) input.charAt(4) - '0';
                    puzzle.addGuess(row - 1, col - 1, value);
                    break;
                case 2:
                    System.out.println("Input \"Row\" \"Col\" .");
                    input = scan.nextLine();
                    if (input.length() < 3 || input.charAt(1) != ' ' ||
                        input.charAt(0) < '1' || input.charAt(0) > '9' ||
                        input.charAt(2) < '1' || input.charAt(2) > '9') {
                        System.out.println("Wrong input.\n");
                        break;
                    }
                    row = (int) input.charAt(0) - '0';
                    col = (int) input.charAt(2) - '0';
                    boolean [] allow = puzzle.getAllowedValue(row - 1, col - 1);
                    System.out.print("(" + row + "," + col + ") can fill with : ");
                    for (int i = 0; i < 9; i++) {
                        if (allow[i]) System.out.print(i+1 + " ");
                    }
                    System.out.println();
                    break;
                case 3:
                    puzzle = new SudokuPuzzle();
                    System.out.println("Success to reset.");
                    break;
                case 4:
                    return;
                default:
                    //System.out.println("SSS");
            }
        }
    }
}
