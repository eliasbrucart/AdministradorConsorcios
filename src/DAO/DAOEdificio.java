package DAO;

import entidades.Edificio;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DAOEdificio implements IDAO<Edificio> {
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/test;
    //notebook
    //private String DB_URL="jdbc:h2:D:\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";

    //Desktop
    private String DB_URL="jdbc:h2:C:\\Users\\elias\\Desktop\\Universidad de Palermo\\Cuatrimestre 2\\Programacion 3\\Proyectos\\AdministradorConsorcios\\DB\\DB";
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
            preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));

            int resultado = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en agregar edificio" + e);
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
            preparedStatement = connection.prepareStatement("DELETE FROM EDIFICIO WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException e){
            throw new DaoException("Error al eliminar el edificio " + e);
        }
    }

    @Override
    public void modificar(Edificio elemento) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto");
            preparedStatement = connection.prepareStatement("UPDATE EDIFICIO SET NOMBRE = ?, DIRECCION = ?, LOCALIDAD = ?, CODIGO_POSTAL = ?, CANTIDAD_UNIDADES = ?, CANTIDAD_PISOS = ?, LIQUIDACION_EXPENSAS = ?, FECHA_LIQUIDACION_EXPENSAS = ? WHERE id = ?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getDireccion());
            preparedStatement.setString(3, elemento.getLocalidad());
            preparedStatement.setInt(4, elemento.getCodigoPostal());
            preparedStatement.setInt(5, elemento.getCantidadUnidades());
            preparedStatement.setInt(6, elemento.getCantidadPisos());
            preparedStatement.setInt(7, elemento.getLiquidacionExpensas());
            //convertir fecha en date
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaLocal = LocalDate.parse(elemento.getFechaLiquidacionExpensas(), formateador);
            preparedStatement.setDate(8, Date.valueOf(fechaLocal));
            preparedStatement.setInt(9, elemento.getId());
            preparedStatement.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al modificar el edificio: " + e);
        }
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
                int idEdificio = Integer.parseInt((rs.getString("id")));
                String nombre=(rs.getString("nombre"));
                String direccion=(rs.getString("direccion"));
                String localidad = (rs.getString("localidad"));
                int codigo = (rs.getInt("codigo_postal"));
                int unidades = (rs.getInt("cantidad_unidades"));
                int pisos = (rs.getInt("cantidad_pisos"));
                int liquidacionExpensas = (rs.getInt("liquidacion_expensas"));
                String idUnidad = (rs.getString("id_unidad"));
                String fechaDB = (rs.getString("fecha_liquidacion_expensas"));
                String fechaLiquidacionExpensas = "";
                if (fechaDB != null){
                    LocalDate fechaLocal = LocalDate.parse(fechaDB);
                    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    fechaLiquidacionExpensas = fechaLocal.format(formateador);
                }
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                edificio.setId(idEdificio);
                edificio.setNombre(nombre);
                edificio.setDireccion(direccion);
                edificio.setLocalidad(localidad);
                edificio.setCodigoPostal(codigo);
                edificio.setCantidadUnidades(unidades);
                edificio.setCantidadPisos(pisos);
                edificio.setLiquidacionExpensas(liquidacionExpensas);
                edificio.setFechaLiquidacionExpensas(fechaLiquidacionExpensas);
                edificio.setIdUnidades(idUnidad);
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
                edificio.setId(rs.getInt("id"));
                edificio.setNombre(rs.getString("nombre"));
                edificio.setDireccion(rs.getString("Direccion"));
                edificio.setLocalidad(rs.getString("Localidad"));
                edificio.setLocalidad(rs.getString("Codigo_Postal"));
                edificio.setLocalidad(rs.getString("Cantidad_Unidades"));
                edificio.setCantidadPisos(rs.getInt("Cantidad_Pisos"));
                edificio.setLiquidacionExpensas(rs.getInt("Liquidacion_Expensas"));
                edificio.setFechaLiquidacionExpensas(rs.getString("Fecha_Liquidacion_Expensas"));
                edificio.setIdUnidades(rs.getString("id_unidad"));
                edificios.add(edificio);
            }
        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error en consultar todos los edificios");
        }
        return edificios;
    }

    public void actualizarBalance(int balanceTotal, int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("UPDATE EDIFICIO SET BALANCE = ? WHERE id = ?");
            preparedStatement.setInt(1, balanceTotal);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al actualizar el balance!");
        }
    }

    public int obtenerBalance(int id) throws DaoException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int ultimoBalance = 0;

        try{
            Class.forName(DB_JDBC_DRIVER);
            System.out.println("no hay driver"); //quitar luego
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("se conecto"); //quitar luego
            preparedStatement = connection.prepareStatement("SELECT BALANCE FROM EDIFICIO WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            if(rs.next()){
                ultimoBalance = rs.getInt("balance");
            }

        }catch (ClassNotFoundException | SQLException e){
            throw new DaoException("Error al obtener el balance!");
        }
        return ultimoBalance;
    }
}
