package com.example.beercycletest;

/**
 * Egy egyszerű osztály, amely egy menü azonosítót reprezentál.
 */
public class MenuId {

    private int menu;//Menu azonosító

    /**
     * Konstruktor egy új MenuId példány létrehozásához a megadott menü azonosítóval.
     *
     * @param menu A beállítandó menü azonosító.
     */
    public MenuId(int menu) {
        this.menu = menu;
    }

    /**
     * Visszaadja a jelenlegi menü azonosítót.
     *
     * @return A menü azonosító.
     */
    public int getId() {
        return menu;
    }

    /**
     * Beállítja a menü azonosítót.
     *
     * @param menu A beállítandó menü azonosító.
     */
    public void setId(int menu) {
        this.menu = menu;
    }
}
