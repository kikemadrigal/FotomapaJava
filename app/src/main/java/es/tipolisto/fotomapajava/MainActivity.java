package es.tipolisto.fotomapajava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import es.tipolisto.fotomapajava.Entidades.ForeCast;
import es.tipolisto.fotomapajava.Entidades.Foto;
import es.tipolisto.fotomapajava.Fragments.Fotos.CrearFotoFragment;
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
    //Atributo utilizado para ver si la respuesta es de tomar un foto
    private static final int REQUEST_IMAGE_CAPTURE=1;
    private CrearFotoFragment crearFotoFragment;
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
        //getSupportActionBar().setIcon(R.mipmap.ic_mapa);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Mostrar icono


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



    /************Menus*************************/
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
        }else if(id==R.id.anadir_item){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                 crearFotoFragment=new CrearFotoFragment();
                cambiarDeFragment(crearFotoFragment);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.borrar_item){
            Toast.makeText(mainActivity, "Borrado!", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.editar_item){
            Toast.makeText(mainActivity, "Editado!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    /**********Fin de menús*********************/

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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");


            crearFotoFragment.getImageView().setImageBitmap(imageBitmap);
        }else{
            Toast.makeText(mainActivity, "Algo salió mal al tomar la foto", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarDeFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentMainAppBarActivityMain,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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










}
