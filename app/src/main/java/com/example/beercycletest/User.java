package com.example.beercycletest;

/**
 * Egy felhasználót reprezentáló osztály.
 */
public class User {
    private String username;//A felhasználónév.
    private String email;//Az e-mail cím.
    private String password;//A jelszó
    private String first_name;//A keresznév
    private String last_name;//A vezetéknév.

    /**
     * Konstruktor egy új User objektum létrehozásához.
     *
     * @param username   A felhasználónév.
     * @param email      Az e-mail cím.
     * @param password   A jelszó.
     * @param first_name Az  keresztnév.
     * @param last_name  A vezetéknév.
     */
    public User(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * Visszaadja a felhasználónevet.
     *
     * @return A felhasználónév.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Beállítja a felhasználónevet.
     *
     * @param username A beállítandó felhasználónév.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Visszaadja az e-mail címet.
     *
     * @return Az e-mail cím.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Beállítja az e-mail címet.
     *
     * @param email Az e-mail cím.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Visszaadja a jelszót.
     *
     * @return A jelszó.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Beállítja a jelszót.
     *
     * @param password A beállítandó jelszó.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Visszaadja az keresztnevet.
     *
     * @return Az kerestnév.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Beállítja az keresztnevet.
     *
     * @param first_name Az keresztnév.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Visszaadja a vezetéknevet.
     *
     * @return A vezetéknév.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Beállítja a vezetéknevet.
     *
     * @param last_name A vezetéknév.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }




}
