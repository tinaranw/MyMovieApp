package com.uc.apiapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.apiapp.model.Cast;
import com.uc.apiapp.model.CastResponse;
import com.uc.apiapp.model.Genre;
import com.uc.apiapp.model.GenreResponse;
import com.uc.apiapp.model.TvShow;
import com.uc.apiapp.model.TvShowResponse;
import com.uc.apiapp.network.ApiEndpoints;
import com.uc.apiapp.network.RetrofitService;
import com.uc.apiapp.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository {
    private static TvShowRepository tvShowRepository;
    private ApiEndpoints apiEndpoints;
    private static final String TAG = "TvShowRepository";

    public TvShowRepository(ApiEndpoints apiEndpoints) {
        this.apiEndpoints = apiEndpoints;
    }

    public static TvShowRepository getInstance(){
        if(tvShowRepository == null){
            tvShowRepository = new TvShowRepository(RetrofitService.createService(ApiEndpoints.class));
        }
        return tvShowRepository;
    }

    public MutableLiveData<List<TvShow>> getTvShowCollection(){
        MutableLiveData<List<TvShow>> listTvShow = new MutableLiveData<>();

        apiEndpoints.getTvShow(Constants.API_KEY).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listTvShow.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listTvShow;
    }

    public MutableLiveData<List<Genre>> getGenres(int id) {
        MutableLiveData<List<Genre>> listGenres = new MutableLiveData<>();

        apiEndpoints.getGenres(Constants.Type.TV_SHOWS,id, Constants.API_KEY).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listGenres.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listGenres;
    }

    public MutableLiveData<List<Cast>> getCasts(int id) {
        MutableLiveData<List<Cast>> listCasts = new MutableLiveData<>();

        apiEndpoints.getCasts(Constants.Type.TV_SHOWS, id, Constants.API_KEY).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listCasts.postValue(response.body().getCast());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCasts;
    }
}
