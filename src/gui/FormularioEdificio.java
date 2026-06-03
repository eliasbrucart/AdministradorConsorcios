package gui;

import service.ServiceEdificio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public void armarTabla(){
        reporte = new JPanel();
        reporte.setLayout(new BorderLayout());
        contenido = new DefaultTableModel();
        jTable = new JTable();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("ID");
        contenido.addColumn("Nombre");
        contenido.addColumn("Direccion");
        reporte.add(scrollPane, BorderLayout.NORTH);
        ServiceEdificio serviceEdificio = new ServiceEdificio();
        JButton volverBtn = new JButton("Volver");
        reporte.add(volverBtn, BorderLayout.SOUTH);


        add(reporte);
    }
}
