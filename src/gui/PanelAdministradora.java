package gui;

import service.ServiceAdministradora;

import javax.swing.*;

public class PanelAdministradora extends JPanel {
    private JButton btnAgregarAdministradora;
    private JButton btnEditarAdministradora;
    private JButton btnEliminarAdministradora;

    private ServiceAdministradora serviceAdministradora;

    public PanelAdministradora(){
        btnAgregarAdministradora = new JButton("Agregar Adm");
        btnEditarAdministradora = new JButton("Editar Adm");
        btnEliminarAdministradora = new JButton("Eliminar Adm");

        serviceAdministradora = new ServiceAdministradora();

        add(btnAgregarAdministradora);
        add(btnEditarAdministradora);
        add(btnEliminarAdministradora);
    }
}
