public class Dama extends Pieza{
	public Dama(int fila,int columna,Jugador jugadorDeLaDama){
		super("Dama",fila,columna,jugadorDeLaDama);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(this.validarCoordenadas(fila,columna))
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a su posicion actual");
		else if(this.obtenerColumna()!=columna&&this.obtenerFila()!=fila){
			if(Math.pow(this.obtenerFila()-fila,2)!=Math.pow(this.obtenerColumna()-columna,2))
				throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion");
			else if(Math.pow(this.obtenerFila()-fila,2)==Math.pow(this.obtenerColumna()-columna,2)){
				if(this.obtenerColumna()<columna&&this.obtenerFila()>fila){//1
					int i=this.obtenerFila()-1;
					for(int j=this.obtenerColumna()+1;j<columna;j++){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()>columna&&this.obtenerFila()>fila){//2
					int i=this.obtenerFila()-1;
					for(int j=this.obtenerColumna()-1;j>columna;j--){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()>columna&&this.obtenerFila()<fila){//3
					int i=this.obtenerFila()+1;
					for(int j=this.obtenerColumna()-1;j>columna;j--){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()<columna&&this.obtenerFila()<fila){//4
					int i=this.obtenerFila()+1;
					for(int j=this.obtenerColumna()+1;j<columna;j++){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
						i++;
					}
				}
			}
		}
		else if(this.obtenerColumna()==columna){
			if(this.obtenerFila()>fila){
				for(int i=this.obtenerFila()-1;i>fila;i--){
					if(tablero.obtenerPieza(i,columna)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
			else{
				for(int i=this.obtenerFila()+1;i<fila;i++){
					if(tablero.obtenerPieza(i,columna)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
		}
		else if(this.obtenerFila()==fila){
			if(this.obtenerColumna()>columna){
				for(int i=this.obtenerColumna()-1;i>columna;i--){
					if(tablero.obtenerPieza(fila,i)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
			else{
				for(int i=this.obtenerColumna()+1;i<columna;i++){
					if(tablero.obtenerPieza(fila,i)!=null){
						throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu dama a esta posicion porque una pieza en el camino lo impide");
					}
				}
			}
		}
	}

	@Override
	public String toString(){
		if(this==null) 
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♕" : "♛";
	}

}