package view;

import java.util.Random;

public class HiloCajasRapidas implements Runnable{

    VentanaSimulacion ventanaSimulacion;

    public HiloCajasRapidas(VentanaSimulacion ventanaSimulacion){
        this.ventanaSimulacion = ventanaSimulacion;
    }

    @Override
    public void run() {

        while(ventanaSimulacion.contadorArrayClientesRapida < ventanaSimulacion.cantidadClientesRapida) {
            if (ventanaSimulacion.caja1Disponible) {
                int tiempoDeEspera = tiempoDeEsperaCajas();
                int completado = 0;

                while (completado < tiempoDeEspera){
                    completado++;
                    try {
                        ventanaSimulacion.revalidate();
                        ventanaSimulacion.repaint();
                        Thread.sleep(ventanaSimulacion.sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Thread clienteCajaRapida = new Thread(new RecorridoCajaRapida1(ventanaSimulacion, ventanaSimulacion.clientesRapida, ventanaSimulacion.contadorArrayClientesRapida));
                clienteCajaRapida.start();
                ventanaSimulacion.contadorArrayClientesRapida++;
            } else if (ventanaSimulacion.caja2Disponible) {
                int tiempoDeEspera = tiempoDeEsperaCajas();
                int completado = 0;

                while (completado < tiempoDeEspera){
                    completado++;
                    try {
                        ventanaSimulacion.revalidate();
                        ventanaSimulacion.repaint();
                        Thread.sleep(ventanaSimulacion.sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Thread clienteCajaRapida = new Thread(new RecorridoCajaRapida2(ventanaSimulacion, ventanaSimulacion.clientesRapida, ventanaSimulacion.contadorArrayClientesRapida));
                clienteCajaRapida.start();
                ventanaSimulacion.contadorArrayClientesRapida++;
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

    public int tiempoDeEsperaCajas() {
        final int MIN = 1, MAX = 7, MINIMO_CAJA = 5; // limites de generacion convencionales.

        // Semillas base de tiempos de espera.
        int seed1 = 20, seed2 = 0, seed3 = 0, seed4 = 15,
                seed5 = 40, seed6 = 10, seed7 = 24;
        int time = 0;

        Random selector = new Random(); // seleccionara una de las semillas declaradas arriba.
        int seleccion = MIN + selector.nextInt((MAX - MIN) + 1); // selecciona una de las 7 seeds.

        switch (seleccion) {
            case 1 -> time = MINIMO_CAJA + selector.nextInt(seed1 + 1); // incluye el 20.
            case 2 -> time = 0;
            case 3 -> time = 0;
            case 4 -> time = MINIMO_CAJA + selector.nextInt(seed4 + 1); // incluye el 15.
            case 5 -> time = MINIMO_CAJA + selector.nextInt(seed5 + 1); // incluye el 40.
            case 6 -> time = MINIMO_CAJA + selector.nextInt(seed6 + 1); // incluye el 10.
            case 7 -> time = MINIMO_CAJA + selector.nextInt(seed7 + 1); // incluye el 24.
        }

        return time;
    }
}
