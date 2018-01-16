/**
 * Clase que simula el comportamiento de un Peon, heredando el comportamiento de una pieza
 * @author Mauricio Chávez
 * @version 15012018
 * @see Pieza
 */
public class Peon extends Pieza{

	/**
	* Constructor que crea un Peon
	* @param fila -- fila donde se encuentra el Peon
	* @param columna -- columna donde se encuentra el Peon
	* @param jugadorDelPeon -- jugador de la pieza
	*/
	public Peon(int fila,int columna,Jugador jugadorDelPeon){
		super("Peon",fila,columna,jugadorDelPeon);
	}

	/**
	* Metodo que verifica el movimiento del Peon en un Tablero, donde arroja una excepcion si no es posible mover al Peon
	* @see Tablero
	* @param fila -- fila donde se encuentra el Peon
	* @param columna -- columna donde se encuentra el Peon
	* @param Tablero -- tablero donde se movera al Peon
	* @throws MovimientoNoValidoExcepcion -- Se arroja si el movimiento del Peon no es valido
	*/
	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(columna!=this.obtenerColumna()){
			if(this.obtenerNumeroJugador()==1){
				if((fila==this.obtenerFila()-1&&columna==this.obtenerColumna()-1)&&(tablero.obtenerPieza(fila,columna)==null)){//MOVIMIENTO ARRIBA IZQUIERDA
					if(tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1)!=null&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1).obtenerNumeroJugador()!=this.obtenerNumeroJugador()&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1).obtenerNumeroMovimientos()==1&&this.obtenerFila()==4){
						this.habilitarCapturaAlPaso();
						peonEliminado = (Peon) tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1);
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion a menos que sea para eliminar a una pieza");
				}
				else if((fila==this.obtenerFila()-1&&columna==this.obtenerColumna()+1)&&(tablero.obtenerPieza(fila,columna)==null)){
					if(tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1)!=null&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1).obtenerNumeroJugador()!=this.obtenerNumeroJugador()&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1).obtenerNumeroMovimientos()==1&&this.obtenerFila()==4){
						this.habilitarCapturaAlPaso();
						peonEliminado = (Peon) tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1);
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion a menos que sea para eliminar a una pieza");
				}
				else if(!(fila==this.obtenerFila()-1&&columna==this.obtenerColumna()-1)&&!(fila==this.obtenerFila()-1&&columna==this.obtenerColumna()+1)) //MOVIMIENTOS EN DIAGONAL INVALIDOS a menos que sea diagonal mas uno
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
			}
			else{
				if((fila==this.obtenerFila()+1&&columna==this.obtenerColumna()-1)&&(tablero.obtenerPieza(fila,columna)==null)){
					if(tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1)!=null&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1).obtenerNumeroJugador()!=this.obtenerNumeroJugador()&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1).obtenerNumeroMovimientos()==1&&this.obtenerFila()==tablero.obtenerNumeroFilas()-3){
						this.habilitarCapturaAlPaso();
						peonEliminado = (Peon) tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()-1);
					}
				else
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion a menos que sea para eliminar a una pieza");
				}
				else if((fila==this.obtenerFila()+1&&columna==this.obtenerColumna()+1)&&(tablero.obtenerPieza(fila,columna)==null)){
					if(tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1)!=null&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1).obtenerNumeroJugador()!=this.obtenerNumeroJugador()&&tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1).obtenerNumeroMovimientos()==1&&this.obtenerFila()==tablero.obtenerNumeroFilas()-3){
						this.habilitarCapturaAlPaso();
						peonEliminado = (Peon) tablero.obtenerPieza(this.obtenerFila(),this.obtenerColumna()+1);
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion a menos que sea para eliminar a una pieza");
				}
				else if(!(fila==this.obtenerFila()+1&&columna==this.obtenerColumna()-1)&&!(fila==this.obtenerFila()+1&&columna==this.obtenerColumna()+1))
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
			}
		}
		else
			if(this.obtenerNumeroJugador()==1){
				if(this.obtenerFila()<=fila)
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				else{
					if(this.obtenerFila()-fila==2){
						if(this.obtenerNumeroMovimientos()!=0)
							throw new MovimientoNoValidoExcepcion("Solo puedes mover dos posiciones al frente en el primer movimiento de tu peon");
						else if(this.obtenerNumeroMovimientos()==0){
							turnoPeonDosEscaques = this.obtenerJugadorDeLaPieza().obtenerTurnos();
						}
						else if(tablero.obtenerPieza(fila+1,columna)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes saltar una pieza");
					}
					else if(this.obtenerFila()-fila!=1 && this.obtenerFila()-fila!=2)
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				}
			}
			else{
				if(this.obtenerFila()>=fila)
					throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				else{
					if(fila-this.obtenerFila()==2){
						if(this.obtenerNumeroMovimientos()!=0)
							throw new MovimientoNoValidoExcepcion("Solo puedes mover dos posiciones al frente en el primer movimiento de tu peon");
						else if(this.obtenerNumeroMovimientos()==0){
							turnoPeonDosEscaques = this.obtenerJugadorDeLaPieza().obtenerTurnos();
						}
						else if(tablero.obtenerPieza(fila-1,columna)!=null)
							throw new MovimientoNoValidoExcepcion("No puedes saltar una pieza");
					}
					else if(fila-this.obtenerFila()!=1 && fila-this.obtenerFila()!=2)
						throw new MovimientoNoValidoExcepcion("No puedes mover a tu peon a esta posicion");
				}
			}
	}

	@Override
	/**
     * Metodo que valida si es posible eliminar a una pieza, arrojando una excepcion si no es posible
     * @param piezaEliminada -- La pieza a la que se va a eliminar
     * @throws EliminacionInvalidaExcepcion -- Se arroja si no es posible eliminar
     */
    public void validarEliminar(Pieza piezaEliminada) throws EliminacionInvalidaExcepcion{
    	if(piezaEliminada==null){
            throw new EliminacionInvalidaExcepcion("No puedes eliminar una pieza que no existe");
        }
        else if(this.obtenerNumeroJugador()==piezaEliminada.obtenerNumeroJugador()){
            throw new EliminacionInvalidaExcepcion("No puedes eliminar esta pieza porque es tuya.");
        }
        else if(piezaEliminada.obtenerColumna()==this.obtenerColumna()){
        	throw new EliminacionInvalidaExcepcion("Los peones no pueden eliminar verticalmente");
        }
    }

    @Override
    /**
	* Metodo que regresa la representacion del Peon en cadena de texto
	* @return String -- cadena del texto que representara al Peon
	*/
	public String toString(){
		if(this==null) 
            return " ";
        else
            return (super.obtenerNumeroJugador()==1) ? "♙" : "♟";
	}
}