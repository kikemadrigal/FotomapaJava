package es.tipolisto.fotomapajava.Fragments.Mapa;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.actions.NoteIntents;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import es.tipolisto.fotomapajava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalMapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, LocationListener {
    private View view;
    private GoogleMap mapa;
    private MapView mapView;
    private List<Address> addresses;
    private Geocoder geocoder;
    private CameraPosition cameraPosition;

    private Location currentLocation;
    private LocationManager locationManager;

    public MenuPrincipalMapaFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Mensaje", "onCreateView");
        view=inflater.inflate(R.layout.fragment_menu_principal_mapa, container, false);
        //El locationService lo utilizamos para controlar el PS

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Has hecho clocik en el floating button", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Mensaje", "onViewCreated");
        mapView=(MapView) view.findViewById(R.id.map);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("Mensaje", "onReadyMap");
        mapa=googleMap;

        locationManager=(LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);


        /*if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mostrarDialogoPermisosNoHabilitados();
            return;
        }else if(comprobrarSiGPSEstaHabilitado()){
            mostrarDialogoGPSNoHabilitado();
            return;
        }*/




        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setMyLocationButtonEnabled(true);

        mapa.setOnMapLongClickListener(this);

        LatLng latLngMurcia=new LatLng(37.9962351773258, -1.14662189440552025);
        cameraPosition=new CameraPosition.Builder()
                .target(latLngMurcia)
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate=CameraUpdateFactory.newCameraPosition(cameraPosition);
        mapa.animateCamera(cameraUpdate);
        mapa.addMarker(new MarkerOptions().position(latLngMurcia).title("Murcia").draggable(true));




        geocoder=new Geocoder(getContext(), Locale.getDefault());

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

    }

   /* @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitud=marker.getPosition().latitude;
        double longitud=marker.getPosition().longitude;



        try {
            addresses= geocoder.getFromLocation(latitud,longitud,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address=addresses.get(0).getAddressLine(0);
        String city=addresses.get(0).getLocality();
        String state=addresses.get(0).getAdminArea();
        String country=addresses.get(0).getCountryName();
        String postalCode=addresses.get(0).getPostalCode();



        Toast.makeText(getContext(), "address "+address+"\n"+
                        "city "+city+"\n"+
                        "state "+state+"\n"+
                        "country "+country+"\n"+
                        "postalCode "+postalCode+"\n"
                , Toast.LENGTH_LONG).show();
    }*/





    @Override
    public void onResume() {
        super.onResume();
        Log.d("Mensaje", "onResume cargado");
    }



    @Override
    public void onMapLongClick(LatLng latLng) {
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(getActivity());

        alertDialog.setMessage("¿Quiere crear una foto aquí?\n.Latitud: "+latLng.latitude+"\nLongitud: "+latLng.longitude)
                .setTitle("Crear foto")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Vamos a crear la foto!!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialog.create();
        alertDialog.show();
    }


    private boolean comprobrarSiGPSEstaHabilitado(){
        boolean activadoGPS=false;
        boolean activadoNetwork;
        boolean permitido=false;
       /* try {
            int gps= Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if(gps==0){
                return false;
            }else{
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }*/
        LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        activadoGPS=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(activadoGPS) Log.d("Mensaje", "GPS activado");
        else  Log.d("Mensaje", "GPS no activado");
        activadoNetwork=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(activadoNetwork) Log.d("Mensaje", "network activado");
        else  Log.d("Mensaje", "network no activado");
        if(activadoGPS || activadoNetwork){
            permitido=true;
        }
        return permitido;
    }




    private void mostrarDialogoGPSNoHabilitado(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("El GPS no está activado, desea activarlo?")
                .setTitle("Activar GPS")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog.create();
        alertDialog.show();
    }

    private void mostrarDialogoPermisosNoHabilitados(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("Esta aplicación necesita habilitar ciertos permisos")
                .setTitle("Activar GPS")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Mensaje", "proveedor: "+location.getProvider());
        Toast.makeText(getContext(), "proveedor "+location.getProvider(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
