package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/**
 * PAJARITO DE TIPO ESTATICO EN EL FONDO DE PANTALLA
*/
import java.util.*;
import java.awt.*;
import java.lang.*;

public final class Pajarito3 extends Pajarito  {

    /**------------------------------------------------------------------------
     * Constructor de PAJARITO3 : pajarito en plano fondo estatico
     *-----------------------------------------------------------------------*/
	public Pajarito3() {
      	super(3);	//el parametro que le pasamos es el tipo de pajarito
		tipoVueloPajarito=ESTATICO;	
		if ((origenPajarito.y<150)|(origenPajarito.y>500)){
			origenPajarito.y=((yPantalla/2)+(factorVuelo*25)-50);
		}//end if (posiciono el pajarito en su sitio estatico (que no salga en el cielo)
		valorPajarito=25;
		setImagen("3",vueloDerecha);
		setSizePajaro(15,15);	// ese es el tamaño X e Y de la imagen de pajaro3
		posicionPajarito=origenPajarito;
	}// end constructor

	//------------------------------------------------------------------------
	public Point ActualizaPosicionPajarito(){
		if (pajaritoVivo){
			tiempoVuelo=tiempoVuelo+1;
			if (tiempoVuelo==40){destroyPajaro();}
		}else{destroyPajaro();}//pajaro muerto
//end if
	return posicionPajarito;
	}//end metodo
	//------------------------------------------------------------------------
}// end clase