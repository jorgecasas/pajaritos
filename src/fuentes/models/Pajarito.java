package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */
import java.util.*;
import java.awt.*;
import java.lang.*;
import java.lang.Math;

/**
 * CLASE ABSTRACTA A PARTIR DE LA CUAL SE EXTIENDEN LOS DIFERENTES TIPOS DE PAJARITOS.
 */
public abstract class Pajarito{
	
	/**------------------------------------------------------------------------
	*	DECLARACION DE VARIABLES
	***-----------------------------------------------------------------------*/
    public static final int   ESTATICO=0;
    public static final int   RECTO=1;
    public static final int   CORTO=2;
    public static final int   LARGO=3;
    public static final int xPantalla=640,yPantalla=480;
    public static final int numeroTipoVuelos=4;


	public int tipoPajarito;
	private String imagenPajarito,imagenPajaritoAsado,imagenActual;
	public Point origenPajarito, posicionPajarito;
	public boolean pajaritoVivo,pajaritoMuerto;
	public int valorPajarito;
	public boolean vueloDerecha;
	public int tipoVueloPajarito;
	public int etapaVuelo=0,tiempoVuelo=0;
	public int factorVuelo; 
	public int sizePajaroX,sizePajaroY; 


	

	/**------------------------------------------------------------------------
	*	Constructor de la clase Pajarito
	***-----------------------------------------------------------------------*/
	public Pajarito(int _tipoPajarito){
		
		tipoPajarito=_tipoPajarito;
		pajaritoVivo=true;// pajarito 
		pajaritoMuerto=false;// si pajarito muerto es que no esta en la pantalla
		factorVuelo=(new Random().nextInt(2)+1);
   		if (new Random().nextInt(2)==1){//el 2 se excluye
					vueloDerecha=true;}
		else {vueloDerecha=false;
		}// end if
		origenPajarito = new Point(new Random().nextInt(xPantalla),new Random().nextInt(yPantalla));
	}// end constructor	
	
	/**------------------------------------------------------------------------
	*	metodos para todos los pajaritos
	***-----------------------------------------------------------------------*/
	public String getImagenPajarito() {
       	 return imagenPajarito;
   	}
	//------------------------------------------------------------------------
	public String getImagenActualPajarito() {
       	 return imagenActual;
   	}

	//------------------------------------------------------------------------
	public String getImagenPajaritoAsado() {
       	 return imagenPajaritoAsado;
   	}
	//------------------------------------------------------------------------
	public void setImagenPajaritoAsado() {
       	 imagenActual=imagenPajaritoAsado;
   	}
	//------------------------------------------------------------------------
	public void setImagen(String _tipoPajarito,boolean _vueloDerecha) {
       	 // sea cual sea el pajaro, le ponemos sus imagenes (segun parametros)
		imagenPajaritoAsado="pajaroasado"+_tipoPajarito+".gif";
		if (_vueloDerecha==true){
			imagenPajarito="pajaro"+_tipoPajarito+"d.gif";// nombre de la imagen del pajaro
		}else{imagenPajarito="pajaro"+_tipoPajarito+"i.gif";// nombre de la imagen del pajaro
		}//end if
		imagenActual=imagenPajarito;
   	}
	//------------------------------------------------------------------------
	public int getValorPajarito() {
       	 return valorPajarito;
   	}

	//------------------------------------------------------------------------
	public void setVuelo() {
		switch(new Random().nextInt(3)){// tipo de vuelo... 3 se excluye
			case 0: tipoVueloPajarito=CORTO; //parabola
				origenPajarito.y=yPantalla; // fondo de la pantalla
				if (vueloDerecha){origenPajarito.x=origenPajarito.x-50;
				}else{origenPajarito.x=origenPajarito.x+50;}
				break;
			case 1: tipoVueloPajarito=LARGO;
				origenPajarito.y=yPantalla; // fondo de la pantalla
				if (vueloDerecha){origenPajarito.x=origenPajarito.x-50;
				}else{origenPajarito.x=origenPajarito.x+50;}
				break;
			default: tipoVueloPajarito=RECTO;
				if (vueloDerecha){origenPajarito.x=-10;}
				else{origenPajarito.x=xPantalla+10;
				}// end if
				break;
		  }// end case;
   	}
	//------------------------------------------------------------------------
	public Point getPosicionPajarito(){
		return posicionPajarito;
	}
	//------------------------------------------------------------------------
	public int getTipoPajarito(){
		return tipoPajarito;
	}

	//------------------------------------------------------------------------
	public void destroyPajaro(){
		pajaritoMuerto=true;
	}
	//------------------------------------------------------------------------
	public void setPajaritoMuriendo(){
		pajaritoVivo=false;
	}

	//------------------------------------------------------------------------
	public boolean getPajaritoMuerto(){
		return pajaritoMuerto;
	}
	//------------------------------------------------------------------------
	public int getSizeX(){	// devuelve tamaño del pajarito X
		return sizePajaroX;
	}
	//------------------------------------------------------------------------
	public int getSizeY(){ // devuelve el tamaño del pajarito Y
		return sizePajaroY;
	}
//------------------------------------------------------------------------
	public void setSizePajaro(int x, int y){ // asigna el tamaño del pajarito X-Y
		sizePajaroX=x;
		sizePajaroY=y;
	}
//-------------------------------------------------------------------------------
    /**
     * @return vueloDerecha;
     */
    public boolean getVueloPajaro() {
        return vueloDerecha;
    }

//-------------------------------------------------------------------------------
    /**
     * @return pajaritoVivo;
     */
    public boolean getPajaritoVivo() {
        return pajaritoVivo;
    }

	//------------------------------------------------------------------------
	public abstract Point ActualizaPosicionPajarito();// esta la definimos en cada pajarito
}
