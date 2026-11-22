package service;

import DAO.DAOGestion;
import DAO.DaoException;
import entidades.Gestion;
import entidades.Unidad;

import java.util.ArrayList;

public class ServiceGestion {

    private DAOGestion daoGestion;

    public ServiceGestion(){
        daoGestion = new DAOGestion();
    }

    public Gestion consultarGestion(int id) throws ServiceException{
        Gestion gestion = null;
        try{
            gestion = daoGestion.consultar(id);
            return gestion;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void insertarGestion(Gestion elemento) throws ServiceException{
        try{
            daoGestion.agregar(elemento);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void agregarGestion(Object[] data) throws ServiceException{
        try{
            Gestion gestion = new Gestion();
            /*unidad.setNombre((String) data[0]);
            unidad.setOcupante((String)data[1]);
            unidad.setAmbientes((int) data[2]);
            unidad.setMetrosCuadrados((int)data[3]);
            unidad.setUbicacion((int)data[4]);
            unidad.setPorcentaje((int)data[5]);
            unidad.setIdEdificio((int)data[6]);*/
            daoGestion.agregar(gestion);
            //daoEdificio.agregar(edificio);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarGestion(Object[] data) throws ServiceException{
        try{
            Gestion gestion = new Gestion();
            /*unidad.setId((int) data[0]);
            unidad.setNombre((String) data[1]);
            unidad.setOcupante((String)data[2]);
            unidad.setAmbientes((int) data[3]);
            unidad.setMetrosCuadrados((int)data[4]);
            unidad.setUbicacion((int)data[5]);
            unidad.setPorcentaje((int)data[6]);*/
            daoGestion.modificar(gestion);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException{
        try{
            daoGestion.eliminar(id);
        }catch (DaoException e){
            throw new ServiceException("Error al eliminar la unidad: " + e);
        }
    }

    public ArrayList<Gestion> consultarTodo() throws ServiceException{
        ArrayList<Gestion> gestiones = new ArrayList<>();
        try{
            gestiones = daoGestion.consultarTodo();
            return gestiones;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todas las unidades: " + e);
        }
    }
}
