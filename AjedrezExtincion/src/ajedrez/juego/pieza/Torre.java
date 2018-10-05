/**
 * Clase que simula el comportamiento de una Torre, heredando el comportamiento de una pieza
 * @author Mauricio Chávez
 * @version 15012018
 * @see Pieza
 */

package ajedrez.juego.pieza;

import ajedrez.excepciones.MovimientoNoValidoExcepcion;
import ajedrez.juego.jugador.Jugador;
import ajedrez.juego.tablero.Tablero;

public class Torre extends Pieza{

	/**
	* Constructor que crea una Torre
	* @param fila -- fila donde se encuentra la Torre
	* @param columna -- columna donde se encuentra la Torre
	* @param jugadorDeLaTorre -- jugador de la pieza
	*/
	public Torre(int fila,int columna,Jugador jugadorDeLaTorre){
		super("Torre",fila,columna,jugadorDeLaTorre);
	}

	/**
	* Metodo que verifica el movimiento de la Torre en un Tablero, donde arroja una excepcion si no es posible mover a la Torre
	* @see Tablero
	* @param fila -- fila donde se encuentra la Torre
	* @param columna -- columna donde se encuentra la Torre
	* @param Tablero -- tablero donde se movera a la Torre
	* @throws MovimientoNoValidoExcepcion -- Se arroja si el movimiento de la Torre no es valido
	*/
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

	@Override
	/**
	* Metodo que regresa la representacion de la Torre en cadena de texto
	* @return String -- cadena del texto que representara a la Torre
	*/
	public String toString(){
		if(this==null)
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♖" : "♜";
	}
}
