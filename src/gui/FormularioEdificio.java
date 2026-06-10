package gui;

import entidades.Edificio;
import service.ServiceEdificio;
import service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.text.Normalizer;

import static java.awt.AWTEventMulticaster.add;

public class FormularioEdificio extends JPanel{
    private PanelManager panelManager;
    private JPanel reporte;
    private JTable jTable;
    private JButton jbutton;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;

    public FormularioEdificio(PanelManager panelManager){
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
                    FormularioEdificio.this,
                    "Error al listar los edificios" + e.getMessage()
            );
            //System.out.println("Error al consultar el edificio: " + e);
        }
        String[] columnas = {"Nombre", "Dirección"};
        Object[][] datos = new Object[edificiosConsultados.size()][2];
        for(int i = 0; i < edificiosConsultados.size(); i++){
            Edificio edificio = edificiosConsultados.get(i);

            datos[i][0] = edificio.getNombre();
            datos[i][1] = edificio.getDireccion();
        }
        /*for(Edificio edificio:edificiosConsultados){
            datos = new Object[][]{
                    {edificio.getNombre(), edificio.getDireccion()}
                    //{"Palacio 2", "Avenida Siempre Viva 742"},
                    //{"Palacio 3", "Ruta 66 Km 10"}
            };
        }*/

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
        btnEditar.setVisible(false); // Oculto por defecto
        btnEliminar.setVisible(false);
        btnAgregar.setVisible(true);
        panelAcciones.add(btnEditar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnAgregar);

        //Creamos los componentes del formulario
        JTextField txtNombre = new JTextField(10);
        JTextField txtDireccion = new JTextField(15);
        JTextField txtLocalidad = new JTextField(20);
        JTextField agregarCodigoPostal = new JTextField(20);
        JTextField agregarCantidadUnidades = new JTextField(20);
        JTextField agregarCantidadPisos = new JTextField(20);
        JTextField agregarLiquidacionExpensas = new JTextField(20);
        JTextField agregarFechaLiquidacionExpensas = new JTextField(20);

        //Creamos un contenedor (Panel) y organizar los componentes
        JPanel panelFormularioAgregar = new JPanel(new GridLayout(9, 9, 5, 5));
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


        //selección de la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Ocultar si no hay selección
                btnEditar.setVisible(tabla.getSelectedRow() != -1); // Mostrar botón
                btnEliminar.setVisible(tabla.getSelectedRow() != -1);
                //btnAgregar.setVisible(tabla.getSelectedRow() != -1);
                // Revalidar y repintar el panel de acciones
                panelAcciones.revalidate();
                panelAcciones.repaint();
            }
        });

        // Acción para el botón editar
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String nombre = (String) tabla.getValueAt(filaSeleccionada, 0);
                    String direccion = (String) tabla.getValueAt(filaSeleccionada, 1);

                    // Usamos 'MiPanelTabla.this' como componente padre para el diálogo
                    /*JOptionPane.showMessageDialog(FormularioEdificio.this,
                            "Editando a: " + nombre + " (" + direccion + ")");*/

                    /*int opcion = JOptionPane.showConfirmDialog(
                            FormularioEdificio.this,
                            panelFormularioAgregar,
                            "Agregar Nuevo Edificio",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if(opcion == JOptionPane.OK_OPTION){
                        String nuevoNombre = txtNombre.getText();
                        String nuevaDireccion = txtDireccion.getText();

                        Object[] data = new Object[9];
                        data[0] = nuevoNombre;
                        data[1] = nuevaDireccion;
                        try{
                            serviceEdificio.agregarEdificio(data);
                        } catch (ServiceException d) {
                            JOptionPane.showMessageDialog(
                                    FormularioEdificio.this,
                                    "Error al agregar edificio"
                            );
                            //throw new ServiceException(d.getMessage());
                        }
                        //Mandar datos al ServiceEdificio para agregar un edificio.
                    }*/
                }
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showConfirmDialog(
                        FormularioEdificio.this,
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
                    } catch (ServiceException d) {
                        JOptionPane.showMessageDialog(
                                FormularioEdificio.this,
                                "Error al agregar edificio" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                    //Mandar datos al ServiceEdificio para agregar un edificio.
                }
            }
        });

        //Agregar los componentes directamente
        add(scrollPane, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
    }
}
