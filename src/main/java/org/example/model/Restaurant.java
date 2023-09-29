package org.example.model;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String location;
    private String cuisineType;

    public Restaurant(int restaurantId, String name, String location, String cuisineType) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
    }

    public Restaurant() {

    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                '}';
    }
}
