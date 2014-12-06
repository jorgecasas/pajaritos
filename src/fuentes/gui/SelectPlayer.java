package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import java.io.*;
import aplication.*;
import models.*;
/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
// CREA EL JFRAME EN EL QUE PEDIMOS QUE ESCOJAN EL JUGADOR

public class SelectPlayer extends JFrame implements ActionListener {
  
	public FrameJuego frameJuego;
	public ControlPlayers controlJugadores;
	public Start menuInicio;

  JLabel labelTexto = new JLabel();
  JLabel labelImagen = new JLabel();
	public String playerSeleccionado;
  JButton botonAceptar = new JButton();
  JButton botonCancelar = new JButton();

    JComboBox comboBox;
	String players[];
	
	public Player jugador[];
	public int numeroJugadores,iteracion;
	public String tipoJuego;
	Player playerAux;


//----------------------------------------------------------------------------------
  public SelectPlayer (int numPlayers,String tipo)throws Exception {
	super();
	numeroJugadores=numPlayers;
	tipoJuego=tipo;
	jugador=new Player[numeroJugadores];
	ControlPlayers controlJugadores=new ControlPlayers();
	iteracion=0;


	//propiedades del frame
    this.getContentPane().setLayout(null);
    this.setTitle("PAJARITOS - Selección de jugador");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(430,190);
    this.setIconImage(loadImage("iconojuego.gif"));
	this.setVisible(true);
   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);


	//propiedades de los label, campos de texto y botones
    
    labelTexto.setBounds(new Rectangle(30, 30, 140, 20));
	labelTexto.setText("Elige el jugador 1");
    labelImagen.setIcon(new ImageIcon("pajarosmall4.png"));
    labelImagen.setBounds(new Rectangle(220,3,200,165));

	// combobox
	
		String players[]= new String[controlJugadores.getNumeroPlayers()];
		players=controlJugadores.getAllPlayers();
		playerSeleccionado=players[0];
		playerAux=controlJugadores.getPlayer(players[0]);

        JComboBox comboBox = new JComboBox(players);
        comboBox.setEditable(false);
        comboBox.setBounds(new Rectangle(60, 60, 140, 20));
        comboBox.addActionListener(this);

	// boton de aceptar
    botonAceptar.setBounds(new Rectangle(50, 120, 100, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada

    botonCancelar.setBounds(new Rectangle(155, 120, 100, 25));
    botonCancelar.setText("Cancelar");
    botonCancelar.setActionCommand("cancelar");	// el mensaje que envia cuando se pulsa
    botonCancelar.addActionListener(this);	// activamos que escuchen la accion realizada

	//añadimos al panel
    this.getContentPane().add(labelTexto, null);
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(botonCancelar, null);
    this.getContentPane().add(comboBox, null);


  }// end metodo

//-------------------------------------------------------------------------
  
  public void actionPerformed(ActionEvent ae) {
	
    	 if (ae.getActionCommand().equals("aceptar")) {// BOTON ACEPTAR
		if (iteracion<numeroJugadores){		
				labelTexto.setText("Elige el jugador "+(iteracion+2));
				jugador[iteracion]=playerAux;
				iteracion=iteracion+1;
		}//end if
		if (iteracion==numeroJugadores){
			this.setVisible(false);
			this.dispose();
			this.jugar();
		}//end if

    	}else{   if (ae.getActionCommand().equals("cancelar")) {// BOTON CANCELAR
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
		  }else{


			// es el combobox
			JComboBox cb = (JComboBox)ae.getSource();
      		playerSeleccionado = (String)cb.getSelectedItem();
			playerAux=new ControlPlayers().getPlayer(playerSeleccionado);
	     
		}//end if

	}//end if

  }//end control pulsaciones raton
//-------------------------------------------------------------------------
public void jugar() {//llama a CONTROLJUEGO que sea
	if (tipoJuego.equalsIgnoreCase("torneo")){
		Torneo torneo=new Torneo(jugador);
		torneo.setVisible(true);
	}else if (tipoJuego.equalsIgnoreCase("liga")){
	}else{
		FrameJuego frameJuego = new FrameJuego(tipoJuego,0,"escenario2a.gif","escenario2b.gif",jugador);
		frameJuego.setVisible(true);
	}//end if
}//end metodo
//-------------------------------------------------------------------------
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