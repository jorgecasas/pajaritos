package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/**
 * ESTA CLASE TIENE EL NOMBRE DE UN JUGADOR Y SU CODIGO SECRETO
*/
import java.util.*;
public class Player  {
    private String playerName,email,movil;
    private int record;

    /**------------------------------------------------------------------------
     * Constructor de PLAYER
     */
    public Player(String _playerName,String _email,String _movil,int _record) {
        playerName=_playerName;
	  email=_email;
	  movil=_movil;
	  record=_record;
    }

    /**------------------------------------------------------------------------
     * @return playerName 
     */
    public String getName() {
        return playerName;
    }

//--------------------------------------------------------------------------------
    public String getMovil() {
        return movil;
    }
//--------------------------------------------------------------------------------
    public String getEmail() {
        return email;
    }
//--------------------------------------------------------------------------------
    public int getRecord() {
        return record;
    }
//--------------------------------------------------------------------------------
    public void setRecord(int x) {
        record=x;
    }
//--------------------------------------------------------------------------------
    public void setEmail(String x) {
        email=x;
    }

//--------------------------------------------------------------------------------
    public void setMovil(String x) {
        movil=x;
    }


//--------------------------------------------------------------------------------
}
