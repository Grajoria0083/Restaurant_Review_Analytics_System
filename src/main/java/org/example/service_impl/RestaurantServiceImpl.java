package org.example.service_impl;

import org.example.model.Review;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.RestaurantService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {

    List<Restaurant> restaurantSet = new ArrayList<>();

    List<Review> reviewList = new ArrayList<>();

    List<User> userList = new ArrayList<>();

    Map<User, Review> userReviewMap = new HashMap<>();

    Map<Restaurant, Review> restaurantReviewMap = new HashMap<>();

    public RestaurantServiceImpl() {
        restaurantSet.addAll(Arrays.asList(new Restaurant(1, "Taj Hotel", "Mumbai", "Indian"),
                new Restaurant(2, "Taj Falaknuma Palace", "Hyderabad", "Indian"),
                new Restaurant(3, "Rambagh Palace", "Jaipur,", "Indian"),
                new Restaurant(4, "Umaid Bhawan Palace", "Rajasthan", "Indian"),
                new Restaurant(5, "Taj Hotel", "Mumbai", "Indian"))
                );

        reviewList.addAll(Arrays.asList(
                new Review(5, 4, 3, "Delicious Food", LocalDate.of(2023,06,01)),
                new Review(1, 2, 5, "Testy Food", LocalDate.of(2023,01,24)),
                new Review(3, 7, 2, "Good Food", LocalDate.of(2022,10,04)),
                new Review(4, 3, 3, "distasteful Food", LocalDate.of(2021,11,06)),
                new Review(5, 8, 4, "Delicious Food", LocalDate.of(2021,06,18)),
                new Review(4, 5, 4, "Flavoursome Food", LocalDate.of(2020,02,28)),
                new Review(3, 6, 5, "Testy Food", LocalDate.of(2023,05,12)),
                new Review(5, 8, 5, "Spicy Food", LocalDate.of(2022,07,03)),
                new Review(2, 1, 1, "enjoyable Food", LocalDate.of(2022,06,30)),
                new Review(3, 7, 4, "Delicious Food", LocalDate.of(2021,12,12)),
                new Review(4, 5, 4, "Flavoursome Food", LocalDate.of(2017,02,28)),
                new Review(2, 6, 5, "Testy Food", LocalDate.of(2023,05,12)),
                new Review(5, 1, 5, "Spicy Food", LocalDate.of(2022,07,03)),
                new Review(1, 3, 1, "sweet Food", LocalDate.of(2019,06,30)),
                new Review(2, 9, 3, "distasteful Food", LocalDate.of(2023,12,12)),
                new Review(4, 4, 4, "yummy Food", LocalDate.of(2023,02,28)),
                new Review(3, 5, 5, "good Food", LocalDate.of(2021,05,12)),
                new Review(2, 1, 5, "Spicy Food", LocalDate.of(2022,07,03)),
                new Review(2, 1, 1, "good Food", LocalDate.of(2021,11,30))

        ));
        userList.addAll(Arrays.asList(new User(1, "Aman",LocalDate.of(2023,11,07)),
                new User(1, "Aman",LocalDate.of(2021,11,04)),
                new User(2, "Gauarv",LocalDate.of(2019,10,02)),
                new User(3, "Rahul",LocalDate.of(2021,11,30)),
                new User(4, "Ram",LocalDate.of(2023,02,01)),
                new User(5, "Anshi",LocalDate.of(2017,02,04)),
                new User(6, "Rohit",LocalDate.of(2020,03,16)),
                new User(7, "Pawan",LocalDate.of(2018,04,23)),
                new User(8, "Saurabh",LocalDate.of(2021,06,18)),
                new User(9, "Gaurav Singh",LocalDate.of(2022,07,21))
        ));


    }


    @Override
    public List<Review> retrieveAllReviewsGivenByParticularUser(int id) {
        return reviewList.stream()
                .filter(r -> r.getUserId()==id)
                .collect(Collectors.toList());
    }


    @Override
    public List<Review> retrieveAllReviewsForParticularRestaurant(int id) {
        return reviewList.stream()
                .filter(r -> r.getRestaurantId()==id)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTheAverageRatingOfTheRestaurant(int id) {

        int count = (int) reviewList.stream()
                .filter(r -> r.getRestaurantId()==id)
                .mapToInt((review) -> review.getRating()).count();
        float sum = reviewList.stream()
                .filter(r -> r.getRestaurantId()==id)
                .mapToInt((review) -> review.getRating()).sum();

        return sum/count;

    }

    @Override
    public List<Restaurant> topFiveReviewedRestaurants() {

        Map<Restaurant, Long> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(r -> restaurantSet.get(r.getRestaurantId()-1) , Collectors.counting()));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Long>::getValue).reversed()).limit(5).map(r -> r.getKey()).toList();

    }

    @Override
    public List<Restaurant> topFiveRatedRestaurants() {

        Map<Restaurant, Double> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(e -> restaurantSet.get(e.getRestaurantId()-1) , Collectors.averagingDouble(e -> e.getRating())));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Double>::getValue).reversed()).limit(5).map(e -> e.getKey()).toList();

    }

    @Override
    public List<User> mostActiveUsers() {

        Map<User, Long> restaurantMap = reviewList.stream()
                .collect(Collectors.groupingBy(e -> userList.get(e.getUserId()-1) , Collectors.counting()));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<User, Long>::getValue).reversed()).limit(2).map(e -> e.getKey()).toList();

    }

    @Override
    public List<Restaurant> findAllRestaurantsByName(String name) {

        return restaurantSet.stream()
                .filter(r -> r.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findAllreviewsMadeInAParticularTime(LocalDate date) {

        return reviewList.stream().filter(r -> r.getDate().compareTo(date)==0).collect(Collectors.toList());


    }

    @Override
    public List<Review> displayReviewsInChronologicalOrder() {

        return reviewList.stream()
                .sorted((r1, r2) -> r1.getComment().compareTo(r2.getComment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> displayRestaurantsInOrderOfAverageRating() {

        Map<Restaurant, Double> restaurantMap = reviewList.stream().collect(Collectors.groupingBy(e -> restaurantSet.get(e.getRestaurantId()-1) , Collectors.averagingDouble(e -> e.getRating())));
        return restaurantMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Restaurant, Double>::getValue).reversed()).map(e -> e.getKey()).toList();

    }
}
