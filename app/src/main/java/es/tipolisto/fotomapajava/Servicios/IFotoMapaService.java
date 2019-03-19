package es.tipolisto.fotomapajava.Servicios;

import java.util.List;

import es.tipolisto.fotomapajava.Entidades.Foto;
import es.tipolisto.fotomapajava.Entidades.ListaFotos;
import es.tipolisto.fotomapajava.Entidades.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IFotoMapaService {
    @GET("photo/showall")
    Call<List<Foto>> getFotos();

    @GET("photo/show/{id}")
    Call<Foto> getFoto(@Path("id") int id);


    /**
     * User
     */
    @FormUrlEncoded
    @POST("usuario/login")
    Call<String> login(@Field("name") String name, @Field("password") String password);

    @GET("usuario/showall")
    Call<List<User>> getUsuarios();
}
