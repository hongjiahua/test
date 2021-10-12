package org.gnnu.dailyTest;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test1 implements B {
    Byte me;
    public static void main(String args[]) {
        int i;
        Test1 a1 = new Test1();
        System.out.println(a1.a());

    }

    @Override
    public int a() {
        try{
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            return 3;
        }
    }

    @Override
    public void b() {

    }
}

interface B extends A , C{
    int value = 10;

    @Override
    int a();

    @Override
    void b();
}
interface A{
    int a();
}
interface C{
    void b();
}