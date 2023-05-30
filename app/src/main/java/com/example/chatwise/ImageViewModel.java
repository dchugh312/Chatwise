package com.example.chatwise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ImageViewModel extends ViewModel {

    private LiveData<List<images>> photosLiveData;
    private ImageRepository imageRepository;

    public ImageViewModel() {
        imageRepository = ImageRepository.getInstance();
    }

    public LiveData<List<images>> getPhotos() {
        if (photosLiveData == null) {
            photosLiveData = imageRepository.getPhotos();
        }
        return photosLiveData;
    }


}
