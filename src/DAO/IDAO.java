package DAO;

import java.util.ArrayList;

public interface IDAO <T>{ //Le pasamos un generico para que los metodos acepten todo tipo de dato.
    public void agregar(T elemento);
    public void eliminar(int id);
    public void modificar(T elemento);
    public void consultar(int id);
    public ArrayList<T> consultarTodo();
}
