package com.example.beercycletest;

public class Reservation {
    private int user_id;
    private String start_time;
    private String location;

    private String  reservation_time;

    private int total_amount;
    private int bicycle_id;
    private int basket_id;

    public Reservation(int user_id, String start_time, String location, String reservation_time, int total_amount, int bicycle_id, int basket_id) {

        this.user_id = user_id;
        this.start_time = start_time;
        this.location = location;
        this.reservation_time = reservation_time;
        this.total_amount = total_amount;
        this.bicycle_id = bicycle_id;
        this.basket_id = basket_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(String reservation_time) {
        this.reservation_time = reservation_time;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getBicycle_id() {
        return bicycle_id;
    }

    public void setBicycle_id(int bicycle_id) {
        this.bicycle_id = bicycle_id;
    }

    public int getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }
}
