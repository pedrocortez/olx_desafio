package br.com.cortez.desafio.imageLoader.impl;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.imageLoader.ImageLoader;

/**
 * Created by Pedro on 8/22/16.
 */

public class PicassoImageLoader implements ImageLoader {

    private WeakReference<Context> contextWeakReference;

    @Override
    public void load(String url, ImageView imageView) {
        Picasso.with(getContext())
                .load(url)
                .into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, int placeHolder) {
        Picasso.with(getContext())
                .load(url)
                .placeholder(placeHolder)

                .into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, int width, int height) {
        Picasso.with(getContext())
                .load(url)
                .resize(width, height)
                .into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, int placeholder, int width, int height) {
        Picasso.with(getContext())
                .load(url)
                .placeholder(placeholder)
                .resize(width, height)
                .into(imageView);
    }

    private Context getContext() {
        if(contextWeakReference == null || contextWeakReference.get() == null) {
            contextWeakReference = new WeakReference<Context>(ChallengeApplication.getInstance());
        }
        return contextWeakReference.get();
    }
}
