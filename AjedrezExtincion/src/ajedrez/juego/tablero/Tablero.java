/**
 *
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.juego.tablero;

import ajedrez.excepciones.TamañoNoSoportadoExcepcion;
import ajedrez.juego.pieza.Pieza;

public class Tablero {

	protected Pieza[][] tablero;
	private int numeroFilas;
	private int numeroColumnas;

	/**
	* Constructor que crea un tablero con ocho filas y ocho columnas
	*/
	public Tablero(){
		this.tablero = new Pieza[8][8];
		this.numeroFilas = 8;
		this.numeroColumnas = 8;
	}

	/**
	* Constructor que crea un tablero con las filas y columnas especificadas, arrojando una excepcion cuando no es posible ocupar mas columnas
	* @param filas -- filas del Tablero
	* @param columnas -- columnas de Tablero
	* @throws TamañoNoSoportadoExcepcion -- Si el numero de columnas sobrepasa veintiocho
	*/
	public Tablero(int filas,int columnas) throws TamañoNoSoportadoExcepcion{
		if(columnas>26)
			throw new TamañoNoSoportadoExcepcion("El tablero no soporta más de 26 columnas");
		this.tablero = new Pieza[filas][columnas];
		this.numeroFilas = filas;
		this.numeroColumnas = columnas;
	}

	/**
	* Constructor que crea un tablero con un arreglo de arreglos de Piezas
	* @param arregloPiezas -- arreglo de arreglos de Pieza
	* @throws TamañoNoSoportadoExcepcion -- Si el numero de columnas sobrepasa veintiocho
	*/
	public Tablero(Pieza[][] arregloPiezas) throws TamañoNoSoportadoExcepcion{
		if(arregloPiezas.length>26)
			throw new TamañoNoSoportadoExcepcion("El tablero no soporta más de 26 columnas");
		arregloPiezas = tablero;
	}

	/**
	* Metodo que obtiene un arreglo de arreglo de Piezas
	* @return Arreglo de arreglos de Piezas
	*/
	public Pieza[][] obtenerArregloPiezas(){
		return tablero;
	}

	/**
	* Metodo que obtiene una pieza en la posicion especificada
	* @return Pieza del Tablero
	*/
	public Pieza obtenerPieza(int fila,int columna){
		Pieza p = null;
		try{
			p = tablero[fila-1][columna-1];
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("No puedes obtener una pieza fuera del tablero");
		}
		return p;
	}

	/**
	* Metodo que obtiene el numero de filas del Tablero
	* @return Filas del Tablero
	*/
	public int obtenerNumeroFilas(){
		return numeroFilas;
	}

	/**
	* Metodo que obtiene el numero de columnas del Tablero
	* @return Columnas del Tablero
	*/
	public int obtenerNumeroColumnas(){
		return numeroColumnas;
	}

	/**
	* Metodo que agrega una pieza al Tablero en la posicion especificada
	* @param pieza -- Pieza a agregar
	* @param fila -- fila de la pieza
	* @param columna -- columna de la pieza
	*/
	public void agregarPieza(Pieza pieza,int fila,int columna){
		tablero[fila-1][columna-1] = pieza;
		pieza.asignarPosicion(fila,columna);
	}

	/**
	* Metodo que quita una pieza del Tablero
	* @param Pieza -- pieza a quitar
	*/
	public void quitarPiezaTablero(Pieza pieza){
		tablero[pieza.obtenerFila()-1][pieza.obtenerColumna()-1] = null;
	}

	@Override
	/**
	* Metodo que devuelve el Tablero como una cadena de Texto
	* @return Tablero como una cadena de texto
	*/
	public String toString(){
    	String tablero = lineaLetras() + lineaDivisionInicial() + lineaPiezas(1);
        for(int i=2;i<=numeroFilas;i++){
          tablero += lineaDivision();
          tablero += lineaPiezas(i);
        }
    	return tablero + lineaDivisionFinal();
    }

	/**
	* Metodo que devuelve el Tablero como una cadena de Texto y lo voltea si el parametro es 2 (para comodidad del segundo jugador)
	* @param numeroJugador -- Numero de jugador en turno
	* @return Tablero como una cadena de texto
	*/
    public String toString(int numeroJugador){
    	if(numeroJugador==1)
    		return toString();
    	else{
    		String tablero = lineaLetras() + lineaDivisionInicial() + lineaPiezas(numeroFilas);
        	for(int i=numeroFilas-1;i>=1;i--){
          		tablero += lineaDivision();
          		tablero += lineaPiezas(i);
        }
    	return tablero + lineaDivisionFinal();
    	}
    }

    /**
    * Metodo privado auxiliar en el metodo toString
    */
    private String lineaLetras(){
    	String string = "      ";
    	for(int i=65;i<=64+numeroColumnas;i++){
    		string += (char) i + "   ";
    	}
    	return string + "\n";
    }

    /**
    * Metodo privado auxiliar en el metodo toString
    */
	private String lineaPiezas(int fila){
	    String string = " " + fila +"  ║ ";
	    for(int i=0;i<numeroColumnas;i++){
	    	if(tablero[fila-1][i]==null)
	    		string+= "  ║ ";
	    	else
	    		string += tablero[fila-1][i].toString() + " ║ ";
	    }
	    return string +"\n";
	}

	/**
    * Metodo privado auxiliar en el metodo toString
    */
	private String lineaDivision(){
		String string = "    ";
		string += "╠═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╬═══";
		}
		return string + "╣\n";
	}

	/**
    * Metodo privado auxiliar en el metodo toString
    */
	private String lineaDivisionInicial(){
    	String string = "    ";
		string += "╔═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╦═══";
		}
		return string + "╗\n";
    }

    /**
    * Metodo privado auxiliar en el metodo toString
    */
    private String lineaDivisionFinal(){
    	String string = "    ";
		string += "╚═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╩═══";
		}
		return string + "╝\n";
    }
}
