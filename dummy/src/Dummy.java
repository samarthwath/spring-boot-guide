public class Dummy {
    public static void main(String[] args) {
        String string = "Samarth";
        Object lock=new Object();

        Runnable runA = () -> {
            for (int index = 0; index < string.length(); index++) {
                if (index % 2 == 0) {
                    synchronized (lock) {
                        System.out.print(string.charAt(index));
                    }

                }
            }
        };

        Runnable runB = () -> {
            for (int index = 0; index < string.length(); index++) {
                if (index % 2 != 0) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(string.charAt(index));
                        lock.notify();
                    }
                }
            }
        };

        Thread threadA=new Thread(runA);
        Thread threadB=new Thread(runB);
        threadA.start();
        threadB.start();

    }
}
