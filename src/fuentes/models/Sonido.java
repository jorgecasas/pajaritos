package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

import javax.sound.sampled.*;
import java.io.*;


public class Sonido extends Thread implements Runnable {
	String fichero;
	int numeroRepeticiones;
//----------------------------------------------------------------------------------------

    public Sonido (String ficheroWav,int num){
		super();
		fichero=ficheroWav;
		numeroRepeticiones=num;
	}
	
	public void run(){

        File sf=new File(fichero);
        AudioFileFormat aff;
        AudioInputStream ais;


        try{
         	aff=AudioSystem.getAudioFileFormat(sf);
	      ais=AudioSystem.getAudioInputStream(sf);
	      AudioFormat af=aff.getFormat();
	      DataLine.Info info = new DataLine.Info(
                                          Clip.class,
                                          ais.getFormat(),
                                          ((int) ais.getFrameLength() *
                                              af.getFrameSize()));

        Clip ol = (Clip) AudioSystem.getLine(info);
        ol.open(ais);
        ol.loop(numeroRepeticiones);	
         }catch(UnsupportedAudioFileException ee){}
        catch(IOException ea){}
        catch(LineUnavailableException LUE){};

        }//end run
//----------------------------------------------------------------------------------------
    }// end clase