/**
 * Excepcion para tipos no validos
 * @author Mauricio Chávez
 * @version 15012018
 */
public class TipoNoValidoExcepcion extends Exception{

	/**
	* Excepcion para arrojar cuando un tipo no es valido
	*/
	public TipoNoValidoExcepcion(){
	}

	/**
	* Excepcion para arrojar cuando un tipo no es valido, especificando el mensaje de la misma
	* @param msg -- El mensaje de la excepcion
	*/	
	public TipoNoValidoExcepcion(String msg) {
        super(msg);
    }
}