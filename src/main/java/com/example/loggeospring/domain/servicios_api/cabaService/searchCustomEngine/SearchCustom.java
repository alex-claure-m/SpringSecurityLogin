package com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom.CustomSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchCustom {

  @GET("/getObjectContent")
  //bueno, esta api labura con un id, pero solamente me servira para verificar condiciones ya que tiene que pasarse cosas
  Call<CustomSearch> getObjetsContenido(@Query("id") String id);
}
