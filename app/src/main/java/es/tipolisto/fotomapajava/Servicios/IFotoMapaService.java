package es.tipolisto.fotomapajava.Servicios;

import es.tipolisto.fotomapajava.Entidades.ListaFotos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IFotoMapaService {
    @GET("showall")
    Call<ListaFotos> getFotos();
}
