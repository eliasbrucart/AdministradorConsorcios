package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    JFrame jFrame;
    public PanelManager(int tipo){
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        mostrar(jPanel);
        /*if(tipo == 1){
        }*/
    }

    public void mostrar(JPanel panel){
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(BorderLayout.CENTER,panel);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
