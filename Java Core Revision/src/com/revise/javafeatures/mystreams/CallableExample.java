package com.revise.javafeatures.mystreams;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable=()->{
            return "Callable taks got executed";
        };

        FutureTask<String> futureTask=new FutureTask<>(callable);
        Thread callableThread=new Thread(futureTask);
        callableThread.start();
        System.out.println(futureTask.get());

        ExecutorService executorService = Executors
                .newFixedThreadPool(2);


        Callable<String> executorCallable=()->{
            System.out.println("Thread: "+Thread.currentThread().getName());
            Thread.sleep(5000);
            return "CallableTask executed";
        };

        Future<String> future= executorService.submit(executorCallable);

        System.out.println("Main Thread continues");

        System.out.println(future.get());

        executorService.shutdown();


    }

}
