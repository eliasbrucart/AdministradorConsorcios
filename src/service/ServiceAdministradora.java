package service;

import DAO.DAOAdministradora;
import DAO.DaoException;
import entidades.Administradora;
import entidades.Edificio;
import entidades.Unidad;

import java.util.ArrayList;

public class ServiceAdministradora {
    private DAOAdministradora daoAdministradora;

    public ServiceAdministradora(){
        daoAdministradora = new DAOAdministradora();
    }

    public Administradora consultarAdministradora(int id) throws ServiceException{
        Administradora administradora = null;
        try{
            administradora = daoAdministradora.consultar(id);
            return administradora;
        } catch (DaoException e){
            throw new ServiceException("Error al consultar la administradora: " + e.getMessage());
        }
    }

    public Administradora consultarAdministradora() throws ServiceException{
        Administradora administradora = null;
        try{
            administradora = daoAdministradora.consultar();
            return administradora;
        } catch (DaoException e){
            throw new ServiceException("Error al consultar la administradora: " + e.getMessage());
        }
    }

    public void insertarAdministradora(Administradora elemento) throws ServiceException{
        try{
            daoAdministradora.agregar(elemento);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void agregarAdministradora(Object[] data) throws ServiceException{
        try{
            Administradora administradora = new Administradora();
            administradora.setNombre((String)data[0]);
            administradora.setDireccion((String)data[1]);
            administradora.setTelefono((long)data[2]);
            administradora.setCuit((long)data[3]);
            daoAdministradora.agregar(administradora);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarAdministradora(Object[] data) throws ServiceException{
        try{
            Administradora administradora = new Administradora();
            administradora.setId((int)data[0]);
            administradora.setNombre((String)data[1]);
            administradora.setDireccion((String)data[2]);
            administradora.setTelefono((long)data[3]);
            administradora.setCuit((long)data[4]);
            daoAdministradora.modificar(administradora);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException{
        try{
            daoAdministradora.eliminar(id);
        }catch (DaoException e){
            throw new ServiceException("Error al eliminar la administradora: " + e);
        }
    }

    public ArrayList<Administradora> consultarTodo() throws ServiceException{
        ArrayList<Administradora> administradoras = new ArrayList<>();
        try{
            administradoras = daoAdministradora.consultarTodo();
            return administradoras;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todas las administradoras: " + e);
        }
    }
}
