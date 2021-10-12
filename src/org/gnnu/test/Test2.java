package org.gnnu.test;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import org.omg.SendingContext.RunTime;

import java.util.*;
import java.util.concurrent.*;

public class Test2 {
    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Task());
            Thread.sleep(2000);

        }
    }


}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

    }
}