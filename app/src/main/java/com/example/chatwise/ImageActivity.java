package com.example.chatwise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageAdapter = new ImageAdapter(new ArrayList<>());
        recyclerView.setAdapter(imageAdapter);

        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.getPhotos().observe(this, photos -> {
            imageAdapter.setImageList(photos);
            imageAdapter.notifyDataSetChanged();


            recyclerView.setVisibility(View.VISIBLE);
        });
    }
}