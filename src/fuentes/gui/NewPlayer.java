package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.net.URL;
import aplication.*;
import models.*;
/**
 * Title:        PAJARITOS
 * @author Jorge Casas Cañada 
 */
// CREA EL JFRAME EN EL QUE PEDIMOS LOS DATOS DEL NUEVO JUGADOR

public class NewPlayer extends JFrame implements ActionListener {
  
  JLabel labelNombre = new JLabel();
  JLabel labelEmail = new JLabel();
  JLabel labelMovil = new JLabel();
  JLabel labelNewPlayer = new JLabel();
  JLabel labelImagen=new JLabel();
  JTextField textNombre = new JTextField();
  JTextField textEmail = new JTextField();
  JTextField textMovil = new JTextField();
  JButton botonAceptar = new JButton();
  JButton botonCancelar = new JButton();

//----------------------------------------------------------------------------------
  public NewPlayer() {
    try {
    		jbInit();
    }catch(Exception e) {e.printStackTrace();
    }//end try
  }//end constructor

//----------------------------------------------------------------------------------
  private void jbInit() throws Exception  {
    
	//propiedades del frame
    this.getContentPane().setLayout(null);
    this.setTitle("PAJARITOS - Añadir nuevo jugador");
    this.setDefaultCloseOperation(0);//Para que al cerrar la ventana no haga nada
    this.setResizable(false);
    this.setSize(390,180);
    this.setIconImage(loadImage("iconojuego.gif"));
   
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
 	this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	this.setVisible(true);


	//propiedades de los label, campos de texto y botones
    labelNombre.setText("Nombre");
    labelNombre.setBounds(new Rectangle(30, 30, 60, 20));

    labelEmail.setText("Email");
    labelEmail.setBounds(new Rectangle(30, 60, 60, 20));

    labelMovil.setToolTipText("");
    labelMovil.setText("Móvil");
    labelMovil.setBounds(new Rectangle(30, 90, 60, 20));

    labelImagen.setIcon(new ImageIcon("pajarosmall1.png"));
    labelImagen.setBounds(new Rectangle(250,3,140,140));

    textNombre.setBounds(new Rectangle(100, 30, 140, 20));
    textEmail.setBounds(new Rectangle(100, 60, 140, 20));
    textMovil.setBounds(new Rectangle(100, 90, 140, 20));


    botonAceptar.setBounds(new Rectangle(50, 120, 100, 25));
    botonAceptar.setText("Aceptar");
    botonAceptar.setActionCommand("aceptar");	// el mensaje que envia cuando se pulsa
    botonAceptar.addActionListener(this);	// activamos que escuchen la accion realizada

    botonCancelar.setBounds(new Rectangle(155, 120, 100, 25));
    botonCancelar.setText("Cancelar");
    botonCancelar.setActionCommand("cancelar");	// el mensaje que envia cuando se pulsa
    botonCancelar.addActionListener(this);	// activamos que escuchen la accion realizada


	//añadimos al panel
    this.getContentPane().add(labelNombre, null);
    this.getContentPane().add(labelEmail, null);
    this.getContentPane().add(labelImagen, null);
    this.getContentPane().add(labelMovil, null);
    this.getContentPane().add(textNombre, null);
    this.getContentPane().add(textEmail, null);
    this.getContentPane().add(textMovil, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(botonCancelar, null);

  }
//-------------------------------------------------------------------------
  
  public void actionPerformed(ActionEvent ae) {
	JFrame menuInicio;
         if (ae.getActionCommand().equals("cancelar")) {// BOTON CANCELAR
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
	  } // end if


	 if (ae.getActionCommand().equals("aceptar")) {// BOTON ACEPTAR

		ControlPlayers control = new ControlPlayers();
		String nombre, email, movil;
		int puntos=0;
		nombre=email=movil=null;
	
 		if (control.getPlayer(textNombre.getText())!=null){//player repetido
			JOptionPane.showMessageDialog(this,
				"Jugador ya existente", 
	   			"PAJARITOS",
				JOptionPane.PLAIN_MESSAGE,// tipo de mensaje de alerta, este es normal
				new ImageIcon("pajaroasado1.gif"));	// imagen que sacamos
		}//end if

	 	if ((textNombre.getText().compareTo("")!=0) &&(control.getPlayer(textNombre.getText())==null)) {// puede que no tengan email ni movil
      		nombre=textNombre.getText();
		      email=textEmail.getText();
      		movil=textMovil.getText();
	      	try { control.setNewPlayer(new Player(nombre,email,movil,0));
		      } catch(Exception k) {k.printStackTrace();
		      }//end try
			this.setVisible(false);
			this.dispose();
			menuInicio= new Start();
			menuInicio.setVisible(true);
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