package br.com.cortez.desafio.view;

import java.util.List;

import br.com.cortez.desafio.model.Ad;

public interface ListAdsView {

    void showLoading();

    void hideLoading();

    void showAds(List<Ad> ads);

    void showError();

}
