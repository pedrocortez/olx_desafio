package br.com.cortez.desafio.repository.impl;

import java.net.UnknownHostException;
import java.util.List;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.model.Ad;
import br.com.cortez.desafio.presenter.event.InternetError;
import br.com.cortez.desafio.presenter.event.LoadAdsFailed;
import br.com.cortez.desafio.presenter.event.LoadAdsSuccess;
import br.com.cortez.desafio.repository.AdRepository;
import br.com.cortez.desafio.repository.http.AdHttpService;
import br.com.cortez.desafio.util.NetworkUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pedro on 8/24/16.
 */

public class AdRepositoryImpl implements AdRepository {


    @Override
    public void getAds() {
        AdHttpService adHttpService =
                ChallengeApplication.getInstance().provideRetrofit().create(AdHttpService.class);

        Call<List<Ad>> caller = adHttpService.getAllAds();

        caller.enqueue(new Callback<List<Ad>>() {
            @Override
            public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
                if(response.isSuccessful()) {
                    List<Ad> ads = response.body();
                    ChallengeApplication.getInstance().provideBus().post(new LoadAdsSuccess(ads));
                } else {
                    if(NetworkUtil.isNetworkAvailable()) {
                        ChallengeApplication.getInstance().provideBus().post(new LoadAdsFailed());
                    } else {
                        ChallengeApplication.getInstance().provideBus().post(new InternetError());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ad>> call, Throwable t) {
                if(t instanceof UnknownHostException) {
                    ChallengeApplication.getInstance().provideBus().post(new InternetError());
                } else {
                    ChallengeApplication.getInstance().provideBus().post(new LoadAdsFailed());
                }

            }
        });
    }
}
