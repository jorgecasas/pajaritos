
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

/** Clase de control de juego de N jugadores a modo simple (primero uno luego otro, gana el de mas puntos)*/
public final class ControlJuegoNP1 extends ControlJuego{
	
	/**------------------------------------------------------------------------
	*	Constructor de la clase ControlJuego
	*------------------------------------------------------------------------*/
	public ControlJuegoNP1(JFrame _frame,int _tiempoJuego,int _nivelJuego,Player _player[],String img1,String img2) throws Exception{
		// inicializo todas las variables de juego y empiezo a jugar
		super();
		tiempoJuego=_tiempoJuego;// en segundos
		nivelJuego=_nivelJuego;
		frame=_frame;
		
		start=false;
		numeroJugadores=_player.length;

		player=new Player[numeroJugadores];
		playerName=new String[numeroJugadores];
		player=_player;

		for (int i=0;i<numeroJugadores;i++){
			playerName[i]=player[i].getName();
		}//end for

		imgEscenario1=img1;
		imgEscenario2=img2;
		
		playerNameActual=playerName[0];
		numeroBalas=numMaxBalas;
		marcador = new Marcador(playerNameActual);

		vectorPajaritos=new ControlPajaritos[numMaxPajaritos];
		for (int i=0;i<=numMaxPajaritos-1;i++){		
			vectorPajaritos[i]=null;			
		}//end for							
		
	
		puntuaciones=new int[numeroJugadores];
		// y ahora ya jugamos y en cada vuelta al bucle creamos los marcadores y 
		// reseteamos los vectores de pajaritos

	}// end constructor
//-------------------------------------------------------------------------------
	public void run(){
		
		for (int turnoJugador=0;turnoJugador<numeroJugadores;turnoJugador++){
			playerNameActual=playerName[turnoJugador];
			esperarCarga();
		
			inicioJuego();
			frame.setVisible(true);
			vueltasBucle=25*tiempoJuego*nivelJuego; // segun nivelJuego (1,2,3..) y
			long tiempoBucle=40/nivelJuego;	// daremos mas vueltas al bucle y + rapidas (40 mseg)

			sonidoPajarosFondo=new Sonido("fondo.wav",(tiempoJuego/6)+2);//poniendo esa cantidad
			sonidoPajarosFondo.start();// sonara hasta que lo paremos el thread

			for (iteraciones=0;iteraciones<=vueltasBucle-1;iteraciones++){// BUCLE PRINCIPAL DE JUEGO
				try{
					sleep((int) tiempoBucle);// esperamos cierto tiempo
				}catch(InterruptedException e){System.out.println("Catch exception "+iteraciones);}
				// actualizamos las posiciones de los pajaros
				actualizaPosicionesPajaritos();
			
				// llamamos a creadores de pajaritos si el num de vueltas es 
				// multiplo de 18*nivelJuego
				if ((iteraciones)%(18*nivelJuego)==0){creaPajarito();}
			}// end for = se ha acabado el tiempo de juego para el jugador correspondiente
			puntuaciones[turnoJugador]=this.getMarcador();
			frame.setVisible(false);

		}//end for turnoJugador
		finJuego();
	}// end run
//-------------------------------------------------------------------------------
	/** Metodo que finaliza el juego*/
	public final void finJuego(){
		//cerramos el frame de juego
		frame.setVisible(false);
		frame.dispose();

		// enviamos resultados y fin juego

		
		ControlRecords controlRecords=new ControlRecords();
		ControlPlayers controlPlayer= new ControlPlayers();

		for (int i=0;i<numeroJugadores;i++){
			controlRecords.comprobarRecord(player[i],puntuaciones[i]);
			controlPlayer.comprobarRecordPlayer(player[i],puntuaciones[i]);
		}//end for	
		
		JFrame frameRecords=new FrameRecords(player,puntuaciones,numeroJugadores);
		frameRecords.setVisible(true);
		

}//end metodo
//-------------------------------------------------------------------------------
	/** Metodo que inicializa e inicia el juego para cada jugador*/
	public final void inicioJuego(){
		numeroBalas=numMaxBalas;
		marcador = new Marcador(playerNameActual);

		for (int i=0;i<=numMaxPajaritos-1;i++){		
			vectorPajaritos[i]=null;			
		}//end for							
	
	

}//end metodo
//-------------------------------------------------------------------------------

}// end clase