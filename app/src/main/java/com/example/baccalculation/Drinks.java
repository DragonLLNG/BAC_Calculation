package com.example.baccalculation;

import android.os.Parcel;
import android.os.Parcelable;

public class Drinks implements Parcelable {

    private double alcPercent;
    private int size;

    public Drinks(){

    }
    public Drinks(double percent, int size){
        this.alcPercent = percent;
        this.size = size;
    }

    //Getters
    public double getAlcPercent(){
        return this.alcPercent;
    }
    public int getSize(){
        return this.size;
    }
    //Setters
    public void setAlcPercent(double percent){
        this.alcPercent = percent;
    }

    public void setSize(int size){
        this.size = size;
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(alcPercent);
        parcel.writeInt(size);
    }
}
