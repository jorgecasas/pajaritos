package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/**
 * PAJARITO DE TIPO ESTATICO EN EL PRIMER PLANO
*/
import java.util.*;
import java.awt.*;
import java.lang.*;


public final class Pajarito0 extends Pajarito  {

    /**------------------------------------------------------------------------
     * Constructor de PAJARITO0 : pajarito en primer plano estatico -- ES LA VACA
     *-----------------------------------------------------------------------*/
	public Pajarito0() {
      	super(0);	//el parametro que le pasamos es el tipo de pajarito
		tipoVueloPajarito=ESTATICO;	
		origenPajarito.y=yPantalla;	// yPantalla es el fondo del todo
		valorPajarito=25;
		setImagen("0",vueloDerecha);	// 0 porque es pajarito de tipo 0
		setSizePajaro(120,190);// tamaño X Y de la imagen del pajaro
		posicionPajarito=origenPajarito;
	}// end constructor

	//------------------------------------------------------------------------
	public Point ActualizaPosicionPajarito(){
		tiempoVuelo=tiempoVuelo+1;
		if (etapaVuelo==0){
			posicionPajarito.y=posicionPajarito.y-5;
			if (tiempoVuelo==40){etapaVuelo=1;}
		}else if (etapaVuelo==1){
			if (tiempoVuelo==70){etapaVuelo=2;}
		}else {posicionPajarito.y=posicionPajarito.y+5;
			if (posicionPajarito.y>yPantalla+30){destroyPajaro();}
		}//end if
		if (pajaritoVivo==false){setImagenPajaritoAsado();}
	return posicionPajarito;
	}//end metodo
	//------------------------------------------------------------------------
}
