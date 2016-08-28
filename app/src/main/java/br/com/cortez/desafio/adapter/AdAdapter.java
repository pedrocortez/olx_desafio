package br.com.cortez.desafio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cortez.desafio.ChallengeApplication;
import br.com.cortez.desafio.R;
import br.com.cortez.desafio.imageLoader.ImageLoader;
import br.com.cortez.desafio.model.Ad;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.ViewHolder> {

    private List<Ad> ads;
    private ImageLoader imageLoader;
    private OnAdClickListener onAdClickListener;
    private WeakReference<Context> context;

    public AdAdapter(OnAdClickListener onAdClickListener) {
        ads = new ArrayList<>();
        this.onAdClickListener = onAdClickListener;
        imageLoader = ChallengeApplication.getInstance().provideImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comp_ads_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Ad ad = ads.get(position);


        int dimension = (int) getContext().getResources().getDimension(R.dimen.ad_thumbnail_size);
        imageLoader.load(ad.getPhotos().getThumbnail(), holder.thumbnail, dimension, dimension);


        holder.title.setText(ad.getTitle());
        holder.date.setText(ad.getDate());
        holder.price.setText(ad.getFormattedPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdClickListener.onClick(ad);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public void addItens(List<Ad> ads) {
        this.ads.addAll(ads);
    }



    private Context getContext() {
        if(context == null || context.get() == null) {
            context = new WeakReference<Context>(ChallengeApplication.getInstance());
        }
        return context.get();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.olx_ads_thumbnail)
        ImageView thumbnail;
        @BindView(R.id.olx_ads_title)
        TextView title;
        @BindView(R.id.olx_ads_price)
        TextView price;
        @BindView(R.id.olx_ads_date)
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }



    public interface OnAdClickListener {

        void onClick(Ad ad);
    }
}
