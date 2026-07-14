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
            edificio.setLocalidad((String)data[2]);
            edificio.setCodigoPostal((int)data[3]);
            edificio.setCantidadUnidades((int)data[4]);
            edificio.setCantidadPisos((int)data[5]);
            edificio.setLiquidacionExpensas((int)data[6]);
            edificio.setFechaLiquidacionExpensas((String)data[7]);
            daoEdificio.agregar(edificio);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarEdificio(Object[] data) throws ServiceException{
        try{
            Edificio edificio = new Edificio();
            edificio.setId((int)data[8]);
            edificio.setNombre((String)data[0]);
            edificio.setDireccion((String)data[1]);
            edificio.setLocalidad((String)data[2]);
            edificio.setCodigoPostal((int)data[3]);
            edificio.setCantidadUnidades((int)data[4]);
            edificio.setCantidadPisos((int)data[5]);
            edificio.setLiquidacionExpensas((int)data[6]);
            edificio.setFechaLiquidacionExpensas((String)data[7]);
            daoEdificio.modificar(edificio);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException{
        try{
            daoEdificio.eliminar(id);
        }catch (DaoException e){
            throw new ServiceException("Error al eliminar edificio: " + e);
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

    public int generarBalance(Object[] data, int id) throws ServiceException{
        try{
            int balanceIngresos = (int)data[0] + (int)data[1];
            int balanceSalidas = (int)data[2] + (int)data[3];
            int balanceTotal = balanceIngresos - balanceSalidas;
            daoEdificio.actualizarBalance(balanceTotal, id);
            return balanceTotal;
        }catch (DaoException e){
            throw new ServiceException("Error al generar el balance! " + e);
        }
    }

    public int obtenerBalance(int id) throws ServiceException {
        try{
            int ultimoBalance = daoEdificio.obtenerBalance(id);
            return ultimoBalance;
        }catch (DaoException e){
            throw new ServiceException("Error al obtener el balance! " + e);
        }
    }
}
