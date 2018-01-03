/**
 *
 * @author Mauricio Chávez
 */
public abstract class Pieza {
    private String nombre;
    private int numeroJugador;
    private int fila;
    private int columna;
    private static int numeroMovimientos;

    public Pieza(String nombre,int numeroJugador,int fila,int columna){
        this.nombre = nombre;
        this.numeroJugador = numeroJugador ;
        this.fila = fila;
        this.columna = columna;
        this.numeroMovimientos = 0;
    }

    public void asignarNombre(String nombre){
        this.nombre = nombre ;
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
            throw new EliminacionInvalidaExcepcion("No puedes eliminar esta pieza porque es tuya.");
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