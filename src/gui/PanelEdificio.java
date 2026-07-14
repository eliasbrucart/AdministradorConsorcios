package gui;

import DAO.DAOUnidad;
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

import static java.awt.AWTEventMulticaster.add;

public class PanelEdificio extends JPanel{
    private PanelManager panelManager;
    private JPanel reporte;
    private JTable jTable;
    private JButton jbutton;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;
    private int idSeleccionado;

    public PanelEdificio(PanelManager panelManager){
        this.panelManager = panelManager;
        armarTabla();
    }

    /*public void armarTabla(){
        reporte = new JPanel();
        reporte.setLayout(new BorderLayout());
        contenido = new DefaultTableModel();
        jTable = new JTable();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("ID");
        contenido.addColumn("Nombre");
        contenido.addColumn("Direccion");
        reporte.add(scrollPane, BorderLayout.CENTER);
        ServiceEdificio serviceEdificio = new ServiceEdificio();
        JButton volverBtn = new JButton("Volver");
        reporte.add(volverBtn, BorderLayout.SOUTH);

        Object [] fila= new Object[3];
        fila[0]= 1;
        fila[1]= "Palacio 1";
        fila[2]= "Amenabar 3357";
        contenido.addRow(fila);
        add(scrollPane, BorderLayout.NORTH);

        add(reporte);
    }*/

    public void armarTabla(){
        //diseño del propio JPanel
        setLayout(new BorderLayout());

        // agregar service de Edificio para consultar sus datos.
        ServiceEdificio serviceEdificio = new ServiceEdificio();
        //Edificio edificio = null;
        ArrayList<Edificio> edificiosConsultados = new ArrayList<>();
        try{
            edificiosConsultados = serviceEdificio.consultarTodo();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar los edificios" + e.getMessage()
            );
            //System.out.println("Error al consultar el edificio: " + e);
        }
        String[] columnas = {"Identificador", "Nombre", "Dirección"};
        Object[][] datos = new Object[edificiosConsultados.size()][3];
        for(int i = 0; i < edificiosConsultados.size(); i++){
            Edificio edificio = edificiosConsultados.get(i);

            datos[i][0] = edificio.getId();
            datos[i][1] = edificio.getNombre();
            datos[i][2] = edificio.getDireccion();
        }

        //modelo y la tabla (celdas no editables directamente)
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        //panel inferior para el botón
        JPanel panelAcciones = new JPanel();
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnAgregar = new JButton("Agregar");
        JButton btnAdministrar = new JButton("Administrar");
        JButton btnLiquidarExp = new JButton("Liquidacion");
        btnEditar.setVisible(false); // Oculto por defecto
        btnEliminar.setVisible(false);
        btnAgregar.setVisible(true);
        btnAdministrar.setVisible(false);
        btnLiquidarExp.setVisible(false);
        panelAcciones.add(btnEditar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnAgregar);
        panelAcciones.add(btnAdministrar);
        panelAcciones.add(btnLiquidarExp);

        PanelUnidades panelUnidades = new PanelUnidades(this);
        panelUnidades.armarPanelEditar();
        //panelUnidades.armarPanelMostrar();
        panelUnidades.armarPanelAgregar();
        panelUnidades.armarPanelEliminar();

        PanelAdministradora panelAdministradora = new PanelAdministradora(this);
        panelAdministradora.armarPanelAgregar();
        panelAdministradora.armarPanelEditar();
        panelAdministradora.armarPanelEliminar();

        //Creamos los componentes del formulario agregar
        JTextField txtNombre = new JTextField(10);
        JTextField txtDireccion = new JTextField(15);
        JTextField txtLocalidad = new JTextField(20);
        JTextField agregarCodigoPostal = new JTextField(20);
        JTextField agregarCantidadUnidades = new JTextField(20);
        JTextField agregarCantidadPisos = new JTextField(20);
        JTextField agregarLiquidacionExpensas = new JTextField(20);
        JTextField agregarFechaLiquidacionExpensas = new JTextField(20);

