package gui;

import javax.swing.*;
import java.awt.*;

public class PanelUnidades extends JPanel {
    private JButton btnMostrarUnidades;
    private JButton btnAgregarUnidades;
    private JButton btnEditarUnidades;
    private JButton btnEliminarUnidades;

    private JPanel panelMostrar;


    private JPanel panelEditar;
    private JTextField idUnidadEditar = new JTextField(10);
    private JTextField nombreUnidadEditar = new JTextField(10);
    private JTextField ocupanteUnidadEditar = new JTextField(10);
    private JTextField ambientesUnidadEditar = new JTextField(10);
    private JTextField metrosUnidadEditar = new JTextField(10);
    private JTextField ubicacionUnidadEditar = new JTextField(10);
    private JTextField porcentajeUnidadEditar = new JTextField(10);


    public PanelUnidades(){
        btnMostrarUnidades = new JButton("Mostrar Unidades");
        btnAgregarUnidades = new JButton("Agregar Unidades");
        btnEditarUnidades = new JButton("Editar Unidades");
        btnEliminarUnidades = new JButton("Eliminar Unidades");

        btnMostrarUnidades.setVisible(false);
        btnAgregarUnidades.setVisible(false);
        btnEditarUnidades.setVisible(false);
        btnEliminarUnidades.setVisible(false);

        add(btnMostrarUnidades);
        add(btnAgregarUnidades);
        add(btnEditarUnidades);
        add(btnEliminarUnidades);
    }

    public JButton getBtnMostrarUnidades() {
        return this.btnMostrarUnidades;
    }

    public JButton getBtnAgregarUnidades() {
        return this.btnAgregarUnidades;
    }

    public JButton getBtnEditarUnidades() {
        return this.btnEditarUnidades;
    }

    public JButton getBtnEliminarUnidades() {
        return this.btnEliminarUnidades;
    }

    public JPanel getPanelEditar(){
        return this.panelEditar;
    }

    public JPanel getPanelMostrar(){
        return this.panelMostrar;
    }

    public void armarPanelEditar(){
        panelEditar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelEditar.add(new JLabel("ID: "));
        panelEditar.add(idUnidadEditar);
        idUnidadEditar.setEditable(false);
        panelEditar.add(new JLabel("Nombre: "));
        panelEditar.add(nombreUnidadEditar);
        panelEditar.add(new JLabel("Ocupante: "));
        panelEditar.add(ocupanteUnidadEditar);
        panelEditar.add(new JLabel("Ambientes: "));
        panelEditar.add(ambientesUnidadEditar);
        panelEditar.add(new JLabel("Metros cuadrados: "));
        panelEditar.add(metrosUnidadEditar);
        panelEditar.add(new JLabel("Ubicacion: "));
        panelEditar.add(ubicacionUnidadEditar);
        panelEditar.add(new JLabel("Porcentaje de ocupacion: "));
        panelEditar.add(porcentajeUnidadEditar);
    }
}
