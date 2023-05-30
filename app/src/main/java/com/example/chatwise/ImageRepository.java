package com.example.chatwise;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository {
    private static final String TAG = ImageRepository.class.getSimpleName();
    private static ImageRepository instance;
    private MutableLiveData<List<images>> photosLiveData;

    private ImageRepository() {
        photosLiveData = new MutableLiveData<>();
    }

    public static ImageRepository getInstance() {
        if (instance == null) {
            instance = new ImageRepository();
        }
        return instance;
    }

    public LiveData<List<images>> getPhotos() {
        if (photosLiveData.getValue() == null) {
            String apiUrl = "https://jsonplaceholder.typicode.com/photos";

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            List<images> photos = parsePhotosFromResponse(response);
                            setPhotosLiveData(photos);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "Error fetching photos: " + error.getMessage());

                        }
                    });

            Context context = MyApplication.getInstance().getApplicationContext();
            Volley.newRequestQueue(context).add(jsonArrayRequest);
        }

        return photosLiveData;
    }

    private void setPhotosLiveData(List<images> photos) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                photosLiveData.setValue(photos);
            }
        });
    }

    private List<images> parsePhotosFromResponse(JSONArray jsonArray) {
        List<images> photos = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonPhoto = jsonArray.getJSONObject(i);
                int id = jsonPhoto.getInt("id");
                String url = jsonPhoto.getString("url");

                images photo = new images(id, url);
                photos.add(photo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return photos;
    }
}

