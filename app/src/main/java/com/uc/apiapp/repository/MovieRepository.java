package com.uc.apiapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.apiapp.model.Movie;
import com.uc.apiapp.model.MovieResponse;
import com.uc.apiapp.network.ApiEndpoints;
import com.uc.apiapp.network.RetrofitService;
import com.uc.apiapp.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private ApiEndpoints apiEndpoints;
    private static final String TAG = "MovieRepository";

    public MovieRepository(ApiEndpoints apiEndpoints) {
        this.apiEndpoints = apiEndpoints;
    }

    public static MovieRepository getInstance(){
        if(movieRepository == null){
            movieRepository = new MovieRepository(RetrofitService.createService(ApiEndpoints.class));
        }
        return movieRepository;
    }

    public MutableLiveData<List<Movie>> getMovieCollection(){
        MutableLiveData<List<Movie>> listMovie = new MutableLiveData<>();

        apiEndpoints.getMovies(Constants.API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listMovie.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listMovie;
    }
}
