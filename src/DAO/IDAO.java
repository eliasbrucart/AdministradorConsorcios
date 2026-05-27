package DAO;

import java.util.ArrayList;

public interface IDAO <T>{ //Le pasamos un generico para que los metodos acepten todo tipo de dato.
    public void agregar(T elemento) throws DaoException;
    public void eliminar(int id) throws DaoException;
    public void modificar(T elemento) throws DaoException;
    public void consultar(int id) throws DaoException;
    public ArrayList<T> consultarTodo();
}