package org.example.service_impl;

import org.example.model.Restaurant;
import org.example.model.Review;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceImplTest {

    private static RestaurantServiceImpl restaurantService;

    @BeforeAll
    public static void beforeAll() {
        restaurantService = new RestaurantServiceImpl();
    }
    


    @Test
    void retrieveAllReviewsGivenByParticularUserTest() {

        assertEquals(restaurantService.retrieveAllReviewsGivenByParticularUser(5).size() ,3);

        assertEquals(restaurantService.retrieveAllReviewsGivenByParticularUser(2).get(0).getRestaurantId() , 1);

        assertEquals(restaurantService.retrieveAllReviewsGivenByParticularUser(2).get(0).getComment() , "Testy Food");

        assertEquals(restaurantService.retrieveAllReviewsGivenByParticularUser(10).size() ,0);

        assertNotEquals(restaurantService.retrieveAllReviewsGivenByParticularUser(5).size(),4);


    }


    @Test
    void calculateTheAverageRatingOfTheRestaurant() {

        assertEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(3),4.0);

        assertEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(4),3.75);

        assertNotEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(2) ,5);
    }


    @Test
    void findAllRestaurantsByName() {

        assertEquals(restaurantService.findAllRestaurantsByName("Rambagh Palace").get(0), restaurantService.restaurantSet.get(2));

        assertEquals(restaurantService.findAllRestaurantsByName("Taj Falaknuma Palace").get(0).getRestaurantId(),2);

        assertEquals(restaurantService.findAllRestaurantsByName("Umaid Bhawan Palace").get(0).getLocation(),"Rajasthan");

        assertEquals(restaurantService.findAllRestaurantsByName("Taj Hotel").size(), 2);
    }


    @Test
    void retrieveAllReviewsForParticularRestaurant() {

        assertEquals(restaurantService.retrieveAllReviewsForParticularRestaurant(1).size() ,2);

        assertEquals(restaurantService.retrieveAllReviewsForParticularRestaurant(2).get(0).getDate() ,LocalDate.of(2022,06,30));

        assertEquals(restaurantService.retrieveAllReviewsForParticularRestaurant(5).get(0).getComment() ,"Delicious Food");

        assertNotEquals(restaurantService.retrieveAllReviewsForParticularRestaurant(5).size(),3);
    }


    @Test
    void findAllreviewsMadeInAParticularTime() {

        assertEquals(restaurantService.findAllreviewsMadeInAParticularTime(LocalDate.of(2022,07,03)).size() ,3);

        assertEquals(restaurantService.findAllreviewsMadeInAParticularTime(LocalDate.of(2021,05,12)).get(0).getRating() ,5);

        assertEquals(restaurantService.findAllreviewsMadeInAParticularTime(LocalDate.of(2022,07,03)).get(0).getComment() ,"Spicy Food");
    }

    @Test
    void displayReviewsInChronologicalOrder() {

       assertEquals(restaurantService.displayReviewsInChronologicalOrder().get(0).getComment(),"Delicious Food");

       assertEquals(restaurantService.displayReviewsInChronologicalOrder().get(18).getComment(),"yummy Food");
    }

    @Test
    void topFiveReviewedRestaurants(){

        assertEquals(restaurantService.topFiveReviewedRestaurants().get(0).getName(),"Taj Falaknuma Palace");

        assertEquals(restaurantService.topFiveReviewedRestaurants().size(),5);

        assertEquals(restaurantService.topFiveReviewedRestaurants().get(0).getRestaurantId(),2);

        assertEquals(restaurantService.topFiveReviewedRestaurants().get(0).getLocation(),"Hyderabad");


    }


    @Test
    void topFiveRatedRestaurants(){

        assertEquals(restaurantService.topFiveRatedRestaurants().get(0).getName(),"Taj Hotel");

        assertEquals(restaurantService.topFiveRatedRestaurants().size(),5);

        assertEquals(restaurantService.topFiveRatedRestaurants().get(0).getRestaurantId(),5);

        assertEquals(restaurantService.topFiveRatedRestaurants().get(0).getLocation(),"Mumbai");
    }

}