public class Rey extends Pieza{
	public Rey(int numeroJugador,int fila,int columna){
		super("Rey",numeroJugador,fila,columna);
	}

	public void validarMovimiento(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(this.validarCoordenadas(fila,columna)){
			throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu rey a su posicion actual");
		}
		else if(this.obtenerColumna()!=columna&&this.obtenerFila()!=fila){
			if(Math.pow(this.obtenerFila()-fila,2)!=1||Math.pow(this.obtenerFila()-fila,2)!=1) 
				throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu rey a esta posicion");
		}
		else if(this.obtenerColumna()==columna&&this.obtenerFila()!=fila){
			if(fila-this.obtenerFila()>1||fila-this.obtenerFila()<-1)
				throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu rey a esta posicion");
		}
		else if(this.obtenerColumna()!=columna&&this.obtenerFila()==fila){//HORIZONTAL -- ENROQUE
			if(Math.pow(columna-this.obtenerColumna(),2)!=1){
				if(Math.pow(columna-this.obtenerColumna(),2)==4)
					this.validarEnroque(fila,columna,tablero);
				else
					throw new MovimientoNoValidoExcepcion("No puedes desplazar a tu rey a esta posicion");
			}
		}
	}

	public void validarEnroque(int fila,int columna,Tablero tablero) throws MovimientoNoValidoExcepcion{
		if(this.obtenerNumeroMovimientos()!=0)
			throw new MovimientoNoValidoExcepcion("Solo puedes hacer enroque en el primer movimiento del rey");
		else{
			if(this.validarCoordenadas(fila,columna))
				throw new MovimientoNoValidoExcepcion("No puedes hacer enroque sin moverte de tu posicion");
			else if(this.obtenerFila()!=fila){
				throw new MovimientoNoValidoExcepcion("No puedes hacer enroque desplazandote a esa posicion");
			}
			else if(Math.pow(this.obtenerColumna()-columna,2)!=4){
				throw new MovimientoNoValidoExcepcion("No puedes hacer enroque desplazandote a esa posicion");
			}
			else if(this.obtenerColumna()>columna){
				if(tablero.obtenerPieza(this.obtenerFila(),1)==null){
					throw new MovimientoNoValidoExcepcion("No es posible hacer enroque porque no hay una torre hacia esa posicion");
				}
				else if(tablero.obtenerPieza(this.obtenerFila(),1).obtenerNumeroMovimientos()!=0){
					throw new MovimientoNoValidoExcepcion("Solo puedes enrocar si la torre a usar no se ha movido");
				}
				else{
					if(tablero.obtenerPieza(this.obtenerFila(),1).obtenerNombre().equals("Torre")&&tablero.obtenerPieza(this.obtenerFila(),1).obtenerNumeroJugador()==this.obtenerNumeroJugador()){
						for(int i=this.obtenerColumna()-1;i>1;i--){
							if(tablero.obtenerPieza(fila,i)!=null){
								throw new MovimientoNoValidoExcepcion("El enroque no es posible porque hay una pieza entre tu torre y tu rey");
							}
						}
						this.habilitarEnroque();
						torreEnroque = (Torre) tablero.obtenerPieza(this.obtenerFila(),1);
						tipoEnroque = 1;
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes hacer enroque porque hace falta una torre aliada");
				}
			}
			else if(this.obtenerColumna()<columna){
				if(tablero.obtenerPieza(this.obtenerFila(),6)==null){
					throw new MovimientoNoValidoExcepcion("No es posible hacer enroque porque no hay una torre hacia esa posicion");
				}
				else if(tablero.obtenerPieza(this.obtenerFila(),6).obtenerNumeroMovimientos()!=0){
					throw new MovimientoNoValidoExcepcion("Solo puedes enrocar si la torre a usar no se ha movido");
				}
				else{
					if(tablero.obtenerPieza(this.obtenerFila(),6).obtenerNombre().equals("Torre")&&tablero.obtenerPieza(this.obtenerFila(),6).obtenerNumeroJugador()==this.obtenerNumeroJugador()){
						for(int i=this.obtenerColumna()+1;i<tablero.obtenerNumeroColumnas();i++){
							if(tablero.obtenerPieza(fila,i)!=null){
								throw new MovimientoNoValidoExcepcion("El enroque no es posible porque hay una pieza entre tu torre y tu rey");
							}
						}
						this.habilitarEnroque();
						torreEnroque = (Torre) tablero.obtenerPieza(this.obtenerFila(),tablero.obtenerNumeroColumnas());
						tipoEnroque = 2;
					}
					else
						throw new MovimientoNoValidoExcepcion("No puedes hacer enroque porque hace falta una torre aliada");
				}
			}
		}
	}
}

//TOMA PEON AL PASO :C