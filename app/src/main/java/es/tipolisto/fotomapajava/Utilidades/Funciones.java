package es.tipolisto.fotomapajava.Utilidades;

public class Funciones {
    public static boolean comprobarCamposVacíos(String campo){
        boolean vacio=true;
        if(!campo.equals("")){
            vacio=false;
        }
        return vacio;
    }


}
