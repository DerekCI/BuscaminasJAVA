import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Celdas {
    private boolean mina;
    private boolean bandera;
    private boolean cubierto;
    private boolean marcada;

    private int valor;

    public Cell() {
        this.cubierto = true;
        this.bandera  = false;
        this.mina  = false;
        this.valor = 0;
    }

    public int getValor() {
        return this.valor;
    }

    public void descubierto() {
        this.cubierto = false;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public boolean vacio() {
        return this.valor == 0;
    }

    public boolean esMina() {
        return this.mina;
    }

    public void setMina(boolean b) {
        this.mina = b;
    }

    public boolean esBandera() {
        return this.bandera;
    }

    public boolean estaCubierto() {
        return this.cubierto;
    }

    public boolean estaMarcada() {
        return this.marcada;
    }

    public boolean minaMarcada() {
        return this.cubierto && this.mina;
    }

    public void marcada() {
        this.marcada = true;
    }

    public void limpiarMarcada() {
        this.marcada = false;
    }

    public void setMinasAlrededor(int contador) {
        this.valor = contador;
    }

    public int getMinasAlrededor() {
        return this.valor;
    }
}