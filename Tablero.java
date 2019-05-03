import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private Image[] img;

    private boolean enCurso;

    public Tablero(JLabel barraStatus) {
        this.barraStatus = barraStatus;
        this.img = new Image[NUM_IMAGENES];
        for (int i = 0; i < NUM_IMAGENES; i++) {
            String ruta = "img/j" + i + ".gif";
            img[i] = new ImageIcon(ruta).getImage();
        }
        this.setDoubleBuffered(true);
        this.addMouseListener(new AdaptadorMinas());
        this.juegoNuevo();
    }

    private void iniciarCeldas () {
        this.celdas = new Celdas[filas][columnas];

        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.celdas[i][j] = new Celdas();
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

            Celdas celda = this.celdas[randX][randY];
            if (!celda.esMina()) {
                celda.setMina(true);
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
    public void paint(Graphics g) {
        int celdasCubiertas = 0;

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                Celdas celda = this.celdas[i][j];
                int tipoImagen;
                int xPosition, yPosition;

                if (celda.estaCubierto()) {
                    celdasCubiertas++;
                }

                if (enCurso) {
                    if (celda.esMina() && !celda.estaCubierto()) {
                        enCurso = false;
                    }
                }

                tipoImagen = this.decideTipo(celda);

                xPosition = (j * TAM_CELDAS);
                yPosition = (i * TAM_CELDAS);

                g.drawImage(img[tipoImagen], xPosition, yPosition, this);
            }
        }
        if (celdasCubiertas == 0 && enCurso) {
            enCurso = false;
            barraStatus.setText("Juego Ganado");
        } else if (!enCurso) {
            barraStatus.setText("Juego Perdido");
        }
    }
    private int decideTipo(Celdas celda) {
        int tipoImagen = celda.getValor();

        if (!enCurso) {
            if (celda.estaCubierto() && celda.esMina()) {
                celda.descubierto();
                tipoImagen = IMAGEN_MINE;
            } else if (celda.esBandera()) {
                if (celda.esMina()) {
                    tipoImagen = IMAGEN_BANDERA;
                } else {
                    tipoImagen = IMAGEN_NO_BANDERA;
                }
            }
        } else {
            if (celda.esBandera()) {
                tipoImagen = IMAGEN_BANDERA;
            } else if (celda.estaCubierto()) {
                tipoImagen = IMAGEN_COVER;
            }
        }

        return tipoImagen;
    }
    public void encontrarCeldasVacias(int x, int y, int profundidad) {

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

                if (!(i == 0 || j == 0)) {
                    continue;
                }

                Celdas celda = this.celdas[xIndex][yIndex];
                if (revisarVacio(celda)) {
                    celda.descubierto();
                    celda.marcada();

                    celdasDescubiertasAlrededor(xIndex, yIndex);
                    encontrarCeldasVacias(xIndex, yIndex, profundidad + 1);
                }
            }
        }
        if(profundidad==0){
            this.limpiarCeldas();
        }
    }
    private void celdasDescubiertasAlrededor(int x, int y) {
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

                if (i == 0 || j == 0) {
                    continue;
                }

                Celdas celda = this.celdas[xIndex][yIndex];
                if (celda.estaCubierto() && !celda.vacio()) {
                    celda.descubierto();
                }
            }
        }
    }
    private boolean revisarVacio(Celdas celda) {
        if (!celda.esBandera()) {
            if (celda.vacio()) {
                return true;
            }
        }
        return false;
    }
    private void limpiarCeldas() {
        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.celdas[i][j].limpiarMarcada();
            }
        }
    }
    public class AdaptadorMinas extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int columnaClickeada = e.getX() / TAM_CELDAS;
            int filaClickeada = e.getY() / TAM_CELDAS;

            boolean rePaint = false;
            Celdas celdaClickeada;

            if (!enCurso) {
                juegoNuevo();
                repaint();
            }

            if ((columnaClickeada < 0 || columnaClickeada >= columnas)
                || (filaClickeada < 0 || filaClickeada >= filas)) {
                return;
            }

            celdaClickeada = celdas[filaClickeada][columnaClickeada];

            if (e.getButton() == MouseEvent.BUTTON3) {
                rePaint = true;

                if (!celdaClickeada.estaCubierto()) {
                    return;
                }

                String str;
                if (!celdaClickeada.esBandera()) {
                    celdaClickeada.setBandera(true);
                    auxMinas--;
                } else {
                    celdaClickeada.setBandera(false);
                    auxMinas++;
                }
                barraStatus.setText(Integer.toString(auxMinas));
            } else {
                if (celdaClickeada.esBandera() || !celdaClickeada.estaCubierto()) {
                    return;
                }

                rePaint = true;
                celdaClickeada.descubierto();
                if (celdaClickeada.esMina()) {
                    enCurso = false;
                } else if (celdaClickeada.vacio()) {
                    encontrarCeldasVacias(filaClickeada, columnaClickeada, 0);
                }
            }

            if (rePaint) {
                repaint();
            }
        }
    }
}
