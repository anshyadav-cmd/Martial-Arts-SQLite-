package com.example.martialartsclub.model;

import androidx.annotation.NonNull;

public class MartialArt {

    private int martialArtID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;


    public MartialArt(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor) {
        this.martialArtID = martialArtID;
        this.martialArtName = martialArtName;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @NonNull
    @Override
    public String toString() {
        return martialArtID  + " " + martialArtName + " " + martialArtPrice + " " + martialArtColor + "\n";
    }
}
