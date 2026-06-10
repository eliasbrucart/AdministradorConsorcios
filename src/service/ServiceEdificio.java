package service;

import DAO.DAOEdificio;
import DAO.DaoException;
import entidades.Edificio;

import javax.sql.rowset.serial.SerialException;
import java.security.Provider;
import java.util.ArrayList;

public class ServiceEdificio {
    private DAOEdificio daoEdificio;

    public ServiceEdificio(){
        daoEdificio = new DAOEdificio();
    }

    public Edificio consultarEdificio(int id) throws ServiceException{
        Edificio edificio = null;
        try{
            edificio = daoEdificio.consultar(id);
            return edificio;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void insertarEdificio(Edificio elemento) throws ServiceException{
        try{
            daoEdificio.agregar(elemento);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void agregarEdificio(Object[] data) throws ServiceException{
        try{
            Edificio edificio = new Edificio();
            edificio.setNombre((String) data[0]);
            edificio.setDireccion((String)data[1]);
            daoEdificio.agregar(edificio);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Edificio> consultarTodo() throws ServiceException{
        ArrayList<Edificio> edificios = new ArrayList<>();
        try{
            edificios = daoEdificio.consultarTodo();
            return edificios;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todos los edificios: " + e);
        }
    }
}
