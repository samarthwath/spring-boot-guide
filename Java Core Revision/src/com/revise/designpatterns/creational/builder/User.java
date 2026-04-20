package com.revise.designpatterns.creational.builder;

public class User {
    private String username;
    private String emailId;
    private String address;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.emailId = builder.emailId;
        this.address = builder.address;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class UserBuilder {
        private String username;
        private String emailId;
        private String address;

        private UserBuilder() {

        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            User user = new User(this);
            return user;
        }
    }
}
