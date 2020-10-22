package com.uc.apiapp.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.apiapp.model.TvShow;
import com.uc.apiapp.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private TvShowRepository repository;

    public TvShowViewModel() {
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }
}
