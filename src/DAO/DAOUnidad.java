package DAO;

import entidades.Administradora;
import entidades.Unidad;

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

    }

    @Override
    public void eliminar(int id) throws DaoException{

    }

    @Override
    public void modificar(Unidad elemento) throws DaoException{

    }

    @Override
    public Unidad consultar(int id) throws DaoException{
        return new Unidad();
    }

    @Override
    public ArrayList<Unidad> consultarTodo() throws DaoException{
        ArrayList<Unidad> unidades = new ArrayList<>();
        return unidades;
    }
}
