package org.gnnu.test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


 class MyTest implements Runnable {
     private static int num=100;
    private static AtomicBoolean atomicInteger = new AtomicBoolean(true);
    @Override
    public void run() {
        if (atomicInteger.compareAndSet(true, false)) {
            for (int i = 0; i < 100; i++) {
                if(num>0) {
                    System.out.println(Thread.currentThread().getName() + "\t" + num--);
                    atomicInteger.set(true);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            run();
        }
    }
}
public class Test6{
    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        Thread t2 = new Thread(myTest);
        Thread t3 = new Thread(myTest);
        t2.start();
        t3.start();

    }

}
