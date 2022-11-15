package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame implements Runnable{

    public JLabel lblTexto, lblIngreseRapida, lblIngreseNormal;
    public JTextField txtCajasRapidas, txtCajasNormales;
    public JButton btnSiguiente;
    public Font fuentePEtiquetas = new Font("Verdana", Font.BOLD, 30);
    public Font fuenteEtiquetas = new Font("Verdana", Font.BOLD, 15);
    public Font fuenteBotones = new Font("Verdana", 0, 30);

    public VentanaInicio(){

        setTitle("Simulación Supermercado");
        setSize(1000,700);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        lblTexto = new JLabel("<html>Simulador de<br><center> colas de</center><center>Supermecado</center></html>");
        lblTexto.setBounds(300,10,300,150);
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setFont(fuentePEtiquetas);
        add(lblTexto);

        lblIngreseRapida = new JLabel("Ingrese # de clientes para cajas rápida: ");
        lblIngreseRapida.setBounds(60,150,350,200);
        lblIngreseRapida.setHorizontalAlignment(SwingConstants.CENTER);
        lblIngreseRapida.setFont(fuenteEtiquetas);
        add(lblIngreseRapida);

        txtCajasRapidas = new JTextField(20);
        txtCajasRapidas.setBounds(410, 240, 120, 25);
        txtCajasRapidas.setHorizontalAlignment(0);
        add(txtCajasRapidas);

        lblIngreseNormal = new JLabel("Ingrese # de clientes para cajas normal: ");
        lblIngreseNormal.setBounds(60,220,350,200);
        lblIngreseNormal.setHorizontalAlignment(SwingConstants.CENTER);
        lblIngreseNormal.setFont(fuenteEtiquetas);
        add(lblIngreseNormal);

        txtCajasNormales = new JTextField(20);
        txtCajasNormales.setBounds(410, 300, 120, 25);
        txtCajasNormales.setHorizontalAlignment(0);
        add(txtCajasNormales);

        btnSiguiente = new JButton("Comenzar");
        btnSiguiente.setBounds(300, 450, 350, 80);
        btnSiguiente.setBackground(Color.GRAY);
        btnSiguiente.setFont(fuenteBotones);
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.addActionListener(e -> {
            VentanaSimulacion ventana = new VentanaSimulacion(Integer.parseInt(txtCajasNormales.getText()), Integer.parseInt(txtCajasRapidas.getText()));
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
