package com.example.loggeospring.domain.servicios_api.cabaService.searchGeo;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.SearchGeoLocation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceGeoLocationSearch {
  //recordar que es el endopoint de la api propiamente, sino no funciona
  @GET("reverseGeocoderLugares")
  Call<SearchGeoLocation> searchGeoLocation(@Query("x") String x, @Query("y") String y,@Query("radio")String radio);
}
