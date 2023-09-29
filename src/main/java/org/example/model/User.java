package org.example.model;

import java.time.LocalDate;

public class User {

    private int userId;
    private String name;
    private LocalDate joinedDate;


    public User(int userId, String name, LocalDate joinedDate) {
        this.userId = userId;
        this.name = name;
        this.joinedDate = joinedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
