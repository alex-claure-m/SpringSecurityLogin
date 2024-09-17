package com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categoria;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchCategories {

  @GET("/getCategorias")
  Call<Categoria> getCategorias();
}
