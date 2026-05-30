//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import DAO.DAOEdificio;
import DAO.DaoException;
import entidades.Edificio;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws DaoException {
        DAOEdificio edificioTest = new DAOEdificio();

        try{
            Edificio edificio = edificioTest.consultar(1);
            System.out.println("edificio consultado: " + edificio.getNombre());
            System.out.println("edificio consultado: " + edificio.getDireccion());
            //System.out.println("DAO EDIFICIO consultar: " + edificioTest.consultar(1));
        } catch (DaoException e) {
            throw new DaoException("Error al consultar el edificio: " + e);
        }

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