package es.tipolisto.fotomapajava.Servicios;

import es.tipolisto.fotomapajava.Utilidades.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static IFotoMapaService getService(){
        //Preparamos la instancia de retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_FOTMAPA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creamos una clase que implemente la instancia del servicio
        IFotoMapaService iFotoMapaService=retrofit.create(IFotoMapaService.class);
        return iFotoMapaService;
    }
}
