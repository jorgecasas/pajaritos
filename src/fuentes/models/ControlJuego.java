package models;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.lang.Object.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.lang.Thread;
import javax.swing.JOptionPane;
import gui.*;


/**
 * Clase abstracta que controla los parámetros de juego
 */
public abstract class ControlJuego extends Thread {

	// Variables del juego
	public int   numMaxBalas=8;
	public int tiempoJuego;	// en segundos
	public int numMaxPajaritos=24;
	public int tipoPajarito,numeroRondas;	
	public int tipoVueloPajarito;
	public String imagenPajarito;
	public int puntosPajarito,numeroJugadores;
	public int numeroTiposPajaritos=4;
	public long tiempoBucle,vueltasBucle;	// para llevar control de juego... bucle de juego 
	public int nivelJuego;
	public ControlPajaritos vectorPajaritos[];	// vector q dice si un pajarito i existe o no
	public int numeroBalas;
	public String playerName[];
	public String playerNameActual,playerCampeon,playerSubcampeon;
	public Marcador marcador;
	public boolean puedoRecargarAMitad=false;
	public String imagenBalas="balas.gif";
	public FrameRecords frameRecords;
	public FrameAsaltos frameAsaltos;
	public JFrame frame;
	public boolean start; // si true puedo comenzar, si false, sigo esperando
	public Thread sonidoPuntual,sonidoPajarosFondo;
	public int iteraciones;
	public String imgEscenario1,imgEscenario2;
	public Player player[],ganadores[];
	public int puntuaciones[];
	public ControlRecords controlRecords;
	public ControlPlayers controlPlayer;
	public ControlJuego controlJuego;

