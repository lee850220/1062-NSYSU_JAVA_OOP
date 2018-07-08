package myjava.homework_part1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
     static class Students {
        ArrayList<StudentInformation> studentList;

        Students() {
            studentList = new ArrayList<StudentInformation>();
        }

        void add(String id, String name, int score) {
            StudentInformation new_one = new StudentInformation(id, name, score);
            studentList.add(new_one);
            System.out.println("This is student " + studentList.size() + '\n');
        }

        void show(int number) {
            if (studentList.size() < number || number < 1) {
                System.out.println("Data no found\n");
            } else {
                studentList.get(number - 1).show_data();
                System.out.println("This is student " + number + '\n');
            }
        }

        void show_all() {
            int pass = 0, fail = 0;
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println("Number : " + (i+1));
                System.out.println("Student id :" + studentList.get(i).getID());
                System.out.println("Student name :" + studentList.get(i).getName());
                if (studentList.get(i).getScore() < 60) {
                    System.out.println("Student " + studentList.get(i).getName() + " fail this project\n");
                    fail++;
                } else {
                    System.out.println("Student " + studentList.get(i).getName() + " pass this project\n");
                    pass++;
                }
            }
            System.out.println("===============");
            System.out.println("Pass : " + pass);
            System.out.println("No pass : " + fail);
            System.out.println();
        }
    }

    public static void main(String[] args) throws InputMismatchException {
        Students student = new Students();
        while (true) {
            try {
                Scanner scan = null;
                int slt;

                scan = new Scanner(System.in);
                System.out.println("Type 0: Exit Program");
                System.out.println("Type 1: add a student's data(student ID,student name and score)");
                System.out.println("Type 2: show student's data");
                System.out.println("Type 3: show all student's data");
                slt = scan.nextInt();
                scan.nextLine();

                switch (slt) {
                    case 0:
                        return;
                    case 1:
                        int score;
                        String id, name;

                        System.out.println("Add new student's data :");
                        System.out.print("student id :");
                        id = scan.nextLine();
                        System.out.print("student name :");
                        name = scan.nextLine();
                        System.out.print("Score :");
                        score = scan.nextInt();

                        student.add(id, name, score);
                        break;
                    case 2:
                        int input;

                        System.out.println("To show which student's information");
                        input = scan.nextInt();
                        student.show(input);
                        break;

                    case 3:
                        System.out.println("====Student's data====");
                        student.show_all();
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
