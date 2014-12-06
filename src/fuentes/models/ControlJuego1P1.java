
package models;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada (Gejor!)
 */
import gui.*;
import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.lang.Object.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * Clase que controla los parámetros de juego para 1 jugador
 */
public final class ControlJuego1P1 extends ControlJuego{
	
	/**------------------------------------------------------------------------
	*	Constructor de la clase ControlJuego
	*------------------------------------------------------------------------*/
	public ControlJuego1P1(JFrame _frame,int _tiempoJuego,int _nivelJuego,Player _player[],String img1,String img2) throws Exception{
		// inicializo todas las variables de juego y empiezo a jugar
		super();
		tiempoJuego=_tiempoJuego;// en segundos
		nivelJuego=_nivelJuego;
		numeroBalas=numMaxBalas;
		start=false;
		player=new Player[1];
		player[0]=_player[0];
		playerName=new String[1];
		playerNameActual=playerName[0]=player[0].getName();
		imgEscenario1=img1;
		imgEscenario2=img2;
		frame=_frame;
		
		inicioJuego();
	
		}// end constructor
//-------------------------------------------------------------------------------
	/** Metodo run del thread principal*/
	public void run(){

		// Bucle de juego -----------------------

		sonidoPajarosFondo=new Sonido("fondo.wav",(tiempoJuego/6)+2);//poniendo esa cantidad
		sonidoPajarosFondo.start();// sonara hasta que lo paremos el thread
		
		vueltasBucle=25*tiempoJuego*nivelJuego; // segun nivelJuego (1,2,3..) y
		long tiempoBucle=40/nivelJuego;	// daremos mas vueltas al bucle y + rapidas (40 mseg)

		for (iteraciones=0;iteraciones<=vueltasBucle-1;iteraciones++){// BUCLE PRINCIPAL DE JUEGO
			try{
				sleep((int) tiempoBucle);// esperamos cierto tiempo
			}catch(InterruptedException e){System.out.println("Catch exception "+iteraciones);}
			// actualizamos las posiciones de los pajaros
			actualizaPosicionesPajaritos();
			
			// llamamos a creadores de pajaritos si el num de vueltas es 
			// multiplo de 18*nivelJuego
			if ((iteraciones)%(18*nivelJuego)==0){creaPajarito();}
		}// end for = se ha acabado el tiempo de juego
		finJuego();
	}// end run
//-------------------------------------------------------------------------------
	/** Metodo que finaliza el juego*/
	public final void finJuego(){
		//cerramos el frame de juego
		frame.setVisible(false);
		frame.dispose();

		// enviamos resultados y fin juego
		puntuaciones=new int[1];
		puntuaciones[0]=this.getMarcador();
		ControlRecords controlRecords=new ControlRecords();
		controlRecords.comprobarRecord(player[0],puntuaciones[0]);
		
		ControlPlayers controlPlayer= new ControlPlayers();
		controlPlayer.comprobarRecordPlayer(player[0],puntuaciones[0]);
		
		
		JFrame frameRecords=new FrameRecords(player,puntuaciones,1);
		frameRecords.setVisible(true);
		

}//end metodo
//-------------------------------------------------------------------------------
	/** Metodo que inicializa e inicia el juego para un jugador*/
	public final void inicioJuego(){

		marcador = new Marcador(playerName[0]);
		
		vectorPajaritos=new ControlPajaritos[numMaxPajaritos];// vector q dice si el pajaro
		for (int i=0;i<=numMaxPajaritos-1;i++){		// i existe durante el 
			vectorPajaritos[i]=null;			// juego. lo inicializo
		}//end for					// false=nohaypajaro
	
	

}//end metodo
//-------------------------------------------------------------------------------
}// end clase