package entidades;

public class Unidad {
    private int metrosCuadrados;
    private int ambientes;

    public Unidad(int metrosCuadrados, int ambientes){
        this.metrosCuadrados = metrosCuadrados;
        this.ambientes = ambientes;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getAmbientes() {
        return ambientes;
    }
}
