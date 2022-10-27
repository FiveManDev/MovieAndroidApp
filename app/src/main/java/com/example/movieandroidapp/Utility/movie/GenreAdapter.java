package com.example.movieandroidapp.Utility.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.model.Genre;

import java.util.List;

public class GenreAdapter extends ArrayAdapter<Genre> {

    public GenreAdapter(@NonNull Context context, int resource, @NonNull List<Genre> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_selected,parent,false);
        TextView tvSelected = convertView.findViewById(R.id.tv_dropdown_selected);

        Genre genre = this.getItem(position);
        if(genre != null){
            tvSelected.setText(genre.getGenreName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_item,parent,false);
        TextView tvGenre = convertView.findViewById(R.id.tv_dropdown_item);

        Genre genre = this.getItem(position);
        if(genre != null){
            tvGenre.setText(genre.getGenreName());
        }
        return convertView;
    }
}
