package com.shulpin.shared.model;

public class Selector {

    private String sortBy;
    private String city;
    private String gender;
    private int minAge;
    private int maxAge;


    public Selector() {
    }

    public Selector(String sortBy, String city, String gender, int minAge, int maxAge) {
        this.sortBy = sortBy;
        this.city = city;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
