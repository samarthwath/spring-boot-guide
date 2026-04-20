package com.revise.javafeatures;

public final class ImmutableClass {
    private final String name;
    private final String password;

    public ImmutableClass(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
