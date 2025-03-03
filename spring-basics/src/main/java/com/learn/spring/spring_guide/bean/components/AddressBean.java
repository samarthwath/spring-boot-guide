package com.learn.spring.spring_guide.bean.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = true)
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AddressBean {
    public AddressBean() {
        System.out.println("Inside default constructor of AddressBean");
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "pincode=" + pincode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public int getPincode() {
        return pincode;
    }

    public AddressBean(int pincode, String city, String country) {
        this.pincode = pincode;
        this.city = city;
        this.country = country;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private int pincode;
    private String city;
    private String country;

    @PreDestroy
    public void cleanup(){
        System.out.println("Cleaning up resources for AddressBean:");
    }

    @PostConstruct
    public void initialize(){
        System.out.println("Initializing resources for AddressBean.");
    }

}
