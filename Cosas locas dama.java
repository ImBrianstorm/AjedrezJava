public void validarMovimiento(int columna, int fila, Pieza[][] arregloPiezas) throws MovimientoNoValidoExcepcion{
	
		Tablero tablero = new Tablero(arregloPiezas);

		if(columna==this.obtenerNumeroColumna()&&fila!=this.obtenerNumeroFila()){
			if(this.obtenerNumeroFila()>fila)
				for(int i=this.obtenerNumeroFila()-1;i>fila;i--){
					if(tablero.obtenerPieza(columna,i)!=null) 
						throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
				}
			else if(this.obtenerNumeroFila()<fila)
				for(int i=this.obtenerNumeroFila()+1;i<fila;i++){
					if(tablero.obtenerPieza(columna,i)!=null) 
						throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
				}
		}
		else if(columna!=this.obtenerNumeroColumna()&&fila==this.obtenerNumeroFila()){
			if(this.obtenerNumeroColumna()>columna)
				for(int i=this.obtenerNumeroColumna()-1;i>columna;i--){
					if(tablero.obtenerPieza(i,fila)!=null) 
						throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
				}
			else if(this.obtenerNumeroColumna()<columna)
				for(int i=this.obtenerNumeroColumna()+1;i<columna;i++){
					if(tablero.obtenerPieza(i,fila)!=null) 
						throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
				}
		}
		else if(columna==this.obtenerNumeroColumna()&&fila==this.obtenerNumeroFila()){
			throw new MovimientoNoValidoExcepcion("Movimiento no permitido: No puedes desplazarte a tu posicion actual.");
		}
		else if(columna!=this.obtenerNumeroColumna()&&fila!=this.obtenerNumeroFila()){
			if((columna-this.obtenerNumeroColumna())!=(fila-this.obtenerNumeroFila()))
				throw new MovimientoNoValidoExcepcion("Movimiento no permitido: No puedes desplazarte a esa posicion");
			else{
				if(columna>this.obtenerNumeroColumna()&&fila<this.obtenerNumeroFila()){
					int j=this.obtenerNumeroFila()-1;
					for(int i=this.obtenerNumeroColumna()+1;i<columna;i++){
						if(arregloPiezas[j-1][i-1]!=null)
							throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
						j--;
					}
				}
				else if(columna>this.obtenerNumeroColumna()&&fila>this.obtenerNumeroFila()){
					int j=this.obtenerNumeroFila()+1;
					for(int i=this.obtenerNumeroColumna()+1;i<columna;i++){
						if(arregloPiezas[j-1][i-1]!=null)
							throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
						j++;
					}
				}
				else if(columna<this.obtenerNumeroColumna()&&fila>this.obtenerNumeroFila()){
					int j=this.obtenerNumeroFila()+1;
					for(int i=this.obtenerNumeroColumna()-1;i>columna;i--){
						if(arregloPiezas[j-1][i-1]!=null)
							throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
						j++;
					}
				}
				else if(columna<this.obtenerNumeroColumna()&&fila<this.obtenerNumeroFila()){
					int j=this.obtenerNumeroFila()-1;
					for(int i=this.obtenerNumeroColumna()-1;i>columna;i--){
						if(arregloPiezas[j-1][i-1]!=null)
							throw new MovimientoNoValidoExcepcion("Movimiento no permitido: existe una pieza en el camino que impide el movimiento");
						j--;
					}
				}
			}
		}
			
	}