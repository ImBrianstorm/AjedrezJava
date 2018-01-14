public class Torre extends Pieza{
	public Torre(int numeroJugador,int fila,int columna,Jugador jugadorDeLaTorre){
		super("Torre",numeroJugador,fila,columna,jugadorDeLaTorre);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(this.validarCoordenadas(fila,columna))
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a su posicion actual");
		else if(this.obtenerColumna()!=columna&&this.obtenerFila()!=fila)
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a esta posicion");
		else if(this.obtenerColumna()==columna){
			if(this.obtenerFila()>fila){
				for(int i=this.obtenerFila()-1;i>fila;i--){
					if(tablero.obtenerPieza(i,columna)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
			else{
				for(int i=this.obtenerFila()+1;i<fila;i++){
					if(tablero.obtenerPieza(i,columna)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
		}
		else if(this.obtenerFila()==fila){
			if(this.obtenerColumna()>columna){
				for(int i=this.obtenerColumna()-1;i>columna;i--){
					if(tablero.obtenerPieza(fila,i)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
			else{
				for(int i=this.obtenerColumna()+1;i<columna;i++){
					if(tablero.obtenerPieza(fila,i)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu torre a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
		}
	}
}