package com.mystreams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAdvanceConcepts {
    public static void main(String[] args) {
        //Unmodifiable list which allows null.
        List<String> unmofiableList = Stream.of("red", "yellow", null).toList();
        //below operation is not allowed.
        //unmofiableList.add("hero");
        //Modifiable list which allows null.
        List<String> modifiableList = Stream.of("red", "pink", null).collect(Collectors.toList());
        modifiableList.add("dress");
        System.out.println("Modifiable list: ");
        System.out.println(modifiableList);

        record Product(String name, String category, int price) {
        }
        ;
        List<Product> products = Stream.of(
                new Product("Laptop", "Electronics", 1000),
                new Product("TV", "Electronics", 1500),
                new Product("Sofa", "Furniture", 700),
                new Product("Table", "Furniture", 300),
                new Product("Lamp", "Home Decor", 50),
                new Product("Laptop", "Electronics", 1000)
        ).toList();

        //All grouping and aggregation functions are part of the Collectors.
        //Example of grouping:
        //Group products by there category:
        Map<String, List<Product>> groupProductByCategory = products.stream()
                .collect(Collectors.groupingBy(product -> product.category));

        System.out.println("Product list: ");
        System.out.println(products);
        System.out.println("Group Products by category: ");
        System.out.println(groupProductByCategory);

        //Calculate the total price of products by category:
        Map<String, Integer> totalPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.category,
                        Collectors.summingInt(product -> product.price)));
        System.out.println("Total price by category: " + totalPriceByCategory);

        //Aggregation: Counting and average of products by category.
        Map<String, Double> averageOfProductsByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.category,
                        Collectors.averagingInt(product -> product.price)
                ));
        System.out.println("Average of Product by category: " + averageOfProductsByCategory);

        //Aggregation: Counting the products by category.

        Map<String, Long> countProductsByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.category,
                        Collectors.counting()
                ));
        System.out.println("Count of products by category: ");
        System.out.println(countProductsByCategory);

        List<Product> filteredProductList = products.stream()
                .filter(product -> product.price > 50)
                .collect(Collectors.toList());
        System.out.println("Product List with price>50");
        System.out.println(filteredProductList);

        //Extract only product categories:
        List<String> productCategories = products.stream()
                .map(product -> product.category)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("List of product categories only: ");
        System.out.println(productCategories);

        //Partitioning: Separate Products into expensive and cheap.
        Map<Boolean, List<Product>> partitionedProductsByPrice = products.stream()
                .collect(Collectors.partitioningBy(
                        product -> product.price > 1000
                ));
        System.out.println("Partitioned products by price whether expensive or cheap: ");
        System.out.println(partitionedProductsByPrice);

        //Summary statistics for product prices.
        //sum, average, min, max, count.
        Map<String, IntSummaryStatistics> summaryStatisticsMap = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.category,
                        Collectors.summarizingInt(product -> product.price)
                ));
        System.out.println("Product summary statistics map: ");
        System.out.println(summaryStatisticsMap);
    }
}
