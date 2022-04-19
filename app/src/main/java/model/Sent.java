package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Sent implements Parcelable {
    private int age;
    private String name;

    public Sent(int age , String name){
        this.age = age;
        this.name= name;
    }

    protected Sent(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public static final Creator<Sent> CREATOR = new Creator<Sent>() {
        @Override
        public Sent createFromParcel(Parcel in) {
            return new Sent(in);
        }

        @Override
        public Sent[] newArray(int size) {
            return new Sent[size];
        }
    };

    @Override
    public String toString() {
        return "Sent{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeString(name);
    }
}
