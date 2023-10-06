package org.example.service_impl;

import org.example.model.Review;
import org.example.service.RestaurantRepository;
import org.example.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private Review review;

    @Mock
    private RestaurantRepository restaurantRepository;
    private RestaurantServiceImpl restaurantService;




    @BeforeEach
    public void beforeAll() {

        MockitoAnnotations.openMocks(this);
         restaurantService = new RestaurantServiceImpl(restaurantRepository);
    }



    @Test
    public void addReview(){
        Review review1 = mock(Review.class);
    }


    @Test
    public void retrieveAllReviewsGivenByParticularUserTest() {

        when(restaurantRepository.allReviewsGivenByParticularUser(2)).thenReturn(Arrays.asList(new Review(1, 2, 5, "Testy Food", LocalDate.of(2023,01,24))));

        List<Review> mockReviews = restaurantService.reviewsOfUser(2);

        assertEquals(mockReviews.size() ,1);

        assertEquals(mockReviews.get(0).getRestaurantId() , 1);

        assertEquals(mockReviews.get(0).getComment() , "Testy Food");

        assertNotEquals(mockReviews.size(),4);


    }


    @Test
    void calculateTheAverageRatingOfTheRestaurant() {

        assertEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(3),4.0);

        assertEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(4),3.75);

        assertNotEquals(restaurantService.calculateTheAverageRatingOfTheRestaurant(2) ,5);
    }


    @Test
    void findAllRestaurantsByName() {

        assertEquals(restaurantService.findAllRestaurantsByName("Rambagh Palace").get(0), restaurantService.restaurantList.get(2));

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