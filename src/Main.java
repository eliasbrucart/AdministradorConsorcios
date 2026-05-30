//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import DAO.DAOEdificio;
import DAO.DaoException;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws DaoException {
        DAOEdificio edificioTest = new DAOEdificio();

        /*String DB_JDBC_DRIVER="org.h2.Driver";
        //private String DB_URL="jdbc:h2:~/test;
        String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

        String DB_USER="root";

        String DB_PASSWORD="1234";

        try {
            Connection connection=null;
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("Encontre el driver!");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Se conecto!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("¡El driver de H2 no está cargado correctamente en el Classpath!");
            //e.printStackTrace();
        }*/

        try{
            System.out.println("DAO EDIFICIO consultar: " + edificioTest.consultar(1));
        } catch (DaoException e) {
            throw new DaoException("Error al consultar el edificio: " + e);
        }
        //System.out.println("Administrador de consorcios");
    }
}