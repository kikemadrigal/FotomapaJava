package es.tipolisto.fotomapajava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.tipolisto.fotomapajava.Entidades.ForeCast;
import es.tipolisto.fotomapajava.Entidades.UsuarioRespuesta;
import es.tipolisto.fotomapajava.Fragments.Mapa.MapActivity;
import es.tipolisto.fotomapajava.Servicios.IUserService;
import es.tipolisto.fotomapajava.Servicios.IWeatherService;
import es.tipolisto.fotomapajava.Utilidades.Constantes;
import es.tipolisto.fotomapajava.Utilidades.Funciones;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNombreUsuario;
    private EditText editTextPasswordusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        editTextNombreUsuario=findViewById(R.id.editTextNombreUsuarioLoginActivity);
        editTextPasswordusuario=findViewById(R.id.editTextPasswordUsuarioLoginActivity);

        Button botonLoginActivity=findViewById(R.id.buttonLoginActivity);
        botonLoginActivity.setOnClickListener(this);

        //cambiardeActivity(MapActivity.class);

    }



    private void comprobarLoginUsuario(){
        //Validamos los campos

        boolean nombreEstaVacio= Funciones.comprobarCamposVacíos(editTextNombreUsuario.getText().toString());
        boolean contraseñaVacia=Funciones.comprobarCamposVacíos(editTextPasswordusuario.getText().toString());

        /*if(nombreEstaVacio){
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_LONG).show();
        }else if(contraseñaVacia){
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_LONG).show();
        }else{*/
            crearRetroFit();
        //}
    }


    private void crearRetroFit(){
        //Preparamos la instancia de retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creamos una clase que implemente la instancia del servicio
        IUserService iUserService=retrofit.create(IUserService.class);

        //Preparamos la petición o la Request pero todavía no lo hemos ejecutado
        Call<UsuarioRespuesta> callUsuarioRespuesta=iUserService.login(editTextNombreUsuario.getText().toString(), editTextPasswordusuario.getText().toString() );
        callUsuarioRespuesta.enqueue(new Callback<UsuarioRespuesta>() {
            @Override
            public void onResponse(Call<UsuarioRespuesta> call, Response<UsuarioRespuesta> response) {
                UsuarioRespuesta usuarioRespuesta=response.body();
                Toast.makeText(LoginActivity.this, "bien", Toast.LENGTH_SHORT).show();
                cambiardeActivity(MainActivity.class);
            }

            @Override
            public void onFailure(Call<UsuarioRespuesta> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
       // Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.buttonLoginActivity:
                comprobarLoginUsuario();
                break;
        }
    }


    private void cambiardeActivity(Class clase){
        Intent intent=new Intent(LoginActivity.this, clase);
        startActivity(intent);
        finish();
    }
}
