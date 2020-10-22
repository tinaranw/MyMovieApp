package com.uc.apiapp.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.apiapp.model.Cast;
import com.uc.apiapp.model.Genre;
import com.uc.apiapp.repository.MovieRepository;
import com.uc.apiapp.repository.TvShowRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;

    public DetailViewModel() {
        this.movieRepository = MovieRepository.getInstance();
        this.tvShowRepository = TvShowRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id) {
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id) {
        return movieRepository.getCasts(id);
    }

    public LiveData<List<Genre>> getTvShowGenre(int id) {
        return tvShowRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getTvShowCast(int id) {
        return tvShowRepository.getCasts(id);
    }
}

