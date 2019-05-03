import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Minas extends JFrame {
    private final int ANCHURA = 500;
    private final int ALTURA = 600;

    private JLabel barraStatus;

    public Minas() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(ANCHURA, ALTURA);
        setLocationRelativeTo(null);
        setTitle("Buscaminas");

        barraStatus = new JLabel("");
        add(barraStatus, BorderLayout.SOUTH);
        add(new Tablero(barraStatus));

        setResizable(false);
        
    }
}