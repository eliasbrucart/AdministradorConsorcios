package entidades;

public class Gestion {
    private int id;
    private float entradas;
    private float salidas;
    private float liquidacionExpensas;
    public Gestion(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEntradas(float entradas) {
        this.entradas = entradas;
    }

    public float getEntradas() {
        return entradas;
    }

    public void setSalidas(float salidas) {
        this.salidas = salidas;
    }

    public float getSalidas() {
        return salidas;
    }

    public void setLiquidacionExpensas(float liquidacionExpensas) {
        this.liquidacionExpensas = liquidacionExpensas;
    }

    public float getLiquidacionExpensas() {
        return liquidacionExpensas;
    }
}
