package com.uc.apiapp.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.apiapp.model.Cast;
import com.uc.apiapp.model.Genre;
import com.uc.apiapp.repository.MovieRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public DetailViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id) {
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id) {
        return movieRepository.getCasts(id);
    }
}

