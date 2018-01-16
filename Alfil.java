/**
 * Clase que simula el comportamiento de un Alfil, heredando el comportamiento de una pieza
 * @author Mauricio Chávez
 * @version 15012018
 * @see Pieza
 */
public class Alfil extends Pieza{
	public Alfil(int fila,int columna,Jugador jugadorDelAlfil){
		super("Alfil",fila,columna,jugadorDelAlfil);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(this.validarCoordenadas(fila,columna))
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a su posicion actual");
		else if(this.obtenerColumna()!=columna&&this.obtenerFila()!=fila){
			if(Math.pow(this.obtenerFila()-fila,2)!=Math.pow(this.obtenerColumna()-columna,2))
				throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion");
			else if(Math.pow(this.obtenerFila()-fila,2)==Math.pow(this.obtenerColumna()-columna,2)){
				if(this.obtenerColumna()<columna&&this.obtenerFila()>fila){//1
					int i=this.obtenerFila()-1;
					for(int j=this.obtenerColumna()+1;j<columna;j++){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()>columna&&this.obtenerFila()>fila){//2
					int i=this.obtenerFila()-1;
					for(int j=this.obtenerColumna()-1;j>columna;j--){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()>columna&&this.obtenerFila()<fila){//3
					int i=this.obtenerFila()+1;
					for(int j=this.obtenerColumna()-1;j>columna;j--){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion porque una pieza en el camino lo impide");
						i--;
					}
				}
				else if(this.obtenerColumna()<columna&&this.obtenerFila()<fila){//4
					int i=this.obtenerFila()+1;
					for(int j=this.obtenerColumna()+1;j<columna;j++){
						if(tablero.obtenerPieza(i,j)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion porque una pieza en el camino lo impide");
						i++;
					}
				}
			}
		}
		else
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu alfil a esta posicion");
	}

	@Override
	public String toString(){
		if(this==null) 
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♗" : "♝";
	}


}