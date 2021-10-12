package org.gnnu.printABC;

public class PrintA extends Thread {
    private Print print;

    public PrintA(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            print.printA();
        }
    }
}
