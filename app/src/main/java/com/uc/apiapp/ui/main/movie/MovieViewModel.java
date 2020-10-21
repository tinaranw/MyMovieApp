package com.uc.apiapp.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.apiapp.model.Movie;
import com.uc.apiapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final MovieRepository repository;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovieCollection(){
        return repository.getMovieCollection();
    }
}
