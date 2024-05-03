package com.example.beercycletest;

/**
 * Egy felhasználó bejelentkezését reprezentáló osztály.
 */
public class UserLogin {
    private String username;//A felhasználónév.
   private String password;//A jelszó.

    /**
     * Konstruktor egy új UserLogin objektum létrehozásához.
     *
     * @param username A felhasználónév.
     * @param password A jelszó.
     */
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;

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
}
