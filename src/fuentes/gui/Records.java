package gui;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada
 */

//import java.awt.*;
//import java.awt.AlphaComposite;
import models.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JComponent;
import java.net.URL;
import java.lang.Integer;
import java.applet.Applet;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

/** JCOMPONENT que se mete en el frame que muestra los records de los jugadores*/
public class Records extends JComponent{
	private static final int yPantalla=480,xPantalla=640;
      private int x,y,numeroJugadores;
	private Image imgRecords,imgFondo;	// imagenes a precargar
	private ControlRecords records;
	private Player player[];
	private int puntuacion[];
	private int desp;

//-----------------------------------------------------------------------------------------
    public Records (Player jugadores[],int puntuaciones[],int _numeroJugadores) {
	// si numerojugadores=0,records absolutos
        super();
	   numeroJugadores=_numeroJugadores;
	  player=jugadores;
	 puntuacion=puntuaciones;
	  cargarImagenes();
	  repaint();


    }// end constructor

//-----------------------------------------------------------------------------------------
    public void paintComponent(Graphics g) {
	update(g);
}// end metodo
//-----------------------------------------------------------------------------------------
	public void update(Graphics g){
      	  super.paintComponent( g );
	        Graphics2D g2 = (Graphics2D) g;
      	  Graphics2D gbi=g2;

		  gbi.setColor(Color.black);
		  gbi.setFont(new Font("Verdana", Font.BOLD, 14));

	desp=15;
	
     	// x e y son las posiciones donde iremos pintando los pajaritos cada vez
     	// pintamos por capas... primero el fondo
	gbi.drawImage(imgRecords,0,0,this);
      gbi.drawImage(imgFondo,300,170,this);
	records = new ControlRecords();
	if (numeroJugadores==0){//son records absolutos,los leemos y dibujamos
		y=225;
		for (int i=1;i<=10;i++){
			gbi.drawString(records.getNameRecord(i),25,y);
			gbi.drawString(records.getRecord(i),250,y);
			y=y+20;
		}//end for
	}//end if
	
	y=220;
	if (numeroJugadores!=0){
		if (numeroJugadores>8){  gbi.setFont(new Font("Verdana", Font.BOLD, 10));desp=12;}
		for (int i=0;i<=numeroJugadores-1;i++){
			gbi.drawString((String) player[i].getName()+"  "+Integer.toString(puntuacion[i]),25,y);				
			y=y+desp;
		}//end for
		y=y+15;
		gbi.drawString("Récords absolutos de todos los tiempos",25,y);
		y=y+15;
		for (int i=1;i<=10;i++){
			gbi.drawString((String) records.getNameRecord(i),25,y);
			gbi.drawString(records.getRecord(i),250,y);
			y=y+desp;
		}//end for
	}//end if
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
//-----------------------------------------------------------------------------------------
	public void cargarImagenes(){// precarga las imagenes que usaremos luego
		imgFondo =loadImage("pajarolado.png");
		imgRecords=loadImage("records.png");
 	
	}
}//clase

