package com.example.loggeospring.domain.servicios_api.cabaService.searchEngine;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchZoneInteresService {

  @GET("buscar")
  Call<InteresZonaSearch> zonasInteres(@Query("texto") String texto);

}
