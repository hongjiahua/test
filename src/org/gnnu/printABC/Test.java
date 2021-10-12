package org.gnnu.printABC;

public class Test {
    public static void main(String[] args) {
        Print print=new Print();
        PrintA printA=new PrintA(print);
        PrintB printb=new PrintB(print);
        PrintC printC=new PrintC(print);
        printA.start();
        printb.start();
        printC.start();
    }
}
