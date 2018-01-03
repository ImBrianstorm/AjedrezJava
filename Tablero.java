/**
 *
 * @author Mauricio Chávez
 */
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

	public Pieza obtenerPieza(int fila,int columna){
		return tablero[fila-1][columna-1];
	}

	public void eliminarPieza(Pieza pieza,Pieza piezaEliminada) throws EliminacionInvalidaExcepcion{
		try{
			pieza.validarEliminar(piezaEliminada);
			tablero[piezaEliminada.obtenerFila()-1][piezaEliminada.obtenerColumna()-1] = null;
		}catch(EliminacionInvalidaExcepcion e){
			System.out.println(e);
		}
	}

	public void quitarPiezaTablero(Pieza pieza){
		tablero[pieza.obtenerFila()-1][pieza.obtenerColumna()-1] = null;
	}

	

	public void moverPieza(Pieza pieza, int columna, int fila){
		try{
			pieza.validarMovimiento(columna,fila,this);
			if(obtenerPieza(columna,fila) != null){
				pieza.validarEliminar(obtenerPieza(columna,fila));
				eliminarPieza(pieza,obtenerPieza(columna,fila));
			}
			quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarPosicion(columna,fila);
			
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

}