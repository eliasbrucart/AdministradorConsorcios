package entidades;

public class Administradora {
    private int id;
    private String nombre;
    private String direccion;
    private int telefono;
    private int cuit;
    public Administradora(){

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

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public int getCuit() {
        return cuit;
    }
}
