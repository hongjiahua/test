package org.gnnu.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test4 implements Runnable {
    private static int ticketNUm = 10000;
    private static int count = 0;
    private AtomicBoolean exists = new AtomicBoolean(true);

    public static void main(String[] args) {
        Test4 test4 = new Test4();
        /* ExecutorService pool = Executors.newFixedThreadPool(((Double) Math.floor(Runtime.getRuntime().availableProcessors() * 0.75)).intValue());*/
        int count = ticketNUm;
        /*for (int i = 0; i < count; i++) {
            pool.execute(test4);
        }*/
        Thread t1 = new Thread(test4, "t1");
        Thread t2 = new Thread(test4, "t2");
        Thread t3 = new Thread(test4, "t3");
        Thread t4 = new Thread(test4, "t4");
        Thread t5 = new Thread(test4, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }

    @Override
    public void run() {
            if (exists.compareAndSet(true, false)) {
                if (ticketNUm > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出第" + ticketNUm-- + "张票");
                    if (ticketNUm % 100 == 0) {
                        System.gc();
                    }
                }
                exists.set(true);
                count = 0;
            } else {
                System.out.println("aaaa");
            }
    }
}
