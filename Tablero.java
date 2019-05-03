import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tablero extends JPanel {
    // Constantes
    private static final int TAM_CELDAS  = 15;
    private static final int NUM_IMAGENES = 13;

    private final int IMAGEN_MINE = 9;
    private final int IMAGEN_COVER = 10;
    private final int IMAGEN_BANDERA = 11;
    private final int IMAGEN_NO_BANDERA = 12;

    private JLabel barraStatus;

    private int numMinas = 40;
    private int auxMinas;

    private int filas = 16, columnas = 16;

    private Celdas[][] celdas;
    //private Image[] img;

    private boolean enCurso;

    public Tablero(JLabel barraStatus) {
        this.barraStatus = barraStatus;
        this.img = new Image[NUM_IMAGENES];
        for (int i = 0; i < NUM_IMAGENES; i++) {
            String ruta = "img/j" + i + ".gif";
            img[i] = new ImageIcon(ruta).getImage();
        }
        this.setDoubleBuffered(true);
        this.addMouseListener(new MinesAdapter());
        this.juegoNuevo();
    }

    private void iniciarCeldas () {
        this.celdas = new Celdas[filas][columnas];

        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.[i][j] = new Celdas();
            }
        }
    }
    public void juegoNuevo (){
        Random random = new Random();
        this.enCurso = true;
        this.auxMinas = numMinas;

        this.iniciarCeldas();
        this.barraStatus.setText(Integer.toString(this.auxMinas));

        int aux = numMinas;
        while (aux >= 0) {
            int randX = random.nextInt(this.filas);
            int randY = random.nextInt(this.columnas);

            Celdas celda = this.celdass[randX][randY];
            if (!celda.isMine()) {
                celda.setMine(true);
                aux--;
            }
        }

        this.setConteoMinas();
    }
    private void setConteoMinas() {

        for (int i = 0; i < this.columnas; ++i) {
            for (int j = 0; j < this.filas; ++j) {
                Celdas celda = this.celdas[i][j];

                if (!celda.esMina()) {
                    int conteo = contarMinasAlrededor(i, j);
                    celda.setMinasAlrededor(conteo);
                }
            }
        }
    }
    private int contarMinasAlrededor(int x, int y) {
        int contador = 0;
        for (int i = -1; i <= 1; ++i) {
            int xIndex = x + i;
            if (xIndex < 0 || xIndex >= this.filas) {
                continue;
            }

            for (int j = -1; j <= 1; ++j) {
                int yIndex = y + j;
                if (yIndex < 0 || yIndex >= this.columnas) {
                    continue;
                }

                if (i == 0 && j == 0) {
                    continue;
                }

                if (this.celdas[xIndex][yIndex].esMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }   
}