/**
 * Excepcion para tamaños no soportados
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.excepciones;

public class TamañoNoSoportadoExcepcion extends Exception{

	/**
	* Excepcion para arrojar cuando el tamaño no esta soportado por un Tablero
	*/
	public TamañoNoSoportadoExcepcion(){
	}

	/**
	* Excepcion para arrojar cuando el tamaño no esta soportado por un Tablero, especificando el mensaje de la misma
	* @param msg -- El mensaje de la excepcion
	*/
	public TamañoNoSoportadoExcepcion(String msg) {
        super(msg);
    }
}
