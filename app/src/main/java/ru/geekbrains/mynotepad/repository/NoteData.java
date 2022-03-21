package ru.geekbrains.mynotepad.repository;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteData implements Parcelable {
    private String title;
    private String description;
    private int backgroundColor;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBackgroundColor(int backgroundColor){
        this.backgroundColor = backgroundColor;
    }

    public NoteData(String title, String description, int backgroundColor) {
        this.title = title;
        this.description = description;
        this.backgroundColor = backgroundColor;
    }

    protected NoteData(Parcel in) {
        title = in.readString();
        description = in.readString();
        backgroundColor = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(backgroundColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
