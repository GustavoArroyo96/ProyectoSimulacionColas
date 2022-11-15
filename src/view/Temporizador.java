package view;

public class Temporizador implements Runnable {

    volatile boolean ejecutar = true;
    private int horas = 0, minutos = 0, segundos = 0, contadorTiempoSeg = 0;
    String tiempo;
    VentanaSimulacion ventanaSimulacion;

    public Temporizador(VentanaSimulacion ventanaSimulacion){
        this.ventanaSimulacion = ventanaSimulacion;
    }

    @Override
    public void run() {
        while (ejecutar) {

            segundos++;
            temporizador();

            ventanaSimulacion.contadorTiempo.setText(tiempo);

            try {
                Thread.sleep(ventanaSimulacion.sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void detener() {
        ejecutar = false;
    }
    public void temporizador() {
        tiempo = "0" + horas;
        tiempo += ":";

        if (minutos < 10) {
            tiempo += "0" + minutos;
        } else {
            tiempo += minutos;
        }

        tiempo += ":";

        if (segundos < 10) {
            tiempo += "0" + segundos;
        } else {
            tiempo += segundos;
        }

        if (segundos == 60) {
            minutos++;
            segundos = 0;
        }
        if (minutos == 60) {
            minutos = 0;
            horas++;
        }
        if (horas == 1) {
            tiempo = "01:00:00";
            detener();
        }
    }
}
