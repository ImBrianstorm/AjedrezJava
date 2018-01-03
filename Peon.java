public class Peon extends Pieza{
	public Peon(int numeroJugador,int fila,int columna){
		super("Peon",numeroJugador,fila,columna);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(columna!=this.obtenerColumna())
			throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
		else
			if(this.obtenerNumeroJugador()==1){
				if(this.obtenerFila()<=fila)
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				else{
					if(this.obtenerFila()-fila<=2){
						if(this.obtenerNumeroMovimientos()!=0)
							throw new MovimientoNoValidoExcepcion("Solo puedes mover dos posiciones al frente en el primer movimiento de tu peon");
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				}
			}
			else{
				if(this.obtenerFila()>=fila)
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				else{
					if(fila-this.obtenerFila()<=2){
						if(this.obtenerNumeroMovimientos()!=0)
							throw new MovimientoNoValidoExcepcion("Solo puedes mover dos posiciones al frente en el primer movimiento de tu peon");
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				}
			}
	}
}