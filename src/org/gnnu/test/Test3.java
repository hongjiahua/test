package org.gnnu.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test3 {
    private AtomicInteger count = new AtomicInteger(0);
    static List<Integer> resList=new ArrayList<>(10000000);
    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList<Integer>();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        Test3 test3 = new Test3();
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(10));
        test3.withThread(list, 5, executorService);


    }

    public void withThread(List list, int maxThread, ExecutorService executorService) throws InterruptedException {
        if (list == null || list.size() == 0) {
            throw new RuntimeException("无数据");
        }
        int mod = list.size() / maxThread; //模
        int res = list.size() % maxThread; //余数

        LocalDateTime start = LocalDateTime.now();
        for (int j = 0; j < maxThread; j++) {
            int finalJ = j;
            if (j < maxThread - 1) {
                executorService.execute(() -> {
                    for (int i = mod * finalJ; i < mod * (finalJ + 1);
                         i++) {
                        System.out.println("Thread" + (finalJ + 1) + "\t" + list.get(i));
                        resList.add(i);
                        count.incrementAndGet();
                    }
                });
            } else {
                executorService.execute(() -> {
                    for (int i = mod * (maxThread - 1); i < (mod * maxThread) + res;
                         i++) {
                        System.out.println("Thread" + maxThread + "\t" + list.get(i));
                        resList.add(i);
                        count.incrementAndGet();
                    }
                });
            }
        }

        executorService.shutdown();
        if (executorService.awaitTermination(30, TimeUnit.MINUTES)) {
            System.out.println("start" + start);
            System.out.println("end" + LocalDateTime.now());
            System.out.println("总次数" + count);
        }
    }
}
