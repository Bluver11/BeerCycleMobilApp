package com.example.beercycletest;

/**
 * A Login osztály reprezentálja a bejelentkezési adatokat.
 * Tartalmazza a felhasználó tokenjét és szerepkörét.
 */
public class Login {

    private String token;// A felhasználó tokenje
    private String role;// A felhasználó szerepköre (pl. "admin", "user", stb.)

    /**
     * Új Login objektumot hoz létre a megadott tokennel és szerepkörrel.
     *
     * @param token A felhasználó tokenje
     * @param role  A felhasználó szerepköre
     */
    public Login(String token, String role) {
        this.token = token;
        this.role = role;
    }

    /**
     * Visszaadja a felhasználó tokenjét.
     *
     * @return A felhasználó tokenje
     */
    public String getToken() {
        return token;
    }

    /**
     * Beállítja a felhasználó tokenjét.
     *
     * @param token A beállítandó token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Visszaadja a felhasználó szerepkörét.
     *
     * @return A felhasználó szerepköre
     */
    public String getRole() {
        return role;
    }

    /**
     * Beállítja a felhasználó szerepkörét.
     *
     * @param role A beállítandó szerepkör
     */
    public void setRole(String role) {
        this.role = role;
    }
}
