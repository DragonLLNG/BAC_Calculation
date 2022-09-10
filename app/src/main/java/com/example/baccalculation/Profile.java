package com.example.baccalculation;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    double weight;
    String gender;

    public Profile(double weight, String gender){
        this.weight = weight;
        this.gender = gender;
    }

    protected Profile(Parcel in) {
        weight = in.readDouble();
        gender = in.readString();
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

    public double getWeight(){
        return weight;
    }

    public String getGender(){
        return gender;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(weight);
        parcel.writeString(gender);
    }
}
