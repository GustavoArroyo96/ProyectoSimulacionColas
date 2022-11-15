package view;

import java.util.ArrayList;
import java.util.Random;

public class RecorridoCajaRapida2 implements Runnable {

    private final int contadorArrayClientesRapida;
    VentanaSimulacion ventanaSimulacion;
    ArrayList<ClientesRapida> clientesRapida = new ArrayList<>();
    int x = 450;
    int y = 530;
    int tRecorridoA = 3, tRecorridoB = 10, tRecorridoC = 16, contadorTiempoSeg = 0;

    public RecorridoCajaRapida2(VentanaSimulacion ventanaSimulacion, ArrayList<ClientesRapida> clientesRapida, int contadorArrayClientesRapida) {
        this.ventanaSimulacion = ventanaSimulacion;
        this.clientesRapida = clientesRapida;
        this.contadorArrayClientesRapida = contadorArrayClientesRapida;
    }


    @Override
    public void run() {
        clientesRapida.get(contadorArrayClientesRapida).setBounds(x, y, 40, 40);
        int tiempoDeAtencion = generarTiempoCajaRapida(), tiempoDeCobro = 0;
        boolean concurrencia = true;
        ventanaSimulacion.caja2Disponible = false;
        ventanaSimulacion.cj2.setVisible(false);

        while(concurrencia) {
            if (contadorTiempoSeg < tRecorridoA) {
                y = y - 33;
                clientesRapida.get(contadorArrayClientesRapida).setBounds(x, y, 40, 40);
                contadorTiempoSeg++;
            } else if (contadorTiempoSeg < tRecorridoB) {
                x = x - 33;
                clientesRapida.get(contadorArrayClientesRapida).setBounds(x, y, 40, 40);
                contadorTiempoSeg++;
            } else if (contadorTiempoSeg < tRecorridoC) {
                y = y - 45;
                clientesRapida.get(contadorArrayClientesRapida).setBounds(x, y, 40, 40);
                contadorTiempoSeg++;
            } else {
                do{
                    tiempoDeCobro++;
                    try {
                        ventanaSimulacion.revalidate();
                        ventanaSimulacion.repaint();
                        Thread.sleep(ventanaSimulacion.sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }while (tiempoDeCobro < tiempoDeAtencion);
                ventanaSimulacion.remove(clientesRapida.get(contadorArrayClientesRapida));
                ventanaSimulacion.lblCajaRapida.setText(ventanaSimulacion.contadorArrayClientesRapida + " / " + ventanaSimulacion.cantidadClientesRapida);
                ventanaSimulacion.caja2Disponible = true;
                ventanaSimulacion.cj2.setVisible(true);
                concurrencia = false ;
            }
            try {
                ventanaSimulacion.revalidate();
                ventanaSimulacion.repaint();
                Thread.sleep(ventanaSimulacion.sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int generarTiempoCajaRapida() {

        final int MIN = 1, MAX = 4, MINIMO_CAJA = 5; // limites de generacion rapidos.

        // semillas base de los tiempos de cajas rapidas (en segundos)
        int seed1 = 80, seed2 = 45, seed3 = 60, seed4 = 30; // SEED 1 ... SEED 4
        int time = 0; // almacena el tiempo generado.

        Random selector = new Random(); // seleccionara una de las semillas declaradas arriba.
        int seleccion = MIN + selector.nextInt((MAX - MIN) + 1); // selecciona una de las 4 seeds.

        switch (seleccion) {
            case 1 -> time = MINIMO_CAJA + selector.nextInt(seed1 + 1); // incluye el 80.
            case 2 -> time = MINIMO_CAJA + selector.nextInt(seed2 + 1); // incluye el 45.
            case 3 -> time = MINIMO_CAJA + selector.nextInt(seed3 + 1); // incluye el 60.
            case 4 -> time = MINIMO_CAJA + selector.nextInt(seed4 + 1); // incluye el 45.
        }

        return time;
    }
}
