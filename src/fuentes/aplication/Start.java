package aplication;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */

import models.*;
import gui.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.border.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.Component.*;
import java.net.URL;
import java.awt.Toolkit;
import java.io.*;





public class Start extends JFrame implements ActionListener {
	
	// declaracion variables de juego
	int numMaxPlayers=16;
	public Start framePrincipal;
	JButton boton_1p, boton_vp, boton_salir, boton_crearPlayer,boton_asalto2players,boton_torneo;
	JLabel label_imagen,label_pajaritos,label_1player,label_variosPlayers;	
	JLabel label_numeroJugadores;
	JComboBox comboBox;
	String numeros[]= new String[numMaxPlayers-1];
	int numeroJugadores;

	MenuPrincipal menuPrincipal;	// le voy a meter el menu 
	
	NewPlayer crearPlayer;
	SelectPlayer seleccionPlayer;
	

	// *****************************************************************************	
	// CONSTRUCTOR DE LA CLASE, se llama igual que la clase esta, donde realizamos
	// las inicializaciones de los objetos que forman la clase
    public Start() {
        	super();
		this.setDefaultCloseOperation(3);//Para que al cerrar la ventana finalece el programa
		this.setResizable(false);
     		this.setTitle("PAJARITOS");
		this.setSize(640,480);
		this.setIconImage(loadImage("iconojuego.gif"));

		//ponemos el frame en mitad de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		Dimension frameSize = this.getSize();
   	    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height)/2); 
		this.setVisible(true);
		

//       JOptionPane.showMessageDialog(this,null, // en null iria el texto junto a foto
//	   			"PAJARITOS",
//				JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
//				new ImageIcon("pajaritos1.jpg"));	// imagen que sacamos
//

		this.getContentPane().setLayout(null);	// cambiamos flowlayout por defecto a borderlayout
		

		// creamos y añadimos los botones

        boton_1p = new JButton("Partida Rápida");	// creo objeto boton
        boton_1p.setActionCommand("1playerSimple");	// el mensaje que envia cuando se pulsa
        boton_1p.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_1p.setBounds(new Rectangle(330,130,300,25));
	  this.getContentPane().add(boton_1p);

        boton_vp = new JButton("Partidas Rápidas");	// creo objeto boton
        boton_vp.setActionCommand("variosPlayerRapidas");	// el mensaje que envia cuando se pulsa
        boton_vp.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_vp.setBounds(new Rectangle(330,310,300,25));
	  this.getContentPane().add(boton_vp);
       
	 boton_asalto2players = new JButton("Asaltos 2 jugadores");	// creo objeto boton
        boton_asalto2players.setActionCommand("asalto2players");	// el mensaje que envia cuando se pulsa
        boton_asalto2players.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_asalto2players.setBounds(new Rectangle(330,280,300,25));
	  this.getContentPane().add(boton_asalto2players);
	
	 boton_torneo = new JButton("Torneo de Asaltos");	// creo objeto boton
        boton_torneo.setActionCommand("torneo");	// el mensaje que envia cuando se pulsa
        boton_torneo.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_torneo.setBounds(new Rectangle(330,340,300,25));
	  this.getContentPane().add(boton_torneo);

	//otros botones opcionales
     
 	  boton_salir = new JButton("Salir");	// creo objeto boton
        boton_salir.setMnemonic('s');	// la letra que se activa con ALT+n
        boton_salir.setActionCommand("salir");	// el mensaje que envia cuando se pulsa
        boton_salir.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_salir.setBounds(new Rectangle(10,400,150,25));
	  this.getContentPane().add(boton_salir);

 	  boton_crearPlayer= new JButton("Crear Jugador");	// creo objeto boton
        boton_crearPlayer.setMnemonic('c');	// la letra que se activa con ALT+n
        boton_crearPlayer.setActionCommand("crearPlayer");	// el mensaje que envia cuando se pulsa
        boton_crearPlayer.addActionListener(this);	// activamos que escuchen la accion realizada
	  boton_crearPlayer.setBounds(new Rectangle(165,400,150,25));
	  this.getContentPane().add(boton_crearPlayer);


	 // creamos labels con y sin imagenes para ponerlas en el menu
	JLabel label_1player=new JLabel("1 Jugador");
	JLabel label_variosPlayers=new JLabel("Varios Jugadores -");
	label_1player.setBounds(new Rectangle(330,110,200,25));
	label_variosPlayers.setBounds(new Rectangle(330,250,125,25));
	this.getContentPane().add(label_1player);
	this.getContentPane().add(label_variosPlayers);



	JLabel label_imagen=new JLabel();
	label_imagen.setIcon(new ImageIcon("pajaro1.png"));
	label_imagen.setBounds(new Rectangle(5,105,320,300));
	this.getContentPane().add(label_imagen);

	
	JLabel label_pajaritos=new JLabel();
	label_pajaritos.setIcon(new ImageIcon("labelpajaritos.png"));
	label_pajaritos.setBounds(new Rectangle(0,0,640,100));
	this.getContentPane().add(label_pajaritos);

		// añado el combobox de eleccion de numero jugadores
	for (int i=2;i<=numMaxPlayers;i++){
		numeros[i-2]=Integer.toString(i);
	}//end for
	numeroJugadores=Integer.parseInt(numeros[0]);
	  JComboBox comboBox = new JComboBox(numeros);
        comboBox.setEditable(false);
        comboBox.setBounds(new Rectangle(540,250, 50, 20));
        comboBox.addActionListener(this);
	this.getContentPane().add(comboBox);
	JLabel label_numeroJugadores=new JLabel("Elige el número:");
	label_numeroJugadores.setBounds(new Rectangle(445,250,150,25));
	this.getContentPane().add(label_numeroJugadores);

		//creamos el menu
		MenuPrincipal menuPrincipal = new MenuPrincipal(this);

		//metemos sonido 
		Sonido musicaFondo=new Sonido("pajarito1.wav",0);
		musicaFondo.start();

      }// end constructor
