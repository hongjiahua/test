package org.gnnu.dailyTest;

public class PrintB extends Thread {
    private Print p;
    public PrintB(Print p) {
        this.p = p;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            p.printB();
        }
    }
}
