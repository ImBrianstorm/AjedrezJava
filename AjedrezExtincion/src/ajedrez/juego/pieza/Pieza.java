/**
 * Clase abstracta que simula el comportamiento de una Pieza
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.juego.pieza;

import ajedrez.excepciones.MovimientoNoValidoExcepcion;
import ajedrez.excepciones.EliminacionInvalidaExcepcion;
import ajedrez.juego.jugador.Jugador;
import ajedrez.juego.tablero.Tablero;

public abstract class Pieza {
    private String nombre;
    private Jugador jugadorDeLaPieza;
    private int numeroJugador;
    private int fila;
    private int columna;
    private int numeroMovimientos;
    private boolean enroque;
    private boolean capturaAlPaso;
    protected int turnoPeonDosEscaques;
    protected Torre torreEnroque;
    protected Peon peonEliminado;
    protected int tipoEnroque;

    /**
    *Constructor que crea una pieza, asignandole nombre, fila donde se encuentra, su columna y el Jugador de la misma
    * @see Jugador
    * @param nombre -- El nombre de la Pieza
    * @param fila -- Fila de la Pieza
    * @param columna -- Columna de la Pieza
    * @param jugadorDeLaPieza -- Jugador de la pieza
    */
    public Pieza(String nombre,int fila,int columna,Jugador jugadorDeLaPieza){
        this.nombre = nombre;
        this.numeroJugador = jugadorDeLaPieza.obtenerNumeroJugador();
        this.fila = fila;
        this.columna = columna;
        this.jugadorDeLaPieza = jugadorDeLaPieza;
        this.numeroMovimientos = 0;
        this.enroque = false;
        this.capturaAlPaso = false;
        this.torreEnroque = null;
        this.peonEliminado = null;
        this.turnoPeonDosEscaques = -1;
    }

    /**
    *Metodo que asigna nombre a una Pieza
    * @param nombre -- Nombre a asignar
    */
    public void asignarNombre(String nombre){
        this.nombre = nombre ;
    }

    /**
    *Metodo que asigna una fila a una Pieza
    * @param fila -- fila a asignar
    */
    public void asignarFila(int fila){
        this.fila = fila ;
    }

    /**
    *Metodo que asigna una columna a una Pieza
    * @param columna -- columna a asignar
    */
    public void asignarcolumna(int columna){
        this.columna = columna ;
    }

    /**
    *Metodo que asigna un Jugador a una Pieza
    * @param JugadorDeLaPieza -- Jugador a asignar a la pieza
    */
    public void asignarJugador(Jugador jugadorDeLaPieza){
        this.jugadorDeLaPieza = jugadorDeLaPieza;
    }

    /**
    *Metodo que asigna una fila y una columna a una Pieza
    * @param fila -- fila de la Pieza
    * @param columna -- columna de la Pieza
    */
    public void asignarPosicion(int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }

    /**
    *Metodo que asigna null a una Torre a Enrocar
    *@see Rey
    */
    public void eliminarTorreAEnrocar(){
        torreEnroque = null;
    }

    /**
    *Metodo que asigna null a un Peon para eliminar en captura al paso
    *@see Peon
    */
    public void eliminarPeonEliminado(){
        peonEliminado = null;
    }

    /**
    *Metodo que suma un movimiento a la pieza
    */
    public void sumarMovimiento(){
        this.numeroMovimientos++;
    }

    /**
    *Metodo que habilita el enroque del Rey
    * @see Rey
    */
    public void habilitarEnroque(){
        this.enroque=true;
    }

    /**
    *Metodo que deshabilita el enroque del Rey
    * @see Rey
    */
    public void deshabilitarEnroque(){
        this.enroque=false;
    }

    /**
    *Metodo que habilita la captura al paso
    * @see Peon
    */
    public void habilitarCapturaAlPaso(){
        this.capturaAlPaso=true;
    }

    /**
    *Metodo que deshabilita la captura al paso
    * @see Peon
    */
    public void deshabilitarCapturaAlPaso(){
        this.capturaAlPaso=false;
    }

    /**
    *Metodo que obtiene el nombre de la pieza como una cadena de texto
    * @return nombre
    */
    public String obtenerNombre(){
        return nombre;
    }

    /**
    *Metodo que obtiene el numero del Jugador de la pieza
    * @return numeroJugador
    */
    public int obtenerNumeroJugador(){
        return numeroJugador;
    }

    /**
    *Metodo que obtiene la fila de la Pieza como un numero entero
    * @return fila
    */
    public int obtenerFila(){
        return fila;
    }

    /**
    *Metodo que obtiene la columna de la Pieza como un numero entero
    * @return columna
    */
    public int obtenerColumna(){
        return columna;
    }

    /**
    *Metodo que obtiene el jugador de la Pieza
    * @return jugadorDeLaPieza
    */
    public Jugador obtenerJugadorDeLaPieza(){
        return jugadorDeLaPieza;
    }


    /**
    *Metodo que obtiene el numero de movimientos de la pieza
    * @return numeroMovimientos
    */
    public int obtenerNumeroMovimientos(){
        return numeroMovimientos ;
    }

    /**
    *Metodo que obtiene la torre a enrocar
    * @see Rey
    * @return Torre
    */
    public Torre obtenerTorreAEnrocar(){
        return torreEnroque;
    }

    /**
    *Metodo que obtiene el tipo de enroque
    * @see Rey
    * @return tipoEnroque -- 1 si es largo y 2 si es corto
    */
    public int obtenerTipoDeEnroque(){
        return tipoEnroque;
    }

    /**
    *Metodo que obtiene el peon para eliminar en captura al paso
    * @see Peon
    * @return peonEliminado
    */
    public Peon obtenerPeonEliminado(){
        return peonEliminado;
    }

    /**
    *Metodo que obtiene el turno donde el Peon se movio dos escaques si es que lo hizo
    * @see Peon
    * @return turnoPeonDosEscaques
    */
    public int obtenerTurnoPeonDosEscaques(){
        return turnoPeonDosEscaques;
    }

    /**
    *Metodo que obtiene un booleado verdadero si es posible enrocar y falso en caso contrario
    * @see Rey
    * @return enroque -- posibilidad de enrocar
    */
    public boolean esPosibleEnrocar(){
        return enroque;
    }

    /**
    *Metodo que obtiene un booleado verdadero si es posible capturar al paso y falso en caso contrario
    * @see Peon
    * @return capturaAlPaso -- posibilidad de capturar al paso
    */
    public boolean esPosibleCapturaAlPaso(){
        return capturaAlPaso;
    }

    /**
    *Metodo que valida si una pieza esta en la posicion pasada como parametro
    * @param fila -- fila donde se verificara la posicion
    * @param columna -- columna donde se verificara la posicion
    * @return boolean -- true si la Pieza se encuentra en esta posicion, false en caso contrario
    */
    public boolean validarCoordenadas(int fila,int columna){
        return this.fila==fila && this.columna==columna;
    }

    /**
    *Metodo abstracto donde las piezas que hereden esta clase validaran sus movimientos en el tablero, arrojando una excepcion si no es posible hacerlo
    * @param fila -- fila a la cual se movera la Pieza
    * @param columna -- columna a la cual se movera la Pieza
    * @param tablero -- tablero en el que la Pieza se movera
    * @throws MovimientoNoValidoExcepcion -- Se arroja si no es posible mover a esa posicion del tablero
    */
   	public abstract void validarMovimiento(int fila,int columna, Tablero tablero) throws MovimientoNoValidoExcepcion;

   	/**
     * Metodo que valida una captura o eliminacion, arrojando una excepcion si no es posible
     * @param piezaEliminada -- Pieza que se eliminara
     * @throws EliminacionInvalidaExcepcion -- Se arroja si no es posible capturar esta Pieza
     */
    public void validarEliminar(Pieza piezaEliminada) throws EliminacionInvalidaExcepcion{
        if(piezaEliminada==null){
            throw new EliminacionInvalidaExcepcion("No puedes eliminar una pieza que no existe");
        }
        else if(this.obtenerNumeroJugador()==piezaEliminada.obtenerNumeroJugador()){
            throw new EliminacionInvalidaExcepcion("No puedes eliminar esta pieza porque es tuya");
        }
    }

    @Override
    /**
     * Metodo que regresa una pieza como una representacion en una cadena de texto, no imprimiendo algo en caso de que la pieza sea null, imprimiendo su primer letra mayuscula si pertenece al primer jugador e imprimiendo su primera letra minuscula si pertenece al segundo jugador
     * @return Pieza como una cadena de texto
     */
    public String toString(){
        if(this==null)
            return " ";
        else
            return (numeroJugador==1) ? String.valueOf(nombre.charAt(0)) : String.valueOf(nombre.charAt(0)).toLowerCase();
    }

    public boolean equals(Pieza pieza){
        return this.nombre==pieza.obtenerNombre() && this.numeroJugador==pieza.obtenerNumeroJugador() && this.fila==pieza.obtenerFila() && this.columna==pieza.obtenerColumna() && this.numeroMovimientos==this.obtenerNumeroMovimientos();
    }
}
