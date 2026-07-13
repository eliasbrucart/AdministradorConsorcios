package gui;

import entidades.Administradora;
import entidades.Edificio;
import org.h2.util.json.JSONValidationTargetWithoutUniqueKeys;
import service.ServiceAdministradora;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAdministradora extends JPanel {
    private FormularioEdificio formularioEdificio;

    private JButton btnAgregarAdministradora;
    private JButton btnEditarAdministradora;
    private JButton btnEliminarAdministradora;

    private ServiceAdministradora serviceAdministradora;

    private JPanel panelAgregar;
    private JTextField nombreAdministradoraAgregar = new JTextField(10);
    private JTextField direccionAdministradoraAgregar = new JTextField(10);
    private JTextField telefonoAdministradoraAgregar = new JTextField(15);
    private JTextField cuitAdministradoraAgregar = new JTextField(15);

    private JPanel panelEditar;
    private JTextField idAdministradoraEditar = new JTextField(10);
    private JTextField nombreAdministradoraEditar = new JTextField(10);
    private JTextField direccionAdministradoraEditar = new JTextField(10);
    private JTextField telefonoAdministradoraEditar = new JTextField(15);
    private JTextField cuitAdministradoraEditar = new JTextField(15);


    public PanelAdministradora(FormularioEdificio formularioEdificio){
        this.formularioEdificio = formularioEdificio;
        serviceAdministradora = new ServiceAdministradora();
        Administradora administradoraConsultada = new Administradora();
        JLabel nombreAdministradora = new JLabel();
        JLabel direccionAdministradora = new JLabel();
        JLabel telefonoAdministradora = new JLabel();
        try{
            administradoraConsultada = serviceAdministradora.consultarAdministradora(1);
            //nombreAdministradora.setText(administradoraConsultada.getNombre());
            nombreAdministradora.setText("Nombre: " + administradoraConsultada.getNombre());
            direccionAdministradora.setText("Direccion: " + administradoraConsultada.getDireccion());
            telefonoAdministradora.setText("Telefono: " + administradoraConsultada.getTelefono());
        }catch(ServiceException e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error al consultar la administradora " + e.getMessage()
            );
        }

        btnAgregarAdministradora = new JButton("Agregar Adm");
        btnEditarAdministradora = new JButton("Editar Adm");
        btnEliminarAdministradora = new JButton("Eliminar Adm");

        add(btnAgregarAdministradora);
        add(btnEditarAdministradora);
        add(btnEliminarAdministradora);
        add(nombreAdministradora);
        add(direccionAdministradora);
        add(telefonoAdministradora);
    }

    public void armarPanelAgregar(){
        panelAgregar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelAgregar.add(new JLabel("Nombre: "));
        panelAgregar.add(nombreAdministradoraAgregar);
        panelAgregar.add(new JLabel("Direccion: "));
        panelAgregar.add(direccionAdministradoraAgregar);
        panelAgregar.add(new JLabel("Telefono: "));
        panelAgregar.add(telefonoAdministradoraAgregar);
        panelAgregar.add(new JLabel("Cuit: "));
        panelAgregar.add(cuitAdministradoraAgregar);
    }

    public void armarPanelEditar(){
        panelEditar = new JPanel(new GridLayout(10, 15, 8, 8));

        panelAgregar.add(new JLabel("ID: "));
        //idAdministradoraEditar.setText("1");
        idAdministradoraEditar.setEditable(false);
        panelEditar.add(idAdministradoraEditar);
        panelEditar.add(new JLabel("Nombre: "));
        panelEditar.add(nombreAdministradoraEditar);
        panelEditar.add(new JLabel("Direccion: "));
        panelEditar.add(direccionAdministradoraEditar);
        panelEditar.add(new JLabel("Telefono: "));
        panelEditar.add(telefonoAdministradoraEditar);
        panelEditar.add(new JLabel("Cuit: "));
        panelEditar.add(cuitAdministradoraEditar);

        try{
            Administradora administradoraConsultada = new Administradora();
            administradoraConsultada = serviceAdministradora.consultarAdministradora(1);

            idAdministradoraEditar.setText(String.valueOf(administradoraConsultada.getId()));
            nombreAdministradoraEditar.setText(administradoraConsultada.getNombre());
            direccionAdministradoraEditar.setText(administradoraConsultada.getDireccion());
            telefonoAdministradoraEditar.setText(String.valueOf(administradoraConsultada.getTelefono()));
            cuitAdministradoraEditar.setText(String.valueOf(administradoraConsultada.getCuit()));
            //nombreAdministradora.setText(administradoraConsultada.getNombre());
        }catch(ServiceException d){
            JOptionPane.showMessageDialog(
                    formularioEdificio,
                    "Error al consultar la administradora " + d.getMessage()
            );
        }
    }

    public void actionBtnAgregarAdministradora(){
        btnAgregarAdministradora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelAgregar,
                        "Agregar Administradora",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    String nombreAdministradora = nombreAdministradoraAgregar.getText();
                    String direccionAdministradora = direccionAdministradoraAgregar.getText();
                    long telefonoAdministradora = Long.parseLong(telefonoAdministradoraAgregar.getText());
                    long cuitAdministradora = Long.parseLong(cuitAdministradoraAgregar.getText());

                    Object[] data = new Object[4];
                    data[0] = nombreAdministradora;
                    data[1] = direccionAdministradora;
                    data[2] = telefonoAdministradora;
                    data[3] = cuitAdministradora;

                    try {
                        serviceAdministradora.agregarAdministradora(data);
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Administradora agregada con exito!"
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
                                "Error al agregar la administradora" + d.getMessage()
                        );
                        //throw new ServiceException(d.getMessage());
                    }
                }
            }
        });
    }

    public void actionBtnEditarAdministradora(){
        btnEditarAdministradora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        formularioEdificio,
                        panelEditar,
                        "Editar Administradora",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if(opcion == JOptionPane.OK_OPTION){
                    int idAdministradora = Integer.parseInt(idAdministradoraEditar.getText());
                    String nombreAdministradora = nombreAdministradoraEditar.getText();
                    String direccionAdministradora = direccionAdministradoraEditar.getText();
                    long telefonoAdministradora = Long.parseLong(telefonoAdministradoraEditar.getText());
                    long cuitAdministradora = Long.parseLong(cuitAdministradoraEditar.getText());

                    Object[] data = new Object[5];
                    data[0] = idAdministradora;
                    data[1] = nombreAdministradora;
                    data[2] = direccionAdministradora;
                    data[3] = telefonoAdministradora;
                    data[4] = cuitAdministradora;

                    try{
                        serviceAdministradora.modificarAdministradora(data);
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Administradora editada con exito!"
                        );
                        //nombreAdministradora.setText(administradoraConsultada.getNombre());
                    }catch(ServiceException d){
                        JOptionPane.showMessageDialog(
                                formularioEdificio,
                                "Error al modificar la administradora " + d.getMessage()
                        );
                    }
                }
            }
        });
    }
}
