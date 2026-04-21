package com.revise.javafeatures.mystreams;

public class Product {
    private String id;
    private int price;
    private String rating;

    public Product(String id, int price, String rating) {
        this.id = id;
        this.price = price;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", rating='" + rating + '\'' +
                '}';
    }
}
