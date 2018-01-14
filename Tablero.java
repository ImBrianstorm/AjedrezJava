/**
 *
 * @author Mauricio Chávez
 */
public class Tablero{

	protected Pieza[][] tablero;
	private int numeroFilas;
	private int numeroColumnas;

	public Tablero(){
		this.tablero = new Pieza[8][8];
		this.numeroFilas = 8;
		this.numeroColumnas = 8;
	}

	public Tablero(int filas,int columnas) throws TamañoNoSoportadoExcepcion{
		if(columnas>26)
			throw new TamañoNoSoportadoExcepcion("El tablero no soporta más de 26 columnas");
		this.tablero = new Pieza[filas][columnas];
		this.numeroFilas = filas;
		this.numeroColumnas = columnas;
	}

	public Tablero(Pieza[][] arregloPiezas) throws TamañoNoSoportadoExcepcion{
		if(tablero.length>26)
			throw new TamañoNoSoportadoExcepcion("El tablero no soporta más de 26 columnas");
		arregloPiezas = tablero;
	}

	public Pieza[][] obtenerArregloPiezas(){
		return tablero;
	}

	public Pieza obtenerPieza(int fila,int columna){
		Pieza p = null;
		try{
			p = tablero[fila-1][columna-1];
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("No puedes obtener una pieza fuera del tablero");
		}
		return p;
	}

	public int obtenerNumeroFilas(){
		return numeroFilas;
	}

	public int obtenerNumeroColumnas(){
		return numeroColumnas;
	}

	public void agregarPieza(Pieza pieza,int fila,int columna){
		tablero[fila-1][columna-1] = pieza;
	}

	public void quitarPiezaTablero(Pieza pieza){
		tablero[pieza.obtenerFila()-1][pieza.obtenerColumna()-1] = null;
	}

	public String toString(){
    	String tablero = lineaLetras() + lineaDivisionInicial() + lineaPiezas(1);
        for(int i=2;i<=numeroFilas;i++){
          tablero += lineaDivision();
          tablero += lineaPiezas(i);
        }
    	return tablero + lineaDivisionFinal();
    }

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

    private String lineaLetras(){
    	String string = "      ";
    	for(int i=65;i<=64+numeroColumnas;i++){
    		string += (char) i + "   ";
    	}
    	return string + "\n";
    }

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

	private String lineaDivision(){
		String string = "    ";
		string += "╠═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╬═══";
		}
		return string + "╣\n";
	}

	private String lineaDivisionInicial(){
    	String string = "    ";
		string += "╔═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╦═══";
		}
		return string + "╗\n";
    }

    private String lineaDivisionFinal(){
    	String string = "    ";
		string += "╚═══";
		for(int i=2;i<=numeroColumnas;i++){
			string += "╩═══";
		}
		return string + "╝\n";
    }
}