package com.example.beercycletest;

public class MenuBeer {

    private int id;
    public String name;
    public String type;

    public int price;


    public MenuBeer(int id,String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getMenuName() {
        return name;
    }

    public void setMenuName(String menuName) {
        this.name = menuName;
    }

    public String getMenuType() {
        return type;
    }

    public void setMenuType(String menuType) {
        this.type = menuType;
    }

    public int getMenuPrice() {
        return price;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.price = menuPrice;
    }

}
