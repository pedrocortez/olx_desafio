package br.com.cortez.desafio;

import android.app.Application;

import br.com.cortez.desafio.imageLoader.ImageLoader;
import br.com.cortez.desafio.imageLoader.impl.PicassoImageLoader;

/**
 * Created by Pedro on 8/22/16.
 */

public class ChallengeApplication extends Application {

    static ChallengeApplication instance;

    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ChallengeApplication getInstance(){
        return instance;
    }

    public ImageLoader provideImageLoader() {
        if(imageLoader == null) {
            imageLoader = new PicassoImageLoader();
        }
        return imageLoader;
    }
}
