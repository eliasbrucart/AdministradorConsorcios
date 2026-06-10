package DAO;

import entidades.Edificio;

import java.sql.*;
import java.util.ArrayList;

public class DAOEdificio implements IDAO<Edificio> {
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/test;
    //notebook
    private String DB_URL="jdbc:h2:D:\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

    //Desktop
    //private String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";
    private String DB_USER="root";

    private String DB_PASSWORD="1234";

    public DAOEdificio(){

    }

    @Override
    public void agregar(Edificio elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("INSERT INTO Edificio (NOMBRE, DIRECCION, LOCALIDAD, CODIGO_POSTAL, CANTIDAD_UNIDADES, CANTIDAD_PISOS, LIQUIDACION_EXPENSAS, FECHA_LIQUIDACION_EXPENSAS) VALUES(?,?,?,?,?,?,?,?)");
            //preparedStatement.setInt(1, 4); //harcoded
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getDireccion());
            //quitar luego
            preparedStatement.setString(3, elemento.getLocalidad());
            preparedStatement.setInt(4, elemento.getCodigoPostal());
            preparedStatement.setInt(5, elemento.getCantidadUnidades());
            preparedStatement.setInt(6, elemento.getCantidadPisos());
            preparedStatement.setLong(7, elemento.getLiquidacionExpensas());
            preparedStatement.setDate(8, Date.valueOf(java.time.LocalDate.now()));

            int resultado = preparedStatement.executeUpdate();
            if (resultado == 1){
                System.out.println("Edificio agregado");
            }else{
                System.out.println("Fallo sentencia SQL");
            }
        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en agregar elemento" + e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException{

    }

    @Override
    public void modificar(Edificio elemento) throws DaoException{

    }

    public void testDB() throws DaoException{
    }

    @Override
    public Edificio consultar(int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //Edificio edificio=null;
        Edificio edificio=new Edificio();
        try {
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM EDIFICIO WHERE id=?");

            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                /*alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setNota(rs.getInt("nota"));
                */
                String nombre=(rs.getString("nombre"));
                String direccion=(rs.getString("direccion"));
                //System.out.println("nombre " + nombre); //borrar print, no se hace en un metodo
                edificio.setNombre(nombre);
                edificio.setDireccion(direccion);
                //int nota=(rs.getInt("nota"));
                //Obtenemos los datos de la tabla y creamos el objeto propiamente dicho con los valores obtenidos.
                //alumno=new Alumno(id, nombre);
                //alumno.cambiarNota(nota);
            }

        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar edificio");
        }
        return edificio;
    }

    @Override
    public ArrayList<Edificio> consultarTodo() throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ArrayList<Edificio> edificios = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT * FROM EDIFICIO");

            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next()){
                Edificio edificio = new Edificio();
                edificio.setNombre(rs.getString("nombre"));
                edificio.setDireccion(rs.getString("Direccion"));
                //faltan mas atributos
                edificios.add(edificio);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar todos los edificios");
        }
        return edificios;
    }
}
