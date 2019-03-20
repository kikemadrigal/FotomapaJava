package es.tipolisto.fotomapajava.Fragments.Fotos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import es.tipolisto.fotomapajava.Fragments.MenuPrincipal.MenuPricipalFragment;
import es.tipolisto.fotomapajava.MainActivity;
import es.tipolisto.fotomapajava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearFotoFragment extends Fragment {
    private ImageView imageView;

    public CrearFotoFragment() {
        // Required empty public constructor
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crear_foto, container, false);
        imageView=view.findViewById(R.id.imageViewCrearFotoFragment);
        Button botonVolver=view.findViewById(R.id.buttonVolverFotoFragment);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().cambiarDeFragment(new MenuPricipalFragment());
            }
        });
        Button botonCrear=view.findViewById(R.id.buttonCrearFotoFragment);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
