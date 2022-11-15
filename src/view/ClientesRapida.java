package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class ClientesRapida extends JLabel {
    private ImageIcon logoCliente = new ImageIcon(Objects.requireNonNull(getClass().getResource("cliente.png")));
    Icon icono;

    public ClientesRapida(){
        JLabel label = new JLabel();
        label.setSize(40,40);
        icono  = new ImageIcon(logoCliente.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        label.setBorder(new LineBorder(Color.CYAN));
        add(label);
    }

}
