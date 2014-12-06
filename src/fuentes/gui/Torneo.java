package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Calendar;
import java.lang.Integer;
import java.util.*;
import javax.swing.filechooser.*;
import models.*;
import aplication.*;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
// CREA EL JFRAME EN EL QUE modificamos un JUGADOR

public class Torneo extends JFrame implements ActionListener {
  
  JLabel labelImagen=new JLabel();
  JLabel labelTexto=new JLabel();
  JLabel labelTiempo=new JLabel("Tiempo de juego");
  JLabel labelEscenario=new JLabel("Escenario");


  JLabel labelEnfrentamiento1=new JLabel();
  JLabel labelEnfrentamiento2=new JLabel();
  JLabel labelEnfrentamiento3=new JLabel();
  JLabel labelEnfrentamiento4=new JLabel();
  JLabel labelEnfrentamiento5=new JLabel();
  JLabel labelEnfrentamiento6=new JLabel();
  JLabel labelEnfrentamiento7=new JLabel();
  JLabel labelEnfrentamiento8=new JLabel();

  JButton botonAceptar = new JButton();
  JButton botonCancelar = new JButton();
  JButton botonGuardar = new JButton();

   
	Player[] jugadores,jugadores2;
	Player aux;
	public ControlPlayers controlJugadores;

	int numJugadores,num;
  
	JComboBox comboEscenarios,comboTiempoJuego;
	String escenarios[]={"1","2"};
	String tiempos[]={"30","45","60","90"};
	int tiempoEscogido,escenarioEscogido;

	File file;RandomAccessFile randomFile;
	Torneo torneo;
    private JFileChooser fileChooser = new JFileChooser();
	String cad;
	
//----------------------------------------------------------------------------------
  public Torneo(Player _jugadores[]) {
	jugadores=_jugadores;
	numJugadores=jugadores.length;
	int x=0;
	for (int i=0;i<numJugadores;i++){// aleatorizamos los enfrentamientos
		int y = (int) ((System.currentTimeMillis()*17)%numJugadores);
 		aux = jugadores[x];
		jugadores[x]=jugadores[y];
		jugadores[y]=aux;
		x++;
	}//end for
    try {
    		jbInit();
    }catch(Exception e) {e.printStackTrace();
    }//end try
  }//end constructor

//----------------------------------------------------------------------------------
  private void jbInit() throws Exception  {
    
	//propiedades del frame
    this.getContentPane().setLayout(null);
    this.setTitle("PAJARITOS - Torneo");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(440,250);
    this.setIconImage(loadImage("iconojuego.gif"));
   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);

    labelImagen.setIcon(new ImageIcon("pajarosmall5.png"));
    labelImagen.setBounds(new Rectangle(140,20,152,180));
	switch(numJugadores){
		case 16: 	labelEnfrentamiento8.setText(jugadores[14].getName()+" vs "+jugadores[15].getName());
    				labelEnfrentamiento8.setBounds(new Rectangle(60,165,150,20));
				labelEnfrentamiento7.setText(jugadores[12].getName()+" vs "+jugadores[13].getName());
    				labelEnfrentamiento7.setBounds(new Rectangle(60,145,150,20));
				labelEnfrentamiento6.setText(jugadores[10].getName()+" vs "+jugadores[11].getName());
    				labelEnfrentamiento6.setBounds(new Rectangle(60,125,150,20));
				labelEnfrentamiento5.setText(jugadores[8].getName()+" vs "+jugadores[9].getName());
    				labelEnfrentamiento5.setBounds(new Rectangle(60,105,150,20));
				this.getContentPane().add(labelEnfrentamiento8, null);
				this.getContentPane().add(labelEnfrentamiento7, null);
				this.getContentPane().add(labelEnfrentamiento6, null);
				this.getContentPane().add(labelEnfrentamiento5, null);
		case 8: 	labelEnfrentamiento4.setText(jugadores[6].getName()+" vs "+jugadores[7].getName());
    				labelEnfrentamiento4.setBounds(new Rectangle(60,85,150,20));
				labelEnfrentamiento3.setText(jugadores[4].getName()+" vs "+jugadores[5].getName());
    				labelEnfrentamiento3.setBounds(new Rectangle(60,65,150,20));
				this.getContentPane().add(labelEnfrentamiento4, null);
				this.getContentPane().add(labelEnfrentamiento3, null);
		case 4: 	labelEnfrentamiento2.setText(jugadores[2].getName()+" vs "+jugadores[3].getName());
    				labelEnfrentamiento2.setBounds(new Rectangle(60,45,150,20));
				this.getContentPane().add(labelEnfrentamiento2, null);
		default:	labelEnfrentamiento1.setText(jugadores[0].getName()+" vs "+jugadores[1].getName());
    				labelEnfrentamiento1.setBounds(new Rectangle(60,25,150,20));
				this.getContentPane().add(labelEnfrentamiento1, null);
				break;
	}//end switch		
	switch (numJugadores){
		case 16: labelTexto.setText("Octavos de final - Enfrentamientos: ");break;
		case 8: labelTexto.setText("Cuartos de final - Enfrentamientos: ");break;
		case 4: labelTexto.setText("Semifinales - Enfrentamientos: ");break;
		default: labelTexto.setText("La Gran final - Enfrentamientos: ");break;
	}//end switch
	labelTexto.setBounds(new Rectangle(10,5,325,20));
	this.getContentPane().add(labelTexto, null);
	escenarioEscogido=1;
	tiempoEscogido=30;
	labelEscenario.setBounds(new Rectangle(285, 60, 130, 20));

