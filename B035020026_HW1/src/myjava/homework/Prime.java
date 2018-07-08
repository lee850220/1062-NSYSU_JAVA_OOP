package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Prime {
    /* Check whether it's prime number */
    public static boolean isPrime(int num) {
        boolean ret;
        if (num == 2 || num == 3) {
            ret = true;
        } else {
            int i;
            int margin = (int) Math.floor(Math.sqrt(num));
            if (margin < 2) margin = 2;

            /* Check from 2 ~ sqrt(num-1) */
            for (i = 2; i <= margin; i++) {
                if (num % i == 0) break;
            }
            if (i == margin + 1) ret = true;
            else ret = false;
        }
        return ret;
    }

    public static void Find_Primes(int num) {
        System.out.println("Show the prime numbers (2 ~ " + num + ")");
        for (int i = 2, cnt = 0; i <= num; i++) {
            if (isPrime(i)) {
                if (cnt == 10) {
                    System.out.print('\n');
                    cnt = 0;
                }
                System.out.print(i);
                System.out.print('\t');
                cnt++;
            }
        }
        System.out.print('\n');
    }

    public static void main(String[] args) throws InputMismatchException {
        int option, num, i, count = 0;
        Scanner scan = null;

        while (true) {
            try {
                /* Get standard input object. */
                scan = new Scanner(System.in);
                /* Print message. */
                System.out.println("1. Check whether it's prime number\n" + "2. Find prime number(2~N)\n" + "3. Leave");
                /* Input an integer. */
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        /* Check whether it's prime number */
                        System.out.println("Input the number:");
                        num = scan.nextInt();
                        if (num < 2) System.out.println("Input error : N must equal greater than 2");
                        else {
                            if (isPrime(num)) System.out.println(num + " is a prime");
                            else System.out.println(num + " is not a prime");
                        }
                        break;
                    case 2:
                        /* Find prime number(2~N) */
                        System.out.println("Input the number:");
                        num = scan.nextInt();
                        if (num < 2) System.out.println("Input error : N must equal greater than 2");
                        else Find_Primes(num);
                        break;
                    case 3:
                        /* End the process */
                        System.out.println("Bye!!!");
                        return;
                    default:
                        System.out.println("Input error : incorrect option");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error : ONLY Integers.");
            }
        }
    }
}