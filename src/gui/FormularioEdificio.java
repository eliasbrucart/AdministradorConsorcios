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
            System.out.println("Error al consultar el edificio: " + e);
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
        btnEditar.setVisible(false); // Oculto por defecto
        panelAcciones.add(btnEditar);

        //selección de la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (tabla.getSelectedRow() != -1) {
                    btnEditar.setVisible(true); // Mostrar botón
                } else {
                    btnEditar.setVisible(false); // Ocultar si no hay selección
                }
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
                    JOptionPane.showMessageDialog(FormularioEdificio.this,
                            "Editando a: " + nombre + " (" + direccion + ")");
                }
            }
        });

        //Agregar los componentes directamente
        add(scrollPane, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
    }
}
