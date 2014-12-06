package aplication;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */


// ESTA CLASE ES PARA CREAR MENUS CONVENCIONALES TIPO : ARCHIVO, EDITAR, AYUDA...
import java.io.*;
import java.lang.*; 
import java.util.*; 
import java.lang.Integer;
import java.awt.*;
import javax.swing.Box;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.net.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.applet.Applet;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.filechooser.*;
import java.util.Calendar;
import models.*;
import gui.*;


public class MenuPrincipal extends JMenu implements ActionListener, ItemListener {
	
	JFrame frame;
	JMenuBar barraMenu;
	JMenu menuJuego, menuJugadores,menuAyuda;
	JMenuItem menuItem;
	private JFileChooser fileChooser = new JFileChooser();

	
//----------------------------------------------------------------------------------
	public MenuPrincipal(JFrame _frame)	{//pasamos el frame al que le ponemos el menu
		
		frame=_frame;
		JMenuBar barraMenu = new JMenuBar();	// creamos el espacio para poner la barra de menu
		frame.setJMenuBar(barraMenu);
		
		// creamos ahora los menus principales
		JMenu menuJuego = new JMenu("Juego");  // el titulo es archivo
		menuJuego.setMnemonic('j');		
		barraMenu.add(menuJuego);

		JMenu menuJugadores = new JMenu("Jugadores");  // el titulo es archivo
		menuJugadores.setMnemonic('u');		
		barraMenu.add(menuJugadores);


		barraMenu.add(Box.createHorizontalGlue()); // ESTO SE PONE PARA QUE EL MENU QUE LE SIGA
					// SE PONGA A LA DERECHA DE LA VENTANA

		JMenu menuAyuda = new JMenu("Ayuda");	// el titulo es ayuda
		menuAyuda.setMnemonic('a');		
		barraMenu.add(menuAyuda);
	

		// añadimos cosas a cada menu principal desplegable: titulos y eso
		// cada vez que añadimos algo hay que crear un nuevo item
		menuItem = new JMenuItem("Crear Jugador",new ImageIcon("icoeditar.jpg"));	// aqui los seleccionables
		menuItem.setMnemonic('c');
       	menuItem.addActionListener(this);	
		menuJugadores.add(menuItem);
		
		menuItem = new JMenuItem("Editar Jugador",new ImageIcon("icoeditar.jpg"));	// aqui los seleccionables
		menuItem.setMnemonic('e');
		menuItem.addActionListener(this);	
		menuJugadores.add(menuItem);
	
		menuItem = new JMenuItem("Ver Jugadores",new ImageIcon("icoeditar.jpg"));	// aqui los seleccionables
		menuItem.setMnemonic('v');
		menuItem.addActionListener(this);	
		menuJugadores.add(menuItem);

		
		
		menuItem = new JMenuItem("Records",new ImageIcon("icoopciones.jpg"));
		menuItem.setMnemonic('r');		
       	menuItem.addActionListener(this);
		menuJuego.add(menuItem);
		
		menuItem = new JMenuItem("Cargar Torneo",new ImageIcon("icoopciones.jpg"));
		menuItem.setMnemonic('t');		
		menuItem.addActionListener(this);
		menuJuego.add(menuItem);
		
		menuJuego.addSeparator();		// inserto un separador

		menuItem = new JMenuItem("Salir",new ImageIcon("icosalir.jpg"));
		menuItem.setMnemonic('s');	
       	menuItem.addActionListener(this);	
		menuJuego.add(menuItem);

	

		// ahora metemos componentes para el siguiente menu ayuda
			

		menuItem = new JMenuItem("Ayuda",new ImageIcon("icoayuda.jpg"));	
		menuItem.setMnemonic('a');
    		menuItem.addActionListener(this);
		menuAyuda.add(menuItem);
		
		menuAyuda.addSeparator();		// inserto un separador

		menuItem = new JMenuItem("Gejor! Web 2002", new ImageIcon("icopapelera.jpg"));	
		menuItem.setMnemonic('g');
      	menuItem.addActionListener(this);
		menuAyuda.add(menuItem);
	}
	
	
	// *****************************************************************************
	// METODO PARA VER QUE HACEMOS CUANDO PULSAMOS EL RATON SOBRE UN OBJETO
	// COMO PUEDE SER UN BOTON O SIMILAR

    public void actionPerformed(ActionEvent e) {
	
		// EN LOS MENUS DE ARCHIVOS SE HACE DIFERENTE QUE EN LOS BOTONES EL ACTION
		// LISTENER, HAY QUE CREAR UNA VARIABLE QUE LEA LO QUE PONE LA OPCION
		// ELEGIDA
		JMenuItem source = (JMenuItem)(e.getSource());

        if (source.getText().equals("Crear Jugador")) {
		frame.setVisible(false);
		NewPlayer crearPlayer=new NewPlayer();
	  } 

	  if (source.getText().equals("Editar Jugador")) {
		frame.setVisible(false);
		ModificarPlayer modificarPlayer=new ModificarPlayer();
	  } 

	  if (source.getText().equals("Ver Jugadores")) {
		frame.setVisible(false);
		VerPlayers verPlayers=new VerPlayers();
	  } 

	  if (source.getText().equals("Records")) {
		frame.setVisible(false);
		frame.dispose();
		JFrame frameRecords=new FrameRecords(null,null,0);
		frameRecords.setVisible(true);
	  } //end if
	  
	  if (source.getText().equals("Cargar Torneo")) {

		  
		  int returnVal = fileChooser.showDialog(this,"Cargar Torneo");
		  
		  if (returnVal == JFileChooser.APPROVE_OPTION) {
		  	File file = fileChooser.getSelectedFile();
		  	Player[] jugadores2 = new Player[4];	
		  	try{
		  		RandomAccessFile randomFile= new RandomAccessFile(file.getName(),"rw");	
		  		int num=randomFile.readInt();randomFile.readLine();
		  		
		  		jugadores2 = new Player[num];	
		  		ControlPlayers controlJugadores = new ControlPlayers();
		  		for (int i=0;i<num;i++){
		  			String cad = randomFile.readLine();
		  			jugadores2[i]=controlJugadores.getPlayer(cad);
		  		} //end for  			
				frame.setVisible(false);
				frame.dispose();
		  		Torneo torneo = new Torneo(jugadores2);
		  		torneo.setVisible(true);  
		  	}catch (Exception e2){}
		  }//end if
	  } //end if
	  
	  if (source.getText().equals("Salir")) {
		// DIALOGO DE PREGUNTA SI/NO
		Object[] options = {"Sí, soy un cobarde", "No"};
		 int n = JOptionPane.showOptionDialog(frame.getContentPane(), "¿Realmente quieres salir y dejar que los pajaritos dominen el mundo?",
			"¿No quieres matar más pajaritos?",
			JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE,
			new ImageIcon("pajaro1d.gif"), //usar icono 
			options, // el vector con los titulos  de opciones de los botones 
			"Sí, soy un cobarde"); //Titulo del boton por defecto
		if (n == JOptionPane.YES_OPTION) { System.exit(0); } 
	  } //end if



	   if (source.getText().equals("Ayuda")) {
		try { 
			Process p=Runtime.getRuntime().exec("explorer index.html"); 
		}catch(Exception e2){} 
	 }//end if
 
	   if (source.getText().equals("Gejor! Web 2002")) {
	  	try { 
			Process p=Runtime.getRuntime().exec("explorer http://www.geocities.com/gejorzzz/index.html"); 
		}catch(Exception e2){} 

	  } 
	
    }
	public void itemStateChanged(ItemEvent e) {
		}

}












