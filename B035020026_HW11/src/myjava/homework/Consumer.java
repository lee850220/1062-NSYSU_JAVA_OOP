package myjava.homework;

public class Consumer implements Runnable{
    private final int num;
    private int tot;

    Consumer (int num, int lb, int ub) {
        this.num = num;
        tot = 0;
    }

    public void run() {
        int count = 0, x;
        try {
            while (true) {
                x = BoundBuffer.take(num);
                if (x == -1) {
                    continue;
                }
                tot += x;
                if (++count == 10) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // print finish
        try {
            BoundBuffer.print(1, num, tot);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
