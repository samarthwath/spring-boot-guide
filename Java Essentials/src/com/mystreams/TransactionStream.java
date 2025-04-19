package com.mystreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class TransactionStream {
    public static void main(String[] args) {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("Grocery", 1000));
        transactionList.add(new Transaction("Entertainment", 2000));
        transactionList.add(new Transaction("Grocery", 5000));
        transactionList.add(new Transaction("Utilities", 1500));
        transactionList.add(new Transaction("Utilities", 2000));
        transactionList.add(new Transaction("Entertainment", 5000));
        System.out.println("Transaction list: ");
        System.out.println(transactionList);

        //Calculate total amount spent on grocery:
        Optional<Integer> sumAmountGroceries = transactionList.stream().filter(transaction -> transaction.getCategory().equals("Grocery")).map(transaction -> transaction.getAmount()).reduce((amountFirst, amountSecond) -> amountFirst + amountSecond);
        System.out.println("Sum of groceries: ");
        System.out.println(sumAmountGroceries.get());
        int grocerySum = transactionList.stream().filter(transaction -> transaction.getCategory().equals("Grocery")).mapToInt(transaction -> transaction.getAmount()).sum();
        System.out.println("Log groceries sum: ");
        System.out.println(grocerySum);

        //Calculate average expense across all categories: 
        double averageExpense = transactionList.stream().mapToInt(transaction -> transaction.getAmount()).average().orElse(0.0);
        System.out.println("Average expense: ");
        System.out.println(averageExpense);
    }
}
