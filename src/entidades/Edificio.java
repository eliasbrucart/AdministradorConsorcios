package entidades;

public class Edificio {
    //componemos las unidades funcionales
    private int id;
    private String nombre;
    private String direccion;
    private String localidad;
    private int codigoPostal;
    private int cantidadPisos;
    private int cantidadUnidades;
    private int liquidacionExpensas;
    private String fechaLiquidacionExpensas;
    private String idUnidades;
    public Edificio(){

    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setLocalidad(String localidad){
        this.localidad = localidad;
    }

    public String getLocalidad(){
        return localidad;
    }

    public void setCodigoPostal(int codigoPostal){
        this.codigoPostal = codigoPostal;
    }

    public int getCodigoPostal(){
        return this.codigoPostal;
    }

    public void setCantidadPisos(int cantidadPisos){
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos(){
        return cantidadPisos;
    }

    public void setCantidadUnidades(int cantidadUnidades){
        this.cantidadUnidades = cantidadUnidades;
    }
    public int getCantidadUnidades(){
        return this.cantidadUnidades;
    }

    public void setLiquidacionExpensas(int liquidacionExpensas){
        this.liquidacionExpensas = liquidacionExpensas;
    }

    public int getLiquidacionExpensas(){
        return this.liquidacionExpensas;
    }

    public void setFechaLiquidacionExpensas(String fechaLiquidacionExpensas){
        this.fechaLiquidacionExpensas = fechaLiquidacionExpensas;
    }

    public String getFechaLiquidacionExpensas(){
        return this.fechaLiquidacionExpensas;
    }

    public void setIdUnidades(String idUnidades) {
        this.idUnidades = idUnidades;
    }

    public String getIdUnidades() {
        return idUnidades;
    }
}
