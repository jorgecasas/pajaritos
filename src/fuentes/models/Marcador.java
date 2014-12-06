package models;

/**
 * Title:        PAJARITOS
 * Copyright:    Copyright (c) 2002
 * @author Jorge Casas Cañada (Gejor!)
 */

/**
 * ESTA CLASE TIENE EL NOMBRE DE UN JUGADOR Y MARCADOR
*/
import java.util.*;
public class Marcador  {
    private int valorMarcador;
    private String playerName;

    /**------------------------------------------------------------------------
     * Constructor de MARCADOR
     */
    public Marcador(String _playerName) {
        resetMarcador();
        playerName=_playerName;
    }

    /**------------------------------------------------------------------------
     * @return playerName 
     */
    public String getPlayerName() {
        return playerName;
    }

    /**------------------------------------------------------------------------
     * @return valorMarcador
     */
    public int getMarcador() {
        return valorMarcador;
    }

   /**------------------------------------------------------------------------
     * @set valorMarcador + nuevos puntos
     */
    public void actualizaMarcador(int _valor) {
        valorMarcador = valorMarcador + _valor;
    }
 
  /**------------------------------------------------------------------------
     * @set valorMarcador
     */
    public void setMarcador(int _valor) {// para poner el valor que nosotros queramos
        valorMarcador = _valor;
    }

   /**------------------------------------------------------------------------
     * @set valorMarcador=0
     */
    public void resetMarcador() {
        valorMarcador=0;
    }

}
