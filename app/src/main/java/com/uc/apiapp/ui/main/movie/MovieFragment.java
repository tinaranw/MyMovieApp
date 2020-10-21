package com.uc.apiapp.ui.main.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.uc.apiapp.R;
import com.uc.apiapp.model.Movie;
import com.uc.apiapp.ui.splash.SplashFragmentDirections;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieFragment extends Fragment {

    @BindView(R.id.btnToDetail)
    Button button;

    private MovieViewModel viewModel;

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

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

        Movie movie= new Movie();

        button.setOnClickListener(view1 -> {
            NavDirections action = MovieFragmentDirections.actionDetail(movie);
            Navigation.findNavController(view).navigate(action);
        });
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if(movies != null){
            Movie movie = movies.get(0);
            button.setText(movie.getTitle());
            Toast.makeText(requireActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();
            //set adapter
//                adapter.setMovies(movies);
//                adapter.notifySetDataChanged();
//                recyclerView.setAdapter(adapter);
            //add adapter to recyclerview
        }
    };
}