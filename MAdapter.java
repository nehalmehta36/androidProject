package com.example.myapplication5;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder>{

    public List<RegisterData> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public MAdapter(List<RegisterData> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RegisterData movie = moviesList.get(position);
        Log.d("!@#","Adapter: "+movie.getUsername()+" "+movie.getEmail()+" "+movie.getPassword());
        holder.title.setText(movie.getUsername());
        holder.genre.setText(movie.getEmail());
        holder.year.setText(movie.getPassword());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
