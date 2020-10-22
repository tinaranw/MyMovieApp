package com.uc.apiapp.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.apiapp.R;
import com.uc.apiapp.adapter.CastAdapter;
import com.uc.apiapp.model.Genre;
import com.uc.apiapp.model.Movie;
import com.uc.apiapp.model.TvShow;
import com.uc.apiapp.ui.MainActivity;
import com.uc.apiapp.ui.main.movie.MovieFragmentDirections;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    @BindView(R.id.backdrop_detail)
    ImageView backdrop;

    @BindView(R.id.poster_detail)
    ImageView poster;

    @BindView(R.id.title_detail)
    TextView title;

    @BindView(R.id.genre_detail)
    TextView genre;

    @BindView(R.id.desc_detail)
    TextView desc;

    @BindView(R.id.rate_detail)
    TextView vote;

    @BindView(R.id.rv_cast)
    RecyclerView rv_cast;

    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel viewModel;
    private CastAdapter adapter;
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastAdapter(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvshow();

            if (movie != null) {
                loadMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else if((tvShow != null)){
                loadTvShow(tvShow);
                observeTvShowViewModel(Integer.parseInt(tvShow.getId_tvshow()));
            }

        }
    }

    private void loadMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getBackdrop()).into(backdrop);
        Glide.with(getActivity()).load(movie.getPoster()).into(poster);
        title.setText(movie.getTitle());
        vote.setText(movie.getRating());
        desc.setText(movie.getOverview());
    }

    private void loadTvShow(TvShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(tvShow.getBackdrop()).into(backdrop);
        Glide.with(getActivity()).load(tvShow.getPoster()).into(poster);
        title.setText(tvShow.getTitle());
        vote.setText(tvShow.getRating());
        desc.setText(tvShow.getOverview());
    }

    private void observeMovieViewModel(int id) {
        viewModel.getMovieGenre(id).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getName() + " | ");
                    } else {
                        genre.append(g.getName());
                    }
                }
            }
        });
        viewModel.getMovieCast(id).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }

    private void observeTvShowViewModel(int id) {
        viewModel.getTvShowGenre(id).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getName() + " | ");
                    } else {
                        genre.append(g.getName());
                    }
                }
            }
        });
        viewModel.getTvShowCast(id).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }
}