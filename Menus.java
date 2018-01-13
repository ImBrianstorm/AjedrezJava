import java.util.Scanner;

public class Menus{
	private Scanner io = new Scanner(System.in);
	private int filaInicial;
	private int filaFinal;
	private int columnaInicial;
	private int columnaFinal;

	public Menus(){
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

	public void menuMovimiento2(int numeroJugador, String nombreJugador,Tablero tablero) throws CoordenadaNoValidaExcepcion,MovimientoNoValidoExcepcion{
		String coordenadas;

		System.out.print(nombreJugador + ", ingresa las coordenadas a la que deseas mover tu pieza (ejemplo:F5) → ");
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

	public static int asciiToColumna(int ascii){
		return ascii-64;
	}
}