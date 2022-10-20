package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class VentanaSimulacion extends JFrame implements Runnable{

    private ImageIcon logoCajero = new ImageIcon(Objects.requireNonNull(getClass().getResource("cajero.png")));
    private ImageIcon logoCliente = new ImageIcon(Objects.requireNonNull(getClass().getResource("cliente.png")));
    public Font fuentePrincipal = new Font("Verdana", Font.BOLD, 20);
    public Font fuenteEtiquetas = new Font("Verdana", Font.PLAIN, 15);
    public Font fuenteBotones = new Font("Verdana", 0, 20);
    private JLabel caja1 = new JLabel("Caja 1");
    private JLabel caja2 = new JLabel("Caja 2");
    private JLabel caja3 = new JLabel("Caja 3");
    private JLabel caja4 = new JLabel("Caja 4");
    private JLabel caja5 = new JLabel("Caja 5");
    private JLabel caja6 = new JLabel("Caja 6");
    private JLabel caja7 = new JLabel("Caja 7");
    private JLabel lblMonitor = new JLabel("Monitor");
    private JLabel lblClientes = new JLabel("Clientes atendidos");
    private JLabel lblTiempo = new JLabel("Tiempo");
    private JLabel lblPartida = new JLabel("Punto de Partida: ");
    public JButton btnTerminar;
    public JLabel contadorTiempo = new JLabel();
    private int horas = 0, minutos = 0, segundos = 0;
    volatile boolean ejecutar = true;

    public VentanaSimulacion(){
        setTitle("Simulación Supermercado");
        setSize(1000,700);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        // Etiquetas funcionales
        etiquetasPrincipales(lblTiempo, 8);
        etiquetasPrincipales(lblMonitor, 300);
        etiquetasPrincipales(lblClientes, 670);
        etiquetasResultantes(contadorTiempo, 8);

        //Cajas rápidas
        iconosCajeros(30,150, logoCajero);
        iconosCajeros(150,150, logoCajero);

        // Se crean los cajeros
        iconosCajeros(300,150, logoCajero);
        iconosCajeros(420,150, logoCajero);
        iconosCajeros(540,150, logoCajero);
        iconosCajeros(660,150, logoCajero);
        iconosCajeros(780,150, logoCajero);

        // Etiquetas cajeros
        etiquetasCajeros(caja1, 8);
        etiquetasCajeros(caja2, 128);
        etiquetasCajeros(caja3, 278);
        etiquetasCajeros(caja4, 398);
        etiquetasCajeros(caja5, 518);
        etiquetasCajeros(caja6, 638);
        etiquetasCajeros(caja7, 758);

        // PRUEBAS!!!!
        lblPartida.setBounds(180, 430, 300, 25);
        lblPartida.setHorizontalAlignment(SwingConstants.CENTER);
        lblPartida.setFont(fuentePrincipal);
        add(lblPartida);

        // RUTA A CAJA 1
        iconosClientes(450, 430, logoCliente);
        iconosClientes(450, 380, logoCliente);
        iconosClientes(100, 380, logoCliente);
        iconosClientes(100, 150, logoCliente);

        // RUTA A CAJA 2
        iconosClientes(450, 430, logoCliente);
        iconosClientes(450, 380, logoCliente);
        iconosClientes(220, 380, logoCliente);
        iconosClientes(220, 150, logoCliente);

        // RUTA A CAJA 3
        iconosClientes(550, 430, logoCliente);
        iconosClientes(550, 330, logoCliente);
        iconosClientes(370, 330, logoCliente);
        iconosClientes(370, 150, logoCliente);

        // RUTA A CAJA 4
        iconosClientes(550, 430, logoCliente);
        iconosClientes(550, 330, logoCliente);
        iconosClientes(490, 330, logoCliente);
        iconosClientes(490, 150, logoCliente);

        // RUTA A CAJA 5
        iconosClientes(550, 430, logoCliente);
        iconosClientes(550, 330, logoCliente);
        iconosClientes(610, 330, logoCliente);
        iconosClientes(610, 150, logoCliente);

        // RUTA A CAJA 6
        iconosClientes(550, 430, logoCliente);
        iconosClientes(550, 330, logoCliente);
        iconosClientes(730, 330, logoCliente);
        iconosClientes(730, 150, logoCliente);

        // RUTA A CAJA 7
        iconosClientes(550, 430, logoCliente);
        iconosClientes(550, 330, logoCliente);
        iconosClientes(850, 330, logoCliente);
        iconosClientes(850, 150, logoCliente);

        btnTerminar = new JButton("Terminar simulación");
        btnTerminar.setBounds(680, 580, 250, 50);
        btnTerminar.setBackground(Color.GRAY);
        btnTerminar.setFont(fuenteBotones);
        btnTerminar.setForeground(Color.WHITE);
        add(btnTerminar);

        Thread t = new Thread(this);
        t.start();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void iconosCajeros(int x, int y, ImageIcon logoCajero){
        JLabel label = new JLabel();
        label.setBounds(x, y, 70, 70);
        Icon icono = new ImageIcon(logoCajero.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        label.setBorder(new LineBorder(Color.CYAN));
        add(label);
    }
    public void iconosClientes(int x, int y, ImageIcon logoCajero){
        JLabel label = new JLabel();
        label.setBounds(x, y, 40, 40);
        Icon icono = new ImageIcon(logoCajero.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        label.setBorder(new LineBorder(Color.CYAN));
        add(label);
    }
    public void etiquetasCajeros(JLabel label, int x){
        label.setBounds(x, 220, 120, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuenteEtiquetas);
        add(label);
    }
    public void etiquetasPrincipales(JLabel label, int x){
        label.setBounds(x, 5, 300, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuentePrincipal);
        add(label);
    }
    public void etiquetasResultantes(JLabel label, int x){
        label.setBounds(x, 35, 300, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuentePrincipal);
        add(label);
    }

    @Override
    public void run() {

        String tiempo;

        while(ejecutar){

            segundos++;
            tiempo = "0"+horas;
            tiempo += ":";

            if(minutos < 10) {
                tiempo += "0"+minutos;
            }else{
                tiempo += minutos;
            }

            tiempo += ":";

            if(segundos < 10){
                tiempo += "0" + segundos;
            }else{
                tiempo += segundos;
            }

            if(segundos == 60) {
                minutos++;
                segundos = 0;
            } if (minutos == 60){
                minutos = 0;
                horas++;
            } if (horas == 1){
                tiempo = "01:00:00";
                detener();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contadorTiempo.setText(tiempo);
        }

    }

    public void detener() {
        ejecutar = false;
    }
}
