package br.com.cortez.desafio.imageLoader;

import android.widget.ImageView;

public interface ImageLoader {

    void load(String url, ImageView imageView);

    void load(String url, ImageView imageView, int placeHolder);

    void load(String url, ImageView imageView, int width, int height);

    void load(int resource, ImageView imageView, int width, int height);

    void load(String url, ImageView imageView, int placeholder,  int width, int height);

}
