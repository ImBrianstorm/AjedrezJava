import java.util.Scanner;

public class Menus{
	private Scanner io = new Scanner(System.in);
	private int filaInicial;
	private int filaFinal;
	private int columnaInicial;
	private int columnaFinal;
	private Pieza piezaCoronada;

	public Menus(){
		this.filaInicial = -1;
		this.filaFinal = -1;
		this.columnaInicial = -1;
	    this.columnaFinal = -1;
	    this.piezaCoronada = null;
	}

	public int obtenerFilaInicial(){
		return filaInicial;
	}

	public int obtenerFilaFinal(){
		return filaFinal;
	}

	public int obtenerColumnaInicial(){
		return columnaInicial;
	}

	public int obtenerColumnaFinal(){
		return columnaFinal;
	}

	public Pieza obtenerPiezaCoronada(){
		return piezaCoronada;
	}

	public void menuMovimiento1(int numeroJugador, String nombreJugador,Tablero tablero) throws CoordenadaNoValidaExcepcion,MovimientoNoValidoExcepcion{
		String coordenadas;
		System.out.print("\n" + nombreJugador + ", ingresa las coordenadas de la pieza a mover → ");
		coordenadas = io.nextLine().trim().toUpperCase();
		try{
			if((int)coordenadas.charAt(0)<65||(int)coordenadas.charAt(0)>90)
				throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio (ejemplo valido: F5)");
		}catch(StringIndexOutOfBoundsException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio (ejemplo valido: F5)");
		}
		columnaInicial = asciiToColumna(coordenadas.charAt(0));
		try{
			filaInicial = Integer.parseInt(coordenadas.substring(1));	
		}catch(NumberFormatException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste un numero a continuacion de tu letra (ejemplo valido: F5)");
		}
		if(filaInicial>tablero.obtenerNumeroFilas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero");
		else if(columnaInicial>tablero.obtenerNumeroColumnas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero");
		if(tablero.obtenerPieza(filaInicial,columnaInicial).obtenerNumeroJugador()!=numeroJugador)
			throw new MovimientoNoValidoExcepcion("No puedes mover una pieza que no es tuya");
	}

	public void menuMovimiento2(Tablero tablero) throws CoordenadaNoValidaExcepcion,MovimientoNoValidoExcepcion{
		String coordenadas;

		System.out.print("Ingresa las coordenadas a la que deseas mover tu pieza → ");
		coordenadas = io.nextLine().trim().toUpperCase();
		if((int)coordenadas.charAt(0)<65||(int)coordenadas.charAt(0)>90)
			throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio (ejemplo valido: F5)");
		else 
			columnaFinal = asciiToColumna(coordenadas.charAt(0));
		try{
			filaFinal = Integer.parseInt(coordenadas.substring(1));	
		}catch(NumberFormatException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste un numero a continuacion de tu letra (ejemplo valido: F5)");
		}
		if(filaInicial>tablero.obtenerNumeroFilas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero (ejemplo valido: F5)");
		else if(columnaInicial>tablero.obtenerNumeroColumnas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero (ejemplo valido: F5)");
	}

	public void menuCoronacion(int numeroJugador,int fila,int columna,Jugador jugadorEnTurno){
		String opcion;
		System.out.println("\n¡Felicidades! Tu peon ha llegado a la ultima fila.");
		System.out.println("Esto quiere decir que puede coronarse y convertirse en dama, alfil o caballo");
		System.out.println("¿Deseas hacerlo? Escribe SI para coronar a tu peon, ingresa otro dato en caso contrario:");
		opcion = io.nextLine().trim().toLowerCase();

		String pieza;
		if(opcion.equals("si")||opcion.equals("sí")){
			System.out.println("\n¿En que pieza deseas que se convierta?\n");
			System.out.println("1 = Torre");
			System.out.println("2 = Caballo");
			System.out.println("3 = Dama");
			System.out.println("Ingrese cualquier otro dato para no coronar:");
			pieza = io.nextLine().trim().toLowerCase();

			if(pieza.equals("1")){
				piezaCoronada = new Torre(numeroJugador,fila,columna,jugadorEnTurno);
			}
			else if(pieza.equals("2")){
				piezaCoronada = new Caballo(numeroJugador,fila,columna,jugadorEnTurno);
			}
			else if(pieza.equals("3")){
				piezaCoronada = new Dama(numeroJugador,fila,columna,jugadorEnTurno);
			}
			else{
				piezaCoronada = null;
			}
		}
	}

	private static int asciiToColumna(int ascii){
		return ascii-64;
	}
}