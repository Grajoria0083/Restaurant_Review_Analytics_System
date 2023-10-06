package org.example.service_impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.example.adpter.LocalDateTypeAdapter;
import org.example.model.Review;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.RestaurantRepository;
import org.example.service.RestaurantService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private Gson gson = new Gson();

    List<Restaurant> restaurantList;

    List<Review> reviewList = new ArrayList<>();

    List<User> userList = new ArrayList<>();

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        setJsonData();
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantServiceImpl() {

    }



    public void setJsonData() {
        try {
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();

            URL restaurantResource = Restaurant.class.getClassLoader().getResource("resturant-data.json");
            byte[] restaurantBytes = Files.readAllBytes(Paths.get(restaurantResource.toURI()));
            String restaurantS = new String(restaurantBytes);
            var restaurantData = gson.fromJson(restaurantS, Restaurant[].class);
            restaurantList =  Arrays.stream(restaurantData).toList();

            URL reviewResource = Review.class.getClassLoader().getResource("review-data.json");
            byte[] reviewBytes = Files.readAllBytes(Paths.get(reviewResource.toURI()));
            String reviewS = new String(reviewBytes);
            var reviewData = gson.fromJson(reviewS, Review[].class);
            reviewList =  Arrays.stream(reviewData).toList();

            URL userResource = User.class.getClassLoader().getResource("user-data.json");
            byte[] userBytes = Files.readAllBytes(Paths.get(userResource.toURI()));
            String userS = new String(userBytes);
            var userData = gson.fromJson(userS, User[].class);
            userList =  Arrays.stream(userData).toList();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



    @Override
    public List<Review> retrieveAllReviewsGivenByParticularUser(int id) {
        return reviewList.stream()
                .filter(r -> r.getUserId()==id).toList();
    }


    @Override
    public List<Review> retrieveAllReviewsForParticularRestaurant(int id) {
        return reviewList.stream()
                .filter(r -> r.getRestaurantId()==id).toList();
    }

    @Override
    public double calculateTheAverageRatingOfTheRestaurant(int id) {

        int count = (int) reviewList.stream()
                .filter(r -> r.getRestaurantId()==id)
                .mapToInt(Review::getRating).count();
        float sum = reviewList.stream()
                .filter(r -> r.getRestaurantId()==id)
                .mapToInt(Review::getRating).sum();

        return sum/count;

    }

    @Override
    public List<Restaurant> topFiveReviewedRestaurants() {

        Map<Restaurant, Long> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(r -> restaurantList.get(r.getRestaurantId()-1) , Collectors.counting()));

        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Long>::getValue).reversed()).limit(5).map(Map.Entry::getKey).toList();

    }

    @Override
    public List<Restaurant> topFiveRatedRestaurants() {

        Map<Restaurant, Double> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(r-> restaurantList.get(r.getRestaurantId()-1), Collectors.averagingDouble(Review::getRating)));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Double>::getValue).reversed()).limit(5).map(Map.Entry::getKey).toList();

    }

    @Override
    public List<User> mostActiveUsers() {

        Map<User, Long> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(e -> userList.get(e.getUserId()-1) , Collectors.counting()));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<User, Long>::getValue).reversed()).limit(2).map(Map.Entry::getKey).toList();

    }

    @Override
    public List<Restaurant> findAllRestaurantsByName(String name) {

        return restaurantList.stream()
                .filter(r -> r.getName().equals(name))
                .toList();
    }

    @Override
    public List<Review> findAllreviewsMadeInAParticularTime(LocalDate date) {

        return reviewList.stream().filter(r -> r.getDate().equals(date)).toList();


    }

    @Override
    public List<Review> displayReviewsInChronologicalOrder() {

        return reviewList.stream()
                .sorted(Comparator.comparing(Review::getComment)).toList();
    }

    @Override
    public List<Restaurant> displayRestaurantsInOrderOfAverageRating() {

        Map<Restaurant, Double> restaurantMap = reviewList.stream().collect(Collectors.groupingBy(e -> restaurantList.get(e.getRestaurantId()-1) , Collectors.averagingDouble(Review::getRating)));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Double>::getValue).reversed()).map(Map.Entry::getKey).toList();

    }


    List<Review> reviewsOfUser(int id) {

        List<Review> reviews =  restaurantRepository.allReviewsGivenByParticularUser(id);
        return reviews;
    }

}
