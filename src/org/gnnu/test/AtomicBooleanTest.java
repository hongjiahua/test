package org.gnnu.test;

import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Created by wxwall on 2017/6/2.
 */
public class AtomicBooleanTest implements Runnable{
    public static AtomicBoolean exits = new AtomicBoolean(true);
    public static void main(String[] args) {
        AtomicBooleanTest abd = new AtomicBooleanTest();
        Thread t1 = new Thread(abd);
        Thread t2 = new Thread(abd);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("begin run");
        System.out.println("real " + exits.get());
        if(exits.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName() + "  " + exits.get() );
            exits.set(true);
        }else{
            run();
        }
    }
}