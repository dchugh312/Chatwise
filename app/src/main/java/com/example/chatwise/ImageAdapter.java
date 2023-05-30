package com.example.chatwise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    protected List<images> imageList;

    public ImageAdapter(List<images> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        images image = imageList.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setImageList(List<images> photos) {

            imageList = photos;
        }



    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView idTextView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            idTextView = itemView.findViewById(R.id.imgId);
        }

        public void bind(images image) {
            idTextView.setText(String.valueOf(image.getId()));
            Picasso.get().load(image.getUrl()).into(imageView);
        }
    }
}
