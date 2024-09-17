package com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom.CustomSearch;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioCustomBusqueda {

  private static ServicioCustomBusqueda instance = null;
  private static final String urlAPI = "https://datosabiertos-catastro-apis.buenosaires.gob.ar";

  private Retrofit retrofit;

  private ServicioCustomBusqueda() {
    this.retrofit = new Retrofit.Builder()
        // url - add converter - construir
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  // patron singleton
  public static ServicioCustomBusqueda getInstance() {
    if (instance == null) {
      instance = new ServicioCustomBusqueda();
    }
    return instance;

  }


  /****************************************** TRAER ID   ********************************************* */
  public CustomSearch getObjectContenidos(String id) throws IOException {
    SearchCustom busquedaPersonalizado = this.retrofit.create(SearchCustom.class);
    Call<CustomSearch> requeestCustom = busquedaPersonalizado.getObjetsContenido(id);
    Response<CustomSearch> responseCustom = requeestCustom.execute();
    return responseCustom.body();
  }

}