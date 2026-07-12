package gui;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;

public class PanelManager {
    private JFrame jFrame;
    private FormularioEdificio formularioEdificio;
    public PanelManager(int tipo){
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        //mostrar(jPanel);
        if(tipo == 1){
            formularioEdificio = new FormularioEdificio(this);
        }
        mostrar(formularioEdificio);
    }

    public void mostrar(JPanel panel){
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(BorderLayout.CENTER,panel);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setSize(1024,768);
    }
}
