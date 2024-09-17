package com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categoria;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;

public class ServiceCategories {

  private static ServiceCategories instance = null;
  private static final String urlAPI = "https://datosabiertos-catastro-apis.buenosaires.gob.ar";

  private Retrofit retrofit;

  private ServiceCategories() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
  public static ServiceCategories getInstance() {
    if (instance == null) {
      instance = new ServiceCategories();
    }
    return instance;
  }

  /* **************************************** GET CATEGORIAS **************************** */
  public Categoria getCategoriasTotales() throws IOException {
    SearchCategories categories = this.retrofit.create(SearchCategories.class);
    Call<Categoria> requeestCategorias = categories.getCategorias(); // esta bien por que la interfaz del CALL devuelve CATEGORIA class
    Response<Categoria> responseCategorias = requeestCategorias.execute();
    System.out.println(responseCategorias.body());
    return responseCategorias.body();
  }


}