//-----------------------------------------------------------------------------------------

	// METODOS PARA VER QUE HACEMOS CUANDO PULSAMOS EL RATON SOBRE UN OBJETO
	// COMO PUEDE SER UN BOTON O SIMILAR

	
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("1playerSimple")) {// BOTON 1 JUGADOR juego simple
			try{
				SelectPlayer seleccionPlayer=new SelectPlayer(1,"1P1");
				seleccionPlayer.setVisible(true);
			}catch(Exception e){};

			this.setVisible(false);// Con esto cerramos el JFrame...
			this.dispose();
	}else if (ae.getActionCommand().equals("crearPlayer")) {// BOTON OPCIONES
			this.setVisible(false);
			this.dispose();	
			NewPlayer crearPlayer=new NewPlayer();
	 }else if (ae.getActionCommand().equals("variosPlayerRapidas")) {// BOTON 2 JUGADORES JUEGO SIMPLE
			try{
				SelectPlayer seleccionPlayer=new SelectPlayer(numeroJugadores,"NP1");
				seleccionPlayer.setVisible(true);
			}catch(Exception e){};
			this.setVisible(false);
	}else if (ae.getActionCommand().equals("asalto2players")) {// BOTON 2 JUGADORES asalots
			try{
				SelectPlayer seleccionPlayer=new SelectPlayer(2,"2P2");
				seleccionPlayer.setVisible(true);
			}catch(Exception e){};
			this.setVisible(false);
	}else if (ae.getActionCommand().equals("torneo")) {// BOTON torneo
			if ((numeroJugadores==2)||(numeroJugadores==4)||(numeroJugadores==8)||(numeroJugadores==16)){
				try{	SelectPlayer seleccionPlayer=new SelectPlayer(numeroJugadores,"torneo");
					seleccionPlayer.setVisible(true);
				}catch(Exception e){};
				this.setVisible(false);
			}else{ JOptionPane.showMessageDialog(this,
					"El torneo es para 2,4,8 o 16 jugadores", // en null iria el texto junto a foto
	   				"PAJARITOS",
					JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
					new ImageIcon("pajarosmall6.png"));	// imagen que sacamos
			}//end if

	  }else if (ae.getActionCommand().equals("salir")) {
		// DIALOGO DE PREGUNTA SI/NO
		Object[] options = {"Sí, soy un cobarde", "No"};
		 int n = JOptionPane.showOptionDialog(this, "¿Realmente quieres salir y dejar que los pajaros dominen el mundo?",
			"¿No quieres matar más pajaritos?", JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE,
			new ImageIcon("pajaro1d.gif"), //usar icono por defecto
			options, // el vector con los titulos  de opciones de los botones 
			"No"); //Titulo del boton por defecto
		if (n == JOptionPane.YES_OPTION) { System.exit(0); } 

	}else {// es el combobox

			JComboBox cb = (JComboBox)ae.getSource();
      		numeroJugadores = Integer.parseInt((String) cb.getSelectedItem());
			
	  } //end if
    }//end metodo

	// *****************************************************************************
      // METODO MAIN QUE ES EL PRIMERO QUE SE EJECUTA AL EMPEZAR EL PROGRAMA

    public static void main(String[] args) {
      	//Creamos frame principal y luego sobre el un JPanel
     		JFrame framePrincipal = new Start();
 		framePrincipal.setVisible(true);
		framePrincipal.setDefaultCloseOperation(3);
		
    }// end metodo

//-----------------------------------------------------------------------------------------
    /**
     * Obtiene una imagen
     */
    public Image loadImage( String s ) {
        Image   im = null;
        URL     u;
        String  s2 = "/" + s;
 	 u = getClass().getResource(s2);
        if( u == null )  {
            u = getClass().getResource(s);
        }  // for jar files ?
        if( u != null ) {
            im = Toolkit.getDefaultToolkit().getImage(u);
        } else {
            im = Toolkit.getDefaultToolkit().getImage(s);
            if( im == null )  {
                im = Toolkit.getDefaultToolkit().getImage(s2);
            }
        }
 	 try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(im, 0);
           tracker.waitForID(0);
       }catch ( Exception e ) {System.out.println("Fallo carga imagen" +s);}

        return im;
    }//end metodo
//-------------------------------------------------------------------------

}
