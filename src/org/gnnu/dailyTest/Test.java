package org.gnnu.dailyTest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test {

    public static void main(String[] args) {
       /* MyThread thread=new MyThread();
        ExecutorService service= new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors(),30L,TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            service.execute(thread);
        }
        service.shutdown();
        while(true){
            if(n)
        }*/
        String[]arr={
                "lixiao1",
                "zhangyuying",
                "wangjing02",
                "lichao1",
                "qinguang",
                "chenjilei",
                "yaolei1",
                "duanyijie",
                "chengxiangbin",
                "fujie1",
                "liuhaoxin",
                "gaochi",
                "jiangtao3",
                "zhenghui",
                "liuhaiyang"

        };
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toUpperCase());
        }

    }

}
class MyThread implements Runnable{
    static int i=10000;
    static int []arr=new int[1000000];
    static AtomicBoolean flag=new AtomicBoolean(true);
    @Override
    public void run() {
    if(flag.compareAndSet(true,false)){
        while (i>0) {
            System.out.println(Thread.currentThread().getName() +"\t"+ i--);
            flag.set(true);
        }
    }else{
        if(i>0) {
            run();
        }
    }

    }
}