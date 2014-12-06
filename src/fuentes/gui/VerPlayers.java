package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import models.*;
import aplication.*;

/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada
 */
// CREA EL JFRAME EN EL QUE PEDIMOS LOS DATOS DEL NUEVO JUGADOR

public class VerPlayers extends JFrame implements ActionListener {
  
  JLabel labelTexto = new JLabel();
  JLabel labelImagen=new JLabel();

  JButton botonAceptar = new JButton();
   		Player playerAux;
		Object[][] data;
		String [] nombreColumnas;
		String players[];



//----------------------------------------------------------------------------------
  public VerPlayers() {
	
    try {
    		jbInit();
    }catch(Exception e) {e.printStackTrace();
    }//end try
  }//end constructor

//----------------------------------------------------------------------------------
  private void jbInit() throws Exception  {
    
	//propiedades del frame
    this.getContentPane().setLayout(null);
    this.setTitle("PAJARITOS - Ver jugadores en activo");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(640,200);
    this.setIconImage(loadImage("iconojuego.gif"));
   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);


	//propiedades de los label, campos de texto ,botones y el combobox
	
    labelTexto.setText("Jugadores en activo");
    labelTexto.setBounds(new Rectangle(160, 10, 120, 20));


    labelImagen.setIcon(new ImageIcon("pajaro0d.gif"));
    labelImagen.setBounds(new Rectangle(480,0,150,190));

    botonAceptar.setBounds(new Rectangle(50, 140, 400, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada

// ahora creamos la tabla con los jugadores y sus características
		ControlPlayers controlJugadores=new ControlPlayers();
		Object data[][]=new Object[controlJugadores.getNumeroPlayers()][4];
		String players[]= new String[controlJugadores.getNumeroPlayers()];
		players=controlJugadores.getAllPlayers();

		for (int i=0;i<controlJugadores.getNumeroPlayers();i++){
			playerAux=controlJugadores.getPlayer(players[i]);
       		data[i][0]=playerAux.getName();
       		data[i][1]=playerAux.getEmail();
       		data[i][2]=playerAux.getMovil();
       		data[i][3]=Integer.toString(playerAux.getRecord());
        	}//end for

        String[] nombreColumnas = {"Jugador", 
                                "Email",
                                "Móvil",
                                "Récord"};

        JTable table = new JTable(data,nombreColumnas);
		table.setCellSelectionEnabled(false);

	  table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        //Creamos el scrollpane en donde meteremos la tabla. 
        JScrollPane scrollPane = new JScrollPane(table);
 	  scrollPane.setBounds(new Rectangle(50, 30, 400, 100));

	//añadimos al panel
    this.getContentPane().add(labelTexto, null);
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(scrollPane, null);

  }
//-------------------------------------------------------------------------
  
  public void actionPerformed(ActionEvent ae) {
	JFrame menuInicio;
         if (ae.getActionCommand().equals("aceptar")) {// BOTON CANCELAR
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
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
}//end clase
