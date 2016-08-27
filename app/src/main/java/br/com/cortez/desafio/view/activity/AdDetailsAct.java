package br.com.cortez.desafio.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.cortez.desafio.R;
import br.com.cortez.desafio.model.Ad;

public class AdDetailsAct extends AppCompatActivity {


    public static void start(Activity activity, Ad ad) {
        Intent i = new Intent(activity, AdDetailsAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Ad", ad);
        i.putExtras(bundle);
        activity.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_details);
    }

}
