package com.example.movieandroidapp.view.user;

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

public class ProfileNavigationAdapter extends ArrayAdapter<String> {

    public ProfileNavigationAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_selected,parent,false);
        TextView tvSelected = convertView.findViewById(R.id.tv_dropdown_selected);

        String item = this.getItem(position);
        if(item != null){
            tvSelected.setText(item);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_item,parent,false);
        TextView itemProfileNavigate = convertView.findViewById(R.id.tv_dropdown_item);

        String item = this.getItem(position);
        if(item != null){
            itemProfileNavigate.setText(item);
        }
        return convertView;
    }
}
