package es.tipolisto.fotomapajava.Fragments.MenuPrincipal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import es.tipolisto.fotomapajava.Entidades.City;
import es.tipolisto.fotomapajava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPricipalFragment extends Fragment {


    public MenuPricipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_menu_pricipal, container, false);

        String json="{id:1,name:'london'}";
        try{
            JSONObject jsonObject=new JSONObject();
            int id=jsonObject.getInt("id");
            String name=jsonObject.getString("name");
            City city=new City(id, name);
            Toast.makeText(getContext(), "Ciudad: id"+city.getId()+", nombre: "+city.getName(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("mensaje", "menu principal cargado");
        return view;
    }

}
