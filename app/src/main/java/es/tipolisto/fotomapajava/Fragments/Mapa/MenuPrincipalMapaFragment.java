package es.tipolisto.fotomapajava.Fragments.Mapa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.tipolisto.fotomapajava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalMapaFragment extends Fragment {


    public MenuPrincipalMapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_principal_mapa, container, false);
    }

}
