package com.threading;

public class ThreadLocalExample {
    public static void main(String[] args) {
        Long firstUserId = 12345l;
        Long secondUserId = 98765l;
        ThreadLocal<Long> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Runnable runnableFirst = () -> {
            System.out.println("Inside the run method of the 1st thread.");
            System.out.println("Log setting the user id for the first thread inside the run method.");
            threadLocal.set(firstUserId);
            //Log after setting you can perform any operations......
            System.out.println("Log get the userId which is set to the firstThread:" + threadLocal.get());
            System.out.println("After the values are set we can remove the thread object from ThreadLocal as it is good practice to avoid memory leaks.");
            threadLocal.remove();
            System.out.println("After remove if you will make a get call it will return null....");
            System.out.println(threadLocal.get());
        };
        Runnable runnableSecond = () -> {
            System.out.println("Inside the run method of the 2nd thread.");
            System.out.println("Log setting the user id for the second thread inside the run method.");
            threadLocal.set(secondUserId);
            //Log after setting you can perform any operations......
            System.out.println("Log get the userId which is set to the secondThread:" + threadLocal.get());
            System.out.println("After the values are set we can remove the thread object from ThreadLocal as it is good practice to avoid memory leaks.");
            threadLocal.remove();
            System.out.println("After remove if you will make a get call it will return null....");
            System.out.println(threadLocal.get());
        };

        Runnable runnableParent = () -> {
            inheritableThreadLocal.set("hello");
            threadLocal.set(10000l);
            Runnable runnableChild = () -> {
                System.out.println("Accessing value of thread from the child thread: " + inheritableThreadLocal.get());
                //Below line will return null as no local copy of the child thread is available and maintained.
                System.out.println("Get value inside child which was set by parent using threadLocal variable: " + threadLocal.get());
            };
            System.out.println("Inside the parent thread: ");
            System.out.println("Log value of inheritableThreadLocal variable from the parent method call: " + inheritableThreadLocal.get());
            System.out.println("Get value in parent which was set by parent using threadLocal variable: " + threadLocal.get());
            Thread childThread = new Thread(runnableChild);
            childThread.start();
        };

        Thread requestThreadFirst = new Thread(runnableFirst);
        Thread requestThreadSecond = new Thread(runnableSecond);
        Thread parentThread = new Thread(runnableParent);
        requestThreadFirst.start();
        requestThreadSecond.start();
        parentThread.start();
    }
}
