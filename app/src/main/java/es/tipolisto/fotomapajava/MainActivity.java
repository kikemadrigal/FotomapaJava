package es.tipolisto.fotomapajava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import es.tipolisto.fotomapajava.Entidades.ForeCast;
import es.tipolisto.fotomapajava.Entidades.Foto;
import es.tipolisto.fotomapajava.Fragments.Fotos.MenuPrincipalFotosFragment;
import es.tipolisto.fotomapajava.Fragments.Mapa.MapActivity;
import es.tipolisto.fotomapajava.Fragments.Mapa.MenuPrincipalMapaFragment;
import es.tipolisto.fotomapajava.Fragments.MenuPrincipal.MenuPricipalFragment;
import es.tipolisto.fotomapajava.Fragments.Usuarios.MenuPrincipalUsuariosFragment;
import es.tipolisto.fotomapajava.Servicios.IFotoMapaService;
import es.tipolisto.fotomapajava.Servicios.IWeatherService;
import es.tipolisto.fotomapajava.Utilidades.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static MainActivity mainActivity;
    public static MainActivity getInstance(){
        if(mainActivity==null){
            mainActivity=new MainActivity();
        }
        return mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Esto lo hacemos para detectar que salga elmenu principalcuando sea la primeravez que se carga
        //Sino pusiermos el if al cambiar la rotación de la pantalla cambiaríaal menuprincipal
        if(savedInstanceState==null){
            cambiarDeFragment(new MenuPricipalFragment());
        }



        mainActivity=this;

        //Toast.makeText(mainActivity, "Ha vuelto a MainActivity create", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu_principal) {
            cambiarDeFragment(new MenuPricipalFragment());
        } else if (id == R.id.nav_fotos) {
            cambiarDeFragment(new MenuPrincipalFotosFragment());
        } else if (id == R.id.nav_mapa) {
            cambiarDeFragment(new MenuPrincipalMapaFragment());
            /*Intent intent=new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
            finish();*/
        } else if (id == R.id.nav_usuarios) {
            cambiarDeFragment(new MenuPrincipalUsuariosFragment());
        } else if (id == R.id.nav_send) {
            Toast.makeText(mainActivity, "Sin acción definida", Toast.LENGTH_LONG).show();
        }else if (id == R.id.nav_share) {
            Toast.makeText(mainActivity, "Sin acción definida", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void apiRetrofitOpenWeather(){
        //Preparamos la instancia de retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creamos una clase que implemente la instancia del servicio
        IWeatherService iWeatherService=retrofit.create(IWeatherService.class);

        //Preparamos la petición o la Request pero todavía no lo hemos ejecutado
        Call callCity=iWeatherService.getCity(Constantes.COORDENADAS_USA,Constantes.API_KEY);

        //Para ejecutarlo:
        callCity.enqueue(new Callback<ForeCast>() {
            @Override
            public void onResponse(Call<ForeCast> call, Response<ForeCast> response) {
                ForeCast foreCast=response.body();
               // Log.d(foreCast.getCity().getName());
            }

            @Override
            public void onFailure(Call<ForeCast> call, Throwable t) {

            }
        });
    }






    public void cambiarDeFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentMainAppBarActivityMain,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
