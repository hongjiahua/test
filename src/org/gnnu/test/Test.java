package org.gnnu.test;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import org.omg.SendingContext.RunTime;

import java.util.*;
import java.util.concurrent.*;

public class Test {
    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for (Integer i = 1; i <= 1000000; i++) {
            list.add(i.toString());

        }
        System.out.println("list 大小" + list.size());

        int count = (list.size()) / 100 + 1;

        List<String> test = new ArrayList<>();
        List<List<String>> lists=new ArrayList<>();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<String> tempList = null;
                for (int i = 0; i < count - 1; i++) {
                    tempList = list.subList(i * 100, ((i + 1) * 100));
                    lists.add(tempList);
                }
                if ((count - 1) * 100 != list.size()) {
                    tempList = list.subList((count - 1) * 100, list.size());
                    lists.add(tempList);
                }

            }

            private void doExecute(List<String> tempList) {
                for (int j = 0; j < tempList.size(); j++) {
                    if (tempList.get(j) != null) {
                        test.add(tempList.get(j));
                    }
                    System.out.print(Thread.currentThread().getName()+"\t");
                    System.out.println(tempList.get(j));
                }
            }
        });
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                int flag=0;
                break;
            }
        }
    }


}
