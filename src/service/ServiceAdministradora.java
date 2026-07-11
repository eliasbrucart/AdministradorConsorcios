package service;

import DAO.DAOAdministradora;
import entidades.Administradora;
import entidades.Edificio;

import java.util.ArrayList;

public class ServiceAdministradora {
    private DAOAdministradora daoAdministradora;

    public ServiceAdministradora(){
        daoAdministradora = new DAOAdministradora();
    }

    public Administradora consultarAdministradora(int id) throws ServiceException{
        Administradora administradora = null;
        return administradora;
    }

    public void insertarAdministradora(Administradora elemento) throws ServiceException{

    }

    public void agregarAdministradora(Object[] data) throws ServiceException{

    }

    public void modificarAdministradora(Object[] data) throws ServiceException{

    }

    public void eliminar(int id) throws ServiceException{

    }

    public ArrayList<Administradora> consultarTodo() throws ServiceException{
        ArrayList<Administradora> administradoras = new ArrayList<>();
        return administradoras;
    }
}
