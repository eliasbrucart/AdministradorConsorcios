//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import DAO.DAOEdificio;
import DAO.DaoException;
import entidades.Edificio;
import gui.PanelManager;
import service.ServiceEdificio;
import service.ServiceException;
import gui.PanelManager;

import java.security.Provider;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws DaoException {
        //DAOEdificio edificioTest = new DAOEdificio();

        //ServiceEdificio serviceEdificio = new ServiceEdificio();

        /*try{
            Edificio edificio = serviceEdificio.consultarEdificio(1);
            System.out.println("edificio consultado: " + edificio.getNombre());
            System.out.println("direccion edificio consultado: " + edificio.getDireccion());
        }catch (ServiceException e){
            System.out.println("Error al consultar el edificio: " + e);
        }*/

        PanelManager panelManager = new PanelManager(1);

        /*try{
            Edificio edificio = edificioTest.consultar(1);
            System.out.println("edificio consultado: " + edificio.getNombre());
            System.out.println("edificio consultado: " + edificio.getDireccion());
            //System.out.println("DAO EDIFICIO consultar: " + edificioTest.consultar(1));
        } catch (DaoException e) {
            throw new DaoException("Error al consultar el edificio: " + e);
        }*/

        /*Edificio edificio = new Edificio();
        edificio.setNombre("Palacio 1");
        edificio.setDireccion("Amenabar 2500");

        try{
            edificioTest.agregar(edificio);
        } catch (DaoException e) {
            throw new DaoException("Error al agregar el edificio: " + e);
        }*/
        //System.out.println("Administrador de consorcios");
    }
}