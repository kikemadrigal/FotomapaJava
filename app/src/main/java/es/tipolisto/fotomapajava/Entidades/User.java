package es.tipolisto.fotomapajava.Entidades;

public class User {
    private int idusuario;
    private String nombreusuario;
    private String claveusuario;
    private String tipousuario;
    private String correousuario;
    private String nombreRealusuario;
    private String apellidosusuario;
    private String webusuario;
    private String validadousuario;
    private String fechausuario;
    private String datosusuario;
    public User(){}

    public User(int idusuario, String nombreusuario, String claveusuario, String tipousuario, String correousuario, String nombreRealusuario, String apellidosusuario, String webusuario, String validadousuario, String fechausuario, String datosusuario) {
        this.idusuario = idusuario;
        this.nombreusuario = nombreusuario;
        this.claveusuario = claveusuario;
        this.tipousuario = tipousuario;
        this.correousuario = correousuario;
        this.nombreRealusuario = nombreRealusuario;
        this.apellidosusuario = apellidosusuario;
        this.webusuario = webusuario;
        this.validadousuario = validadousuario;
        this.fechausuario = fechausuario;
        this.datosusuario = datosusuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getClaveusuario() {
        return claveusuario;
    }

    public void setClaveusuario(String claveusuario) {
        this.claveusuario = claveusuario;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getCorreousuario() {
        return correousuario;
    }

    public void setCorreousuario(String correousuario) {
        this.correousuario = correousuario;
    }

    public String getNombreRealusuario() {
        return nombreRealusuario;
    }

    public void setNombreRealusuario(String nombreRealusuario) {
        this.nombreRealusuario = nombreRealusuario;
    }

    public String getApellidosusuario() {
        return apellidosusuario;
    }

    public void setApellidosusuario(String apellidosusuario) {
        this.apellidosusuario = apellidosusuario;
    }

    public String getWebusuario() {
        return webusuario;
    }

    public void setWebusuario(String webusuario) {
        this.webusuario = webusuario;
    }

    public String getValidadousuario() {
        return validadousuario;
    }

    public void setValidadousuario(String validadousuario) {
        this.validadousuario = validadousuario;
    }

    public String getFechausuario() {
        return fechausuario;
    }

    public void setFechausuario(String fechausuario) {
        this.fechausuario = fechausuario;
    }

    public String getDatosusuario() {
        return datosusuario;
    }

    public void setDatosusuario(String datosusuario) {
        this.datosusuario = datosusuario;
    }

    @Override
    public String toString() {
        return "User{" +
                "idusuario=" + idusuario +
                ", nombreusuario='" + nombreusuario + '\'' +
                ", claveusuario='" + claveusuario + '\'' +
                ", tipousuario='" + tipousuario + '\'' +
                ", correousuario='" + correousuario + '\'' +
                ", nombreRealusuario='" + nombreRealusuario + '\'' +
                ", apellidosusuario='" + apellidosusuario + '\'' +
                ", webusuario='" + webusuario + '\'' +
                ", validadousuario='" + validadousuario + '\'' +
                ", fechausuario='" + fechausuario + '\'' +
                ", datosusuario='" + datosusuario + '\'' +
                '}';
    }
}
