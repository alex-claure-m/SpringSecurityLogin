package com.example.loggeospring.domain.servicios_api.weather;


import com.example.loggeospring.domain.servicios_api.weather.entities.Pais;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit; // son 2 dependencias retrofit distintos,
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


public class ServicioClimaOpenSource {
	private static ServicioClimaOpenSource instance = null; // patron singleton

	private Retrofit retrofit;


	private ServicioClimaOpenSource() {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(new InterceptorAPI("a4f6298da7fe7b4beb55b1aae7cdd1dc"))
        .build();

    this.retrofit = new Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

	public static ServicioClimaOpenSource getInstance() {
    if (instance == null) {
      instance = new ServicioClimaOpenSource();
    }
    return instance;
  }

  /* ********************** ACA DEBERIA HACER UN TESTEO DE COMO TRAE LAS COSAS ***************************** */
  //me trae el body del pais
	public Pais getCountry(String city) throws IOException {
    ClimaOpenSourceService climaOpenSource = this.retrofit.create(ClimaOpenSourceService.class);
    Call<Pais> requestPaisArg = climaOpenSource.obtenerClimaPorCiudad(city);
    Response<Pais> responsePaisArgs = requestPaisArg.execute();
    //System.out.println("a ver si funca el response "+ responsePaisArgs);
    return responsePaisArgs.body();
  }
}
