package gui;

import entidades.Edificio;
import entidades.Unidad;
import service.ServiceEdificio;
import service.ServiceException;
import service.ServiceUnidad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelUnidades extends JPanel {
    private FormularioEdificio formularioEdificio;
    ServiceUnidad serviceUnidad;

    private int idSeleccionado;
    private JButton btnMostrarUnidades;
    private JButton btnAgregarUnidades;
    private JButton btnEditarUnidades;
    private JButton btnEliminarUnidades;

    private JButton btnBuscarUnidadEditar = new JButton("Buscar");
    private JButton btnBuscarUnidadEliminar = new JButton("Buscar");

    private JPanel panelMostrar;
    private JScrollPane scrollPane;

    private JPanel panelAgregar;
    private JTextField nombreUnidadAgregar = new JTextField(10);
    private JTextField ocupanteUnidadAgregar = new JTextField(10);
    private JTextField ambientesUnidadAgregar = new JTextField(10);
    private JTextField metrosUnidadAgregar = new JTextField(10);
    private JTextField ubicacionUnidadAgregar = new JTextField(10);
    private JTextField porcentajeUnidadAgregar = new JTextField(10);

    private JPanel panelEditar;
    private JTextField idUnidadEditar = new JTextField(10);
    private JTextField nombreUnidadEditar = new JTextField(10);
    private JTextField ocupanteUnidadEditar = new JTextField(10);
    private JTextField ambientesUnidadEditar = new JTextField(10);
    private JTextField metrosUnidadEditar = new JTextField(10);
    private JTextField ubicacionUnidadEditar = new JTextField(10);
    private JTextField porcentajeUnidadEditar = new JTextField(10);

    private JPanel panelEliminar;
    private JTextField idUnidadEliminar = new JTextField(10);
    private JTextField nombreUnidadEliminar = new JTextField(10);
    private JTextField ocupanteUnidadEliminar = new JTextField(10);
    private JTextField ambientesUnidadEliminar = new JTextField(10);
    private JTextField metrosUnidadEliminar = new JTextField(10);
    private JTextField ubicacionUnidadEliminar = new JTextField(10);
    private JTextField porcentajeUnidadEliminar = new JTextField(10);


    public PanelUnidades(FormularioEdificio formularioEdificio){
        serviceUnidad = new ServiceUnidad();
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

        this.formularioEdificio = formularioEdificio;
    }

    public void setIdSeleccionado(int id){
        this.idSeleccionado = id;
    }

    public int getIdSeleccionado(){
        return this.idSeleccionado;
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

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public void setPanelAgregar(JPanel panelAgregar) {
        this.panelAgregar = panelAgregar;
    }

    public JPanel getPanelAgregar() {
        return this.panelAgregar;
    }

    public void armarPanelAgregar(){
        panelAgregar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelAgregar.add(new JLabel("Nombre: "));
        panelAgregar.add(nombreUnidadAgregar);
        panelAgregar.add(new JLabel("Ocupante: "));
        panelAgregar.add(ocupanteUnidadAgregar);
        panelAgregar.add(new JLabel("Ambientes: "));
        panelAgregar.add(ambientesUnidadAgregar);
        panelAgregar.add(new JLabel("Metros: "));
        panelAgregar.add(metrosUnidadAgregar);
        panelAgregar.add(new JLabel("Ubicacion: "));
        panelAgregar.add(ubicacionUnidadAgregar);
        panelAgregar.add(new JLabel("Porcentaje: "));
        panelAgregar.add(porcentajeUnidadAgregar);
    }

    public void armarPanelEditar(){
        panelEditar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelEditar.add(new JLabel("ID: "));
        panelEditar.add(idUnidadEditar);
        idUnidadEditar.setEditable(true);
        //JButton btnBuscarUnidad = new JButton("Buscar");
        panelEditar.add(btnBuscarUnidadEditar);
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

    public void armarPanelEliminar(){
        panelEliminar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelEliminar.add(new JLabel("ID: "));
        panelEliminar.add(idUnidadEliminar);
        idUnidadEditar.setEditable(true);
        //JButton btnBuscarUnidad = new JButton("Buscar");
        panelEliminar.add(btnBuscarUnidadEliminar);
        panelEliminar.add(new JLabel("Nombre: "));
        panelEliminar.add(nombreUnidadEliminar);
        panelEliminar.add(new JLabel("Ocupante: "));
        panelEliminar.add(ocupanteUnidadEliminar);
        panelEliminar.add(new JLabel("Ambientes: "));
        panelEliminar.add(ambientesUnidadEliminar);
        panelEliminar.add(new JLabel("Metros cuadrados: "));
        panelEliminar.add(metrosUnidadEliminar);
        panelEliminar.add(new JLabel("Ubicacion: "));
        panelEliminar.add(ubicacionUnidadEliminar);
        panelEliminar.add(new JLabel("Porcentaje de ocupacion: "));
        panelEliminar.add(porcentajeUnidadEliminar);
    }

    public void armarPanelMostrar() {
        panelMostrar = new JPanel(new GridLayout(2, 2, 4, 4));
        panelMostrar.setPreferredSize(new Dimension(400, 400));
        // agregar service de Edificio para consultar sus datos.
        //ServiceUnidad serviceUnidad = new ServiceUnidad();
        ServiceEdificio serviceEdificio = new ServiceEdificio();
        int idEdificioConsultado = 0;
        try{
            Edificio edificioConsultado = serviceEdificio.consultarEdificio(this.idSeleccionado);
            idEdificioConsultado = edificioConsultado.getId();
        }catch(ServiceException e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error al consultar el edificio " + e.getMessage()
            );
        }

        ArrayList<Unidad> unidadesConsultadas = new ArrayList<>();
        try{
            unidadesConsultadas = serviceUnidad.consultarTodoPorID(idEdificioConsultado);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar las unidades" + e.getMessage()
            );
            //System.out.println("Error al consultar el edificio: " + e);
        }
        String[] columnas = {"Identificador", "Nombre", "Ocupante"};
        Object[][] datos = new Object[unidadesConsultadas.size()][3];
        for(int i = 0; i < unidadesConsultadas.size(); i++){
            Unidad unidad = unidadesConsultadas.get(i);

            datos[i][0] = unidad.getId();
            datos[i][1] = unidad.getNombre();
            datos[i][2] = unidad.getOcupante();
        }

        //modelo y la tabla (celdas no editables directamente)
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        scrollPane = new JScrollPane(tabla);

        panelMostrar.add(scrollPane);
    }

    public void actionBtnMostrarUnidades(){
        btnMostrarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelMostrar,
                        "Unidades del edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });
    }

    public void actionBtnAgregarUnidad(){
        btnAgregarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelAgregar, //modificar
                        "Agregar unidades",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    String nombreUnidad = nombreUnidadAgregar.getText();
                    String ocupanteUnidad = ocupanteUnidadAgregar.getText();
                    int ambientesUnidad = Integer.parseInt(ambientesUnidadAgregar.getText());
                    int metrosUnidad = Integer.parseInt(metrosUnidadAgregar.getText());
                    int ubicacionUnidad = Integer.parseInt(ubicacionUnidadAgregar.getText());
                    int porcentajeUnidad = Integer.parseInt(porcentajeUnidadAgregar.getText());
                    int idEdificio = idSeleccionado;

                    Object[] data = new Object[7];
                    data[0] = nombreUnidad;
                    data[1] = ocupanteUnidad;
                    data[2] = ambientesUnidad;
                    data[3] = metrosUnidad;
                    data[4] = ubicacionUnidad;
                    data[5] = porcentajeUnidad;
                    data[6] = idEdificio;

                    try {
                        serviceUnidad.agregarUnidad(data);
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Unidad agregada con exito!"
                        );
                        /*DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }*/
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Error al agregar la unidad" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }
            }
        });
    }

    public void actionBtnBuscarUnidadEditar(){
        btnBuscarUnidadEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEditarUnidad = Integer.parseInt(idUnidadEditar.getText());

                try {
                    Unidad unidadConsultada = serviceUnidad.consultarUnidad(idEditarUnidad);
                    idUnidadEditar.setText(String.valueOf(unidadConsultada.getId()));
                    nombreUnidadEditar.setText(unidadConsultada.getNombre());
                    ocupanteUnidadEditar.setText(unidadConsultada.getOcupante());
                    ambientesUnidadEditar.setText(String.valueOf(unidadConsultada.getAmbientes()));
                    metrosUnidadEditar.setText(String.valueOf(unidadConsultada.getMetrosCuadrados()));
                    porcentajeUnidadEditar.setText(String.valueOf(unidadConsultada.getPorcentaje()));
                    JOptionPane.showMessageDialog(
                            formularioEdificio,
                            "Unidad encontrada con exito!"
                    );
                        /*DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }*/
                } catch (ServiceException d) {
                    JOptionPane.showMessageDialog(
                            formularioEdificio,
                            "Error al buscar la unidad" + d.getMessage()
                    );
                    //throw new ServiceException(d.getMessage());
                }
            }
        });
    }

    public void actionBtnBuscarUnidadEliminar(){
        btnBuscarUnidadEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEditarUnidad = Integer.parseInt(idUnidadEliminar.getText());

                try {
                    Unidad unidadConsultada = serviceUnidad.consultarUnidad(idEditarUnidad);
                    idUnidadEliminar.setText(String.valueOf(unidadConsultada.getId()));
                    nombreUnidadEliminar.setText(unidadConsultada.getNombre());
                    ocupanteUnidadEliminar.setText(unidadConsultada.getOcupante());
                    ambientesUnidadEliminar.setText(String.valueOf(unidadConsultada.getAmbientes()));
                    metrosUnidadEliminar.setText(String.valueOf(unidadConsultada.getMetrosCuadrados()));
                    porcentajeUnidadEliminar.setText(String.valueOf(unidadConsultada.getPorcentaje()));
                    JOptionPane.showMessageDialog(
                            formularioEdificio,
                            "Unidad encontrada con exito!"
                    );
                        /*DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }*/
                } catch (ServiceException d) {
                    JOptionPane.showMessageDialog(
                            formularioEdificio,
                            "Error al buscar la unidad" + d.getMessage()
                    );
                    //throw new ServiceException(d.getMessage());
                }
            }
        });
    }

    public void actionBtnEditarUnidades(){
        btnEditarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelEditar, //modificar
                        "Editar unidad",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    int idEditarUnidad = Integer.parseInt(idUnidadEditar.getText());
                    String nombreEditarUnidad = nombreUnidadEditar.getText();
                    String ocupanteEditarUnidad = ocupanteUnidadEditar.getText();
                    int ambientesEditarUnidad = Integer.parseInt(ambientesUnidadEditar.getText());
                    int metrosEditarUnidad = Integer.parseInt(metrosUnidadEditar.getText());
                    int ubicacionEditarUnidad = Integer.parseInt(ubicacionUnidadEditar.getText());
                    int porcentajeEditarUnidad = Integer.parseInt(porcentajeUnidadEditar.getText());

                    Object[] data = new Object[7];
                    data[0] = idEditarUnidad;
                    data[1] = nombreEditarUnidad;
                    data[2] = ocupanteEditarUnidad;
                    data[3] = ambientesEditarUnidad;
                    data[4] = metrosEditarUnidad;
                    data[5] = ubicacionEditarUnidad;
                    data[6] = porcentajeEditarUnidad;

                    try {
                        serviceUnidad.modificarUnidad(data);
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Unidad editada con exito!"
                        );
                        /*DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }*/
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Error al editar la unidad" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }

            }
        });
    }

    public void actionBtnEliminarUnidad(){
        btnEliminarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelEliminar, //modificar
                        "Eliminar unidad",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    int idEliminarUnidad = Integer.parseInt(idUnidadEliminar.getText());
                    /*String nombreEliminarUnidad = nombreUnidadEliminar.getText();
                    String ocupanteEliminarUnidad = ocupanteUnidadEliminar.getText();
                    int ambientesEliminarUnidad = Integer.parseInt(ambientesUnidadEliminar.getText());
                    int metrosEliminarUnidad = Integer.parseInt(metrosUnidadEliminar.getText());
                    int ubicacionEliminarUnidad = Integer.parseInt(ubicacionUnidadEliminar.getText());
                    int porcentajeEliminarUnidad = Integer.parseInt(porcentajeUnidadEliminar.getText());*/

                    /*Object[] data = new Object[7];
                    data[0] = idEliminarUnidad;
                    data[1] = nombreEliminarUnidad;
                    data[2] = ocupanteEliminarUnidad;
                    data[3] = ambientesEliminarUnidad;
                    data[4] = metrosEliminarUnidad;
                    data[5] = ubicacionEliminarUnidad;
                    data[6] = porcentajeEliminarUnidad;*/

                    try {
                        serviceUnidad.eliminar(idEliminarUnidad);
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Unidad eliminada con exito!"
                        );
                        /*DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }*/
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Error al eliminar la unidad" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }
            }
        });
    }
}
