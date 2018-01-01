public class Caballo extends Pieza{
	public Caballo(int jugador,int columna,int fila){
		super("Caballo",jugador,columna,fila);
	}

	public void validarMovimiento(int columna,int fila,Pieza[][] arregloPiezas) throws MovimientoNoValidoExcepcion{
		if(((columna==(this.obtenerNumeroColumna()+1) && (fila==(this.obtenerNumeroFila()+2) || fila==(this.obtenerNumeroFila()-2)))||(columna==(this.obtenerNumeroColumna()-1) && (fila==(this.obtenerNumeroFila()+2) || fila==(this.obtenerNumeroFila()-2)))||(columna==(this.obtenerNumeroColumna()+2) && (fila==(this.obtenerNumeroFila()+1) || fila==(this.obtenerNumeroFila()-1)))||(columna==(this.obtenerNumeroColumna()-2) && (fila==(this.obtenerNumeroFila()+1) || fila==(this.obtenerNumeroFila()-1))))==false)
			throw new MovimientoNoValidoExcepcion("Este movimiento no está permitido");
	}

}