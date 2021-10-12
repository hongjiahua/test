package org.gnnu.producerAndCustomer;

public class Customer extends Thread {
    private Product p;

    public Customer(Product p) {
        this.p = p;
    }

    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            p.getPruduct();
        }
    }
}
