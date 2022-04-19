package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Send implements Parcelable {
    private double piece;
    private String size;

    public Send(double piece, String size){
        this.piece = piece;
        this.size = size;
    }

    protected Send(Parcel in) {
        piece = in.readDouble();
        size = in.readString();
    }

    public static final Creator<Send> CREATOR = new Creator<Send>() {
        @Override
        public Send createFromParcel(Parcel in) {
            return new Send(in);
        }

        @Override
        public Send[] newArray(int size) {
            return new Send[size];
        }
    };

    @Override
    public String toString() {
        return "Send{" +
                "piece=" + piece +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(piece);
        parcel.writeString(size);
    }
}
