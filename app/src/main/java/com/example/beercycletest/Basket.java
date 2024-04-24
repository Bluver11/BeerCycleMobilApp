package com.example.beercycletest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Basket {

    private int id;
    private int userId;
    private boolean deleted;
    private Optional<LocalDateTime> deletedAt = Optional.empty();

    private List<MenuBeer> menu;

    public Basket(int id, int userId, boolean deleted, LocalDateTime deletedAt, List<MenuBeer> menu) {
        this.id = id;
        this.userId = userId;
        this.deleted = deleted;
        this.deletedAt = Optional.ofNullable(deletedAt);
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Optional<LocalDateTime> getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Optional<LocalDateTime> deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<MenuBeer> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBeer> menu) {
        this.menu = menu;
    }
}
