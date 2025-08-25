package com.javafeatures;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupingAndAggregation {

    public static void main(String[] args) {

        //Immutable list which does not allows null:\
        //If while adding the elements if we add null it will give NPE.
        List<String> alphabetsString = List.of("ABC", "DEF");
        System.out.println("Log alphabet string: " + alphabetsString);
        //below lines will be giving UnsupportedOperationException.
        //alphabetsString.add("KJH");
        //alphabetsString.set(0, "IKL");

        //Immutable list which allows null.
        List<String> stringList = Stream.of("AKK", "LPO", null).toList();
        System.out.println("Log stringList: ");
        System.out.println(stringList);

        //Modifiable list which allows null.
        //Basic approach you can just create a list with null value. 
        List<String> modifiableList = Stream.of("AAA", "KKK", null).collect(Collectors.toList());
        System.out.println("Modifiable list before: ");
        System.out.println(modifiableList);
        modifiableList.set(2, "Hey there!");
        System.out.println("Modifiable list after: ");
        System.out.println(modifiableList);


        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Grocery", 1000));
        transactions.add(new Transaction("Grocery", 500));
        transactions.add(new Transaction("Entertainment", 2000));
        transactions.add(new Transaction("Entertainment", 1000));
        transactions.add(new Transaction("Miscellaneous", 5000));

        //Grouping products by category.
        Map<String, List<Transaction>> groupByCategory = transactions
                .stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getCategory()));
        System.out.println("Grouping products by category: ");
        System.out.println(groupByCategory);

        //Calculate the total price of product by category:

        Map<String, Integer> totalPriceByCategory = transactions
                .stream()
                .collect(
                        Collectors
                                .groupingBy(Transaction::getCategory,
                                        Collectors.summingInt(Transaction::getAmount))
                );
        System.out.println("Log total price by category: ");
        System.out.println(totalPriceByCategory);


        //Average of products by category:
        Map<String, Double> averageOfProductsByCategory = transactions
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::getCategory,
                                Collectors.averagingDouble(Transaction::getAmount)
                        )
                );
        System.out.println("Log average of products by category: ");
        System.out.println(averageOfProductsByCategory);

        //Product with price greater than 2000.
        List<Transaction> productWithPriceGreaterThanTwoThousand = transactions
                .stream()
                .filter(transaction -> transaction.getAmount() >= 2000)
                .collect(Collectors.toList());

        System.out.println("Product with price greater than 2000");
        System.out.println(productWithPriceGreaterThanTwoThousand);

        //count products by category:
        Map<String, Long> countOfProductsByCategory = transactions
                .stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.counting()
                ));

        System.out.println("Count of products by category: ");
        System.out.println(countOfProductsByCategory);

        //Extracting product name/categories.
        List<String> categoryNames = transactions
                .stream()
                .map(Transaction::getCategory)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Category names: ");
        System.out.println(categoryNames);

        //Partitioning: Partition products into expensive and cheap.
        Map<Boolean, List<Transaction>> partitionedProducts = transactions
                .stream()
                .collect(Collectors.partitioningBy(transaction -> transaction.getAmount() > 1500));

        System.out.println("Partition products into expensive and cheap categories: ");
        System.out.println(partitionedProducts);

        //Summary statistics by category:
        //Covers 5 metrics (sum, max, min, avg, count)
        Map<String, IntSummaryStatistics> summaryStatisticsMap = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summarizingInt(Transaction::getAmount)
                ));
        System.out.println("Log summaryStatisticsMap by category: ");
        System.out.println(summaryStatisticsMap);
    }
}
