/**
 *
 * @author Mauricio Chávez
 * @version 15012018
 */
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

    public void asignarNombre(String nombre){
        this.nombre = nombre ;
    }

    public void asignarJugador(Jugador jugadorDeLaPieza){
        this.jugadorDeLaPieza = jugadorDeLaPieza;
    }

    public void habilitarEnroque(){
        this.enroque=true;
    }

    public void deshabilitarEnroque(){
        this.enroque=false;
    }

    public void habilitarCapturaAlPaso(){
        this.capturaAlPaso=true;
    }

    public void deshabilitarCapturaAlPaso(){
        this.capturaAlPaso=false;
    }

    public boolean esPosibleEnrocar(){
        return enroque;
    }

    public boolean esPosibleCapturaAlPaso(){
        return capturaAlPaso;
    }

    public Torre obtenerTorreAEnrocar(){
        return torreEnroque;
    }

    public void eliminarTorreAEnrocar(){
        torreEnroque = null;
    }

    public int obtenerTipoDeEnroque(){//1 largo 2 corto
        return tipoEnroque;
    }

    public Peon obtenerPeonEliminado(){
        return peonEliminado;
    }

    public int obtenerTurnoPeonDosEscaques(){
        return turnoPeonDosEscaques;
    }

    public void eliminarPeonEliminado(){
        peonEliminado = null;
    }

    public void asignarNumeroJugador(int numeroJugador){
        this.numeroJugador = numeroJugador;
    }

    public void asignarPosicion(int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public void sumarMovimiento(){
        this.numeroMovimientos++;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public int obtenerNumeroJugador(){
        return numeroJugador;
    }

    public int obtenerColumna(){
        return columna;
    }

    public int obtenerFila(){
        return fila;
    }

    public int obtenerNumeroMovimientos(){
    	return numeroMovimientos ;
    }

    public Jugador obtenerJugadorDeLaPieza(){
        return jugadorDeLaPieza;
    }

    public boolean validarCoordenadas(int fila,int columna){
        return this.fila==fila && this.columna==columna;
    }

   	public abstract void validarMovimiento(int fila,int columna, Tablero tablero) throws MovimientoNoValidoExcepcion;

   	/**
     *
     * @param piezaEliminada
     * @throws EliminacionInvalidaExcepcion
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