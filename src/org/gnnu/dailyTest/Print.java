package org.gnnu.dailyTest;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {
    private static final Lock lock = new ReentrantLock();
    AtomicBoolean exists=new AtomicBoolean(true);
    private int who = 1;
    Condition aConditon = lock.newCondition();
    Condition bConditon = lock.newCondition();
    Condition cConditon = lock.newCondition();

    public void printA() {
            lock.lock();
            try{
            if (who != 1) {
                try {
                    aConditon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            who = 2;
            bConditon.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            if (who != 2) {
                try {
                    bConditon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            who = 3;
            cConditon.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            if (who != 3) {
                try {
                    cConditon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            who = 1;
            aConditon.signal();
        } finally {
            lock.unlock();
        }
    }
}
