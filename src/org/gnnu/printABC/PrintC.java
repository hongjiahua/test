package org.gnnu.printABC;

public class PrintC extends Thread {
    private Print print;

    public PrintC(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            print.printC();
        }
    }
}
