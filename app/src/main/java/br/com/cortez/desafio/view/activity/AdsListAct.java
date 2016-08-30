package br.com.cortez.desafio.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.R;
import br.com.cortez.desafio.adapter.AdAdapter;
import br.com.cortez.desafio.imageLoader.ImageLoader;
import br.com.cortez.desafio.model.Ad;
import br.com.cortez.desafio.presenter.AdsListPresenter;
import br.com.cortez.desafio.view.ListAdsView;
import br.com.cortez.desafio.view.decoration.GridDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AdsListAct extends AppCompatActivity implements ListAdsView {


    @BindView(R.id.olx_listads)
    RecyclerView listAds;

    @BindView(R.id.olx_layout_loading)
    View loading;

    @BindView(R.id.olx_error_layout)
    View error;
    @BindView(R.id.olx_error_text)
    TextView errorText;
    @BindView(R.id.olx_error_image)
    ImageView errorImage;

    @BindView(R.id.olx_toolbar)
    Toolbar toolbar;

    private AdsListPresenter adsListPresenter;
    private AdAdapter adapter;
    private ImageLoader imageLoader;

    int gridSpanCount;
    GridDividerItemDecoration gridDividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_list);
        ButterKnife.bind(this);

        imageLoader = ChallengeApplication.getInstance().provideImageLoader();

        toolbar.setTitle(R.string.ads_list_title);
        setSupportActionBar(toolbar);

        configSpanCount();

        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.grid_space);
        gridDividerItemDecoration = new GridDividerItemDecoration(dimensionPixelSize, gridSpanCount);
        listAds.addItemDecoration(gridDividerItemDecoration);
        adapter = new AdAdapter(getAdListener());

        listAds.setAdapter(adapter);
        configLayoutGrid();


        adsListPresenter = ChallengeApplication.getInstance().provideAdsListPresenter(this);
        adsListPresenter.loadViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adsListPresenter.resume();
    }

    @Override
    protected void onPause() {
        adsListPresenter.pause();
        super.onPause();
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
    public void showGenericError() {
        showError();
        errorText.setText(R.string.generic_error);

        int dimension = (int) getResources().getDimension(R.dimen.ad_thumbnail_size);
        imageLoader.load(R.drawable.generic_error, errorImage, dimension, dimension);
    }

    public void showError() {
        loading.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        listAds.setVisibility(View.GONE);
    }

    @Override
    public void showInternetError() {
        showError();
        errorText.setText(R.string.internet_error);
    }

    private AdAdapter.OnAdClickListener getAdListener() {
        return new AdAdapter.OnAdClickListener() {
            @Override
            public void onClick(Ad ad) {
                AdDetailsAct.start(AdsListAct.this, ad);
            }
        };
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        configSpanCount();
        configLayoutGrid();
    }

    private void configSpanCount() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSpanCount = 3;
        } else {
            gridSpanCount = 2;
        }
    }

    private void configLayoutGrid() {


        listAds.removeItemDecoration(gridDividerItemDecoration);
        listAds.invalidateItemDecorations();

        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.grid_space);
        gridDividerItemDecoration = new GridDividerItemDecoration(dimensionPixelSize, gridSpanCount);
        listAds.addItemDecoration(gridDividerItemDecoration);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, gridSpanCount);
        listAds.setLayoutManager(gridLayoutManager);


        listAds.invalidate();
        adapter.notifyDataSetChanged();

    }
}
