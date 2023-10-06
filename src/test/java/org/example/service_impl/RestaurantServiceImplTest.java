package org.example.service_impl;

import org.example.model.Restaurant;
import org.example.model.Review;
import org.example.service.RestaurantRepository;
import org.example.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;


class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;
    private RestaurantServiceImpl restaurantService;
    private List<Review> mockReviewList;




    @BeforeEach
    public void beforeAll() {

        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantServiceImpl(restaurantRepository);
        mockReviewList = Arrays.asList(new Review(1, 2, 5, "Testy Food", LocalDate.of(2023, 1, 24)));
    }



    @Test
    public void addReview(){
        Review review1 = mock(Review.class);
    }


    @Test
    public void retrieveAllReviewsGivenByParticularUserTest() {

        int userId = 2;

        when(restaurantRepository.allReviewsGivenByParticularUser(userId)).thenReturn(mockReviewList);

        List<Review> mockReviews = restaurantService.reviewsOfUser(userId);

        assertEquals(1, mockReviews.size(), "Expected one review for user");

        assertEquals(1, mockReviews.get(0).getRestaurantId(), "Expected restaurant ID to be 1");

        assertEquals("Testy Food", mockReviews.get(0).getComment(), "Expected comment to be 'Testy Food'");

        assertNotEquals(4, mockReviews.size(), "Expected list size not to be 4");


    }


    @Test
    void calculateTheAverageRatingOfTheRestaurant() {

        int restaurantId1 = 3;
        int restaurantId2 = 4;
        int restaurantId3 = 2;

        double avgRating1 = restaurantService.calculateTheAverageRatingOfTheRestaurant(restaurantId1);
        double avgRating2 = restaurantService.calculateTheAverageRatingOfTheRestaurant(restaurantId2);
        double avgRating3 = restaurantService.calculateTheAverageRatingOfTheRestaurant(restaurantId3);


        assertEquals(4.0, avgRating1, "Average rating for restaurant 3 should be 4.0");

        assertEquals(3.75, avgRating2, "Average rating for restaurant 4 should be 3.75");

        assertNotEquals(5, avgRating3, "Average rating for restaurant 2 should not be 5");
    }


    @Test
    void findAllRestaurantsByName() {

        String restaurantName1 = "Rambagh Palace";
        String restaurantName2 = "Taj Falaknuma Palace";
        String restaurantName3 = "Umaid Bhawan Palace";
        String restaurantName4 = "Taj Hotel";


        List<Restaurant> foundRestaurants1 = restaurantService.findAllRestaurantsByName(restaurantName1);
        List<Restaurant> foundRestaurants2 = restaurantService.findAllRestaurantsByName(restaurantName2);
        List<Restaurant> foundRestaurants3 = restaurantService.findAllRestaurantsByName(restaurantName3);
        List<Restaurant> foundRestaurants4 = restaurantService.findAllRestaurantsByName(restaurantName4);


        assertEquals(restaurantService.restaurantList.get(2), foundRestaurants1.get(0),
                "Expected Rambagh Palace to match restaurant at index 2");
        assertEquals(2, foundRestaurants2.get(0).getRestaurantId(),
                "Expected restaurant ID for Taj Falaknuma Palace to be 2");
        assertEquals("Rajasthan", foundRestaurants3.get(0).getLocation(),
                "Expected location for Umaid Bhawan Palace to be Rajasthan");
        assertEquals(2, foundRestaurants4.size(),
                "Expected 2 restaurants for Taj Hotel");
    }


    @Test
    void retrieveAllReviewsForParticularRestaurant() {

        int restaurantId1 = 1;
        int restaurantId2 = 2;
        int restaurantId3 = 5;

        List<Review> reviews1 = restaurantService.retrieveAllReviewsForParticularRestaurant(restaurantId1);
        List<Review> reviews2 = restaurantService.retrieveAllReviewsForParticularRestaurant(restaurantId2);
        List<Review> reviews3 = restaurantService.retrieveAllReviewsForParticularRestaurant(restaurantId3);

        assertEquals(2, reviews1.size(), "Expected 2 reviews for restaurant 1");

        assertEquals(LocalDate.of(2022, 6, 30), reviews2.get(0).getDate(),
                "Expected date for the first review of restaurant 2");

        assertEquals("Delicious Food", reviews3.get(0).getComment(),
                "Expected comment for the first review of restaurant 5");

        assertNotEquals(3, reviews3.size(), "Expected not to have 3 reviews for restaurant 5");
    }


    @Test
    void findAllreviewsMadeInAParticularTime() {

        LocalDate targetDate1 = LocalDate.of(2022, 7, 3);

        List<Review> reviews1 = restaurantService.findAllreviewsMadeInAParticularTime(targetDate1);

        assertEquals(3, reviews1.size(), "Expected 3 reviews made on July 3, 2022");

        assertEquals(5, reviews1.get(0).getRating(), "Expected rating 5 for the first review on May 12, 2021");

        assertEquals("Spicy Food", reviews1.get(0).getComment(), "Expected comment 'Spicy Food' for July 3, 2022");
    }

    @Test
    void displayReviewsInChronologicalOrder() {

        List<Review> reviews = restaurantService.displayReviewsInChronologicalOrder();

        assertEquals("Delicious Food", reviews.get(0).getComment(), "Expected first review to be 'Delicious Food'");

        assertEquals("yummy Food", reviews.get(18).getComment(), "Expected 19th review to be 'yummy Food'");
    }

    @Test
    void topFiveReviewedRestaurants(){

        List<Restaurant> topFiveReviewed = restaurantService.topFiveReviewedRestaurants();
        assertEquals("Taj Falaknuma Palace", topFiveReviewed.get(0).getName(),
                "Expected the top-reviewed restaurant to be 'Taj Falaknuma Palace'");
        assertEquals(5, topFiveReviewed.size(), "Expected 5 top-reviewed restaurants");
        assertEquals(2, topFiveReviewed.get(0).getRestaurantId(),
                "Expected restaurant ID for the top-reviewed restaurant to be 2");
        assertEquals("Hyderabad", topFiveReviewed.get(0).getLocation(),
                "Expected location for the top-reviewed restaurant to be 'Hyderabad'");


    }


    @Test
    void topFiveRatedRestaurants(){

        List<Restaurant> topFiveRated = restaurantService.topFiveRatedRestaurants();

        assertEquals("Taj Hotel", topFiveRated.get(0).getName(),"Expected the top-rated restaurant to be 'Taj Hotel'");
        assertEquals(5, topFiveRated.size(), "Expected 5 top-rated restaurants");
        assertEquals(5, topFiveRated.get(0).getRestaurantId(),"Expected restaurant ID for the top-rated restaurant to be 5");
        assertEquals("Mumbai", topFiveRated.get(0).getLocation(),"Expected location for the top-rated restaurant to be 'Mumbai'");
    }

}