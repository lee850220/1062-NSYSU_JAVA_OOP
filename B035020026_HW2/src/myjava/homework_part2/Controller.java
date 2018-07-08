package myjava.homework_part2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    public static void main(String args[]) throws InputMismatchException {
        Scanner scan = null;
        ItemList list = new ItemList();
        while(true) {
            try {
                int input;
                scan = new Scanner(System.in);
                String name;

                System.out.println("type 0: exit program");
                System.out.println("type 1: add item to list");
                System.out.println("type 2: remove item from list");
                System.out.println("type 3: show the list");
                input = scan.nextInt();
                scan.nextLine();

                switch(input) {
                    case 0:
                        return;
                    case 1:
                        System.out.print("Add item name :");
                        name = scan.nextLine();
                        list.addItem(name);
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("remove item name :");
                        name = scan.nextLine();
                        list.remove(name);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("show the list");
                        list.printList();
                        break;
                    default:
                        System.out.println("Input error : incorrect option\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error : ONLY Integers.\n");
            }
        }
    }
}
