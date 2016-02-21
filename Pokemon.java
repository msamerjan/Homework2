package edu.lclark.homework2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maiaphoebedylansamerjan on 2/18/16.
 */
public class Pokemon implements Parcelable {
    private String mName, mId, mSpeciesId, mHeight, mWeight;


    public Pokemon(String csvStr) {
        String[] split = csvStr.trim().split(",");

        mId = split[0];
        mName = split[1];
        mSpeciesId = split[2];
        mHeight = split[3];
        mWeight = split[4];
    }

    public String getImageUrl() {
        return "http://img.pokemondb.net/artwork/" + getName() + ".jpg";
    }


    public String getName() {
        return mName;
    }
    public String getId() {
        return mId;
    }

    public String getSpeciesId() {
        return mSpeciesId;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getWeight() {
        return mWeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mId);
        dest.writeString(mSpeciesId);
        dest.writeString(mHeight);
        dest.writeString(mWeight);
    }

    public static final Parcelable.Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public Pokemon(Parcel source) {
        mName = source.readString();
        mId = source.readString();
        mSpeciesId = source.readString();
        mHeight = source.readString();
        mWeight = source.readString();
    }
}
