package org.gnnu.printABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {
    private Lock lock = new ReentrantLock();
    private int who = 1;
    private
    Condition aCondition = lock.newCondition();
    Condition bCondition = lock.newCondition();
    Condition cCondition = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            if (who != 1) {
                aCondition.await();
            }
            Thread.sleep(1000);
            System.out.println("A");
            who=2;
            bCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            if (who != 2) {
                bCondition.await();
            }
            Thread.sleep(1000);
            System.out.println("B");
            who=3;
            cCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void printC() {
        lock.lock();
        try {
            if (who != 3) {
                cCondition.await();
            }
            Thread.sleep(1000);
            System.out.println("C");
            who=1;
            aCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
