package service;

import DAO.DAOEdificio;
import DAO.DaoException;
import entidades.Edificio;

import javax.sql.rowset.serial.SerialException;
import java.security.Provider;

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
}
