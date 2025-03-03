package com.learn.spring.spring_guide.bean.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = true)
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonBean {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "PersonBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressBean=" + addressBean +
                '}';
    }

    private AddressBean addressBean;

    public PersonBean() {
        System.out.println("Inside default constructor of PersonBean");
    }

    @Autowired
    public PersonBean(AddressBean addressBean) {
        System.out.println("Inside injection constructor of PersonBean");
        this.addressBean = addressBean;
    }

    public PersonBean(int id, String name, AddressBean addressBean) {
        this.id = id;
        this.name = name;
        this.addressBean = addressBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up resources for PersonBean:");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Initializing resources for PersonBean.");
    }
}
