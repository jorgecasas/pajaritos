package models;
import java.io.*;


/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/** Clase que sirve para el control de los fichero con los jugadores existentes, y su uso al iniciar partidas*/
public class ControlPlayers {

//--------------------------------------------------------------------------------
  public ControlPlayers () {}
//--------------------------------------------------------------------------------
/** Devuelve el jugador con un nombre determinado*/
public Player getPlayer(String _nombre) {// devuelve el jugador con ese nombre
	String nombre;
	String email;
	String movil;
      int record;

 	String cad,cad2;
	RandomAccessFile file;

    try {
   	 file = new RandomAccessFile("jugadores.dat","rw");
  	 while ((cad=file.readLine()) != null){
	      if(cad.equalsIgnoreCase(_nombre)==true){// es este player
      		  nombre=cad;
		        email=file.readLine();
		        movil=file.readLine();
		        record=file.readInt();
		        cad2=file.readLine();
		        return(new Player(nombre,email,movil,record));
	      }else{ for(int i=0;i<3;i++){  cad=file.readLine();}// no es este player
		      file.readInt();
		      file.readLine();
		}//end if
    	}// end while
    } catch(Exception e2){ e2.printStackTrace();
    }// end try
    return(null);// si no existe el jugador
  }//end metodo

//--------------------------------------------------------------------------------
	/** Crea un nuevo jugador en el fichero de jugadores */
  public void setNewPlayer(Player player)
  {

    RandomAccessFile file;
    try {
   	file = new RandomAccessFile("jugadores.dat","rw");
  	file.seek(file.length());//pone el puntero al final de fichero 
	file.writeBytes(player.getName());file.writeByte('\n');
	file.writeBytes(player.getEmail());file.writeByte('\n');
	file.writeBytes(player.getMovil());file.writeByte('\n');
	file.writeInt(player.getRecord());file.writeByte('\n');
	file.writeBytes("------------------------------------");file.writeByte('\n');
    }catch(Exception e2){  e2.printStackTrace();
    }//end try
  }//end metodo

//--------------------------------------------------------------------------------
/** Borra un jugador con un nombre determinado del fichero de jugadores*/
  public void deletePlayer(String nombre){
    RandomAccessFile file;
    long pos;
    String cad;
    boolean salir=false;
    try {
	    file = new RandomAccessFile("jugadores.dat","rw");
	    pos=file.getFilePointer();
  	  while (((cad=file.readLine()) != null) && !salir) {
	      if(cad.equalsIgnoreCase(nombre)) {
		        file.seek(pos);//coloca el puntero para escribir en la linea que toque
		        file.writeBytes("------------------------------------");
    			    salir=true;
	      }else{ for(int i=0;i<3;i++){ cad=file.readLine();}
		      file.readInt();
		      file.readLine();
    			pos=file.getFilePointer();
    		}//end if
    	   }//end while
	}catch(Exception e2) {e2.printStackTrace();
      }//end try
  }//end metodo

//--------------------------------------------------------------------------------
/** Modifica algun parametro de un jugador en el fichero de jugadores*/
  public void modificaPlayer(Player player){
    RandomAccessFile file;
    String cad;
    long pos;
    boolean salir=false;
    try  {
	    file = new RandomAccessFile("jugadores.dat","rw");
	    pos=file.getFilePointer();
	    while (((cad=file.readLine()) != null) && !salir){
  		    salir=(cad.equalsIgnoreCase(player.getName()));
    		    if(!salir){// no es el player a modificar
      		  for(int i=0;i<2;i++){cad=file.readLine();}
		        file.readInt();file.readLine();
      		  file.readLine();
                }else{// es el player a modificar
				file.writeBytes(player.getEmail());file.writeByte('\n');
				file.writeBytes(player.getMovil());file.writeByte('\n');
				file.writeInt(player.getRecord());file.writeByte('\r');

	         }//end if
    	    }//end while
    }catch(Exception e2){e2.printStackTrace();
    }//end try
  }//end metodo
//--------------------------------------------------------------------------------
/** Comprueba si la puntuacion realizada por un jugador es mayor que el record actual del jugador*/ 
  public void comprobarRecordPlayer(Player player,int puntuacion){
    RandomAccessFile file;
    String cad;
    long pos;
    boolean salir=false;
    try  {
	    file = new RandomAccessFile("jugadores.dat","rw");
	    pos=file.getFilePointer();
	    while (((cad=file.readLine()) != null) && !salir){
  		    salir=(cad.equalsIgnoreCase(player.getName()));
    		    if(!salir){// no es el player a modificar
      		  for(int i=0;i<2;i++){cad=file.readLine();}
		        file.readInt();file.readLine();
      		  file.readLine();
                    pos=file.getFilePointer();//posicionamos el cursor
		    }else{// es el player a modificar
			  if (puntuacion>player.getRecord()){
				for(int i=0;i<2;i++){cad=file.readLine();}
				pos=file.getFilePointer();//posicionamos el cursor
				file.writeInt(puntuacion);
			  }//end if
			}//end if
    	    }//end while
    }catch(Exception e2){e2.printStackTrace();
    }//end try
  }//end metodo

//--------------------------------------------------------------------------------
/** Devuelve un vector de cadenas con los nombres de todos los jugadores*/
 public String[] getAllPlayers(){// devuelve los nombres de todos jugadores
	RandomAccessFile file;
   	String cad;
	String players[]=new String[this.getNumeroPlayers()];
	
    try  {
	    file = new RandomAccessFile("jugadores.dat","rw");
	   for (int i=0;i<this.getNumeroPlayers();i++){
			players[i]=file.readLine();
			file.readLine();	
			file.readLine();
			file.readInt();
			file.readLine();
			file.readLine();
		
		}//end for
    }catch(Exception e2){e2.printStackTrace();
    }//end try
	return players;
}//end metodo
//--------------------------------------------------------------------------------
	/** Devuelve el numero de jugadores que hay en el fichero de jugadores*/
 public int getNumeroPlayers(){// devuelve el numero de jugadores
	RandomAccessFile file;
   	String cad;
	int i=0;
	

    try  {
	    file = new RandomAccessFile("jugadores.dat","rw");
	    while (((cad=file.readLine()) != null) ){
			file.readLine();// leemos el nombre de todos
			file.readLine();
			file.readInt();
			file.readLine();
			file.readLine();
			i++;
		}//end while
    }catch(Exception e2){e2.printStackTrace();
    }//end try
	return i;
}//end metodo


//--------------------------------------------------------------------------------
}//end clase