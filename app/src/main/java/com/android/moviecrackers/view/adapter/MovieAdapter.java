package com.android.moviecrackers.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.moviecrackers.R;
import com.android.moviecrackers.databinding.AdapterMovieLayoutBinding;
import com.android.moviecrackers.interfaces.ItemClickListener;
import com.android.moviecrackers.model.movielist.MovieResult;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<MovieResult> movieResultList;
    private AdapterMovieLayoutBinding movieLayoutBinding;
    private ViewHolder viewHolder;
    private MovieResult movieResult;
    private ItemClickListener itemClickListener;

    public MovieAdapter(Context mContext, List<MovieResult> movieResultList) {
        this.mContext = mContext;
        this.movieResultList = movieResultList;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        movieLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_movie_layout, parent, false);
        viewHolder = new ViewHolder(movieLayoutBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        movieResult = movieResultList.get(position);
        holder.adapterMovieLayoutBinding.setMovie(movieResult);
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AdapterMovieLayoutBinding adapterMovieLayoutBinding;

        ViewHolder(@NonNull AdapterMovieLayoutBinding adapterMovieLayoutBinding) {
            super(adapterMovieLayoutBinding.getRoot());
            this.adapterMovieLayoutBinding = adapterMovieLayoutBinding;
            this.adapterMovieLayoutBinding.executePendingBindings();
            this.adapterMovieLayoutBinding.lytMovieItemId.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
