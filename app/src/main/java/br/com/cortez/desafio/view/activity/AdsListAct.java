package br.com.cortez.desafio.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_list);
        ButterKnife.bind(this);

        imageLoader = ChallengeApplication.getInstance().provideImageLoader();

        toolbar.setTitle(R.string.ads_list_title);
        setSupportActionBar(toolbar);


        adapter = new AdAdapter(getAdListener());


        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.grid_space);
        int gridSize = 2;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GridDividerItemDecoration gridDividerItemDecoration = new GridDividerItemDecoration(dimensionPixelSize, gridSize);

        listAds.addItemDecoration(gridDividerItemDecoration);
        listAds.setAdapter(adapter);
        listAds.setLayoutManager(gridLayoutManager);


        adsListPresenter = ChallengeApplication.getInstance().provideAdsListPresenter(this);
        adsListPresenter.loadViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
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

        int dimension = (int) getResources().getDimension(R.dimen.ad_thumbnail_size);
        imageLoader.load(R.drawable.no_connection, errorImage, dimension, dimension);
    }

    private AdAdapter.OnAdClickListener getAdListener() {
        return new AdAdapter.OnAdClickListener() {
            @Override
            public void onClick(Ad ad) {
                AdDetailsAct.start(AdsListAct.this, ad);
            }
        };
    }
}
