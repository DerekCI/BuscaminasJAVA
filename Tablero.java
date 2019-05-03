import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
    // Constantes
    private static final int TAM_CELDAS  = 15;
    private static final int NUM_IMAGENES = 13;

    private final int IMAGEN_MINE = 9;
    private final int IMAGEN_COVER = 10;
    private final int IMAGEN_BANDERA = 11;
    private final int IMAGEN_NO_BANDERA = 12;

    private JLabel barraStatus;

    private int numMinas = 40;
    private int minasRestantes;

    private int filas = 16, columnas = 16;

    //private Cell[][] cells;
    //private Image[] img;

    private boolean enCurso;
}