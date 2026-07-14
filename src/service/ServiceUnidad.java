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

    public void calcularExpensasPorUnidad(int id) throws ServiceException{
        try{
            System.out.println("llego a calcular expensas por unidad!");
            Unidad unidad = consultarUnidad(id);
            int porcentajeUnidad = 0;
            int ubicacionUnidad = 0;
            int ambientes = 0;
            if(unidad.getPorcentaje() > 30){
                porcentajeUnidad += 30000;
            }
            //porcentajeUnidad += unidades.get(i).getPorcentaje();
            if(unidad.getUbicacion() == 1){
                ubicacionUnidad += 10000;
            } else if(unidad.getUbicacion() == 2){
                ubicacionUnidad += 20000;
            }
            if(unidad.getAmbientes() >= 2){
                ambientes += 10000;
            }else if(unidad.getAmbientes() < 2){
                ambientes += 5000;
            }
            int expensaFinal = porcentajeUnidad + ubicacionUnidad + ambientes;
            daoUnidad.actualizarExpensas(id, expensaFinal);
            //return expensaFinal;
        }catch(DaoException e){
            throw new ServiceException("Error al consultar todas las unidades del edificio: " + id + " error: " + e);
        }

    }

    public int calcularExpensasUnidad(int id) throws ServiceException{
        ArrayList<Unidad> unidades = new ArrayList<>();
        int expensaFinal = 0;
        try{
            unidades = daoUnidad.consultarTodoPorID(id);
            int porcentajeUnidad = 0;
            int ubicacionUnidad = 0;
            int ambientes = 0;
            for(int i = 0; i < unidades.size(); i++){
                if(unidades.get(i).getPorcentaje() > 30){
                    porcentajeUnidad += 30000;
                }
                //porcentajeUnidad += unidades.get(i).getPorcentaje();
                if(unidades.get(i).getUbicacion() == 1){
                    ubicacionUnidad += 10000;
                } else if(unidades.get(i).getUbicacion() == 2){
                    ubicacionUnidad += 20000;
                }
                if(unidades.get(i).getAmbientes() >= 2){
                    ambientes += 10000;
                }else if(unidades.get(i).getAmbientes() < 2){
                    ambientes += 5000;
                }
                expensaFinal = porcentajeUnidad + ubicacionUnidad + ambientes;
                return expensaFinal;
            }
            //return unidades;
        }catch (DaoException e){
            throw new ServiceException("Error al consultar todas las unidades del edificio: " + id + " error: " + e);
        }
        return expensaFinal;
    }
}
