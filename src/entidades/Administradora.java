package entidades;

import java.util.ArrayList;

public class Administradora {
    private int id;
    private String nombre;
    private String direccion;
    private long telefono;
    private long cuit;
    private ArrayList<Edificio> edificios;
    public Administradora(){
        edificios = new ArrayList<>();
    }

    public Administradora(String nombre, String direccion, int telefono, int cuit){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuit = cuit;
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
        return nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public long getCuit() {
        return cuit;
    }

    public void setEdificio(Edificio edificios){
        this.edificios.add(edificios);
    }

    public ArrayList<Edificio> getEdificios(){
        return this.edificios;
    }
}
