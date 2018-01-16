/**
 * Clase que simula el comportamiento de un Caballo, heredando el comportamiento de una pieza
 * @author Mauricio Chávez
 * @version 15012018
 * @see Pieza
 */
public class Caballo extends Pieza{

	/**
	* Constructor que crea un Caballo
	* @param fila -- fila donde se encuentra el Caballo
	* @param columna -- columna donde se encuentra el Caballo
	* @param jugadorDelCaballo -- jugador de la pieza
	*/
	public Caballo(int fila,int columna,Jugador jugadorDelCaballo){
		super("Caballo",fila,columna,jugadorDelCaballo);
	}

	/**
	* Metodo que verifica el movimiento del Caballo en un Tablero, donde arroja una excepcion si no es posible mover al Caballo
	* @see Tablero
	* @param fila -- fila donde se encuentra el Caballo
	* @param columna -- columna donde se encuentra el Caballo
	* @param Tablero -- tablero donde se movera al Caballo
	* @throws MovimientoNoValidoExcepcion -- Se arroja si el movimiento del Caballo no es valido
	*/
	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(((columna==(this.obtenerColumna()+1) && (fila==(this.obtenerFila()+2) || fila==(this.obtenerFila()-2)))||(columna==(this.obtenerColumna()-1) && (fila==(this.obtenerFila()+2) || fila==(this.obtenerFila()-2)))||(columna==(this.obtenerColumna()+2) && (fila==(this.obtenerFila()+1) || fila==(this.obtenerFila()-1)))||(columna==(this.obtenerColumna()-2) && (fila==(this.obtenerFila()+1) || fila==(this.obtenerFila()-1))))==false)
			throw new MovimientoNoValidoExcepcion("No puedes mover a tu caballo a esta posicion");
	}

	@Override
	/**
	* Metodo que regresa la representacion del Caballo en cadena de texto
	* @return String -- cadena del texto que representara al Caballo
	*/
	public String toString(){
		if(this==null) 
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♘" : "♞";
	}
}