	//para los asaltos 1 vs 1
	public int numeroAsalto,numeroPlayerGanador;
	public boolean noHayGanador,asaltoGanadoPlayer1,asaltoGanadoPlayer2;

//-------------------------------------------------------------------------------
	/**------------------------------------------------------------------------
	*	Constructor de la clase ControlJuego
	*------------------------------------------------------------------------*/
	public ControlJuego() {
		super();
		controlJuego=this;
	}// end constructor

//-------------------------------------------------------------------------------
	/**------------------------------------------------------------------------
	*	Metodo - creaPajarito - busca en el vector de pajaritos si hay 
	* pajaritos libres (algun componente del vector a false). En cuanto encuentra uno
	* (componente i) crea el pajarito[i] segun sea su tipo (clases pajarito1, pajarito2...)
	* pasandoles los diferentes parametros segun sea el pajaro uno u otro, y pone el 
	* componente i del vectorPajaritos a true.
	* 	
	***----------------------------------------------------------------------*/
	public void creaPajarito(){
		boolean puedoCrearPajaro=true;
		for (int i=0;i<=numMaxPajaritos-1;i++){// busco si puedo crear pajaro
			if ((vectorPajaritos[i]==null)&&(puedoCrearPajaro)){//puedo crear pajaro i-esimo
				int tipoPajarito = new Random().nextInt(2*(numeroTiposPajaritos-1)+1);
				switch (tipoPajarito){// CREACION DEL PAJARITO SEGUN EL TIPO
					case 0: // pajarito estatico q sale desde abajo
						vectorPajaritos[i]=new ControlPajaritos(new Pajarito0());
						break;// creo el pajaro0 y lo meto en el vector de control
					case 1:// al poner varias posibilidades para estos case, mas
					case 2:// probable que salga uno u otro tipo de pajaro
					case 3: // pajarito que vuela en plano cercano a usuario
						vectorPajaritos[i]=new ControlPajaritos(new Pajarito1());
						break;
					case 4:
					case 5: // pajarito que vuela en plano alejado a usuario
						vectorPajaritos[i]=new ControlPajaritos(new Pajarito2());
						break;
					default: // pajarito estatico de fondo (3)
						vectorPajaritos[i]=new ControlPajaritos(new Pajarito3());
					}// end switch
				puedoCrearPajaro=false;
			}// end if
			// si no hay un componente del vectorPajaritos sin pajarito o
			// estan ocupados todos no hacemos nada
		}// end for
	}// end metodo creaPajarito

//-------------------------------------------------------------------------------
	/** Metodo que actualiza las posiciones en pantalla de los pajaritos del vector de pajaritos*/
	public void actualizaPosicionesPajaritos(){
		for (int i=0;i<=numMaxPajaritos-1;i++){// busco si puedo mover pajaro
			if (vectorPajaritos[i]!=null){//puedo mover pajaro i-esimo
				if (vectorPajaritos[i].getPajaritoMuerto()==true){vectorPajaritos[i]=null;// mato pajaro
				}else{vectorPajaritos[i].setPosicion((vectorPajaritos[i].getPajaro()).ActualizaPosicionPajarito());
				}//end if
			}//end if
		}// end for
	}// end metodo

//-------------------------------------------------------------------------------
	/** Metodo que devuelve el numero de balas disponibles*/
	public int getNumeroBalas(){// devuelve el numero de balas que quedan en el cargador
		return numeroBalas;
	}// end metodo

//-------------------------------------------------------------------------------
	/** Metodo que que comprueba si para las coordenadas x e y del raton 
	* hemos matado un pajarito y llama a metodos de sonido
	*/
	public void disparo(int _x,int _y) {// punto del disparo...
		int distanciaX,distanciaY;
		int x=_x;
		int y=_y;
		if (numeroBalas>0){// hay disparo pues tenemos balas
			numeroBalas=numeroBalas-1;

			sonidoPuntual=new Sonido("disparo.wav",0);
			sonidoPuntual.start();// arrancamos el thread de este sonido

		 	for (int i=0;i<numMaxPajaritos;i++){// busco si he dado a algun pajaro
				if ((vectorPajaritos[i].getPajaroAsado())==false){//solo cuento si pajaro esta vivo todavia
					distanciaX=x-vectorPajaritos[i].getPosicionX();
					if ((distanciaX>=0)&&(distanciaX<=(vectorPajaritos[i].getPajaro()).getSizeX())){
						// comprobamos si le hemos dado en el eje de las Y
						distanciaY=y-vectorPajaritos[i].getPosicionY();
						if ((distanciaY>=0)&&(distanciaY<=(vectorPajaritos[i].getPajaro()).getSizeY())){
							// le hemos dado: sumamos puntos al marcador y matamos al pajaro
							marcador.actualizaMarcador(vectorPajaritos[i].getValorPajaro());
							(vectorPajaritos[i].getPajaro()).setPajaritoMuriendo();//pajaro cae a tierra
							if (vectorPajaritos[i].getTipoPajarito()==0){//es vaca
								sonidoVaca();
							}else{sonidoPajarito();// es pajaro
							}//end if
						}//end if
					}//end if
				}//end if
			}//end for
		}else{sonidoPuntual=new Sonido("nobalas.wav",0);
			sonidoPuntual.start();// arrancamos el thread de este sonido
		}//end if
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que recarga las balas del cargador */
	public void recargaBalas(){
		if (puedoRecargarAMitad){numeroBalas=numMaxBalas;
		}else if (numeroBalas==0){numeroBalas=numMaxBalas;
		}//end if
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que devuelve el numero maximo de pajaritos que podemos tener*/
	public int getNumMaxPajaritos(){
		return numMaxPajaritos;
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que devuelve el valor del marcador del jugador que esta jugando*/
	public int getMarcador(){
		return marcador.getMarcador();
	}//end metodo
//-------------------------------------------------------------------------------
	/** Metodo que devuelve el nombre del jugador que esta jugando*/
	public String getPlayer(){
		return playerNameActual;
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que pone la variable start a verdadero, lo que indica que comienza el juego*/
	public void setStart(){
		start=true;
	}//end metodo
//-------------------------------------------------------------------------------
	/** Metodo que sirve para dar un tiempo para que se carguen las imagenes del juego*/
	public void esperarCarga(){// bucle infinito hasta que se cargen las imagenes
		
		JOptionPane.showMessageDialog(frame,
				playerNameActual+" : ¿Preparado para eliminar a los invasores?", 
	   			"PAJARITOS",
				JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
				new ImageIcon("pajaroasado1.gif"));	// imagen que sacamos
	
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que devuelve el vectorPajaritos para su tratamiento*/
	public ControlPajaritos[] getVectorPajaritos(){
		return vectorPajaritos;
	}//end metodo


//-------------------------------------------------------------------------------
	/** Metodo que devuelve el numero de segundos que quedan de juego*/
	public int getSegundos(){ // devuelve segundos para el marcador
		return (tiempoJuego-(iteraciones/(25*nivelJuego)))%60;
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que devuelve el numero de minutos que quedan de juego*/	
	public int getMinutos(){
		return (tiempoJuego-(iteraciones/(25*nivelJuego)))/60;
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que llama a metodo de sonido cuando un pajaro muere */
	public void sonidoPajarito(){// cuando matas a un pajaro saca un sonido diferente
		int i=new Random().nextInt(4);
		switch(i){
			case 0: sonidoPuntual=new Sonido("pajarito1.wav",0);break;
			case 1: sonidoPuntual=new Sonido("pajarito2.wav",0);break;
			case 2: sonidoPuntual=new Sonido("pajarito3.wav",0);break;
			default: sonidoPuntual=new Sonido("pajarito4.wav",0);break;
		}// end case
			sonidoPuntual.start();// arrancamos el thread de este sonido
	}//end metodo

//-------------------------------------------------------------------------------
	/** Metodo que llama a metodo de sonido cuando una vaca muere */	
	public void sonidoVaca(){// cuando matas a una vaca saca un sonido diferente
		int i=new Random().nextInt(3);
		switch(i){
			case 0: sonidoPuntual=new Sonido("vaca1.wav",0);break;
			case 1: sonidoPuntual=new Sonido("vaca2.wav",0);break;
			default: sonidoPuntual=new Sonido("vaca3.wav",0);break;
		}// end case
			sonidoPuntual.start();// arrancamos el thread de este sonido
	}//end metodo

//-------------------------------------------------------------------------------
public abstract void finJuego();
public abstract void inicioJuego();

//-------------------------------------------------------------------------------
}// end clase