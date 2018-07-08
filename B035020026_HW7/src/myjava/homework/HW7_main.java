package myjava.homework;

import java.io.IOException;
import java.util.*;

public class HW7_main {

    public static void main(String[] args) {
        int a, b;
        String id;
        readCSV r = new readCSV();
        Scanner scan = new Scanner(System.in);
        try {
            r.readfile(); // file is in the project root directory
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        while (true) {
            String input;
            System.out.println("Which data of Market you want Graphic ? (1. Products 2. ID_Sex 3. ID purchase history)");
            input = scan.nextLine();
            if (input.length() > 1 || input.charAt(0) < '1' || input.charAt(0) > '3') {
                System.out.println("wrong input.\n");
            } else {
                a = Integer.parseInt(input);
                break;
            }
        }

        switch (a) {
            case 1: case 2:
                while (true) {
                    String input;
                    System.out.println("Which kind of Graph ? 1.Bar Chart 2.Pie Chart(3D)");
                    input = scan.nextLine();
                    if (input.length() > 1 || input.charAt(0) < '1' || input.charAt(0) > '2') {
                        System.out.println("wrong input.\n");
                    } else {
                        b = Integer.parseInt(input);
                        break;
                    }
                }
                r.printRes(a, b);
                break;
            case 3:
                System.out.println("Input ID :");
                id = scan.next();
                r.printRes(id);
                break;
        }
    }
}
