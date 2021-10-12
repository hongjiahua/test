package org.gnnu.producerAndCustomerAutomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private String brand;
    private String name;
    private boolean flag = false;
    private AtomicBoolean exists = new AtomicBoolean(true);

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
        if (exists.compareAndSet(true, false)) {
            this.setName(name);
            this.setBrand(brand);
            System.out.println("生产者生产了" + brand + name);
            flag = true;
            exists.set(true);
        }
    }

    public void getPruduct() {
        if (exists.compareAndSet(false, true)) {
            System.out.println("消费者购买了" + brand + name);
            flag = false;
            exists.set(false);
        }
    }
}

