public class Caballo extends Pieza{
	public Caballo(int fila,int columna,Jugador jugadorDelCaballo){
		super("Caballo",fila,columna,jugadorDelCaballo);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(((columna==(this.obtenerColumna()+1) && (fila==(this.obtenerFila()+2) || fila==(this.obtenerFila()-2)))||(columna==(this.obtenerColumna()-1) && (fila==(this.obtenerFila()+2) || fila==(this.obtenerFila()-2)))||(columna==(this.obtenerColumna()+2) && (fila==(this.obtenerFila()+1) || fila==(this.obtenerFila()-1)))||(columna==(this.obtenerColumna()-2) && (fila==(this.obtenerFila()+1) || fila==(this.obtenerFila()-1))))==false)
			throw new MovimientoNoValidoExcepcion("No puedes mover a tu caballo a esta posicion");
	}

	@Override
	public String toString(){
		if(this==null) 
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♘" : "♞";
	}
}