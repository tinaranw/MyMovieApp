package com.uc.apiapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.uc.apiapp.util.Constants;

public class Movie implements Parcelable {

    @SerializedName("id")
    private String id_movie;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String rating;

    public Movie(){

    }

    public Movie(String id_movie, String popularity, String poster, String backdrop, String title, String overview, String releaseDate, String rating) {
        this.id_movie = id_movie;
        this.popularity = popularity;
        this.poster = poster;
        this.backdrop = backdrop;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        id_movie = in.readString();
        popularity = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        rating = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId_movie() {
        return id_movie;
    }

    public void setId_movie(String id_movie) {
        this.id_movie = id_movie;
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
        dest.writeString(id_movie);
        dest.writeString(popularity);
        dest.writeString(poster);
        dest.writeString(backdrop);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(rating);
    }
}
