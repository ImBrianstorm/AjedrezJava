/**
 *
 * @author Mauricio Chávez
 */
public class LimiteRegistrosAlcanzadoExcepcion extends Exception{

	public LimiteRegistrosAlcanzadoExcepcion(){
	}

	public LimiteRegistrosAlcanzadoExcepcion(String msg) {
        super(msg);
    }
}