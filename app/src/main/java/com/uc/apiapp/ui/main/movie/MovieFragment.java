package com.uc.apiapp.ui.main.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.uc.apiapp.R;
import com.uc.apiapp.adapter.MovieAdapter;
import com.uc.apiapp.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieFragment extends Fragment {

    @BindView(R.id.progressBarLoadingMovie)
    ProgressBar loading;

    @BindView(R.id.rv_movie)
    RecyclerView rv_movie;

    private MovieViewModel viewModel;
    private MovieAdapter movieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        loading(true);
        movieAdapter = new MovieAdapter(getContext());

        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie.setAdapter(movieAdapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

//        button.setOnClickListener(view1 -> {
//            NavDirections action = MovieFragmentDirections.actionMovieToDetail(movie);
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if(movies != null){
            //set adapter
            movieAdapter.setListMovie(movies);
            movieAdapter.notifyDataSetChanged();
            loading(false);
        }
    };

    private void loading(Boolean state) {
        if (state) {
            rv_movie.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rv_movie.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}