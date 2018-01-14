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
		System.out.print(nombreJugador + ", ingresa las coordenadas de la pieza a mover (ejemplo:F5) → ");
		coordenadas = io.nextLine().trim().toUpperCase();
		try{
			if((int)coordenadas.charAt(0)<65||(int)coordenadas.charAt(0)>90)
				throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio");
		}catch(StringIndexOutOfBoundsException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio");
		}
		columnaInicial = asciiToColumna(coordenadas.charAt(0));
		try{
			filaInicial = Integer.parseInt(coordenadas.substring(1));	
		}catch(NumberFormatException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste un numero a continuacion de tu letra");
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

		System.out.print("Ingresa las coordenadas a la que deseas mover tu pieza (ejemplo:F5) → ");
		coordenadas = io.nextLine().trim().toUpperCase();
		if((int)coordenadas.charAt(0)<65||(int)coordenadas.charAt(0)>90)
			throw new CoordenadaNoValidaExcepcion("No ingresaste una letra al inicio");
		else 
			columnaFinal = asciiToColumna(coordenadas.charAt(0));
		try{
			filaFinal = Integer.parseInt(coordenadas.substring(1));	
		}catch(NumberFormatException e){
			throw new CoordenadaNoValidaExcepcion("No ingresaste un numero a continuacion de tu letra");
		}
		if(filaInicial>tablero.obtenerNumeroFilas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero");
		else if(columnaInicial>tablero.obtenerNumeroColumnas())
			throw new CoordenadaNoValidaExcepcion("No existen estas coordenadas en el tablero");
	}

	public void menuCoronacion(int numeroJugador,int fila,int columna){
		String opcion;
		System.out.println("\n¡Felicidades! Tu peon ha llegado a la ultima fila.");
		System.out.println("Esto quiere decir que puede coronarse y convertirse en dama, alfil o caballo");
		System.out.println("¿Deseas hacerlo? Escribe SI para coronar a tu peon, ingresa otro dato en caso contrario");
		opcion = io.nextLine().trim().toLowerCase();

		String pieza;
		if(opcion.equals("si")||opcion.equals("sí")){
			System.out.println("¿En que pieza deseas que se convierta?\n");
			System.out.println("1 = Torre");
			System.out.println("2 = Caballo");
			System.out.println("3 = Dama");
			System.out.println("Ingrese cualquier otro dato para no coronar");
			pieza = io.nextLine().trim().toLowerCase();

			if(pieza.equals("1")){
				piezaCoronada = new Torre(numeroJugador,fila,columna);
			}
			else if(pieza.equals("2")){
				piezaCoronada = new Caballo(numeroJugador,fila,columna);
			}
			else if(pieza.equals("3")){
				piezaCoronada = new Dama(numeroJugador,fila,columna);
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