        //Creamos un contenedor (Panel) y organizar los componentes
        JPanel panelFormularioAgregar = new JPanel(new GridLayout(10, 10, 6, 6));
        panelFormularioAgregar.add(new JLabel("Nombre:"));
        panelFormularioAgregar.add(txtNombre);
        panelFormularioAgregar.add(new JLabel("Dirección:"));
        panelFormularioAgregar.add(txtDireccion);
        panelFormularioAgregar.add(new JLabel("Localidad:"));
        panelFormularioAgregar.add(txtLocalidad);
        panelFormularioAgregar.add(new JLabel("Codigo Postal:"));
        panelFormularioAgregar.add(agregarCodigoPostal);
        panelFormularioAgregar.add(new JLabel("Cantidad Unidades: "));
        panelFormularioAgregar.add(agregarCantidadUnidades);
        panelFormularioAgregar.add(new JLabel("Cantidad Pisos: "));
        panelFormularioAgregar.add(agregarCantidadPisos);
        panelFormularioAgregar.add(new JLabel("Liquidacion Expensas:"));
        panelFormularioAgregar.add(agregarLiquidacionExpensas);
        panelFormularioAgregar.add(new JLabel("Fecha Liquidacion Expensas:"));
        panelFormularioAgregar.add(agregarFechaLiquidacionExpensas);

        //Creamos los componentes del formulario editar
        JTextField idEdificioModificar = new JTextField(10);
        JTextField editarNombre = new JTextField(10);
        JTextField editarDireccion = new JTextField(10);
        JTextField editarLocalidad = new JTextField(10);
        JTextField editarCodigoPostal = new JTextField(10);
        JTextField editarCantidadUnidades = new JTextField(10);
        JTextField editarCantidadPisos = new JTextField(10);
        JTextField editarLiquidacionExpensas = new JTextField(10);
        JTextField editarFechaLiquidacionExpensas = new JTextField(10);

        JPanel panelFormularioEditar = new JPanel(new GridLayout(10, 15, 8, 8));
        //panelFormularioEditar.setLayout(null);
        panelFormularioEditar.setPreferredSize(new Dimension(400, 400));
        panelFormularioEditar.add(new JLabel("ID: "));
        panelFormularioEditar.add(idEdificioModificar);
        idEdificioModificar.setEditable(false);
        panelFormularioEditar.add(new JLabel("Nombre:"));
        panelFormularioEditar.add(editarNombre);
        panelFormularioEditar.add(new JLabel("Dirección:"));
        panelFormularioEditar.add(editarDireccion);
        panelFormularioEditar.add(new JLabel("Localidad:"));
        panelFormularioEditar.add(editarLocalidad);
        panelFormularioEditar.add(new JLabel("Codigo Postal:"));
        panelFormularioEditar.add(editarCodigoPostal);
        panelFormularioEditar.add(new JLabel("Cantidad Unidades: "));
        panelFormularioEditar.add(editarCantidadUnidades);
        panelFormularioEditar.add(new JLabel("Cantidad Pisos: "));
        panelFormularioEditar.add(editarCantidadPisos);
        panelFormularioEditar.add(new JLabel("Liquidacion Expensas:"));
        panelFormularioEditar.add(editarLiquidacionExpensas);
        panelFormularioEditar.add(new JLabel("Fecha Liquidacion Expensas:"));
        panelFormularioEditar.add(editarFechaLiquidacionExpensas);

        //Componentes formulario Eliminar
        JTextField idEdificioEliminar = new JTextField(10);
        JTextField nombreEliminar = new JTextField(10);
        JTextField direccionEliminar = new JTextField(10);
        JTextField localidadEliminar = new JTextField(10);
        JTextField codigoPostalEliminar = new JTextField(10);
        JTextField cantidadUnidadesEliminar = new JTextField(10);
        JTextField cantidadPisosEliminar = new JTextField(10);
        JTextField liquidacionExpensasEliminar = new JTextField(10);
        JTextField fechaLiquidacionExpensasEliminar = new JTextField(10);

        JPanel panelTituloEliminar = new JPanel(new BorderLayout());
        JLabel tituloEliminar = new JLabel("Eliminar edificio", SwingConstants.CENTER);
        tituloEliminar.setFont(tituloEliminar.getFont().deriveFont(Font.BOLD, 24f));
        panelTituloEliminar.add(tituloEliminar, BorderLayout.NORTH);

