package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class VentanaSimulacion extends JFrame{

    private ImageIcon logoCajero = new ImageIcon(Objects.requireNonNull(getClass().getResource("cajero.png")));
    public Font fuentePrincipal = new Font("Verdana", Font.BOLD, 20);
    public Font fuenteEtiquetas = new Font("Verdana", Font.PLAIN, 15);
    public Font fuenteBotones = new Font("Verdana", 0, 20);
    public JLabel caja1, caja2, caja3, caja4, caja5, caja6, caja7, cj1, cj2, cj3, cj4, cj5, cj6, cj7;
    public JLabel lblMonitor, lblClientes, lblTiempo, lblPartida, lblCajaRapida, lblCajaNormal;
    public JButton btnTerminar;
    public JLabel contadorTiempo = new JLabel();
    public ArrayList<ClientesRapida> clientesRapida;
    ArrayList<ClientesNormal> clientesNormal;
    int contadorArrayClientesRapida = 0, cantidadClientesRapida = 0;
    int contadorArrayClientesNormal = 0, cantidadClientesNormal = 0;
    int sleep = 500;
    boolean caja1Disponible = true, caja2Disponible = true, caja3Disponible = true, caja4Disponible = true,
        caja5Disponible = true, caja6Disponible = true, caja7Disponible = true;

    public VentanaSimulacion(int cantidadClientesNormal, int cantidadClientesRapida) {
        setTitle("Simulación Supermercado");
        setSize(1000, 700);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        // *********** Añadimos los clientes totales a nuestro arreglo. *****************
        this.cantidadClientesRapida = cantidadClientesRapida;
        clientesRapida = new ArrayList<>();
        for (int i = 0; i < cantidadClientesRapida; i++) {
            clientesRapida.add(new ClientesRapida());
        }

        this.cantidadClientesNormal = cantidadClientesNormal;
        clientesNormal = new ArrayList<>();
        for (int i = 0; i < cantidadClientesNormal; i++) {
            clientesNormal.add(new ClientesNormal());
        }
        // ******************************************************************************

        // ************* Agregamos los clientes a nuestro JFrame *********************
        for (int i = 0; i < cantidadClientesNormal; i++) {
            clientesNormal.get(i).setBounds(550, 530, 40, 40);
            add(clientesNormal.get(i));
        }

        for (int i = 0; i < cantidadClientesRapida; i++) {
            clientesRapida.get(i).setBounds(450, 530, 40, 40);
            add(clientesRapida.get(i));
        }

        parteDeInterfaz();
        // ******************************************************************************
        Thread t = new Thread(new Temporizador(this));
        t.start();

        Thread hiloCajasRapidas = new Thread(new HiloCajasRapidas(this));
        hiloCajasRapidas.start();

        Thread hiloCajasNormales = new Thread(new HiloCajasNormales(this));
        hiloCajasNormales.start();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void parteDeInterfaz() {

        caja1 = new JLabel("Caja 1");
        caja2 = new JLabel("Caja 2");
        caja3 = new JLabel("Caja 3");
        caja4 = new JLabel("Caja 4");
        caja5 = new JLabel("Caja 5");
        caja6 = new JLabel("Caja 6");
        caja7 = new JLabel("Caja 7");
        cj1 = new JLabel("1");
        cj2 = new JLabel("2");
        cj3 = new JLabel("3");
        cj4 = new JLabel("4");
        cj5 = new JLabel("5");
        cj6 = new JLabel("6");
        cj7 = new JLabel("7");
        lblMonitor = new JLabel("Cajas Disponibles");
        lblClientes = new JLabel("Clientes atendidos");
        lblTiempo = new JLabel("Tiempo");
        lblPartida = new JLabel("Punto de Partida: ");
        lblCajaRapida = new JLabel("0 / " + cantidadClientesRapida);
        lblCajaNormal = new JLabel("0 / " + cantidadClientesNormal);

        // Etiquetas funcionales
        etiquetasPrincipales(lblTiempo, 8);
        etiquetasPrincipales(lblMonitor, 300);
        etiquetasPrincipales(lblClientes, 670);
        etiquetasResultantes(contadorTiempo, 8);
        etiquetasCajaDisponible(cj1, 350);
        etiquetasCajaDisponible(cj2, 380);
        etiquetasCajaDisponible(cj3, 410);
        etiquetasCajaDisponible(cj4, 440);
        etiquetasCajaDisponible(cj5, 470);
        etiquetasCajaDisponible(cj6, 500);
        etiquetasCajaDisponible(cj7, 530);

        cj1.setVisible(true);
        cj2.setVisible(true);
        cj3.setVisible(true);
        cj4.setVisible(true);
        cj5.setVisible(true);
        cj6.setVisible(true);
        cj7.setVisible(true);

        //Cajas rápidas
        iconosCajeros(30, 150, logoCajero);
        iconosCajeros(150, 150, logoCajero);

        // Se crean los cajeros
        iconosCajeros(300, 150, logoCajero);
        iconosCajeros(420, 150, logoCajero);
        iconosCajeros(540, 150, logoCajero);
        iconosCajeros(660, 150, logoCajero);
        iconosCajeros(780, 150, logoCajero);

        // Etiquetas cajeros
        etiquetasCajeros(caja1, 8);
        etiquetasCajeros(caja2, 128);
        etiquetasCajeros(caja3, 278);
        etiquetasCajeros(caja4, 398);
        etiquetasCajeros(caja5, 518);
        etiquetasCajeros(caja6, 638);
        etiquetasCajeros(caja7, 758);

        lblCajaRapida.setBounds(670, 30, 300, 25);
        lblCajaRapida.setHorizontalAlignment(SwingConstants.CENTER);
        lblCajaRapida.setFont(fuentePrincipal);
        add(lblCajaRapida);

        lblCajaNormal.setBounds(670, 60, 300, 25);
        lblCajaNormal.setHorizontalAlignment(SwingConstants.CENTER);
        lblCajaNormal.setFont(fuentePrincipal);
        add(lblCajaNormal);

        lblPartida.setBounds(180, 530, 300, 25);
        lblPartida.setHorizontalAlignment(SwingConstants.CENTER);
        lblPartida.setFont(fuentePrincipal);
        add(lblPartida);

        btnTerminar = new JButton("Terminar simulación");
        btnTerminar.setBounds(680, 600, 250, 50);
        btnTerminar.setBackground(Color.GRAY);
        btnTerminar.setFont(fuenteBotones);
        btnTerminar.setForeground(Color.WHITE);
        add(btnTerminar);
    }

    public void iconosCajeros(int x, int y, ImageIcon logoCajero) {
        JLabel label = new JLabel();
        label.setBounds(x, y, 70, 70);
        Icon icono = new ImageIcon(logoCajero.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        label.setBorder(new LineBorder(Color.CYAN));
        add(label);
    }

    public void etiquetasCajeros(JLabel label, int x) {
        label.setBounds(x, 220, 120, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuenteEtiquetas);
        add(label);
    }

    public void etiquetasPrincipales(JLabel label, int x) {
        label.setBounds(x, 5, 300, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuentePrincipal);
        add(label);
    }

    public void etiquetasCajaDisponible(JLabel label, int x){
        label.setBounds(x, 30, 25, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuentePrincipal);
        add(label);
    }

    public void etiquetasResultantes(JLabel label, int x) {
        label.setBounds(x, 35, 300, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuentePrincipal);
        add(label);
    }

}