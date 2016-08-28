package br.com.cortez.desafio.presenter;

import com.squareup.otto.Subscribe;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.presenter.event.InternetError;
import br.com.cortez.desafio.presenter.event.LoadAdsFailed;
import br.com.cortez.desafio.presenter.event.LoadAdsSuccess;
import br.com.cortez.desafio.repository.AdRepository;
import br.com.cortez.desafio.view.ListAdsView;

/**
 * Created by Pedro on 8/20/16.
 */

public class AdsListPresenter {

    private ListAdsView listAdsView;
    private AdRepository adRepository;

    public AdsListPresenter(ListAdsView listAdsView, AdRepository adRepository) {
        this.listAdsView = listAdsView;
        this.adRepository = adRepository;
    }

    public void loadViews() {
        listAdsView.showLoading();
        adRepository.getAds();
    }

    @Subscribe
    public void onLoadAdsSuccess(LoadAdsSuccess loadAdsSuccess) {
        listAdsView.hideLoading();
        listAdsView.showAds(loadAdsSuccess.getAds());
    }

    @Subscribe
    public void onFailedLoadAds(LoadAdsFailed loadAdsFailed) {
        listAdsView.hideLoading();
        listAdsView.showGenericError();
    }

    @Subscribe
    public void onFailedLoadAds(InternetError internetError) {
        listAdsView.hideLoading();
        listAdsView.showInternetError();
    }




    public void resume() {
        ChallengeApplication.getInstance().provideBus().register(this);
    }

    public void pause() {
        ChallengeApplication.getInstance().provideBus().unregister(this);
    }

}
