/**
 * Excepcion para limite de registros alcanzado
 * @author Mauricio Chávez
 * @version 15012018
 */
public class LimiteRegistrosAlcanzadoExcepcion extends Exception{

	/**
	* Excepcion para arrojar cuando se alcanzo el limite de registros
	*/
	public LimiteRegistrosAlcanzadoExcepcion(){
	}

	/**
	* Excepcion para arrojar cuando se alcanzo el limite de registros, especificando el mensaje de la misma
	* @param msg -- El mensaje de la excepcion
	*/
	public LimiteRegistrosAlcanzadoExcepcion(String msg) {
        super(msg);
    }
}