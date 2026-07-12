package service;

import DAO.DAOUnidad;
import DAO.DaoException;
import entidades.Administradora;
import entidades.Unidad;

import java.util.ArrayList;

public class ServiceUnidad {
    private DAOUnidad daoUnidad;

    public ServiceUnidad(){
        daoUnidad = new DAOUnidad();
    }

    public Unidad consultarUnidad(int id) throws ServiceException{
        Unidad unidad = null;
        return unidad;
    }

    public void insertarUnidad(Unidad elemento) throws ServiceException{

    }

    public void agregarUnidad(Object[] data) throws ServiceException{

    }

    public void modificarUnidad(Object[] data) throws ServiceException{

    }

    public void eliminar(int id) throws ServiceException{

    }

    public ArrayList<Unidad> consultarTodo() throws ServiceException{
        ArrayList<Unidad> unidades = new ArrayList<>();
        try{
            unidades = daoUnidad.consultarTodo();
            return unidades;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todas las unidades: " + e);
        }
    }

    public ArrayList<Unidad> consultarTodoPorID(int id) throws ServiceException{
        ArrayList<Unidad> unidades = new ArrayList<>();
        try{
            unidades = daoUnidad.consultarTodoPorID(id);
            return unidades;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todas las unidades del edificio: " + id + " error: " + e);
        }
    }
}
