package myjava.homework;

import java.util.concurrent.locks.*;

public class Producer implements Runnable {
    private int num;
    private final int lowerbound;
    private final int upperbound;

    Producer (int num, int lb, int ub) {
        this.num = num;
        lowerbound = lb;
        upperbound = ub;
    }

    public void run() {
        for (int i = lowerbound; i <= upperbound; i++) {
            try {
                BoundBuffer.put(num, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // print finish
        try {
            BoundBuffer.print(0, num, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
