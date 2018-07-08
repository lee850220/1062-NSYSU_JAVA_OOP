package myjava.homework;

import java.util.concurrent.locks.*;

public class BoundBuffer {
    private static final Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition(); // Write condition
    private static final Condition notEmpty = lock.newCondition(); // Read condition
    private static int[] items;
    private static int putptr, takeptr;
    private static int count = 0; // buf items
    private static int Winit, Rinit;

    public BoundBuffer(int size) {
        items = new int[size];
        Winit = Rinit = 1;
        for (int i = 0; i < size; i++) {
            items[i] = -1;
        }
    }

    static void setPtr(int index) {
        putptr = takeptr = index;
    }

    static int getLength() {
        return count;
    }

    static void getStat() {
        // print cell info
        System.out.print("buffer cells:  ");
        for (int item : items) {
            System.out.printf("%4d  ", item);
        }
        System.out.print("\n                ");
        for (int i = 0; i < items.length; i++) {
            System.out.print("----  ");
        }
        System.out.print("\n                ");

        // print pointer
        if (takeptr == putptr) { // WR
            for (int i = 0; i < takeptr; i++) {
                System.out.print("      ");
            }
            System.out.println(" WR\n");
        } else if (takeptr < putptr) { // R ... W
            for (int i = 0; i < takeptr; i++) {
                System.out.print("      ");
            }
            System.out.print("  R   ");
            for (int i = takeptr; i < putptr - 1; i++) {
                System.out.print("      ");
            }
            System.out.println("  W\n");
        } else { // W ... R
            for (int i = 0; i < putptr; i++) {
                System.out.print("      ");
            }
            System.out.print("  W   ");
            for (int i = putptr; i < takeptr - 1; i++) {
                System.out.print("      ");
            }
            System.out.println("  R\n");
        }
    }

    static void put(int num, int x ) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) { // if full
                System.out.printf("Buffer is full. Producer%d waits.\n", num);
                notFull.await(); // interrupt writing
            }
            if (Winit == 1) {
                Winit = 0;
            } else {
                if (++putptr == items.length) putptr = 0; // circular index
            }
            items[putptr] = x;
            count++;
            System.out.printf("Producer%d writes %d (buffer cells occupied: %d)\n", num, x, getLength());
            getStat();
            notEmpty.signal(); // call reading
        } finally {
            lock.unlock();
        }
    }

    static int take(int num) throws InterruptedException {
        lock.lock();
        int x;
        try {
            while (count == 0) { // if empty
                System.out.printf("Buffer is empty. Consumer%d waits.\n", num);
                notEmpty.await();
            }
            if (Rinit == 1) {
                Rinit = 0;
            } else {
                if (++takeptr == items.length) takeptr = 0; // circular index
            }

            x = items[takeptr];
            // if get wrong item
            if (x < num*10 - 9 || x > num*10) {
                takeptr --;
                return -1;
            }
            count--;
            System.out.printf("Consumer%d reads %d (buffer cells occupied: %d)\n", num, x, getLength());
            getStat();
            notFull.signal(); // call writing
            return x;
        } finally {
            lock.unlock();
        }
    }

    static void print(int type, int num, int tot) throws InterruptedException {
        lock.lock();
        try {
            if (type == 0) {
                System.out.printf("Producer%d done producing\n", num);
                System.out.printf("Terminating Producer%d\n", num);
            } else {
                System.out.printf("Consumer%d read values totaling: %d\n", num, tot);
                System.out.printf("Terminating Consumer%d\n", num);
            }
        } finally {
             lock.unlock();
        }
    }
}
