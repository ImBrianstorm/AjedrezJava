public class Juego extends Tablero{
	public Juego() throws TamañoNoSoportadoExcepcion{
		super(8,8);
	}

	private Pieza[][] tablero = super.obtenerArregloPiezas();

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[7][0] = new Torre(1,8,1);
		tablero[7][1] = new Caballo(1,8,2);
		tablero[7][2] = new Alfil(1,8,3);
		tablero[7][3] = new Dama(1,8,4);
		tablero[7][4] = new Rey(1,8,5);
		tablero[7][5] = new Alfil(1,8,6);
		tablero[7][6] = new Caballo(1,8,7);
		tablero[7][7] = new Torre(1,8,8);

		for(int i=0;i<=7;i++) tablero[6][i] = new Peon(1,7,i+1);
		
		//JUGADOR 2

		tablero[0][0] = new Torre(2,1,1);
		tablero[0][1] = new Caballo(2,1,2);
		tablero[0][2] = new Alfil(2,1,3);
		tablero[0][3] = new Dama(2,1,4);
		tablero[0][4] = new Rey(2,1,5);
		tablero[0][5] = new Alfil(2,1,6);
		tablero[0][6] = new Caballo(2,1,7);
		tablero[0][7] = new Torre(2,1,8);

		for(int i=0;i<=7;i++) tablero[1][i] = new Peon(2,2,i+1);
	}

	private void enroque(Pieza rey, Pieza torre,int tipoEnroque){
		if(tipoEnroque==1){
			super.quitarPiezaTablero(torre);
			super.agregarPieza(torre,rey.obtenerFila(),rey.obtenerColumna()-1);
		}
		else if(tipoEnroque==2){
			super.quitarPiezaTablero(torre);
			super.agregarPieza(torre,rey.obtenerFila(),rey.obtenerColumna()+1);
		}
	}

	private void capturaAlPaso(Pieza peonAtacado){
		super.quitarPiezaTablero(peonAtacado);
	}


	public void moverPieza(Pieza pieza,int fila,int columna){
		try{
			pieza.validarMovimiento(fila,columna,this);
			if(pieza.esPosibleEnrocar()){
				enroque(pieza,pieza.obtenerTorreAEnrocar(),pieza.obtenerTipoDeEnroque());
				pieza.deshabilitarEnroque();
				pieza.eliminarTorreAEnrocar();
			}
			if(pieza.esPosibleCapturaAlPaso()){
				capturaAlPaso(pieza.obtenerPeonEliminado());
				pieza.deshabilitarCapturaAlPaso();
				pieza.eliminarPeonEliminado();
			}
			if(obtenerPieza(fila,columna) != null){
				pieza.validarEliminar(obtenerPieza(fila,columna));
				super.eliminarPieza(pieza,obtenerPieza(fila,columna));
			}
			super.quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarPosicion(fila,columna);
			tablero[fila-1][columna-1].sumarMovimiento();

			
		}catch(MovimientoNoValidoExcepcion e){
			System.out.println(e);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Movimiento no permitido: está fuera del tablero");
		}catch(NullPointerException e){
			System.out.println("Movimiento no permitido: no puedes mover una pieza que no existe");
		}catch(EliminacionInvalidaExcepcion e){
			System.out.println(e);
		}
	}
}