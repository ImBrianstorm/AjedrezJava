/**
 * Clase que simula el comportamiento de una Dama, heredando el comportamiento de una pieza
 * @author Mauricio Chávez
 * @version 15012018
 * @see Pieza
 */

package ajedrez.juego.pieza;

import ajedrez.excepciones.MovimientoNoValidoExcepcion;
import ajedrez.juego.jugador.Jugador;
import ajedrez.juego.tablero.Tablero;

public class Dama extends Pieza{

	/**
	* Constructor que crea una Dama
	* @param fila -- fila donde se encuentra la Dama
	* @param columna -- columna donde se encuentra la Dama
	* @param jugadorDeLaDama -- jugador de la pieza
	*/
	public Dama(int fila,int columna,Jugador jugadorDeLaDama){
		super("Dama",fila,columna,jugadorDeLaDama);
	}

	/**
	* Metodo que verifica el movimiento de la Dama en un Tablero, donde arroja una excepcion si no es posible mover a la Dama
	* @see Tablero
	* @param fila -- fila donde se encuentra la Dama
	* @param columna -- columna donde se encuentra la Dama
	* @param Tablero -- tablero donde se movera a la Dama
	* @throws MovimientoNoValidoExcepcion -- Se arroja si el movimiento de la Dama no es valido
	*/
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
	/**
	* Metodo que regresa la representacion de la Dama en cadena de texto
	* @return String -- cadena del texto que representara a la Dama
	*/
	public String toString(){
		if(this==null)
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♕" : "♛";
	}

}
