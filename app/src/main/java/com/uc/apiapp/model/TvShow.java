package com.uc.apiapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.uc.apiapp.util.Constants;

public class TvShow implements Parcelable {
    @SerializedName("id")
    private String id_tvshow;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String rating;

    public TvShow(){

    }

    public TvShow(String id_tvshow, String popularity, String poster, String backdrop, String title, String overview, String releaseDate, String rating) {
        this.id_tvshow = id_tvshow;
        this.popularity = popularity;
        this.poster = poster;
        this.backdrop = backdrop;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    protected TvShow(Parcel in) {
        id_tvshow = in.readString();
        popularity = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        rating = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getId_tvshow() {
        return id_tvshow;
    }

    public void setId_tvshow(String id_tvshow) {
        this.id_tvshow = id_tvshow;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return Constants.BASE_IMAGE_URL + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return Constants.BASE_IMAGE_URL + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_tvshow);
        dest.writeString(popularity);
        dest.writeString(poster);
        dest.writeString(backdrop);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(rating);
    }
}
