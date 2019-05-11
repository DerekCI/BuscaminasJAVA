import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Minas extends JFrame {
    private final int ANCHURA = 240;
    private final int ALTURA = 300;

    private JLabel barraStatus;
    public Minas(String jugador1,String jugador2) {

        
        setVisible(true);
        setSize(ANCHURA, ALTURA);
        setLocationRelativeTo(null);
        setTitle("Buscaminas");

        barraStatus = new JLabel("");
        add(barraStatus, BorderLayout.SOUTH);
        add(new Tablero(barraStatus,jugador1,jugador2));

        setResizable(false);
        
    }
    
}