	// los combos
	JComboBox comboEscenarios = new JComboBox(escenarios);
	comboEscenarios.setEditable(false);
	comboEscenarios.setBounds(new Rectangle(380, 60, 50, 20));
	comboEscenarios.addActionListener(this);
	comboEscenarios.addItemListener(new ItemListener(){
      	public void itemStateChanged(ItemEvent e){
			JComboBox cb = (JComboBox)e.getSource();
		  	escenarioEscogido = Integer.parseInt((String)cb.getSelectedItem());
  	     }
 	});
	labelTiempo.setBounds(new Rectangle(285, 90, 130, 20));

	JComboBox comboTiempoJuego = new JComboBox(tiempos);
	comboTiempoJuego.setEditable(false);
	comboTiempoJuego.setBounds(new Rectangle(380, 90, 50, 20));
	comboTiempoJuego.addActionListener(this);
	comboTiempoJuego.addItemListener(new ItemListener(){
      	public void itemStateChanged(ItemEvent e){
			JComboBox cb = (JComboBox)e.getSource();
		  	tiempoEscogido = Integer.parseInt((String)cb.getSelectedItem());
  	     }
 	});

    botonAceptar.setBounds(new Rectangle(50, 190, 100, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada

    botonCancelar.setBounds(new Rectangle(150, 190, 100, 25));
    botonCancelar.setText("Cancelar");
    botonCancelar.setActionCommand("cancelar");	// el mensaje que envia cuando se pulsa
    botonCancelar.addActionListener(this);	// activamos que escuchen la accion realizada

    botonGuardar.setBounds(new Rectangle(250, 190, 150, 25));
    botonGuardar.setText("Guardar Torneo");
    botonGuardar.setActionCommand("guardar");	// el mensaje que envia cuando se pulsa
    botonGuardar.addActionListener(this);	// activamos que escuchen la accion realizada
 
	//añadimos al panel
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(labelEscenario, null);
    this.getContentPane().add(labelTiempo, null);
    this.getContentPane().add(comboTiempoJuego, null);
    this.getContentPane().add(comboEscenarios, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(botonCancelar, null);
    this.getContentPane().add(botonGuardar, null);
   


  }
//-------------------------------------------------------------------------
  
  public void actionPerformed(ActionEvent ae) {
	JFrame menuInicio;
      if (ae.getActionCommand().equals("cancelar")) {// BOTON CANCELAR
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
		
	}//end if
      if (ae.getActionCommand().equals("aceptar")) {// BOTON aceptar
			this.setVisible(false);
			this.dispose();
			FrameJuego frameJuego = new FrameJuego("torneo",tiempoEscogido,"escenario"+escenarioEscogido+"a.gif","escenario"+escenarioEscogido+"b.gif",jugadores);
			frameJuego.setVisible(true);

	}//end if
      if (ae.getActionCommand().equals("guardar")) {// BOTON GUARDAR TORNEO
	  int returnVal = fileChooser.showDialog(this,"Guardar Torneo");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           	File file = fileChooser.getSelectedFile();
			try{
				randomFile = new RandomAccessFile(file.getName(),"rw");
				randomFile.setLength(0);
      	   		randomFile.writeInt(numJugadores);randomFile.writeByte('\n');
				for (int i=0;i<numJugadores;i++){
					randomFile.writeBytes(jugadores[i].getName());randomFile.writeByte('\n');
				}//end for
			}catch(Exception e){}
		}//end if
	}//end if
      
  }//end control pulsaciones raton
//-------------------------------------------------------------------------
    /**
     * Obtiene una imagen
     */
    public Image loadImage( String s ) {
        Image   im = null;
        URL     u;
        String  s2 = "/" + s;
 	 u = getClass().getResource(s2);
        if( u == null )  {
            u = getClass().getResource(s);
        }  // for jar files ?
        if( u != null ) {
            im = Toolkit.getDefaultToolkit().getImage(u);
        } else {
            im = Toolkit.getDefaultToolkit().getImage(s);
            if( im == null )  {
                im = Toolkit.getDefaultToolkit().getImage(s2);
            }
        }
 	 try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(im, 0);
           tracker.waitForID(0);
       }catch ( Exception e ) {System.out.println("Fallo carga imagen" +s);}

        return im;
    }//end metodo
//-------------------------------------------------------------------------

}