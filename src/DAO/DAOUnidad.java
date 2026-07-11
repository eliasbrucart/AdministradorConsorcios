package DAO;

import entidades.Administradora;
import entidades.Edificio;
import entidades.Unidad;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DAOUnidad implements IDAO<Unidad>{
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/test;
    //notebook
    //private String DB_URL="jdbc:h2:D:\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

    //Desktop
    private String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";
    private String DB_USER="root";

    private String DB_PASSWORD="1234";
    public DAOUnidad(){

    }

    @Override
    public void agregar(Unidad elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("INSERT INTO UNIDAD (NOMBRE, OCUPANTE, AMBIENTES, METROS, UBICACION, PORCENTAJE) VALUES(?,?,?,?,?,?)");
            //preparedStatement.setInt(1, 4); //harcoded
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getOcupante());
            //quitar luego
            preparedStatement.setInt(3, elemento.getAmbientes());
            preparedStatement.setInt(4, elemento.getMetrosCuadrados());
            preparedStatement.setInt(5, elemento.getUbicacion()); //0 o 1
            preparedStatement.setInt(6, elemento.getPorcentaje());

            int resultado = preparedStatement.executeUpdate();
            /*if (resultado == 1){
                System.out.println("Edificio agregado");
            }else{
                System.out.println("Fallo sentencia SQL");
            }*/
        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en agregar unidad" + e);
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
            preparedStatement = connection.prepareStatement("DELETE FROM UNIDAD WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException e){
            throw new DaoException("Error al eliminar la unidad " + e);
        }
    }

    @Override
    public void modificar(Unidad elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("UPDATE UNIDAD SET NOMBRE = ?, OCUPANTE = ?, AMBIENTES = ?, METROS = ?, UBICACION = ?, PORCENTAJE = ? WHERE id = ?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getOcupante());
            preparedStatement.setInt(3, elemento.getAmbientes());
            preparedStatement.setInt(4, elemento.getMetrosCuadrados());
            preparedStatement.setInt(5, elemento.getUbicacion());
            preparedStatement.setInt(6, elemento.getPorcentaje());
            preparedStatement.setInt(7, elemento.getId());
            preparedStatement.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al modificar la unidad: " + e);
        }
    }

    @Override
    public Unidad consultar(int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //Edificio edificio=null;
        Unidad unidad=new Unidad();
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM UNIDAD WHERE id=?");

            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                /*alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setNota(rs.getInt("nota"));
                */
                int idUnidad = Integer.parseInt((rs.getString("id")));
                String nombre=(rs.getString("nombre"));
                String ocupante=(rs.getString("ocupante"));
                int ambientes = (rs.getInt("ambientes"));
                int metros = (rs.getInt("metros"));
                int ubicacion = (rs.getInt("ubicacion"));
                int porcentaje = (rs.getInt("porcentaje"));
                //String fechaLiquidacionExpensas = (rs.getString("fecha_liquidacion_expensas"));
                unidad.setId(idUnidad);
                unidad.setNombre(nombre);
                unidad.setOcupante(ocupante);
                unidad.setAmbientes(ambientes);
                unidad.setMetrosCuadrados(metros);
                unidad.setUbicacion(ubicacion);
                unidad.setPorcentaje(porcentaje);
            }

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar la unidad");
        }
        return unidad;
    }

    @Override
    public ArrayList<Unidad> consultarTodo() throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Unidad> unidades = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM UNIDAD");

            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next()){
                Unidad unidad = new Unidad();
                unidad.setId(rs.getInt("id"));
                unidad.setNombre(rs.getString("nombre"));
                unidad.setOcupante(rs.getString("ocupante"));
                unidad.setAmbientes(rs.getInt("ambientes"));
                unidad.setMetrosCuadrados(rs.getInt("metros"));
                unidad.setUbicacion(rs.getInt("ubicacion"));
                unidad.setPorcentaje(rs.getInt("porcentaje"));
                unidades.add(unidad);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar todas las unidades");
        }
        return unidades;
    }
}
