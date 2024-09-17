package com.example.loggeospring.domain.servicios_api.weather;
import okhttp3.HttpUrl; // para laburar con las solicitudes y rtas de protcolo http - apuntado para la api key
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class InterceptorAPI implements Interceptor {
  private final String apikey;
  //constructor
  public InterceptorAPI(String apikey) {
    this.apikey = apikey;
  }
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originRequest = chain.request(); //obtengo la solicitud http orogina
    HttpUrl modifiedUrl = originRequest.url().newBuilder()
        .addQueryParameter("appid", apikey).build(); // y a cada linea le agregare la modificacion - en este caso es la api key
    Request modifiedRequest = originRequest.newBuilder()
        .url(modifiedUrl)
        .build();
    return chain.proceed(modifiedRequest); // devuelvo el link modificado, pero en realida esto lo usaria el climaOpenSource
  }
}
