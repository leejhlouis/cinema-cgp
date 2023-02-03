package com.cinemacgp.models;

import androidx.annotation.Nullable;

public class Rent {
    private String name;
    private String phone;
    private String cinema;

    private static Rent rent;

    private Rent(String name, String phone, String cinema){
        this.name = name;
        this.phone = phone;
        this.cinema = cinema;
    }

    public static boolean hasRented(){
        return rent != null;
    }

    public static Rent getInstance(@Nullable String name, @Nullable String phone, @Nullable String cinema){
        return rent = hasRented() ? rent : new Rent(name, phone, cinema);
    }
}
