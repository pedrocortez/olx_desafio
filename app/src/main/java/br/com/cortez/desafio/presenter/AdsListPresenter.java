package br.com.cortez.desafio.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.model.Ad;
import br.com.cortez.desafio.view.ListAdsView;

/**
 * Created by Pedro on 8/20/16.
 */

public class AdsListPresenter {

    private ListAdsView listAdsView;

    public AdsListPresenter(ListAdsView listAdsView) {
        this.listAdsView = listAdsView;
    }

    public void loadViews() {
        listAdsView.showLoading();
        onLoadAdsSuccess();
    }

    public void onLoadAdsSuccess() {

        listAdsView.hideLoading();
        listAdsView.showAds(readFromAssetsToShowAds());

    }

    private List<Ad> readFromAssetsToShowAds() {
        BufferedReader reader = null;
        List<Ad> ads = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getContext().getAssets().open("ads.json"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            StringBuffer jsonStr  = new StringBuffer("");


            while ((mLine = reader.readLine()) != null) {
                jsonStr.append(mLine);
            }

            Type listType = new TypeToken<ArrayList<Ad>>(){}.getType();

            ads = new Gson().fromJson(jsonStr.toString(), listType);


        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return ads;
    }

    private Context getContext() {
        return ChallengeApplication.getInstance();
    }

}
