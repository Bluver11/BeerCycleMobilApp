package com.example.beercycletest;

/**
 * A `Reservation` osztály reprezentálja a foglalás adatait.
 * Tartalmazza a felhasználó azonosítóját, kezdési időpontot, helyszínt,
 * foglalási idő, foglalás összegét, kerékpár azonosítóját és kosár azonosítóját.
 */
public class Reservation {
    private int user_id;// A felhasználó azonosítója
    private String start_time;// A foglalás kezdési időpontja
    private String location;// A foglalás helyszíne

    private String  reservation_time;// A foglalási idő

    private int total_amount;// Az foglalás összege
    private int bicycle_id;// A kerékpár azonosítója
    private int basket_id;// A kosár azonosítója

    /**
     * Új `Reservation` objektumot hoz létre a megadott adatokkal.
     *
     * @param user_id          A felhasználó azonosítója
     * @param start_time       A foglalás kezdési időpontja
     * @param location         A foglalás helyszíne
     * @param reservation_time A foglalási idő
     * @param total_amount     Az foglalás összege
     * @param bicycle_id       A kerékpár azonosítója
     * @param basket_id        A kosár azonosítója
     */
    public Reservation(int user_id, String start_time, String location, String reservation_time, int total_amount, int bicycle_id, int basket_id) {

        this.user_id = user_id;
        this.start_time = start_time;
        this.location = location;
        this.reservation_time = reservation_time;
        this.total_amount = total_amount;
        this.bicycle_id = bicycle_id;
        this.basket_id = basket_id;
    }

    /**
     * Visszaadja a felhasználó azonosítóját.
     *
     * @return A felhasználó azonosítója
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Beállítja a felhasználó azonosítóját.
     *
     * @param user_id A beállítandó felhasználó azonosítója
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Visszaadja a foglalás kezdési időpontját.
     *
     * @return A foglalás kezdési időpontja
     */
    public String getStart_time() {
        return start_time;
    }

    /**
     * Beállítja a foglalás kezdési időpontját.
     *
     * @param start_time A beállítandó kezdési időpont
     */
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    /**
     * Visszaadja a foglalás helyszínét.
     *
     * @return A foglalás helyszíne
     */
    public String getLocation() {
        return location;
    }

    /**
     * Beállítja a foglalás helyszínét.
     *
     * @param location A beállítandó helyszín
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Visszaadja a foglalási időt.
     *
     * @return A foglalási idő
     */
    public String getReservation_time() {
        return reservation_time;
    }

    /**
     * Beállítja a foglalás időt.
     *
     * @param reservation_time A beállítandó idő
     */
    public void setReservation_time(String reservation_time) {
        this.reservation_time = reservation_time;
    }

    /**
     * Visszaadja a foglalás összegét.
     *
     * @return A foglalás összegét
     */
    public int getTotal_amount() {
        return total_amount;
    }

    /**
     * Beállítja a foglalás összegét
     *
     * @param total_amount A foglalás összege
     */
    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * Visszaadja a kerékpár azonosítóját
     *
     * @return A kerékpár azonosítóját
     */
    public int getBicycle_id() {
        return bicycle_id;
    }

    /**
     * Beállítja a kerékpár azonosítóját
     *
     * @param bicycle_id A kerékpár azonosítója
     */
    public void setBicycle_id(int bicycle_id) {
        this.bicycle_id = bicycle_id;
    }

    /**
     * Visszaadja a kosár azonosítóját
     *
     * @return A kosár azonosítója
     */
    public int getBasket_id() {
        return basket_id;
    }

    /**
     * Beállítja a kosár azonosítóját
     *
     * @param basket_id A kosár azonosítója
     */
    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }
}
