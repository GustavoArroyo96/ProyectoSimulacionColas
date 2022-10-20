package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHoras extends JFrame {

    private JLabel lblTexto;
    public JComboBox cmbHoras;
    public JButton btnIniciar;
    public Font fuenteEtiquetas = new Font("Verdana", Font.BOLD, 30);
    public Font fuenteBotones = new Font("Verdana", 0, 30);
    private String[] horas = {"9:00am a 10:00am","10:00am a 11:00am", "11:00am a 12:00pm",
                                "12:00pm a 01:00pm", "01:00pm a 02:00pm", "02:00pm a 03:00pm",
                                "03:00pm a 04:00pm", "04:00pm a 05:00pm", "06:00pm a 07:00pm",
                                "07:00pm a 08:00pm", "08:00pm a 09:00pm", "09:00pm a 10:00pm"};

    public VentanaHoras(){
        setTitle("Simulación Supermercado");
        setSize(1000,700);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        lblTexto = new JLabel("<html><center>Eliga las horas<br>de la simulación</center></html>");
        lblTexto.setBounds(300,100,350,200);
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setFont(fuenteEtiquetas);
        add(lblTexto);

        cmbHoras = new JComboBox(horas);
        cmbHoras.setBounds(330,280,300,30);
        add(cmbHoras);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(380, 350, 180, 50);
        btnIniciar.setBackground(Color.GRAY);
        btnIniciar.setFont(fuenteBotones);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaSimulacion ventana = new VentanaSimulacion();
                ventana.setVisible(true);
                setVisible(false);
            }
        });
        add(btnIniciar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
