package org.gnnu.producerAndCustomer;

public class Test {
    public static void main(String[] args) {
        Product product = new Product();
        Producer producer = new Producer(product);
        Customer customer = new Customer(product);
        producer.start();
        customer.start();
    }
}
