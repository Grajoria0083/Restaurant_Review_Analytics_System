package org.example.service;

import org.example.model.Restaurant;
import org.example.model.Review;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    List<Review> allReviewsGivenByParticularUser(int id);
}
