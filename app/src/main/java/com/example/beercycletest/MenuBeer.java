package com.example.beercycletest;

/**
 * A `MenuBeer` osztály egy menüelemet reprezentál, amely a menüvel kapcsolatos információkat tartalmaz.
 */
public class MenuBeer {

    private int id;//A menüelem azonosítója.

    /**
     * The Name.
     */
    public String name;//A menüelem neve (sör,étel neve).
    /**
     * The Type.
     */
    public String type;//A menüelem típusa (snack vagy ital).

    /**
     * The Price.
     */
    public int price;//A menüelem ára.

    /**
     * Konstruktor egy új `MenuBeer` példány létrehozásához.
     *
     * @param id    A menüelem azonosítója.
     * @param name  A menüelem neve (sör,étel neve).
     * @param type  A menüelem típusa (snack vagy ital).
     * @param price A menüelem ára.
     */
    public MenuBeer(int id,String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    /**
     * Visszaadja a menüelem azonosítóját.
     *
     * @return A menüelem azonosítója.
     */
    public int getId(){
        return id;
    }

    /**
     * Beállítja a menüelem azonosítóját.
     *
     * @param id A beállítandó menüelem azonosítója.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Visszaadja a menüelem nevét (sör,étel nevét).
     *
     * @return A menüelem neve.
     */
    public String getMenuName() {
        return name;
    }

    /**
     * Beállítja a menüelem nevét (sör,étel nevét).
     *
     * @param menuName A beállítandó menüelem neve.
     */
    public void setMenuName(String menuName) {
        this.name = menuName;
    }

    /**
     * Visszaadja a menüelem típusát (snack vagy ital).
     *
     * @return A menüelem típusa.
     */
    public String getMenuType() {
        return type;
    }

    /**
     * Beállítja a menüelem típusát (snack vagy ital).
     *
     * @param menuType A beállítandó menüelem típusa.
     */
    public void setMenuType(String menuType) {
        this.type = menuType;
    }

    /**
     * Visszaadja a menüelem árát.
     *
     * @return A menüelem ára.
     */
    public int getMenuPrice() {
        return price;
    }

    /**
     * Beállítja a menüelem árát.
     *
     * @param menuPrice A beállítandó menüelem ára.
     */
    public void setMenuPrice(Integer menuPrice) {
        this.price = menuPrice;
    }

}
