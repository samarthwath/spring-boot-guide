package com.revise.threading;

public class MyEvenOddCharacterPrinter {
    int index = 0;
    boolean isEvenTurn = true;
    private final String str;

    public MyEvenOddCharacterPrinter(String str) {
        this.str = str;
    }

    public synchronized void printEven() {
        while (index < str.length()) {
            while (!isEvenTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (index < str.length()) {
                System.out.print(str.charAt(index) + "");
                index++;
            }
        }
    }

    public synchronized void printOdd() {
        while (index < str.length()) {
            while (isEvenTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (index < str.length()) {
                System.out.print(str.charAt(index) + "");
                index++;
            }
        }
    }

    public static void main(String[] args) {
        MyEvenOddCharacterPrinter printer = new MyEvenOddCharacterPrinter("Samarth");
        Thread threadEven = new Thread(printer::printEven);
        Thread threadOdd = new Thread(printer::printOdd);
        threadEven.start();
        threadOdd.start();
    }

}
