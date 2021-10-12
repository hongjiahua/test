package org.gnnu.dailyTest;

public class PrintC extends Thread {
    private Print p;
    public PrintC(Print p) {
        this.p = p;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            p.printC();
        }
    }
}
