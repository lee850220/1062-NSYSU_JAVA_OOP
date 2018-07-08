package myjava.homework;

public class Main {

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main( String args[] ) {
        // Check input
        if (args.length < 1) {
            System.out.println("[Error] : too few arguments.");
            return;
        }

        int buf_size = CheckInt(args[0]);
        if (buf_size == -1) {
            System.out.println("wrong input.");
            return;
        }

        new BoundBuffer(buf_size);
        System.out.printf("Initial State (buffer cells occupied: %d)\n", BoundBuffer.getLength());
        BoundBuffer.getStat();

        new Thread(new Producer(1,1, 10)).start();
        new Thread(new Producer(2,11, 20)).start();
        new Thread(new Consumer(1,1, 10)).start();
        new Thread(new Consumer(2,11, 20)).start();
    }
}
