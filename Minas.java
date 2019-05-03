import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Minas extends JFrame {
    private final int ANCHURA = 250;
    private final int ALTURA = 300;

    private JLabel barraStatus;

    public Minas() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHURA, ALTURA);
        setLocationRelativeTo(null);
        setTitle("Buscaminas");

        barraStatus = new JLabel("");
        add(barraStatus, BorderLayout.SOUTH);
        add(new Board(barraStatus));

        setResizable(false);
        setVisible(true);
    }
}