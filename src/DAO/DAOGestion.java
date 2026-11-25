package DAO;

import entidades.Gestion;

import java.sql.*;
import java.util.ArrayList;

public class DAOGestion implements IDAO<Gestion> {
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/test;
    //notebook
    //private String DB_URL="jdbc:h2:D:\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

    //Desktop
    private String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";
    private String DB_USER="root";

    private String DB_PASSWORD="1234";

    public DAOGestion(){

    }

    @Override
    public void agregar(Gestion elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("INSERT INTO GESTION (ID_EDIFICIO, ENTRADAS, SALIDAS, LIQUIDACION_EXPENSAS) VALUES(?,?,?,?)");
            //preparedStatement.setInt(1, 4); //harcoded
            preparedStatement.setInt(1, elemento.getIdEdificio());
            preparedStatement.setFloat(2, elemento.getEntradas());
            preparedStatement.setFloat(3, elemento.getSalidas());
            preparedStatement.setFloat(4, elemento.getLiquidacionExpensas());

            preparedStatement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en agregar Administradora" + e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("DELETE FROM GESTION WHERE id_edificio = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException e){
            throw new DaoException("Error al eliminar la administradora " + e);
        }
    }

    @Override
    public void modificar(Gestion elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("UPDATE GESTION SET ENTRADAS = ?, SALIDAS = ?, LIQUIDACION_EXPENSAS = ? WHERE id_edificio = ?");
            preparedStatement.setFloat(1, elemento.getEntradas());
            preparedStatement.setFloat(2, elemento.getSalidas());
            preparedStatement.setFloat(3, elemento.getLiquidacionExpensas());
            preparedStatement.setInt(4, elemento.getIdEdificio());
            preparedStatement.executeUpdate();
            //int filasAfectadas = preparedStatement.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al modificar la administradora: " + e);
        }
    }

    @Override
    public Gestion consultar(int id) throws DaoException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //Edificio edificio=null;
        Gestion gestion = new Gestion();
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM GESTION WHERE id=?");

            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                int idGestion = Integer.parseInt((rs.getString("id")));
                int idEdificio = Integer.parseInt((rs.getString("id_edificio")));
                float entradas=(rs.getFloat("entradas"));
                float salidas=(rs.getFloat("salidas"));
                float liquidacionExpensas=(rs.getFloat("liquidacion_expensas"));
                //long telefono = (rs.getLong("telefono"));
                //long cuit = (rs.getLong("cuit"));
                //System.out.println("nombre " + nombre); //borrar print, no se hace en un metodo
                gestion.setId(idGestion);
                gestion.setIdEdificio(idEdificio);
                gestion.setEntradas(entradas);
                gestion.setSalidas(salidas);
                gestion.setLiquidacionExpensas(liquidacionExpensas);
            }

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar la administradora");
        }
        return gestion;
    }

    @Override
    public ArrayList<Gestion> consultarTodo() throws DaoException{
       Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Gestion> gestiones = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM GESTION");

            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next()){
                Gestion gestion = new Gestion();
                gestion.setId(rs.getInt("id"));
                gestion.setIdEdificio(rs.getInt("id_edificio"));
                gestion.setEntradas(rs.getFloat("entradas"));
                gestion.setSalidas(rs.getInt("salidas"));
                gestion.setLiquidacionExpensas(rs.getFloat("liquidacion_expensas"));
                gestiones.add(gestion);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar todas las administradoras");
        }
        return gestiones;
        //ArrayList<Gestion> gestiones = new ArrayList<Gestion>();
        //return gestiones;
    }
}
