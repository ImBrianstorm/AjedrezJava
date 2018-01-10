public class Peon extends Pieza{
	public Peon(int numeroJugador,int fila,int columna){
		super("Peon",numeroJugador,fila,columna);
	}

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
     *
     * @param piezaEliminada
     * @throws EliminacionInvalidaExcepcion
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
}