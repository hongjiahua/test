package org.gnnu.producerAndCustomer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private String brand;
    private String name;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition customerCondition = lock.newCondition();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(String brand, String name) {
        lock.lock();
        try {
            if (flag == true) {
                try {
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(200);
            this.setName(name);
            this.setBrand(brand);
            System.out.println("生产者生产了" + brand + name);
            flag = true;
            customerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getPruduct() {
        lock.lock();
        try {
            if (!flag) {
                try {
                    customerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者购买了" + brand + name);
            Thread.sleep(200);
            flag = false;
            producerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

