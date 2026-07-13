package entidades;

public class Unidad {
    private int id;
    private String nombre;
    private String ocupante;
    private int ambientes;
    private int metrosCuadrados;
    private int ubicacion;
    private int porcentaje; //porcentaje de ocupacion en el edificio.
    private int idEdificio;

    public Unidad(){

    }

    public Unidad(String nombre, String ocupante, int ambientes, int metrosCuadrados, int ubicacion, int porcentaje){
        this.nombre = nombre;
        this.ocupante = ocupante;
        this.ambientes = ambientes;
        this.metrosCuadrados = metrosCuadrados;
        this.ubicacion = ubicacion;
        this.porcentaje = porcentaje;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setOcupante(String ocupante) {
        this.ocupante = ocupante;
    }

    public String getOcupante() {
        return this.ocupante;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getAmbientes() {
        return this.ambientes;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getMetrosCuadrados() {
        return this.metrosCuadrados;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getUbicacion() {
        return this.ubicacion;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getPorcentaje() {
        return  this.porcentaje;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public int getIdEdificio() {
        return idEdificio;
    }
}
