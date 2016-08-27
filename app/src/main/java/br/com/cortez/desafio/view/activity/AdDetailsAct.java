package br.com.cortez.desafio.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.R;
import br.com.cortez.desafio.imageLoader.ImageLoader;
import br.com.cortez.desafio.model.Ad;
import br.com.cortez.desafio.view.AdDetailsView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdDetailsAct extends AppCompatActivity implements AdDetailsView{


    private static final String EXTRA_AD = "ad";
    private Ad ad;
    private ImageLoader imageLoader;

    @BindView(R.id.olx_ad_photo)
    ImageView detail;
    @BindView(R.id.olx_ad_title)
    TextView title;
    @BindView(R.id.olx_ad_price)
    TextView price;
    @BindView(R.id.olx_ad_date)
    TextView date;
    @BindView(R.id.olx_ad_seller)
    TextView seller;
    @BindView(R.id.olx_ad_zipcode)
    TextView zipcode;
    @BindView(R.id.olx_ad_detail_toolbar)
    Toolbar toolbar;



    public static void start(Activity activity, Ad ad) {
        Intent i = new Intent(activity, AdDetailsAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_AD, ad);
        i.putExtras(bundle);
        activity.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_details);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        ad = (Ad) extras.getSerializable(EXTRA_AD);

        imageLoader = ChallengeApplication.getInstance().provideImageLoader();

        showAdDetail();

    }


    @Override
    public void showAdDetail() {

        setSupportActionBar(toolbar);

        imageLoader.load(ad.getPhotos().getDetailHd(), detail);
        title.setText(ad.getTitle());
        price.setText(ad.getFormattedPrice());
        date.setText(ad.getDate());
        seller.setText(ad.getSeller());
        zipcode.setText(ad.getZipcode());

    }
}
