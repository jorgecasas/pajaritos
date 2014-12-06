package gui;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
import models.*;
import aplication.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.Component.*;
import java.net.URL;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.lang.Integer;
import java.awt.event.*;
import javax.swing.border.*;	
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;


//--------------------------------------------------------------------------------

public class FrameRecords extends JFrame implements ActionListener {
	    public static final int   NOABSOLUTOS=0;// despues de jugar 
	    public static final int   ABSOLUTOS=1;
	
		JButton botonAceptar;
		JPanel panel1,panel2;
		public int tipoRecord, numeroJugadores,puntuacion[];
		public Player player[];
		public Records componenteRecords;
		Start framePrincipal;
//--------------------------------------------------------------------------------
	/**
	* Constructor del frame. Si tipoRecord es ABSOLUTOS=1, numeroJugadores puede
	* tomar cualquier valor(0) y el player y puntuacion ser null
	*/
	public FrameRecords(Player _player[],int _puntuacion[],int _numeroJugadores){
		player=_player;
		puntuacion=_puntuacion;
		numeroJugadores=_numeroJugadores;
		try{jf_init();
		}catch(Exception e) {e.printStackTrace();}
	}//end constructor

    private void jf_init() throws Exception { 
 		
       //Opciones del frame
	  
        this.setDefaultCloseOperation(0);//Para que al cerrar no haga nada
        this.setResizable(false);
        this.setTitle("PAJARITOS");
	  this.setSize(640,480);
	  this.setIconImage(loadImage("iconojuego.gif"));
	 
	  this.getContentPane().setLayout(new BorderLayout());
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = this.getSize();
	    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	   

	JButton botonAceptar=new JButton("Aceptar");
        botonAceptar.setMnemonic('a');	// la letra que se activa con ALT+n
        botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
        botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada


	
	 componenteRecords=new Records(player,puntuacion,numeroJugadores);
	  componenteRecords.setBorder(BorderFactory.createEtchedBorder());
        componenteRecords.setDoubleBuffered(true);
        componenteRecords.setOpaque(true);
	  componenteRecords.setSize(640,470);
  	 

	this.getContentPane().add(componenteRecords,BorderLayout.CENTER);
	this.getContentPane().add(botonAceptar,BorderLayout.SOUTH);
	componenteRecords.setVisible(true);
	 this.setVisible(true);
	 	this.getContentPane().setVisible(true);
    	}//end metodo 


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


 public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("aceptar")) {// BOTON aceptar
		this.setVisible(false);// Con esto cerramos el JFrame...
		this.dispose();
		JFrame framePrincipal = new Start();
 		framePrincipal.setVisible(true);
		framePrincipal.setDefaultCloseOperation(3);
	}//end if
   }//end metodo

//-------------------------------------------------------------------------
}//end clase FrameRecords
