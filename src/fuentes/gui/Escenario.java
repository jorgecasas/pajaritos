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




//-----------------------------------------------------------------------------------------
/** Clase que crea un JComponent que servira de campo de tiro*/
public class Escenario extends JComponent{
    //  AlphaComposite ac1,ac2;
	private static final int yPantalla=480,xPantalla=640;
      private int x,y;
	private String fondoEscenario,cercaEscenario;
	private ControlJuego juego;
	private ControlPajaritos vectorPajaritos[];
	private int maxNumPajaritos;
	private boolean vueloDerecha,pajaroAsado;
	private int puntosMarcador,numeroBalas;
	private Image imgFondoLejos,imgFondoCerca;	// imagenes a precargar
	private Image imgPajaro0,imgPajaroAsado0;
	private Image imgPajaro1d,imgPajaro1i,imgPajaroAsado1;
	private Image imgPajaro2d,imgPajaro2i,imgPajaroAsado2;
	private Image imgPajaro3;
	private Image imgBalas,imgCursor;



//-----------------------------------------------------------------------------------------
/** Constructor del escenario de juego*/
    public Escenario (ControlJuego jj,String esc1,String esc2) {
        super();
	  juego=jj;
	  fondoEscenario=esc1;	// nombres de las imagenes del escenario
	  cercaEscenario=esc2;
	  vectorPajaritos=juego.getVectorPajaritos();
	  maxNumPajaritos=juego.getNumMaxPajaritos();
	  puntosMarcador=juego.getMarcador();
	  numeroBalas=juego.getNumeroBalas();
	  cargarImagenes();

    }// end constructor

//-----------------------------------------------------------------------------------------
	/** Pinta los graficos del Jcomponent escenario llamando a update*/
    public void paintComponent(Graphics g) {
	update(g);
}// end metodo
//-----------------------------------------------------------------------------------------
	/** Actualiza los graficos del JComponent del escenario de juego*/
	public void update(Graphics g){
      	  super.paintComponent( g );
	        Graphics2D g2 = (Graphics2D) g;
      	  Graphics2D gbi=g2;
		  gbi.setFont(new Font("Verdana", Font.BOLD, 30));

     	// x e y son las posiciones donde iremos pintando los pajaritos cada vez
     	// pintamos por capas... primero el fondo
            gbi.drawImage(imgFondoLejos,0,0,this);
	
		// ahora pintamos los pajaros de tipo 3 (desde el fondo hacia delante
		for (int i=0;i<=maxNumPajaritos-1;i++){
			if ((vectorPajaritos[i]!=null)&&(vectorPajaritos[i].getTipoPajarito()==3)){
				x= vectorPajaritos[i].getPosicionX();
				y= vectorPajaritos[i].getPosicionY();
				gbi.drawImage(imgPajaro3,x,y,this);
			}//end if
		}//end for

		// ahora pintamos los pajaros de tipo 2 	
		for (int i=0;i<=maxNumPajaritos-1;i++){
			if ((vectorPajaritos[i]!=null)&&(vectorPajaritos[i].getTipoPajarito()==2)){
				x=vectorPajaritos[i].getPosicionX();
				y=vectorPajaritos[i].getPosicionY();
				vueloDerecha=vectorPajaritos[i].getVuelo();
				pajaroAsado=vectorPajaritos[i].getPajaroAsado();
				if ((vueloDerecha==true)&&(pajaroAsado==false)){
					gbi.drawImage(imgPajaro2d,x,y,this);
				}else if((vueloDerecha==false)&&(pajaroAsado==false)){
					gbi.drawImage(imgPajaro2i,x,y,this);
				}else{gbi.drawImage(imgPajaroAsado2,x,y,this);
				}// end if - tipo pajarito 2
			}//end if
		}//end for

		// ahora pintamos los pajaros de tipo 1 	
		for (int i=0;i<=maxNumPajaritos-1;i++){
			if ((vectorPajaritos[i]!=null)&&(vectorPajaritos[i].getTipoPajarito()==1)){
				x=vectorPajaritos[i].getPosicionX();
				y=vectorPajaritos[i].getPosicionY();
				vueloDerecha=vectorPajaritos[i].getVuelo();
				pajaroAsado=vectorPajaritos[i].getPajaroAsado();
				if ((vueloDerecha==true)&&(pajaroAsado==false)){
					gbi.drawImage(imgPajaro1d,x,y,this);
				}else if((vueloDerecha==false)&&(pajaroAsado==false)){
					gbi.drawImage(imgPajaro1i,x,y,this);
				}else{ gbi.drawImage(imgPajaroAsado1,x,y,this);
				}// end if - tipo pajarito 1
			}//end if
		}//end for

		// ahora pintamos el escenario cercano (la colina). Actualizar x e y
		gbi.drawImage(imgFondoCerca,0,330,this);
		
		// pintamos los pajaros de tipo 0
		for (int i=0;i<=maxNumPajaritos-1;i++){
			if ((vectorPajaritos[i]!=null)&&(vectorPajaritos[i].getTipoPajarito()==0)){
				x=vectorPajaritos[i].getPosicionX();
				y=vectorPajaritos[i].getPosicionY();
				pajaroAsado=vectorPajaritos[i].getPajaroAsado();
				if (pajaroAsado==false){gbi.drawImage(imgPajaro0,x,y,this);
				}else{gbi.drawImage(imgPajaroAsado0,x,y,this);
				}// end if - tipo pajarito 0
			}//end if
		}//end for

		// dibujamos el contador de balas
		numeroBalas=juego.getNumeroBalas();
		x=600;
		for (int i=0;i<=numeroBalas-1;i++){
			gbi.drawImage(imgBalas,x,380,this);
			x=x-25;
		}// end for

		// dibujamos el marcador
		gbi.setColor(Color.white);
		gbi.drawString((String) juego.getPlayer(),420,35);
		gbi.drawString((String) Integer.toString(juego.getMarcador()),535,35);

		// dibujamos el reloj
		gbi.drawString((String) Integer.toString(juego.getMinutos())+":" +(String) Integer.toString(juego.getSegundos()/10)+(String) Integer.toString(juego.getSegundos()%10),25,35);
	
      
    }// end metodo
//-----------------------------------------------------------------------------------------
    /**
     * Obtiene una imagen del lugar donde se encuentre
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
	/** Carga las imagenes con las que dibujamos el escenario de juego*/
	public void cargarImagenes(){// precarga las imagenes que usaremos luego
		imgFondoLejos =loadImage(fondoEscenario);
		imgFondoCerca =loadImage(cercaEscenario);
		imgPajaro0 =loadImage("pajaro0d.gif");
		imgPajaroAsado0 =loadImage("pajaroasado0.gif");
		imgPajaro1d =loadImage("pajaro1d.gif");
		imgPajaro1i =loadImage("pajaro1i.gif");
		imgPajaroAsado1 =loadImage("pajaroasado1.gif");
		imgPajaro2d =loadImage("pajaro2d.gif");
		imgPajaro2i =loadImage("pajaro2i.gif");
		imgPajaroAsado2 =loadImage("pajaroasado2.gif");
		imgPajaro3 =loadImage("pajaro3d.gif");
		imgBalas =loadImage("balas.gif");
		imgCursor=loadImage("cursor.gif");
 	
      	juego.setStart(); // empezamos a jugar

	}
}//clase
