package org.gnnu.dailyTest;

public class PrintA extends Thread {
    private Print p;

    public PrintA(Print p) {
        this.p = p;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            p.printA();
        }
    }
}
