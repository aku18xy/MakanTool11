package com.example.nas.makantool11;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nas on 19/12/2017.
 */

public class FoodModel implements Parcelable {

    private String Name;
    private String Image;
    private String Description;
    private String Price;
    private String Discount;
    private String MenuId;

    public FoodModel() {
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return Price;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.Image);
        dest.writeString(this.Description);
        dest.writeString(this.Price);
        dest.writeString(this.Discount);
        dest.writeString(this.MenuId);
    }

    protected FoodModel(Parcel in) {
        this.Name = in.readString();
        this.Image = in.readString();
        this.Description = in.readString();
        this.Price = in.readString();
        this.Discount = in.readString();
        this.MenuId = in.readString();
    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel source) {
            return new FoodModel(source);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };
}
