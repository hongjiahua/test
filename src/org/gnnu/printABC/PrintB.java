package org.gnnu.printABC;

public class PrintB  extends Thread{
    private Print print;

    public PrintB(Print print) {
        this.print = print;
    }
    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            print.printB();
        }
    }
}
