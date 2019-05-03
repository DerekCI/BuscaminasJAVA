import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AdaptadorMinas extends MouseAdapter {
    public void clickeo(MouseEvent e) {

        private static final int TAM_CELDAS  = 15;
        private int filas = 16, columnas = 16;
        private int auxMinas;
        private Celdas[][] celdas;

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
                doRepaint = true;

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
                statusBar.setText(Integer.toString(remainderMines));
            } else {
                if (pressedCell.isMarked() || !pressedCell.isCovered()) {
                    return;
                }

                rePaint = true;

                celdas.descubierto();
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