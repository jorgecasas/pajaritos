package models;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada
 */
//-------------------------------------------------------------------------------
import java.awt.*;

/** Clase que sirve para controlar los pajaritos del juego y todos sus parametros*/
public class ControlPajaritos {
	private Pajarito pajaro;    //Referencia al pajarito 
	private Point posicion;   
	private int valorPajarito;
//-------------------------------------------------------------------------------
    /**
     * Constructor de la clase ControlPajaritos necesaria para controlar los pajaritos
     * @param p	   Pajaro i-esimo que esta en la posicion pos
     */
    public ControlPajaritos(Pajarito p) {
        pajaro=p;
        posicion=pajaro.getPosicionPajarito();
	  valorPajarito=pajaro.getValorPajarito();
    }//constructor
//-------------------------------------------------------------------------------

    /**
     * @return posicion  X donde se encuentra el pajarito
     */
    public int getPosicionX()    {
        return posicion.x;
    }
//-------------------------------------------------------------------------------
    /**
     * @return posicion Y donde se encuentra el pajarito;
     */
    public int getPosicionY() {
        return posicion.y;
    }
//-------------------------------------------------------------------------------
    /**
     * @return como vuela el pajaro (vueloDerecha)
     */
    public boolean getVuelo() {
        if (pajaro.getVueloPajaro()==true) {return true;}
	  return false;
    }
//-------------------------------------------------------------------------------
    /**
     * @return si pajarito vivo o muerto
     */
    public boolean getPajaroAsado() {
	 if (pajaro.getPajaritoVivo()){return false;}
	 return true;
    }

//-------------------------------------------------------------------------------
    /**
     * @return nombre imagen actual del pajarito;
     */
    public String getImagenPajaro() {
        return pajaro.getImagenActualPajarito();
    }
//-------------------------------------------------------------------------------
    /**
     * @return tipoPajarito
     */
    public int getTipoPajarito() {
        return pajaro.getTipoPajarito();
    }

//-------------------------------------------------------------------------------

    /**
     * @set posicion  donde se encuentra el pajarito
     */
    public void setPosicion(Point pos)    {
        posicion=pos;
    }
//-------------------------------------------------------------------------------

    /**
     * @return puntos que vale el pajarito
     */
    public int getValorPajaro()    {
        	if (pajaro==null){return 0;}
		return valorPajarito;
    }


//-------------------------------------------------------------------------------
    /**
     * @return Pajaro 
     */
    public Pajarito getPajaro() { 
	 return pajaro;
    }
//-------------------------------------------------------------------------------
	 /**
	* @return boolean con pajaro muerto o no 
	 */
	public boolean getPajaritoMuerto(){
		return pajaro.getPajaritoMuerto();
	}
//------------------------------------------------------------------------
}//ControlPajaritos
