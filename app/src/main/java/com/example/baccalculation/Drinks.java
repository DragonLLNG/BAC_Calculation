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

    protected Drinks(Parcel in) {
        alcPercent = in.readDouble();
        size = in.readInt();
    }

    public static final Creator<Drinks> CREATOR = new Creator<Drinks>() {
        @Override
        public Drinks createFromParcel(Parcel in) {
            return new Drinks(in);
        }

        @Override
        public Drinks[] newArray(int size) {
            return new Drinks[size];
        }
    };

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