        JPanel panelFormularioEliminar = new JPanel(new GridLayout(10, 10,8,8));
        //panelFormularioEliminar.add(tituloEliminar);
        panelFormularioEliminar.add(new JLabel("ID: "));
        panelFormularioEliminar.add(idEdificioEliminar);
        panelFormularioEliminar.add(new JLabel("Nombre:"));
        panelFormularioEliminar.add(nombreEliminar);
        panelFormularioEliminar.add(new JLabel("Direccion:"));
        panelFormularioEliminar.add(direccionEliminar);
        panelFormularioEliminar.add(new JLabel("Localidad:"));
        panelFormularioEliminar.add(localidadEliminar);
        panelFormularioEliminar.add(new JLabel("Codigo Postal:"));
        panelFormularioEliminar.add(codigoPostalEliminar);
        panelFormularioEliminar.add(new JLabel("Cantidad Unidades:"));
        panelFormularioEliminar.add(cantidadUnidadesEliminar);
        panelFormularioEliminar.add(new JLabel("Cantidad Pisos:"));
        panelFormularioEliminar.add(cantidadPisosEliminar);
        panelFormularioEliminar.add(new JLabel("Liquidacion Expensas:"));
        panelFormularioEliminar.add(liquidacionExpensasEliminar);
        panelFormularioEliminar.add(new JLabel("Fecha Liquidacion Expensas:"));
        panelFormularioEliminar.add(fechaLiquidacionExpensasEliminar);

        panelTituloEliminar.add(panelFormularioEliminar, BorderLayout.CENTER);

        idEdificioEliminar.setEditable(false);
        nombreEliminar.setEditable(false);
        direccionEliminar.setEditable(false);
        localidadEliminar.setEditable(false);
        codigoPostalEliminar.setEditable(false);
        cantidadUnidadesEliminar.setEditable(false);
        cantidadPisosEliminar.setEditable(false);
        liquidacionExpensasEliminar.setEditable(false);
        fechaLiquidacionExpensasEliminar.setEditable(false);

        JPanel administrarEdificio = new JPanel(new GridLayout(10, 10,8,8));
        JTextField expensasOrdinarias = new JTextField(10);
        JTextField expensasExtraordinarias = new JTextField(10);
        JTextField sueldoEmpleado = new JTextField(10);
        JTextField gastosMantenimiento = new JTextField(10);
        JLabel balanceTotalLabel = new JLabel();

        administrarEdificio.add(new JLabel("Expensas ordinarias: "));
        administrarEdificio.add(expensasOrdinarias);
        administrarEdificio.add(new JLabel("Expensas Extraordinarias: "));
        administrarEdificio.add(expensasExtraordinarias);
        administrarEdificio.add(new JLabel("Sueldo empleado: "));
        administrarEdificio.add(sueldoEmpleado);
        administrarEdificio.add(new JLabel("Gastos mantenimiento: "));
        administrarEdificio.add(gastosMantenimiento);
        administrarEdificio.add(balanceTotalLabel);

        JPanel liquidarExpensasPanel = new JPanel(new GridLayout(10, 10,8,8));
        JLabel cantidadUnidades = new JLabel();
        JLabel liquidacionTotal = new JLabel();

        liquidarExpensasPanel.add(cantidadUnidades);
        liquidarExpensasPanel.add(liquidacionTotal);

        //selección de la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();

