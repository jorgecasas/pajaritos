package gui;

import models.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import aplication.*;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
// CREA EL JFRAME EN EL QUE modificamos un JUGADOR

public class ModificarPlayer extends JFrame implements ActionListener {
  
  JLabel labelNombre = new JLabel();
  JLabel labelEmail = new JLabel();
  JLabel labelMovil = new JLabel();
  JLabel labelRecord = new JLabel();
  JLabel labelEligePlayer = new JLabel();
  JLabel labelImagen=new JLabel();

  JTextField textEmail = new JTextField();
  JTextField textMovil = new JTextField();
  JTextField textRecord = new JTextField();

  JButton botonAceptar = new JButton();
  JButton botonCancelar = new JButton();
   
	JComboBox comboBox;
	String players[];
	Player playerAux;
	public String playerSeleccionado;
	public ControlPlayers controlJugadores;

//----------------------------------------------------------------------------------
  public ModificarPlayer() {
	
    try {
    		jbInit();
    }catch(Exception e) {e.printStackTrace();
    }//end try
  }//end constructor

//----------------------------------------------------------------------------------
  private void jbInit() throws Exception  {
    
	//propiedades del frame
    this.getContentPane().setLayout(null);
    this.setTitle("PAJARITOS - Modificar jugador");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(415,250);
    this.setIconImage(loadImage("iconojuego.gif"));
   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);

	// inicializamos el control de jugadores
	ControlPlayers controlJugadores=new ControlPlayers();


	//propiedades de los label, campos de texto ,botones y el combobox
	labelEligePlayer.setText("Elige el jugador a modificar");
	labelEligePlayer.setBounds(new Rectangle(30, 30, 200, 20));
	
		//combobox
		String players[]= new String[controlJugadores.getNumeroPlayers()];
		players=controlJugadores.getAllPlayers();
		playerSeleccionado=players[0];
		playerAux=null;
		playerAux=controlJugadores.getPlayer(players[0]);
       
	  JComboBox comboBox = new JComboBox(players);
        comboBox.setEditable(false);
        comboBox.setBounds(new Rectangle(100, 70, 100, 20));
        comboBox.addActionListener(this);


    labelNombre.setText("Nombre");
    labelNombre.setBounds(new Rectangle(30, 70, 60, 20));

    labelEmail.setText("Email");
    labelEmail.setBounds(new Rectangle(30, 100, 60, 20));

    labelMovil.setToolTipText("");
    labelMovil.setText("Móvil");
    labelMovil.setBounds(new Rectangle(30, 130, 60, 20));

    labelImagen.setIcon(new ImageIcon("pajarosmall5.png"));
    labelImagen.setBounds(new Rectangle(250,43,152,180));

    textEmail.setBounds(new Rectangle(100, 100, 140, 20));
    textMovil.setBounds(new Rectangle(100, 130, 140, 20));

	labelRecord.setText("Récord");
	labelRecord.setBounds(new Rectangle(30, 160, 60, 20));

	textRecord.setBounds(new Rectangle(100, 160, 140, 20));
	textRecord.setEditable(false);

		//inicializo texto de los textFields
		playerAux=new ControlPlayers().getPlayer(playerSeleccionado);
		textEmail.setText(playerAux.getEmail());
		textMovil.setText(playerAux.getMovil());
		textRecord.setText(Integer.toString(playerAux.getRecord()));

    botonAceptar.setBounds(new Rectangle(50, 190, 100, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada

    botonCancelar.setBounds(new Rectangle(155, 190, 100, 25));
    botonCancelar.setText("Cancelar");
    botonCancelar.setActionCommand("cancelar");	// el mensaje que envia cuando se pulsa
    botonCancelar.addActionListener(this);	// activamos que escuchen la accion realizada


	//añadimos al panel
    this.getContentPane().add(labelNombre, null);
    this.getContentPane().add(labelEmail, null);
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(labelRecord, null);
    this.getContentPane().add(labelEligePlayer, null);
    this.getContentPane().add(labelMovil, null);
    this.getContentPane().add(textEmail, null);
    this.getContentPane().add(textMovil, null);
    this.getContentPane().add(textRecord, null);
    this.getContentPane().add(comboBox, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(botonCancelar, null);

  }
//-------------------------------------------------------------------------
  
  public void actionPerformed(ActionEvent ae) {
	JFrame menuInicio;
         if (ae.getActionCommand().equals("cancelar")) {// BOTON CANCELAR
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
	  } else {
		if(ae.getActionCommand().equals("aceptar")) {// BOTON ACEPTAR

			playerAux.setEmail(textEmail.getText());
			playerAux.setMovil(textMovil.getText());

	      	try { new ControlPlayers().modificaPlayer(playerAux);
		      } catch(Exception k) {k.printStackTrace();
		      }//end try
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
					
	    	}else{//es el combobox
			JComboBox cb = (JComboBox)ae.getSource();
      		playerSeleccionado = (String)cb.getSelectedItem();
			playerAux=new ControlPlayers().getPlayer(playerSeleccionado);
			textEmail.setText(playerAux.getEmail());
			textMovil.setText(playerAux.getMovil());
			textRecord.setText(Integer.toString(playerAux.getRecord()));
		}//end if
	}//end if
  }//end control pulsaciones raton
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