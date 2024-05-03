package com.example.beercycletest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * A kosarat reprezentáló osztály menüelemek tárolására.
 */
public class Basket {
    private int id;// A kosár egyedi azonosítója
    private int userId;// A kosárhoz kapcsolt felhasználó azonosítója
    private boolean deleted;// Megadja, hogy a kosár törölve van-e
    private Optional<LocalDateTime> deletedAt = Optional.empty();// A kosár törlésének dátuma és ideje (opcionális, lehet null)

    private List<MenuBeer> menu;// A kosárban található menüelemek listája

    /**
     * Új Basket objektumot hoz létre.
     *
     * @param id A kosár egyedi azonosítója
     * @param userId A kosárhoz kapcsolt felhasználó azonosítója
     * @param deleted Megadja, hogy a kosár törölve van-e
     * @param deletedAt A kosár törlésének dátuma és ideje (opcionális, lehet null)
     * @param menu A kosárban található menüelemek listája
     */
    public Basket(int id, int userId, boolean deleted, LocalDateTime deletedAt, List<MenuBeer> menu) {
        this.id = id;
        this.userId = userId;
        this.deleted = deleted;
        this.deletedAt = Optional.ofNullable(deletedAt);
        this.menu = menu;
    }
    /**
     * Visszaadja a kosár egyedi azonosítóját.
     *
     * @return A kosár azonosítója
     */
    public int getId() {
        return id;
    }

    /**
     * Beállítja a kosár egyedi azonosítóját.
     *
     * @param id A beállítandó kosár azonosítója
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Visszaadja a kosárhoz kapcsolt felhasználó azonosítóját.
     *
     * @return A felhasználó azonosítója
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Beállítja a kosárhoz kapcsolt felhasználó azonosítóját.
     *
     * @param userId A beállítandó felhasználó azonosítója
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Ellenőrzi, hogy a kosár törölve van-e.
     *
     * @return true, ha a kosár törölve van, különben false
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Beállítja a kosár törlési állapotát.
     *
     * @param deleted true, ha a kosár törölve van, különben false
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Visszaadja a kosár törlésének dátumát és idejét (ha alkalmazható).
     *
     * @return Egy opcionális objektum a törlési időponttal, vagy üres, ha nem törölt
     */
    public Optional<LocalDateTime> getDeletedAt() {
        return deletedAt;
    }
    /**
     * Beállítja a kosár törlésének dátumát és idejét (ha alkalmazható).
     *
     * @param deletedAt A beállítandó törlési időpont (opcionális, lehet null)
     */
    public void setDeletedAt(Optional<LocalDateTime> deletedAt) {
        this.deletedAt = deletedAt;
    }
    /**
     * Visszaadja a kosárban található menüelemek listáját.
     *
     * @return A menüelemek listája
     */
    public List<MenuBeer> getMenu() {
        return menu;
    }

    /**
     * Beállítja a kosárban található menüelemek listáját.
     *
     * @param menu A beállítandó menüelemek listája
     */
    public void setMenu(List<MenuBeer> menu) {
        this.menu = menu;
    }
}
