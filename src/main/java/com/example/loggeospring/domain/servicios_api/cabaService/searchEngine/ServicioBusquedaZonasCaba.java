package com.example.loggeospring.domain.servicios_api.cabaService.searchEngine;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioBusquedaZonasCaba {
  private static ServicioBusquedaZonasCaba instance = null;
  private static final String urlAPI = "https://datosabiertos-catastro-apis.buenosaires.gob.ar";

  private Retrofit retrofit;

  private ServicioBusquedaZonasCaba() {
    this.retrofit = new Retrofit.Builder()
        // url - add converter - construir
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  // patron singleton
  public static ServicioBusquedaZonasCaba getInstance() {
    if (instance == null) {
      instance = new ServicioBusquedaZonasCaba();
    }
    return instance;

  }

  /* ************************************** ZONA INTERES ********************************************** */
  public InteresZonaSearch listadoDeInteres(String busqueda) throws IOException {
  	SearchZoneInteresService searchZoneInteresService = this.retrofit.create(SearchZoneInteresService.class);
    Call<InteresZonaSearch> requeestInteresZona = searchZoneInteresService.zonasInteres(busqueda);
    Response<InteresZonaSearch> responseInteresZona = requeestInteresZona.execute();
    //System.out.println("esto es el response:" +responseInteresZona);
    return responseInteresZona.body();
  }

}