            if(filaSeleccionada != -1){
                Object valorCelda = tabla.getValueAt(filaSeleccionada, 0);
                idSeleccionado = Integer.parseInt(valorCelda.toString());
                System.out.println(idSeleccionado);
                //remover luego
                //System.out.println(idSeleccionado);
                if (!e.getValueIsAdjusting()) {
                    // Ocultar si no hay selección
                    btnEditar.setVisible(tabla.getSelectedRow() != -1); // Mostrar botón
                    btnEliminar.setVisible(tabla.getSelectedRow() != -1);
                    btnAdministrar.setVisible(tabla.getSelectedRow() != -1);
                    btnLiquidarExp.setVisible(tabla.getSelectedRow() != -1);

                    panelUnidades.getBtnMostrarUnidades().setVisible(tabla.getSelectedRow() != -1);
                    panelUnidades.getBtnAgregarUnidades().setVisible(tabla.getSelectedRow() != -1);
                    panelUnidades.getBtnEditarUnidades().setVisible(tabla.getSelectedRow() != -1);
                    panelUnidades.getBtnEliminarUnidades().setVisible(tabla.getSelectedRow() != -1);
                    //panelUnidades.getBtnAgregarUnidades().setVisible(tabla.getSelectedRow() != -1);

                    // Revalidar y repintar el panel de acciones
                    panelAcciones.revalidate();
                    panelAcciones.repaint();

                    panelUnidades.revalidate();
                    panelAcciones.repaint();

                    panelUnidades.setIdSeleccionado(idSeleccionado);
                }
            }/*else{
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, selecciona un edificio de la lista.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }*/

        });

        panelUnidades.setIdSeleccionado(idSeleccionado);
        //System.out.println(idSeleccionado);


        /*panelUnidades.getBtnEditarUnidades().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        FormularioEdificio.this,
                        panelUnidades.getPanelEditar(),
                        "Editar Nueva Unidad",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });*/

        /*panelUnidades.getBtnMostrarUnidades().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        FormularioEdificio.this,
                        panelUnidades.getPanelMostrar(),
                        "Unidades del edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });8?

        /*panelUnidades.getBtnAgregarUnidades().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        FormularioEdificio.this,
                        panelUnidades.getPanelAgregar(), //modificar
                        "Agregar unidades",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });*/

        //System.out.println(idSeleccionado);
        panelUnidades.actionBtnAgregarUnidad(idSeleccionado);
        panelUnidades.actionBtnMostrarUnidades(idSeleccionado);
        panelUnidades.actionBtnEditarUnidades();
        panelUnidades.actionBtnEliminarUnidad();
        panelUnidades.actionBtnBuscarUnidadEditar();
        panelUnidades.actionBtnBuscarUnidadEliminar();

        panelAdministradora.actionBtnAgregarAdministradora();
        panelAdministradora.actionBtnEditarAdministradora();
        panelAdministradora.actionBtnEliminarAdministradora();

        /*panelUnidades.getBtnEliminarUnidades().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        FormularioEdificio.this,
                        panelUnidades.getPanelMostrar(), //modificar
                        "Eliminar unidades",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });*/

        btnLiquidarExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        PanelEdificio.this,
                        liquidarExpensasPanel,
                        "Liquidacion total de expensas",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                //traer todas las unidades del edificio.
                //sumar el porcentaje, ubicacion y ambientes para el calculo de expensas.
                //sumar todas las liquidaciones y mostrar las liquidaciones por unidad.
                liquidarExpensasPanel.removeAll();
                try{
                    ArrayList<Unidad> unidades = new ArrayList<>();
                    ServiceUnidad serviceUnidad = new ServiceUnidad();
                    serviceUnidad.calcularExpensasPorUnidad(idSeleccionado);
                    unidades = serviceUnidad.consultarTodoPorID(idSeleccionado);
                    int expensas = 0;
                    JLabel[] labelsUnidades = new JLabel[unidades.size()];
                    for (int i = 0; i < unidades.size(); i++){
                        expensas += unidades.get(i).getExpensas();

                        labelsUnidades[i] = new JLabel("Unidad " + (i + 1) + " - Expensas: " + unidades.get(i).getExpensas());

                        liquidarExpensasPanel.add(labelsUnidades[i]);
                    }
                    liquidacionTotal.setText("Liquidacion total del edificio: " + String.valueOf(expensas));
                    liquidarExpensasPanel.add(liquidacionTotal);
                    liquidarExpensasPanel.revalidate();
                    liquidarExpensasPanel.repaint();
                    //mover a service
                }catch(ServiceException d){
                    JOptionPane.showMessageDialog(
                            PanelEdificio.this,
                            "Error al obtener todas las unidades!" + d.getMessage()
                    );
                }

                //liquidacionTotal.setText("liquidacion");
            }
        });

        btnAdministrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        PanelEdificio.this,
                        administrarEdificio,
                        "Agregar Nuevo Edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                try {
                    int ultimoBalance = serviceEdificio.obtenerBalance(idSeleccionado);
                    balanceTotalLabel.setText("Balance total: " + String.valueOf(ultimoBalance));
                    //balanceTotalLabel.setText("Balance total: " + String.valueOf(balanceTotal));
                } catch (ServiceException d) {
                    JOptionPane.showMessageDialog(
                            PanelEdificio.this,
                            "No hay un ultimo balance del edificio" + d.getMessage()
                    );
                    //throw new ServiceException(d.getMessage());
                }


                if(opcion == JOptionPane.OK_OPTION) {
                    int expensasOrd = Integer.parseInt(expensasOrdinarias.getText());
                    int expensasExtr = Integer.parseInt(expensasExtraordinarias.getText());
                    int sueldo = Integer.parseInt(sueldoEmpleado.getText());
                    int mantenimiento = Integer.parseInt(gastosMantenimiento.getText());


                    Object[] data = new Object[4];
                    data[0] = expensasOrd;
                    data[1] = expensasExtr;
                    data[2] = sueldo;
                    data[3] = mantenimiento;

                    try {
                        int balanceTotal = serviceEdificio.generarBalance(data, idSeleccionado);
                        balanceTotalLabel.setText("Balance total: " + String.valueOf(balanceTotal));
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Balance generado con exito!"
                        );
                        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Error al agregar edificio" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                    //Mandar datos al ServiceEdificio para agregar un edificio.
                }
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        PanelEdificio.this,
                        panelFormularioAgregar,
                        "Agregar Nuevo Edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION) {
                    String nuevoNombre = txtNombre.getText();
                    String nuevaDireccion = txtDireccion.getText();
                    String nuevaLocalidad = txtLocalidad.getText();
                    int nuevoCodigoPostal = Integer.parseInt(agregarCodigoPostal.getText());
                    int nuevoCantidadUnidades = Integer.parseInt(agregarCantidadUnidades.getText());
                    int nuevoCantidadPisos = Integer.parseInt(agregarCantidadPisos.getText());
                    int nuevoLiquidacionExpensas = Integer.parseInt(agregarLiquidacionExpensas.getText());
                    String nuevoFechaLiquidacionExpensas = agregarFechaLiquidacionExpensas.getText();

                    Object[] data = new Object[9];
                    data[0] = nuevoNombre;
                    data[1] = nuevaDireccion;
                    data[2] = nuevaLocalidad;
                    data[3] = nuevoCodigoPostal;
                    data[4] = nuevoCantidadUnidades;
                    data[5] = nuevoCantidadPisos;
                    data[6] = nuevoLiquidacionExpensas;
                    data[7] = nuevoFechaLiquidacionExpensas;
                    try {
                        serviceEdificio.agregarEdificio(data);
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Edificio agregado con exito!"
                        );
                        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Error al agregar edificio" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                    //Mandar datos al ServiceEdificio para agregar un edificio.
                }
            }
        });

        btnEditar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //service buscar en DB por id
                Edificio edificioConsultado = new Edificio();
                try {
                    edificioConsultado = serviceEdificio.consultarEdificio(idSeleccionado);
                    idEdificioModificar.setText(String.valueOf(edificioConsultado.getId()));
                    editarNombre.setText(edificioConsultado.getNombre());
                    editarDireccion.setText(edificioConsultado.getDireccion());
                    editarLocalidad.setText(edificioConsultado.getLocalidad());
                    editarCodigoPostal.setText(String.valueOf(edificioConsultado.getCodigoPostal()));
                    editarCantidadUnidades.setText(String.valueOf(edificioConsultado.getCantidadUnidades()));
                    editarCantidadPisos.setText(String.valueOf(edificioConsultado.getCantidadPisos()));
                    editarLiquidacionExpensas.setText(String.valueOf(edificioConsultado.getLiquidacionExpensas()));
                    editarFechaLiquidacionExpensas.setText(edificioConsultado.getFechaLiquidacionExpensas());
                } catch (ServiceException d) {
                    JOptionPane.showMessageDialog(
                            PanelEdificio.this,
                            "Error al consultar edificio" + d.getMessage()
                    );
                }

                int opcion = JOptionPane.showConfirmDialog(
                        PanelEdificio.this,
                        panelFormularioEditar,
                        "Editar Edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (opcion == JOptionPane.OK_OPTION) {
                    int idEdificio = Integer.parseInt(idEdificioModificar.getText());
                    String nuevoNombre = editarNombre.getText();
                    String nuevoDireccion = editarDireccion.getText();
                    String nuevoLocalidad = editarLocalidad.getText();
                    int nuevoCodigoPostal = Integer.parseInt(editarCodigoPostal.getText());
                    int nuevoCantidadUnidades = Integer.parseInt(editarCantidadUnidades.getText());
                    int nuevoCantidadPisos = Integer.parseInt(editarCantidadPisos.getText());
                    int nuevoLiquidacionExpensas = Integer.parseInt(editarLiquidacionExpensas.getText());
                    String nuevoFechaLiquidacionExpensas = editarFechaLiquidacionExpensas.getText();

                    Object[] data = new Object[9];
                    data[0] = nuevoNombre;
                    data[1] = nuevoDireccion;
                    data[2] = nuevoLocalidad;
                    data[3] = nuevoCodigoPostal;
                    data[4] = nuevoCantidadUnidades;
                    data[5] = nuevoCantidadPisos;
                    data[6] = nuevoLiquidacionExpensas;
                    data[7] = nuevoFechaLiquidacionExpensas;
                    data[8] = idEdificio;

                    try {
                        serviceEdificio.modificarEdificio(data);
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Edificio modificado con exito!"
                        );
                        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Error al modificar edificio" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //llenar con los datos
                Edificio edificioConsultado = new Edificio();
                try{
                    edificioConsultado = serviceEdificio.consultarEdificio(idSeleccionado);
                    idEdificioEliminar.setText(String.valueOf(edificioConsultado.getId()));
                    nombreEliminar.setText(edificioConsultado.getNombre());
                    direccionEliminar.setText(edificioConsultado.getDireccion());
                    localidadEliminar.setText(edificioConsultado.getLocalidad());
                    codigoPostalEliminar.setText(String.valueOf(edificioConsultado.getCodigoPostal()));
                    cantidadUnidadesEliminar.setText(String.valueOf(edificioConsultado.getCantidadUnidades()));
                    cantidadPisosEliminar.setText(String.valueOf(edificioConsultado.getCantidadPisos()));
                    liquidacionExpensasEliminar.setText(String.valueOf(edificioConsultado.getLiquidacionExpensas()));
                    fechaLiquidacionExpensasEliminar.setText(edificioConsultado.getFechaLiquidacionExpensas());
                } catch (ServiceException d) {
                    JOptionPane.showMessageDialog(
                            PanelEdificio.this,
                            "Error al eliminar edificio" + d.getMessage()
                    );
                }
                int opcion = JOptionPane.showConfirmDialog(
                        PanelEdificio.this,
                        panelTituloEliminar,
                        "Eliminar Edificio",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (opcion == JOptionPane.OK_OPTION){
                    try{
                        serviceEdificio.eliminar(idSeleccionado);
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Edificio eliminado con exito!"
                        );
                        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        modelo.setRowCount(0); //limpia la tabla

                        ArrayList<Edificio> lista = serviceEdificio.consultarTodo();

                        for (Edificio ed : lista) {
                            modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
                        }
                    }catch(ServiceException d){
                        JOptionPane.showMessageDialog(
                                PanelEdificio.this,
                                "Error al eliminar edificio" + d.getMessage()
                        );
                    }
                }
            }
        });

        //Agregar los componentes directamente
        add(scrollPane, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
        add(panelAdministradora, BorderLayout.NORTH);
        add(panelUnidades, BorderLayout.WEST);
    }

    public void recargarDatosTabla(ServiceEdificio serviceEdificio, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); //limpia la tabla

        ArrayList<Edificio> lista;

        try{
            lista = serviceEdificio.consultarTodo();

            for (Edificio ed : lista) {
                modelo.addRow(new Object[]{ed.getId(), ed.getNombre(), ed.getDireccion()});
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(
                    PanelEdificio.this,
                    "Error al listar todos los edificios" + e.getMessage()
            );
        }
    }
}
