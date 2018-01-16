/**
 * Excepcion para movimientos no validos
 * @author Mauricio Chávez
 * @version 15012018
 */
public class MovimientoNoValidoExcepcion extends Exception{

	/**
	* Excepcion para arrojar cuando un movimiento no es valido
	*/
	public MovimientoNoValidoExcepcion(){
	}

	/**
	* Excepcion para arrojar cuando un movimiento no es valido, especificando el mensaje de la misma
	* @param msg -- El mensaje de la excepcion
	*/
	public MovimientoNoValidoExcepcion(String msg) {
        super(msg);
    }
}