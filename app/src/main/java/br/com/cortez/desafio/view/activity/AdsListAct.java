package br.com.cortez.desafio.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.cortez.desafio.R;
import br.com.cortez.desafio.adapter.AdAdapter;
import br.com.cortez.desafio.adapter.decoration.VerticalSelectionDividerItemDecoration;
import br.com.cortez.desafio.model.Ad;
import br.com.cortez.desafio.presenter.AdsListPresenter;
import br.com.cortez.desafio.view.ListAdsView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AdsListAct extends AppCompatActivity implements ListAdsView{


    @BindView(R.id.olx_listads)
    RecyclerView listAds;

    @BindView(R.id.olx_layout_loading)
    View loading;

    @BindView(R.id.olx_error_layout)
    View error;

    private AdsListPresenter adsListPresenter;
    private AdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_list);
        ButterKnife.bind(this);

        adapter = new AdAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        VerticalSelectionDividerItemDecoration verticalSelectionDividerItemDecoration =
                new VerticalSelectionDividerItemDecoration(R.dimen.space_recycler, 1);

        listAds.addItemDecoration(verticalSelectionDividerItemDecoration);
        listAds.setAdapter(adapter);
        listAds.setLayoutManager(linearLayoutManager);


        adsListPresenter = new AdsListPresenter(this);
        adsListPresenter.loadViews();


    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
        listAds.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        listAds.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAds(List<Ad> ads) {
        adapter.addItens(ads);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }
}
