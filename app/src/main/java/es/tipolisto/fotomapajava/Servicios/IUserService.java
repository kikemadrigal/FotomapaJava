package es.tipolisto.fotomapajava.Servicios;

import es.tipolisto.fotomapajava.Entidades.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserService {
    /**
     * Select usuarios
     *
     */
    //Forma 1
    @GET("show/15")
    Call<User> getuser();
    //Fomar 2
    @GET("show/{idusuario}")
    Call<User> getUsusuario(@Path("idusuario") int idusuario);
}
