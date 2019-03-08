package es.tipolisto.fotomapajava.Entidades;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class UsuarioRespuesta {
    @SerializedName("user")
    private User user;
    private String token;
    private String mensaje;

    public UsuarioRespuesta(User user, String token, String mensaje) {
        this.user = user;
        this.token = token;
        this.mensaje=mensaje;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public User JSON_pase(Response response){
        user=null;
        return user;
    }


}
