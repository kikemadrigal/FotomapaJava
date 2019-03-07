package es.tipolisto.fotomapajava.Fragments.Usuarios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.tipolisto.fotomapajava.Entidades.User;
import es.tipolisto.fotomapajava.R;
import es.tipolisto.fotomapajava.Servicios.IUserService;
import es.tipolisto.fotomapajava.Utilidades.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalUsuariosFragment extends Fragment {
    private TextView textView;

    public MenuPrincipalUsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        apiRetrofitUser();
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu_principal_usuarios, container, false);
        textView=view.findViewById(R.id.textViewFragmentmenuPrincipalUsuarios);
        return view;
    }




    private void apiRetrofitUser(){
        //Preparamos la instancia de retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creamos una clase que implemente la instancia del servicio
        IUserService iUserService=retrofit.create(IUserService.class);

        //Preparamos la petición o la Request pero todavía no lo hemos ejecutado
        Call<User> callGetuser=iUserService.getUsusuario(15);

        //Para ejecutarlo:
        callGetuser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User User=response.body();
                 textView.setText(response.body().getNombreusuario());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                 textView.setText("Mal");
            }
        });
    }
}
