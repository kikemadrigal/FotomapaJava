package es.tipolisto.fotomapajava.Servicios;

import java.util.List;

import es.tipolisto.fotomapajava.Entidades.Foto;
import es.tipolisto.fotomapajava.Entidades.ListaFotos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IFotoMapaService {
    @GET("photo/showall")
    Call<List<Foto>> getFotos();

}
