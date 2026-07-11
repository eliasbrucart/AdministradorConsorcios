package DAO;

import entidades.Administradora;
import entidades.Edificio;

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

    }

    @Override
    public void eliminar(int id) throws DaoException{

    }

    @Override
    public void modificar(Administradora elemento) throws DaoException{

    }

    @Override
    public Administradora consultar(int id) throws DaoException{
        return new Administradora();
    }

    @Override
    public ArrayList<Administradora> consultarTodo() throws DaoException{
        ArrayList<Administradora> administradora = new ArrayList<>();
        return administradora;
    }
}
