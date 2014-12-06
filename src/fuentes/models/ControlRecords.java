package models;
import java.io.*;
import java.lang.Integer;


/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/** Clase que sirve para mantener el control de los records y actualizar fichero de records*/
public class ControlRecords {
//--------------------------------------------------------------------------------
  public ControlRecords() {}
//--------------------------------------------------------------------------------
 
/** Devuelve el nombre del jugador que tiene el record absoluto de todos los tiempos en la posicion i-esima*/
  public String getNameRecord(int _posicion){//devuelve el jugador del record de la posicion i
	String nombre;
	int posicion;
    RandomAccessFile file;
	posicion=_posicion;

   try {
	file = new RandomAccessFile("records.dat","rw");
	if (posicion>10){nombre=null;
	}else{for (int j=1;j<posicion;j++){//hasta llegar a la posicion a leer
		file.readLine();//lee nombre
		file.readInt();//lee record
		file.readLine();//lee separacion
		}//end for
		//ahora leemos nombre y record
		nombre=file.readLine();
		return nombre;
	}//end if
	}catch(Exception e2){e2.printStackTrace();
    }//end try
	return null;
  }//end metodo
//--------------------------------------------------------------------------------
/** Devuelve el record de la posicion iesima del fichero de records*/
  public String getRecord(int _posicion){//devuelve el record de la posicion i
	String nombre;
	String record;
	int posicion;
    RandomAccessFile file;
	posicion=_posicion;

   try {
	file = new RandomAccessFile("records.dat","rw");
	if (posicion>10){record=null;
	}else{for (int j=1;j<posicion;j++){//hasta llegar a la posicion a leer
		file.readLine();//lee nombre
		file.readInt();//lee puntuacion
		file.readLine();
		
		}//end for
		//ahora leemos nombre y record
		file.readLine();
		record=Integer.toString(file.readInt());
		return record;
	}//end if
	}catch(Exception e2){e2.printStackTrace();
    }//end try
	return null;
  }//end metodo
//--------------------------------------------------------------------------------
	/** Comprueba si un jugador ha batido su maxima puntuacion de todos los tiempos*/
  public void comprobarRecord(Player player,int puntuacion) {//modifica los records
		// y los ordena si hay nuevo record
    RandomAccessFile file;
	String vectorNombres[]=new String[10];
	int vectorPuntos[]=new int[10];
	int posicionRecord=10;
	long pos;
	boolean hayrecord=false;

 	try {
	    file = new RandomAccessFile("records.dat","rw");
	   // hay 10 records
		pos=file.getFilePointer();
		for (int i=0;i<=9;i++){
			vectorNombres[i]=file.readLine();
			vectorPuntos[i]=file.readInt();//lee el record
			file.readLine();//separacion
			if ((puntuacion>=vectorPuntos[i])&&(!hayrecord)){//hay record
				posicionRecord=i;
				hayrecord=true;
			}//end if
		}//end for

	//copiamos los records que estan bien, el resto los desplazamos
		
		for (int i=9;i>=posicionRecord+1;i--){
			vectorPuntos[i]=vectorPuntos[i-1];
			vectorNombres[i]=vectorNombres[i-1];
		}//end for

		if (posicionRecord<10){
			vectorPuntos[posicionRecord]=puntuacion;
			vectorNombres[posicionRecord]=player.getName();
		}//end if

		//escribimos los records en el fichero
		file.setLength(0);//borramos todo
		pos=file.getFilePointer();
		file.seek(pos);
		for (int i=0;i<=9;i++){
			file.writeBytes(vectorNombres[i]);file.writeByte('\n');
			file.writeInt(vectorPuntos[i]);file.writeByte('\n');
		}//end for
    }catch(Exception e2){e2.printStackTrace();
    }//end try
  }//end metodo
//--------------------------------------------------------------------------------
/** Resetea el fichero de records - Metodo de control*/
  public void resetRecords (){		// y los ordena si hay nuevo record
    RandomAccessFile file;
    String auxnombre;
    int auxrecord;
	
	auxnombre="Gejor";
	auxrecord=80;
	
    try {
	    file = new RandomAccessFile("records.dat","rw");
		file.setLength(0);
	   // hay 10 records
		for (int i=0;i<=9;i++){
				file.writeBytes(auxnombre);file.writeByte('\n');
				file.writeInt(auxrecord=auxrecord-5);file.writeByte('\n');

		}//end for
    }catch(Exception e2){e2.printStackTrace();
    }//end try
}//end metodo

//--------------------------------------------------------------------------------
}//end clase