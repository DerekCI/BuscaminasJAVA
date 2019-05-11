import javax.swing.JOptionPane;
import java.lang.String;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Buscaminas extends JFrame implements ActionListener {

    public JTextField caja;       
    public JTextField caja2;
    private JButton boton;          
    private JLabel texto;
    private JLabel texto2;
    private String nombre1;
    private String nombre2;

    
    

    public Buscaminas() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }

    private void configurarVentana() {
        this.setTitle("Buscaminas");                   // colocamos titulo a la ventana
        this.setSize(310, 310);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        texto = new JLabel();
        texto2 = new JLabel();
        caja = new JTextField("Ana Laura");
        caja2 = new JTextField("Derek");
        boton = new JButton();

        texto.setText("Jugador 1");  
        texto.setBounds(50, 50, 100, 25);

        texto2.setText("Jugador 2");
        texto2.setBounds(50, 100, 100, 25);   

        caja.setBounds(150, 50, 100, 25);   
        caja2.setBounds(150, 100, 100, 25);

        boton.setText("Jugar");   // colocamos un texto al boton
        boton.setBounds(50, 220, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        
        nombre1 = caja.getText();
    	nombre2 = caja2.getText();
        // adicionamos los componentes a la ventana
        this.add(texto);
        this.add(caja);
        this.add(texto2);
        this.add(caja2);
        this.add(boton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Minas(nombre1,nombre2); 
    }

    public static void main(String[] args) {
        
        Buscaminas B = new Buscaminas();      
        B.setVisible(true);             
    }
}