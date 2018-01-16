/**
 * Excepcion para capturas no validas
 * @author Mauricio Chávez
 * @version 15012018
 */
public class CapturaNoValidaExcepcion extends Exception{

	/**
	* Excepcion para arrojar cuando una captura no es valida
	*/
	public CapturaNoValidaExcepcion(){
	}

	/**
	* Excepcion para arrojar cuando una captura no es valida, especificando el mensaje de la misma
	* @param msg -- El mensaje de la excepcion
	*/
	public CapturaNoValidaExcepcion(String msg) {
        super(msg);
    }
}