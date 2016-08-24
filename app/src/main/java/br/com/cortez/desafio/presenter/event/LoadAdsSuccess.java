package br.com.cortez.desafio.presenter.event;

import java.util.List;

import br.com.cortez.desafio.model.Ad;

public class LoadAdsSuccess {

    private List<Ad> ads;

    public LoadAdsSuccess(List<Ad> ads) {
        this.ads = ads;
    }

    public List<Ad> getAds() {
        return ads;
    }
}
