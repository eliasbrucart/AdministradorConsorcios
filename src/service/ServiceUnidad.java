package service;

import DAO.DAOUnidad;
import DAO.DaoException;
import entidades.Administradora;
import entidades.Edificio;
import entidades.Unidad;

import java.util.ArrayList;

public class ServiceUnidad {
    private DAOUnidad daoUnidad;

    public ServiceUnidad(){
        daoUnidad = new DAOUnidad();
    }

    public Unidad consultarUnidad(int id) throws ServiceException{
        Unidad unidad = null;
        try{
            unidad = daoUnidad.consultar(id);
            return unidad;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void insertarUnidad(Unidad elemento) throws ServiceException{
        try{
            daoUnidad.agregar(elemento);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void agregarUnidad(Object[] data) throws ServiceException{
        try{
            Unidad unidad = new Unidad();
            unidad.setNombre((String) data[0]);
            unidad.setOcupante((String)data[1]);
            unidad.setAmbientes((int) data[2]);
            unidad.setMetrosCuadrados((int)data[3]);
            unidad.setUbicacion((int)data[4]);
            unidad.setPorcentaje((int)data[5]);
            unidad.setIdEdificio((int)data[6]);
            daoUnidad.agregar(unidad);
            //daoEdificio.agregar(edificio);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarUnidad(Object[] data) throws ServiceException{
        try{
            Unidad unidad = new Unidad();
            unidad.setId((int) data[0]);
            unidad.setNombre((String) data[1]);
            unidad.setOcupante((String)data[2]);
            unidad.setAmbientes((int) data[3]);
            unidad.setMetrosCuadrados((int)data[4]);
            unidad.setUbicacion((int)data[5]);
            unidad.setPorcentaje((int)data[6]);
            daoUnidad.modificar(unidad);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException{
        try{
            daoUnidad.eliminar(id);
        }catch (DaoException e){
            throw new ServiceException("Error al eliminar la unidad: " + e);
        }
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
