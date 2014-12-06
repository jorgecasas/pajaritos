package gui;

import aplication.*;
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
import javax.swing.ImageIcon;


/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
// CREA EL JFRAME EN EL QUE MOSTRAMOS AL UN CAMPEON Y AL SUBCAMPEON DE LOS ASALTOS

public class FrameAsaltos extends JFrame implements ActionListener {
  
  JLabel labelCampeon = new JLabel();
  JLabel labelSubcampeon = new JLabel();
  JLabel labelTexto1 = new JLabel();
  JLabel labelImagen=new JLabel();

	String campeon,subcampeon;

  JButton botonAceptar = new JButton();



//----------------------------------------------------------------------------------
  public FrameAsaltos(String _campeon,String _subcampeon) {
	campeon=_campeon;
	subcampeon=_subcampeon;
	try {
    		jbInit();
	}catch(Exception e) {e.printStackTrace();
	}//end try
  }//end constructor

//----------------------------------------------------------------------------------
  private void jbInit() throws Exception  {
    
	//propiedades del frame
    this.getContentPane().setLayout(null);
	this.getContentPane().setVisible(true);
    this.setTitle("PAJARITOS - CUADRO DE HONOR DE LOS ASALTOS");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(640,220);
    this.setIconImage(loadImage("iconojuego.gif"));
	   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);


	//propiedades de los label, campos de texto ,botones y el combobox
	labelCampeon.setFont(new Font("Verdana", Font.BOLD, 30));
	labelCampeon.setText("Campeón "+campeon);
	labelCampeon.setBounds(new Rectangle(160, 102, 300, 40));
 
	labelSubcampeon.setFont(new Font("Verdana", Font.BOLD, 12));
 	labelSubcampeon.setText("Subcampeón "+subcampeon);
	labelSubcampeon.setBounds(new Rectangle(210, 140, 300, 20));



    labelImagen.setIcon(new ImageIcon("campeones.png"));
    labelImagen.setBounds(new Rectangle(20,0,600,100));

    botonAceptar.setBounds(new Rectangle(20, 170, 600, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada



	//añadimos al panel
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(labelCampeon, null);
    this.getContentPane().add(labelSubcampeon, null);
    this.getContentPane().add(botonAceptar, null);

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
