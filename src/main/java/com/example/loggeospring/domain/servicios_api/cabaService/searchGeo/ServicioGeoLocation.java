package com.example.loggeospring.domain.servicios_api.cabaService.searchGeo;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.SearchGeoLocation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoLocation {
  private static ServicioGeoLocation instance = null; // singleton inicializado con null
  private static final String urlAPI = "https://datosabiertos-catastro-apis.buenosaires.gob.ar";
  private Retrofit retrofit;

  private ServicioGeoLocation() {
    this.retrofit = new Retrofit.Builder()
        // url - add converter - construir
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
  public static ServicioGeoLocation getInstance() {
    if (instance == null) {
      instance = new ServicioGeoLocation();
    }
    return instance;

  }
  /******************************SEARCH GEOLOCATION - GETINSTANCE ************* ******** */
  public SearchGeoLocation searchLocation(String x, String y, String radio) throws IOException {
    ServiceGeoLocationSearch serviceGeoLocationSearch = this.retrofit.create(ServiceGeoLocationSearch.class);
    Call<SearchGeoLocation> requestGeoLocation = serviceGeoLocationSearch.searchGeoLocation(x, y,radio);
    Response<SearchGeoLocation> responseGeoLocation = requestGeoLocation.execute();
    System.out.println("esto es el response:" + responseGeoLocation);
    return responseGeoLocation.body();
  }

}
