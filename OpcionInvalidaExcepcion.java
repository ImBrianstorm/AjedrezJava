public class OpcionInvalidaExcepcion extends Exception{
	public OpcionInvalidaExcepcion(){
		super("No existe una accion para esta opcion");
	}
}