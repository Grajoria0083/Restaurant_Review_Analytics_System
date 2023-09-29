package org.example.service;

import org.example.model.Review;
import org.example.model.Restaurant;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Review> retrieveAllReviewsGivenByParticularUser(int id);
    List<Review> retrieveAllReviewsForParticularRestaurant(int id);
    double calculateTheAverageRatingOfTheRestaurant(int id);
    List<Restaurant> topFiveReviewedRestaurants();
    List<Restaurant> topFiveRatedRestaurants();
    List<User> mostActiveUsers();
    List<Restaurant> findAllRestaurantsByName(String name);
    List<Review> findAllreviewsMadeInAParticularTime(LocalDate date);
    List<Review> displayReviewsInChronologicalOrder();
    List<Restaurant> displayRestaurantsInOrderOfAverageRating();
}
