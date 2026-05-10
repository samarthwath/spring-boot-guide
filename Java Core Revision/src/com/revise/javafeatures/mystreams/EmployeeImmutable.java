package com.revise.javafeatures.mystreams;

public final class EmployeeImmutable {
    private final String name;
    private final String email;
    private final String city;

    @Override
    public String toString() {
        return "EmployeeImmutable{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public EmployeeImmutable(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
