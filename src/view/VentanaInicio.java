package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame implements Runnable{

    public JLabel lblTexto;
    public JButton btnSiguiente;
    public Font fuenteEtiquetas = new Font("Verdana", Font.BOLD, 30);
    public Font fuenteBotones = new Font("Verdana", 0, 30);

    public VentanaInicio(){

        setTitle("Simulación Supermercado");
        setSize(1000,700);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        lblTexto = new JLabel("<html>Simulador de<br><center> colas de</center><center>Supermecado</center></html>");
        lblTexto.setBounds(300,100,350,200);
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setFont(fuenteEtiquetas);
        add(lblTexto);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(300, 350, 350, 80);
        btnSiguiente.setBackground(Color.GRAY);
        btnSiguiente.setFont(fuenteBotones);
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.addActionListener(e -> {
            VentanaHoras ventana = new VentanaHoras();
            ventana.setVisible(true);
            setVisible(false);
        });
        add(btnSiguiente);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {

    }

}
