package com.uc.apiapp.ui.main.tvShow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.apiapp.R;
import com.uc.apiapp.adapter.TvShowAdapter;
import com.uc.apiapp.model.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TvShowFragment extends Fragment {

    @BindView(R.id.progressBarLoadingTvShow)
    ProgressBar loading;

    @BindView(R.id.rv_tvshow)
    RecyclerView rv_tvshow;

    private TvShowViewModel viewModel;
    private TvShowAdapter tvShowAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        loading(true);
        tvShowAdapter = new TvShowAdapter(getContext());

        rv_tvshow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tvshow.setAdapter(tvShowAdapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
        if(tvShows != null){
            //set adapter
            tvShowAdapter.setListTvShow(tvShows);
            tvShowAdapter.notifyDataSetChanged();
            loading(false);
        }
    };

    private void loading(Boolean state) {
        if (state) {
            rv_tvshow.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rv_tvshow.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}