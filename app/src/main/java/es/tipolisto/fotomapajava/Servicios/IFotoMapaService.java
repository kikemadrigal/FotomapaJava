package es.tipolisto.fotomapajava.Servicios;

import java.util.List;

import es.tipolisto.fotomapajava.Entidades.Foto;
import es.tipolisto.fotomapajava.Entidades.ListaFotos;
import es.tipolisto.fotomapajava.Entidades.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IFotoMapaService {
    /**
     * Foto
     */
    @GET("photo/showall")
    Call<List<Foto>> getFotos();

    @GET("photo/show/{id}")
    Call<Foto> getFoto(@Path("id") int id);

    @FormUrlEncoded
    @POST("photo/create")
    Call<String> create(@Field("name") String name, @Field("text") String text, @Field("type") String type, @Field("address") String address, @Field("city") String city, @Field("lat") String lat, @Field("lng") String lng, @Field("user") String user,@Field("timestamp") String timestamp);
    @Multipart
    @PUT("user/photo")
    Call<User> subirFoto(@Part("photo") RequestBody photo, @Part("description") RequestBody description);
    /**
     * User
     */
    @FormUrlEncoded
    @POST("usuario/login")
    Call<String> login(@Field("name") String name, @Field("password") String password);

    @GET("usuario/showall")
    Call<List<User>> getUsuarios();


}
