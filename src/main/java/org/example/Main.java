package org.example;


import org.example.service_impl.RestaurantServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {

        RestaurantServiceImpl restaurantService= new RestaurantServiceImpl();

        System.out.println("Enter the Number as per Services");
        System.out.println("1 findAllRestaurantsByName");
        System.out.println("2 retrieveAllReviewsForParticularRestaurant");
        System.out.println("3 retrieveAllReviewsGivenByParticularUser");
        System.out.println("4 calculateTheAverageRatingOfTheRestaurant");
        System.out.println("5 displayReviewsInChronologicalOrder");
        System.out.println("6 findAllreviewsMadeInAParticularTime");
        System.out.println("7 topFiveRatedRestaurants");
        System.out.println("8 topFiveReviewedRestaurants");
        System.out.println("9 mostActiveUsers");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        switch (n){
            case 1:
                scanner.nextLine();
                String s = scanner.nextLine();
                System.out.println(restaurantService.findAllRestaurantsByName(s));
                break;
            case 2:
                System.out.println(restaurantService.retrieveAllReviewsForParticularRestaurant(scanner.nextInt()));
                break;
            case 3:
                System.out.println(restaurantService.retrieveAllReviewsGivenByParticularUser(scanner.nextInt()));
                break;
            case 4:
                System.out.println(restaurantService.calculateTheAverageRatingOfTheRestaurant(scanner.nextInt()));
                break;
            case 5:
                System.out.println(restaurantService.displayReviewsInChronologicalOrder());
                break;
            case 6:
                String dateString = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                System.out.println(restaurantService.findAllreviewsMadeInAParticularTime(localDate));
                break;
            case 7:
                System.out.println(restaurantService.topFiveRatedRestaurants());
                break;
            case 8:
                System.out.println(restaurantService.topFiveReviewedRestaurants());
                break;
            case 9:
                System.out.println(restaurantService.mostActiveUsers());

                break;
            default:
                System.out.println("Invalid service");
        }








//        System.out.println(restaurantService.findAllRestaurantsByName("Umaid Bhawan Palace"));
//        System.out.println(restaurantService.retrieveAllReviewsForParticularRestaurant(2));
//        System.out.println(restaurantService.retrieveAllReviewsGivenByParticularUser(1));
//        System.out.println(restaurantService.calculateTheAverageRatingOfTheRestaurant(4));
//        System.out.println(restaurantService.displayReviewsInChronologicalOrder());
//        System.out.println(restaurantService.findAllreviewsMadeInAParticularTime(LocalDate.of(2022,07,03)));
//        System.out.println(restaurantService.topFiveRatedRestaurants());
//        System.out.println(restaurantService.topFiveReviewedRestaurants());
//        System.out.println(restaurantService.mostActiveUsers());
    }
}