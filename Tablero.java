public class Tablero{

	private Pieza[][] tablero;

	public Tablero(){
		tablero = new Pieza[6][6];
	}

	public Tablero(Pieza[][] arregloPiezas){
		arregloPiezas = tablero;
	}

	public Pieza[][] obtenerArregloPiezas(){
		return tablero;
	}

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(1,1,6);
		tablero[5][1] = new Caballo(1,2,6);
		tablero[5][2] = new Dama(1,3,6);
		tablero[5][3] = new Rey(1,4,6);
		tablero[5][4] = new Caballo(1,5,6);
		tablero[5][5] = new Torre(1,6,6);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(1,i+1,5);
		
		//JUGADOR 2

		tablero[0][0] = new Torre(2,1,1);
		tablero[0][1] = new Caballo(2,2,1);
		tablero[0][2] = new Dama(2,3,1);
		tablero[0][3] = new Rey(2,4,1);
		tablero[0][4] = new Caballo(2,5,1);
		tablero[0][5] = new Torre(2,6,1);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,i+1,2);
	}

	public String toString(){
        return "      A   B   C   D   E   F  \n" +
               "    + — + — + — + — + — + — +\n" +
               " 1  | " + this.piezasFilaToString(1) +
               "    + — + — + — + — + — + — +\n" +
               " 2  | " + this.piezasFilaToString(2) +
               "    + — + — + — + — + — + — +\n" +
               " 3  | " + this.piezasFilaToString(3) +
               "    + — + — + — + — + — + — +\n" +
               " 4  | " + this.piezasFilaToString(4) +
               "    + — + — + — + — + — + — +\n" +
               " 5  | " + this.piezasFilaToString(5) +
               "    + — + — + — + — + — + — +\n" +
               " 6  | " + this.piezasFilaToString(6) +
               "    + — + — + — + — + — + — +";
     }

	private String piezasFilaToString(int fila){
	    String string = "";
	    for(int i=0;i<=5;i++){
	    	if(tablero[fila-1][i]==null) string+= "  | ";
	    	else string += tablero[fila-1][i].toString() + " | ";
	    } 
	    string += "\n";

	    return string;
	}

	public void eliminarPieza(Pieza pieza,Pieza piezaEliminada) throws EliminacionInvalidaExcepcion{
		try{
			pieza.validarEliminar(piezaEliminada);
			tablero[piezaEliminada.obtenerNumeroFila()-1][piezaEliminada.obtenerNumeroColumna()-1] = null;
		}catch(EliminacionInvalidaExcepcion e){
			System.out.println(e);
		}
	}

	public void quitarPiezaTablero(Pieza pieza){
		tablero[pieza.obtenerNumeroFila()-1][pieza.obtenerNumeroColumna()-1] = null;
	}

	public Pieza obtenerPieza(int columna,int fila){
		return tablero[fila-1][columna-1];
	}

	public void moverPieza(Pieza pieza, int columna, int fila){
		try{
			pieza.validarMovimiento(columna,fila,tablero);
			if(obtenerPieza(columna,fila) != null){
				pieza.validarEliminar(obtenerPieza(columna,fila));
				eliminarPieza(pieza,obtenerPieza(columna,fila));
			}
			quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarCoordenadas(columna,fila);
			
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