package com.revise.threading;

public class EvenOddCharacterPrinter {

    private final String str;
    private int index = 0;
    private boolean evenTurn = true;

    public EvenOddCharacterPrinter(String str) {
        this.str = str;
    }

    public synchronized void printEven() {
        while (index < str.length()) {

            while (!evenTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (index < str.length()) {
                System.out.println(Thread.currentThread().getName()
                        + " -> " + str.charAt(index));
                index++;
                evenTurn = false;
                notifyAll();
            }
        }
    }

    public synchronized void printOdd() {
        while (index < str.length()) {

            while (evenTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (index < str.length()) {
                System.out.println(Thread.currentThread().getName()
                        + " -> " + str.charAt(index));
                index++;
                evenTurn = true;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        String input = "ABCDEFGH";

        EvenOddCharacterPrinter printer =
                new EvenOddCharacterPrinter(input);

        Thread evenThread =
                new Thread(printer::printEven, "EvenThread");

        Thread oddThread =
                new Thread(printer::printOdd, "OddThread");

        evenThread.start();
        oddThread.start();
    }
}
