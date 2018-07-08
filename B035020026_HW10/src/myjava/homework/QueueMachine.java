package myjava.homework;

import java.util.LinkedList;

public class QueueMachine {
    private LinkedList<Transaction> queue = new LinkedList<Transaction>();

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Transaction get() {
        return queue.removeFirst();
    }

    public void add(Transaction newTran) {
        queue.addLast(newTran);
    }
}
