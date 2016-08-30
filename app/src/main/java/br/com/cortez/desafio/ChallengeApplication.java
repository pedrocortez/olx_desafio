package br.com.cortez.desafio;

import android.app.Application;

import com.squareup.otto.Bus;

import java.io.IOException;

import br.com.cortez.desafio.imageLoader.ImageLoader;
import br.com.cortez.desafio.imageLoader.impl.PicassoImageLoader;
import br.com.cortez.desafio.presenter.AdsListPresenter;
import br.com.cortez.desafio.repository.AdRepository;
import br.com.cortez.desafio.repository.impl.AdRepositoryImpl;
import br.com.cortez.desafio.util.NetworkUtil;
import br.com.cortez.desafio.view.ListAdsView;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pedro on 8/22/16.
 */

public class ChallengeApplication extends Application {

    static ChallengeApplication instance;

    private ImageLoader imageLoader;
    private Retrofit retrofit;
    private Bus bus;

    private AdRepository adRepository;
    private AdsListPresenter adsListPresenter;

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


    public Retrofit provideRetrofit() {

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .cache(new Cache(getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                    .addInterceptor(new Interceptor() {
                        @Override public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            if (NetworkUtil.isNetworkAvailable()) {
                                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                            } else {
                                request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                            }
                            return chain.proceed(request);
                        }
                    })

                    .build();




            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ahul.github.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public Bus provideBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }


    public AdRepository provideAdRepository() {
        if(adRepository == null) {
            adRepository = new AdRepositoryImpl();
        }
        return adRepository;
    }

    public AdsListPresenter provideAdsListPresenter(ListAdsView listAdsView) {
        if(adsListPresenter == null) {
            adsListPresenter = new AdsListPresenter(listAdsView, provideAdRepository());
        }
        return adsListPresenter;
    }
}
