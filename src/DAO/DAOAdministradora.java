package DAO;

import entidades.Administradora;
import entidades.Edificio;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DAOAdministradora implements IDAO<Administradora> {
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/test;
    //notebook
    //private String DB_URL="jdbc:h2:D:\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

    //Desktop
    private String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";
    private String DB_USER="root";

    private String DB_PASSWORD="1234";

    public DAOAdministradora(){

    }

    @Override
    public void agregar(Administradora elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("INSERT INTO ADMINISTRADORA (NOMBRE, DIRECCION, TELEFONO, CUIT) VALUES(?,?,?,?)");
            //preparedStatement.setInt(1, 4); //harcoded
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getDireccion());
            preparedStatement.setLong(3, elemento.getTelefono());
            preparedStatement.setLong(4, elemento.getCuit());

            preparedStatement.executeUpdate();

            //int resultado = preparedStatement.executeUpdate();
            /*if (resultado == 1){
                System.out.println("Edificio agregado");
            }else{
                System.out.println("Fallo sentencia SQL");
            }*/
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
            preparedStatement = connection.prepareStatement("DELETE FROM ADMINISTRADORA WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException e){
            throw new DaoException("Error al eliminar la administradora " + e);
        }
    }

    @Override
    public void modificar(Administradora elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("UPDATE ADMINISTRADORA SET NOMBRE = ?, DIRECCION = ?, TELEFONO = ?, CUIT = ? WHERE id = ?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getDireccion());
            preparedStatement.setLong(3, elemento.getTelefono());
            preparedStatement.setLong(4, elemento.getCuit());
            preparedStatement.setInt(5, elemento.getId());
            preparedStatement.executeUpdate();
            //int filasAfectadas = preparedStatement.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al modificar la administradora: " + e);
        }
    }

    @Override
    public Administradora consultar(int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //Edificio edificio=null;
        Administradora administradora=new Administradora();
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM ADMINISTRADORA WHERE id=?");

            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                /*alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setNota(rs.getInt("nota"));
                */
                int idAdministradora = Integer.parseInt((rs.getString("id")));
                String nombre=(rs.getString("nombre"));
                String direccion=(rs.getString("direccion"));
                long telefono = (rs.getLong("telefono"));
                long cuit = (rs.getLong("cuit"));
                //System.out.println("nombre " + nombre); //borrar print, no se hace en un metodo
                administradora.setId(idAdministradora);
                administradora.setNombre(nombre);
                administradora.setDireccion(direccion);
                administradora.setTelefono(telefono);
                administradora.setCuit(cuit);
                //int nota=(rs.getInt("nota"));
                //Obtenemos los datos de la tabla y creamos el objeto propiamente dicho con los valores obtenidos.
                //alumno=new Alumno(id, nombre);
                //alumno.cambiarNota(nota);
            }

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar la administradora");
        }
        return administradora;
    }

    public Administradora consultar() throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //Edificio edificio=null;
        Administradora administradora=new Administradora();
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM ADMINISTRADORA");

            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                /*alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setNota(rs.getInt("nota"));
                */
                int idAdministradora = Integer.parseInt((rs.getString("id")));
                String nombre=(rs.getString("nombre"));
                String direccion=(rs.getString("direccion"));
                long telefono = (rs.getLong("telefono"));
                long cuit = (rs.getLong("cuit"));
                //System.out.println("nombre " + nombre); //borrar print, no se hace en un metodo
                administradora.setId(idAdministradora);
                administradora.setNombre(nombre);
                administradora.setDireccion(direccion);
                administradora.setTelefono(telefono);
                administradora.setCuit(cuit);
                //int nota=(rs.getInt("nota"));
                //Obtenemos los datos de la tabla y creamos el objeto propiamente dicho con los valores obtenidos.
                //alumno=new Alumno(id, nombre);
                //alumno.cambiarNota(nota);
            }

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar la administradora");
        }
        return administradora;
    }

    @Override
    public ArrayList<Administradora> consultarTodo() throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Administradora> administradoras = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM ADMINISTRADORA");

            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next()){
                Administradora administradora = new Administradora();
                administradora.setId(rs.getInt("id"));
                administradora.setNombre(rs.getString("nombre"));
                administradora.setDireccion(rs.getString("direccion"));
                administradora.setTelefono(rs.getInt("telefono"));
                administradora.setCuit(rs.getInt("cuit"));
                administradoras.add(administradora);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar todas las administradoras");
        }
        return administradoras;
    }
}
