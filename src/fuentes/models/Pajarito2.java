package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/**
 * PAJARITO VOLADOR AL FONDO - TIPO 2
*/
import java.util.*;
import java.awt.*;
import java.lang.*;

public final class Pajarito2 extends Pajarito  {



    /**------------------------------------------------------------------------
     * Constructor de PAJARITO2
     *---------------------------------------------------------------------*/
	public Pajarito2() {
		super(2);	//el parametro que le pasamos es el tipo de pajarito
		setVuelo();
		valorPajarito=25;
		setImagen("2",vueloDerecha);
		setSizePajaro(20,20);// tamaño X Y de la imagen del pajaro
		posicionPajarito=origenPajarito;
	}// end constructor

  	//------------------------------------------------------------------------
	public Point ActualizaPosicionPajarito(){
		tiempoVuelo=tiempoVuelo+1;
		if (pajaritoVivo){// pajarito VIVO y volando
			switch(tipoVueloPajarito){
				case CORTO: // parabola corta
					if (vueloDerecha){posicionPajarito.x=posicionPajarito.x+1;
					}else{posicionPajarito.x=posicionPajarito.x-1;
					}//end if
					posicionPajarito.y=yPantalla-(int) Math.rint((yPantalla/factorVuelo)*(double)Math.sin(0.0135*tiempoVuelo));
					if (posicionPajarito.y>=yPantalla+20){destroyPajaro();}// destruir pajaro
					break;
				case LARGO: // parabola larga
					if (vueloDerecha){posicionPajarito.x=posicionPajarito.x+2;
					}else{posicionPajarito.x=posicionPajarito.x-2;
					}//end if
					posicionPajarito.y=yPantalla-(int) Math.rint((yPantalla/factorVuelo)*(double) Math.sin(0.00515*tiempoVuelo));
					if (posicionPajarito.y>=yPantalla+20){destroyPajaro();}// destruir pajaro
					break;
				default: // vuelo recto
					if (vueloDerecha){posicionPajarito.x=posicionPajarito.x+3;
						if (posicionPajarito.x>xPantalla+20){destroyPajaro();}
					}else{posicionPajarito.x=posicionPajarito.x-3;
						if (posicionPajarito.x<-10){destroyPajaro();}
					}//end if
					break;
			}//end switch
		}else{ // pajarito muerto, cae a tierra
			setImagenPajaritoAsado();
			posicionPajarito.y=posicionPajarito.y+5;
			if (posicionPajarito.y>yPantalla+5){destroyPajaro();}// destruir pajaro//end if
		}// end if
		return posicionPajarito;
	}//end metodo
	//------------------------------------------------------------------------


  


}
