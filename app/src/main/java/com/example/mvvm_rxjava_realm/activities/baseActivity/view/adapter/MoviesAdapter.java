package com.example.mvvm_rxjava_realm.activities.baseActivity.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.baseActivity.viewModel.MovieViewModel;
import com.example.mvvm_rxjava_realm.databinding.ItemMovieBinding;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<MovieViewModel> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MoviesAdapter(ArrayList<MovieViewModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemMovieBinding itemMovieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);

        return new ViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieViewModel movieViewModel = arrayList.get(position);
        holder.bind(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding itemMovieBinding;

        public ViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }

        private void bind(MovieViewModel movieViewModel) {
            this.itemMovieBinding.setItem(movieViewModel);
            this.itemMovieBinding.executePendingBindings();
        }

        public ItemMovieBinding getItemMovieBinding() {
            return itemMovieBinding;
        }
    }
}
