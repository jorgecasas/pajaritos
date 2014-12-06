
package models;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
 
import gui.*;
import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.lang.Object.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


/** Control de juego 2 jugadores en modo combate (al mejor de 3 asaltos) para usar en torneos*/
public final class ControlJuegoNP2 extends ControlJuego{
	
	/**------------------------------------------------------------------------
	*	Constructor de la clase ControlJuego
	***----------------------------------------------------------------------*/
	public ControlJuegoNP2(JFrame _frame,int _tiempoJuego,int _nivelJuego,Player _player[],String img1,String img2) throws Exception{
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
		numeroRondas=numeroJugadores/2;
		ganadores = new Player[numeroRondas];

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
		asaltoGanadoPlayer1=asaltoGanadoPlayer2=false;
		noHayGanador=true;
		numeroAsalto=0;
	}// end constructor
//-------------------------------------------------------------------------------
	/** Metodo que hace de bucle principal del thread*/
	public void run(){
		for (int ronda=0;ronda<numeroRondas;ronda++){
			
			numeroAsalto=0;
			JOptionPane.showMessageDialog(frame,
				"Siguiente enfrentamiento: "+playerName[2*ronda]+" vs "+playerName[1+2*ronda], 
		   		"PAJARITOS - Siguiente enfrentamiento del torneo",
				JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
				new ImageIcon("pajaro1i.gif"));	// imagen que sacamos

			while(noHayGanador==true){
				numeroAsalto++;
				JOptionPane.showMessageDialog(frame,
					"Asalto número "+numeroAsalto, 
	   				"PAJARITOS",
					JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
					new ImageIcon("pajaro1d.gif"));	// imagen que sacamos

				for (int turnoJugador=0;turnoJugador<2;turnoJugador++){
					playerNameActual=playerName[turnoJugador+2*ronda];
					esperarCarga();
		
					numeroBalas=numMaxBalas;
					marcador = new Marcador(playerNameActual);
	
					for (int i=0;i<=numMaxPajaritos-1;i++){		
						vectorPajaritos[i]=null;			
					}//end for							

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
				// comprobamos records personales
				ControlRecords controlRecords=new ControlRecords();
				ControlPlayers controlPlayer= new ControlPlayers();

				for (int i=0;i<2;i++){
					controlRecords.comprobarRecord(player[i+2*ronda],puntuaciones[i]);
					controlPlayer.comprobarRecordPlayer(player[i+2*ronda],puntuaciones[i]);
				}//end for	

				//miramos que jugador gana el asalto
				if ((puntuaciones[0]>puntuaciones[1])&&(asaltoGanadoPlayer1)){// gana player 1
					noHayGanador=false;
					numeroPlayerGanador=0+2*ronda;
					playerCampeon=playerName[2*ronda];
					playerSubcampeon=playerName[1+2*ronda];

				}//end if

				if ((puntuaciones[0]<puntuaciones[1])&&(asaltoGanadoPlayer2)){// gana player 1
					noHayGanador=false;
					numeroPlayerGanador=1+2*ronda;
					playerCampeon=playerName[1+2*ronda];
					playerSubcampeon=playerName[2*ronda];
				
				}//end if

				if (puntuaciones[0]>puntuaciones[1]){asaltoGanadoPlayer1=true;
					JOptionPane.showMessageDialog(frame,
						playerName[0+2*ronda]+" gana el asalto", 
	   					"PAJARITOS",
						JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
						new ImageIcon("pajaro1d.gif"));	// imagen que sacamos
				}//end if
				if (puntuaciones[0]<puntuaciones[1]){asaltoGanadoPlayer2=true;
					JOptionPane.showMessageDialog(frame,
						playerName[1+2*ronda]+" gana el asalto", 
		   				"PAJARITOS",
						JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
						new ImageIcon("pajaro1i.gif"));	// imagen que sacamos
				}//end if
				
			}//end while
			asaltoGanadoPlayer1=asaltoGanadoPlayer2=false;
			noHayGanador=true;
			frame.setVisible(false);
			frame.dispose();
			puntuaciones[0]=puntuaciones[1]=0;
			ControlPlayers controlPlayer= new ControlPlayers();
			ganadores[ronda]=controlPlayer.getPlayer(playerName[numeroPlayerGanador]);
			JOptionPane.showMessageDialog(frame,
				playerName[numeroPlayerGanador]+" gana el enfrentamiento", 
		   		"PAJARITOS - Fin del enfrentamiento",
				JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
				new ImageIcon("pajaro1i.gif"));	// imagen que sacamos

	}//end for
	finJuego();

	}// end run
//-------------------------------------------------------------------------------
	/** Metodo que finaliza el juego*/
	public final void finJuego(){

		//cerramos el frame de juego
		frame.setVisible(false);
		frame.dispose();
		if (ganadores.length==1){
			JFrame frameAsaltos = new FrameAsaltos(playerCampeon,playerSubcampeon);
			frameAsaltos.setVisible(true);
		}else{
			Torneo torneo= new Torneo(ganadores);
			torneo.setVisible(true);		
		}//end if

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