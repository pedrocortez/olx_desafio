package br.com.cortez.desafio.repository.http;

import java.util.List;

import br.com.cortez.desafio.model.Ad;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AdHttpService {


    @GET("ads.json")
    Call<List<Ad>> getAllAds();

}
