package view;

import java.util.ArrayList;
import java.util.Random;

public class RecorridoCajaNormal5 implements Runnable{

    private final int contadorArrayClientesNormal;
    VentanaSimulacion ventanaSimulacion;
    ArrayList<ClientesNormal> clientesNormal = new ArrayList<>();
    int x = 550;
    int y = 530;
    int tRecorridoB = 10, tRecorridoC = 16, contadorTiempoSeg = 0;

    public RecorridoCajaNormal5(VentanaSimulacion ventanaSimulacion, ArrayList<ClientesNormal> clientesNormal, int contadorArrayClientesNormal){
        this.ventanaSimulacion = ventanaSimulacion;
        this.clientesNormal = clientesNormal;
        this.contadorArrayClientesNormal = contadorArrayClientesNormal;
    }
    @Override
    public void run() {

        clientesNormal.get(contadorArrayClientesNormal).setBounds(x, y, 40, 40);
        int tiempoDeAtencion = generarTiempoCajaNormal(), tiempoDeCobro = 0;
        boolean concurrencia = true;
        ventanaSimulacion.caja7Disponible = false;
        ventanaSimulacion.cj7.setVisible(false);

        while(concurrencia) {
            if (contadorTiempoSeg < tRecorridoB) {
                x = x + 30;
                clientesNormal.get(contadorArrayClientesNormal).setBounds(x, y, 40, 40);
                contadorTiempoSeg++;
            } else if (contadorTiempoSeg < tRecorridoC) {
                y = y - 63;
                clientesNormal.get(contadorArrayClientesNormal).setBounds(x, y, 40, 40);
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
                ventanaSimulacion.remove(clientesNormal.get(contadorArrayClientesNormal));
                ventanaSimulacion.lblCajaNormal.setText(ventanaSimulacion.contadorArrayClientesNormal + " / " + ventanaSimulacion.cantidadClientesNormal);
                ventanaSimulacion.caja7Disponible = true;
                ventanaSimulacion.cj7.setVisible(true);
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

    private int generarTiempoCajaNormal() {
        final int MIN = 1, MAX = 4, MINIMO_CAJA = 80; // limites de generacion convencionales.

        // semillas base de los tiempos de cajas convencionales (en segundos)
        int seed1 = 180, seed2 = 185, seed3 = 230, seed4 = 210; // SEED 1 ... SEED 4
        int time = 0; // alamacena el tiempo generado.

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


