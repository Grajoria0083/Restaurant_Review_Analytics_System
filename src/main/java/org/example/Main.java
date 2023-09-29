package org.example;


import org.example.service_impl.RestaurantServiceImpl;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        RestaurantServiceImpl restaurantService= new RestaurantServiceImpl();

//        System.out.println(restaurantService.findAllRestaurantsByName("Umaid Bhawan Palace"));
//        System.out.println(restaurantService.retrieveAllReviewsForParticularRestaurant(2));
//        System.out.println(restaurantService.retrieveAllReviewsGivenByParticularUser(1));
//        System.out.println(restaurantService.calculateTheAverageRatingOfTheRestaurant(4));
//        System.out.println(restaurantService.displayReviewsInChronologicalOrder());
//        System.out.println(restaurantService.findAllreviewsMadeInAParticularTime(LocalDate.of(2022,07,03)));
        System.out.println(restaurantService.topFiveRatedRestaurants());
//        System.out.println(restaurantService.topFiveReviewedRestaurants());
//        System.out.println(restaurantService.mostActiveUsers());
    }
}