package com.hackerrank.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Home implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float area;
    private Float price;
    private String city;

    // Default constructor (required by JPA)
    public Home() {}

    // All args constructor
    public Home(Integer id, Float area, Float price, String city) {
        this.id = id;
        this.area = area;
        this.price = price;
        this.city = city;
    }

    // Constructor without id (for convenience)
    public Home(Float area, Float price, String city) {
        this.area = area;
        this.price = price;
        this.city = city;
    }

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Float getArea() { return area; }
    public void setArea(Float area) { this.area = area; }

    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    // ------------------ MANUAL BUILDER -------------------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private Float area;
        private Float price;
        private String city;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder area(Float area) {
            this.area = area;
            return this;
        }

        public Builder price(Float price) {
            this.price = price;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Home build() {
            return new Home(id, area, price, city);
        }
    }
}
