package gui;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
import models.*;
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
import java.awt.Cursor;
import java.awt.Toolkit;

//--------------------------------------------------------------------------------

public class FrameJuego extends JFrame {

	Escenario jC_escenario;
	Point puntoDisparo;
	ControlJuego juego;
	Cursor objetivo;
	String imgEscenario1,imgEscenario2;
	String tipoJuego;
	public Player jugador[];
	int tiempoJuego;

		
//--------------------------------------------------------------------------------
    /**
     * Constructor del frame
     */
	public FrameJuego(String _tipoJuego,int _tiempo,String img1,String img2,Player _jugador[]){
		tipoJuego=_tipoJuego;
		imgEscenario1=img1;
		imgEscenario2=img2;
		jugador=_jugador;
		tiempoJuego=_tiempo;
		try{jf_init();
		}catch(Exception e) {e.printStackTrace();}
	}//end constructor

    private void jf_init() throws Exception { 
 		
       //Opciones del frame 
	   
        this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
        this.setResizable(false);
		
        this.setTitle("PAJARITOS");
	  	this.setSize(640,480);
	    this.setIconImage(loadImage("iconojuego.gif"));
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
	
	// Ahora creamos el thread de controlJuego... segun el tipo de juego que nos hayan pasado
	crearThreadJuego();
	
	 jC_escenario =new Escenario(juego,imgEscenario1,imgEscenario2);
	 jC_escenario.addMouseListener(new java.awt.event.MouseAdapter() {
                                         public void mousePressed(MouseEvent e) {
                                           jC_escenario_mousePressed(e);
                                        }
                                      }
                                   );

        jC_escenario.setDoubleBuffered(true);
        jC_escenario.setOpaque(true);
	  jC_escenario.setSize(640,480);

	this.getContentPane().add(jC_escenario,BorderLayout.CENTER);

	// creamos el objetivo del raton
	Toolkit kit = Toolkit.getDefaultToolkit(); 
	objetivo=(kit.createCustomCursor(loadImage("objetivo.gif"), new Point(14, 14), "Cursor"));
	this.getContentPane().setCursor(objetivo);

    	}//end metodo 
//------------------------------------------------------------------------------------
    //Captura de los distintos eventos de raton

   void jC_escenario_mousePressed(MouseEvent e) {// disparo
	if ((e.getModifiers() & InputEvent.BUTTON3_MASK)== InputEvent.BUTTON3_MASK){
	// en este caso hemos pulsado el boton izquierdo=> recarga balas
		juego.recargaBalas();
	}else{
		int x=e.getX();
		int y=e.getY();
		try{
			juego.disparo(x,y);
		}catch(Exception exc){}
		jC_escenario.repaint();
	 }//end if
		
    }// end metodo
//-----------------------------------------------------------------------------------------
	public void crearThreadJuego(){
		if (tipoJuego.equalsIgnoreCase("1P1")==true){//CONTROLJUEGO1P1
			try {	
				juego=new ControlJuego1P1(this,90,1,jugador,imgEscenario1,imgEscenario2);    
				juego.esperarCarga();
				juego.start();
			}catch (Exception e){};
		}//end if
		if (tipoJuego.equalsIgnoreCase("NP1")==true){//CONTROLJUEGO1P1
			try {	
				juego=new ControlJuegoNP1(this,90,1,jugador,imgEscenario1,imgEscenario2);    
				juego.start();
			}catch (Exception e){};
		}//end if

		if (tipoJuego.equalsIgnoreCase("2P2")==true){//CONTROLJUEGO2P2
			try {	
				juego=new ControlJuego2P2(this,60,1,jugador,imgEscenario1,imgEscenario2);    
				juego.start();// ASALTOS DE 1 MINUTO
			}catch (Exception e){};
		}//end if
		if (tipoJuego.equalsIgnoreCase("torneo")==true){//CONTROLJUEGO2P2
			try {	
				juego=new ControlJuegoNP2(this,tiempoJuego,1,jugador,imgEscenario1,imgEscenario2);    
				juego.start();// ASALTOS DE lo que elija el usuario
			}catch (Exception e){};
		}//end if

	

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

}//FrameJuego