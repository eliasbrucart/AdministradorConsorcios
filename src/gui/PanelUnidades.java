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
    private PanelEdificio panelEdificio;
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
    private JTable tabla;
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


    public PanelUnidades(PanelEdificio panelEdificio){
        serviceUnidad = new ServiceUnidad();
        btnMostrarUnidades = new JButton("Mostrar Unidades");
        btnAgregarUnidades = new JButton("Agregar Unidades");
        btnEditarUnidades = new JButton("Editar Unidades");
        btnEliminarUnidades = new JButton("Eliminar Unidades");

        btnMostrarUnidades.setVisible(false);
        btnAgregarUnidades.setVisible(false);
        btnEditarUnidades.setVisible(false);
        btnEliminarUnidades.setVisible(false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(btnMostrarUnidades);
        add(Box.createVerticalStrut(10));
        add(btnAgregarUnidades);
        add(Box.createVerticalStrut(10));
        add(btnEditarUnidades);
        add(Box.createVerticalStrut(10));
        add(btnEliminarUnidades);

        this.panelEdificio = panelEdificio;
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

    /*public void armarPanelEditar(){
        panelEditar = new JPanel(new GridLayout(0, 2, 8, 8));
        panelEditar.setPreferredSize(new Dimension(400, 400));

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

    public void armarPanelEditar2(){
        // Cuadrícula de 2 columnas (Izquierda: Labels, Derecha: Campos)
        panelEditar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelEditar.setPreferredSize(new Dimension(400, 400));

        // --- FILA 1: ID (Especial con subpanel) ---
        panelEditar.add(new JLabel("ID: "));

        JPanel subPanelId = new JPanel(new GridLayout(0, 2, 8, 12)); // Espacio de 5px entre campo y botón
        idUnidadEditar.setEditable(true);
        subPanelId.add(idUnidadEditar, BorderLayout.CENTER); // El campo ocupa el centro
        subPanelId.add(btnBuscarUnidadEditar, BorderLayout.EAST); // El botón va a la derecha
        panelEditar.add(subPanelId); // Agregamos el subpanel al diseño principal

        // --- FILA 2 en adelante: Estructura normal de 2 elementos por fila ---
        panelEditar.add(new JLabel("Nombre: "));
        nombreUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(nombreUnidadEditar);

        panelEditar.add(new JLabel("Ocupante: "));
        ocupanteUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(ocupanteUnidadEditar);

        panelEditar.add(new JLabel("Ambientes: "));
        ambientesUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(ambientesUnidadEditar);

        panelEditar.add(new JLabel("Metros cuadrados: "));
        metrosUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(metrosUnidadEditar);

        panelEditar.add(new JLabel("Ubicacion: "));
        ubicacionUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(ubicacionUnidadEditar);

        panelEditar.add(new JLabel("Porcentaje de ocupacion: "));
        porcentajeUnidadEditar.setPreferredSize(new Dimension(150, 15));
        panelEditar.add(porcentajeUnidadEditar);
    }*/

    public void armarPanelEditar(){
        panelEditar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelEditar.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 12));

        formPanel.add(new JLabel("ID: "));

        JPanel subPanelId = new JPanel(new BorderLayout(5, 0));
        idUnidadEditar.setEditable(true);

        idUnidadEditar.setPreferredSize(new Dimension(60, 25));

        subPanelId.add(idUnidadEditar, BorderLayout.CENTER);
        subPanelId.add(btnBuscarUnidadEditar, BorderLayout.EAST);
        formPanel.add(subPanelId);

        formPanel.add(new JLabel("Nombre: "));
        nombreUnidadEditar.setPreferredSize(new Dimension(150, 25)); // Tamaño estándar ideal para los textos
        formPanel.add(nombreUnidadEditar);

        formPanel.add(new JLabel("Ocupante: "));
        ocupanteUnidadEditar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ocupanteUnidadEditar);

        formPanel.add(new JLabel("Ambientes: "));
        ambientesUnidadEditar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ambientesUnidadEditar);

        formPanel.add(new JLabel("Metros cuadrados: "));
        metrosUnidadEditar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(metrosUnidadEditar);

        formPanel.add(new JLabel("Ubicación: "));
        ubicacionUnidadEditar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ubicacionUnidadEditar);

        formPanel.add(new JLabel("Porcentaje de ocupación: "));
        porcentajeUnidadEditar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(porcentajeUnidadEditar);

        panelEditar.add(formPanel);
    }

    public void armarPanelEliminar(){
        panelEliminar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelEliminar.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 12));

        formPanel.add(new JLabel("ID: "));

        JPanel subPanelId = new JPanel(new BorderLayout(5, 0));
        idUnidadEliminar.setEditable(true);

        idUnidadEliminar.setPreferredSize(new Dimension(60, 25));

        subPanelId.add(idUnidadEliminar, BorderLayout.CENTER);
        subPanelId.add(btnBuscarUnidadEliminar, BorderLayout.EAST);
        formPanel.add(subPanelId);

        formPanel.add(new JLabel("Nombre: "));
        nombreUnidadEliminar.setPreferredSize(new Dimension(150, 25)); // Tamaño estándar ideal para los textos
        formPanel.add(nombreUnidadEliminar);

        formPanel.add(new JLabel("Ocupante: "));
        ocupanteUnidadEliminar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ocupanteUnidadEliminar);

        formPanel.add(new JLabel("Ambientes: "));
        ambientesUnidadEliminar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ambientesUnidadEliminar);

        formPanel.add(new JLabel("Metros cuadrados: "));
        metrosUnidadEliminar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(metrosUnidadEliminar);

        formPanel.add(new JLabel("Ubicación: "));
        ubicacionUnidadEliminar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(ubicacionUnidadEliminar);

        formPanel.add(new JLabel("Porcentaje de ocupación: "));
        porcentajeUnidadEliminar.setPreferredSize(new Dimension(150, 25));
        formPanel.add(porcentajeUnidadEliminar);

        panelEliminar.add(formPanel);
    }

    public void armarPanelMostrar() {
        // 1. IMPORTANTE: Si el panel ya existe, solo limpiamos la tabla vieja antes de consultar
        if (panelMostrar == null) {
            panelMostrar = new JPanel(new GridLayout(2, 2, 4, 4));
            panelMostrar.setPreferredSize(new Dimension(400, 400));
        }

        ServiceEdificio serviceEdificio = new ServiceEdificio();
        int idEdificioConsultado = 0;
        try {
            // Nota: Cambié idSeleccionado por 'id' que viene como parámetro del método
            Edificio edificioConsultado = serviceEdificio.consultarEdificio(idSeleccionado);
            idEdificioConsultado = edificioConsultado.getId();
        } catch(ServiceException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar el edificio " + e.getMessage());
            return; // Detenemos la ejecución si falla el edificio
        }

        ArrayList<Unidad> unidadesConsultadas = new ArrayList<>();
        try {
            unidadesConsultadas = serviceUnidad.consultarTodoPorID(idEdificioConsultado);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Error al listar las unidades " + e.getMessage());
        }

        String[] columnas = {"Identificador", "Nombre", "Ocupante"};
        Object[][] datos = new Object[unidadesConsultadas.size()][3];
        for(int i = 0; i < unidadesConsultadas.size(); i++) {
            Unidad unidad = unidadesConsultadas.get(i);
            datos[i][0] = unidad.getId();
            datos[i][1] = unidad.getNombre();
            datos[i][2] = unidad.getOcupante();
        }

        // 2. LA SOLUCIÓN: Si la tabla ya existe, solo le cambiamos el modelo de datos
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (tabla == null) {
            // Primera vez: Creamos la tabla y el scrollpane por única vez
            tabla = new JTable(modelo);
            scrollPane = new JScrollPane(tabla);
            panelMostrar.add(scrollPane);
        } else {
            // Siguientes clics: Le asignamos el nuevo modelo con los datos frescos
            tabla.setModel(modelo);
        }

        //panelMostrar.revalidate();
        //panelMostrar.repaint();
        // 3. OBLIGATORIO EN SWING: Forzar al contenedor principal a redibujarse
        //panelEdificio.revalidate();
        //panelEdificio.repaint();
    }

    public void actualizarPanelMostrarUnidades() throws ServiceException {
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0); //limpia la tabla

        ArrayList<Unidad> lista = serviceUnidad.consultarUnidadesPorIDEdificio(idSeleccionado);

        for (Unidad ed : lista) {
            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getOcupante()});
        }

        //this.revalidate();
        //this.repaint();
    }


    public void actionBtnMostrarUnidades(int id){
        System.out.println("dentro de funcion id: " + id);
        btnMostrarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        panelEdificio,
                        panelMostrar,
                        "Unidades del edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                //armarPanelMostrar();
                try {
                    actualizarPanelMostrarUnidades();
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void actionBtnAgregarUnidad(int id){
        System.out.println("dentro de funcion id: " + id);
        btnAgregarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        panelEdificio,
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
                                panelEdificio,
                                "Unidad agregada con exito!"
                        );
                        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Unidad> lista = serviceUnidad.consultarTodo();

                        for (Unidad ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getOcupante()});
                        }
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                panelEdificio,
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
                            panelEdificio,
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
                            panelEdificio,
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
                            panelEdificio,
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
                            panelEdificio,
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
                        panelEdificio,
                        panelEditar, //modificar
                        "Editar unidad",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    //int idEditarUnidad = Integer.parseInt(idUnidadEditar.getText());
                    int idEditarUnidad = idSeleccionado;
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
                                panelEdificio,
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
                                panelEdificio,
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
                        panelEdificio,
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
                                panelEdificio,
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
                                panelEdificio,
                                "Error al eliminar la unidad" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }
            }
        });
    }
}
