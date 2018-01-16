import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Mauricio Chávez
 * @version 15012018
 */
public class Menus{
	private Scanner io = new Scanner(System.in);
	private Scanner in = new Scanner(System.in);
	private int filaInicial;
	private int filaFinal;
	private int columnaInicial;
	private int columnaFinal;
	private int nivelJuego;
	private int opcionInicio;
	private int tipoJuego;
	private Pieza piezaCoronada;
	private String nombrePrimerJugador;
	private String nombreSegundoJugador;

	public Menus(){
		this.filaInicial = -1;
		this.filaFinal = -1;
		this.columnaInicial = -1;
	    this.columnaFinal = -1;
	    this.piezaCoronada = null;
	    this.tipoJuego = -1;
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

	public int obtenerTipoJuego(){
		return tipoJuego;
	}

	public int obtenerOpcionInicio(){
		return opcionInicio;
	}

	public int obtenerNivelJuego(){
		return nivelJuego;
	}

	public String obtenerNombrePrimerJugador(){
		return nombrePrimerJugador;
	}

	public String obtenerNombreSegundoJugador(){
		return nombreSegundoJugador;
	}

	public void menuNivelJuego(){
		boolean opcionInvalida;
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.println((char)27 + "[37m" + nombrePrimerJugador + ", ingresa el nivel del juego\n");
		do{
			opcionInvalida=false;
			System.out.println((char)27 + "[33m1 = Nivel 1: Maximo 20 turnos");
			System.out.println((char)27 + "[32m2 = Nivel 2: Maximo 40 turnos");
			System.out.println((char)27 + "[36m3 = Nivel 3: Maximo 100 turnos\n");
			System.out.print((char)27 + "[37mIngrese cualquier otro dato para salir: ");
			try{
				this.nivelJuego = Integer.parseInt(io.nextLine().trim());
				if(this.nivelJuego<1||this.nivelJuego>3) throw new InputMismatchException();
			}catch(InputMismatchException e){
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				System.out.println((char)27 + "[31m¡Solo existen tres niveles, intentalo de nuevo!\n");
				opcionInvalida=true;
				System.out.println((char)27 + "[37mIngresa el nivel del juego\n");
			}catch(NumberFormatException e){
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				System.out.println((char)27 + "[31m¡No ingresaste un numero, intentalo de nuevo!\n");
				opcionInvalida=true;
				System.out.println((char)27 + "[37m¿Jugaras contra otro jugador o contra la maquina?\n");
			}
		}while(opcionInvalida);
	}

	public void menuTipoJuego(){
		boolean opcionInvalida;
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.println((char)27 + "[37m" + nombrePrimerJugador + ", ¿jugaras contra otro jugador o contra la computadora?\n");
		do{
			opcionInvalida=false;
			System.out.println((char)27 + "[36m1 = Humano");
			System.out.println((char)27 + "[32m2 = Computadora\n");
			System.out.print((char)27 + "[37mIngrese cualquier otro dato para salir: ");
			try{
				this.tipoJuego = Integer.parseInt(io.nextLine().trim());
				if(this.tipoJuego!=1&&this.tipoJuego!=2) throw new InputMismatchException();
			}catch(InputMismatchException e){
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				System.out.println((char)27 + "[31m¡Solo existen dos tipos de juego, intentalo de nuevo!\n");
				opcionInvalida=true;
				System.out.println((char)27 + "[37m¿Jugaras contra otro jugador o contra la maquina?\n");
			}catch(NumberFormatException e){
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				System.out.println((char)27 + "[31m¡No ingresaste un numero, intentalo de nuevo!\n");
				opcionInvalida=true;
				System.out.println((char)27 + "[37m¿Jugaras contra otro jugador o contra la maquina?\n");
			}
		}while(opcionInvalida);
	}

	public void menuNombreJ1(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.print((char)27 + "[37m¡Bienvenido! Ingresa tu nombre para continuar: ");
		this.nombrePrimerJugador = io.nextLine().trim();
	}

	public void menuNombreJ2(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.print((char)27 + "[37mIngresa el nombre del segundo jugador: ");
		this.nombreSegundoJugador = io.nextLine().trim();
	}

	public void menuOpcionesInicio(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.println((char)27 + "[37mHola " + nombrePrimerJugador + ", selecciona que deseas hacer a continuacion:\n");
		System.out.println((char)27 + "[31m1 = Jugar");
		System.out.println((char)27 + "[32m2 = Ver las instrucciones");
		System.out.println((char)27 + "[33m3 = Ver los registros de partidas");
		System.out.println((char)27 + "[36m4 = Opciones\n");//Aqui metere los diseños de tablero, incluir alfil alv, diseños pieza, que no se voltee el tablero
		System.out.print((char)27 + "[37mIngrese cualquier otro dato para salir: ");
		try{
			this.opcionInicio = in.nextInt();
			if(this.opcionInicio<1||this.opcionInicio>4) throw new InputMismatchException();
		}catch(InputMismatchException e){
			System.out.println((char)27 + "[37m\nHasta luego :)");
			System.exit(0);
		}
	}

	public void menuMovimiento1(int numeroJugador, String nombreJugador,Tablero tablero) throws CoordenadaNoValidaExcepcion,MovimientoNoValidoExcepcion{
		String coordenadas;
		System.out.print((char)27 + "[37m" + nombreJugador + ", ingresa las coordenadas de la pieza a mover → ");
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

		System.out.print((char)27 + "[37mIngresa las coordenadas a la que deseas mover tu pieza → ");
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

	public void menuCoronacion(int fila,int columna,Jugador jugadorEnTurno){
		String opcion;
		System.out.println((char)27 + "[37m\n¡Felicidades, " + jugadorEnTurno.obtenerNombreJugador() +"! Tu peon ha llegado a la ultima fila.");
		System.out.println((char)27 + "[37mEsto quiere decir que puede coronarse y convertirse en dama, torre o caballo");
		System.out.print((char)27 + "[37m¿Deseas hacerlo? Escribe SI para coronar a tu peon, ingresa otro dato en caso contrario: ");
		opcion = io.nextLine().trim().toLowerCase();

		String pieza;
		if(opcion.equals("si")||opcion.equals("sí")){
			System.out.println((char)27 + "[37m\n¿En que pieza deseas que se convierta?\n");
			System.out.println((char)27 + "[32m1 = Torre");
			System.out.println((char)27 + "[36m2 = Caballo");
			System.out.println((char)27 + "[33m3 = Dama\n");
			System.out.print((char)27 + "[37mIngrese cualquier otro dato para no coronar: ");
			pieza = io.nextLine().trim().toLowerCase();

			if(pieza.equals("1")){
				piezaCoronada = new Torre(fila,columna,jugadorEnTurno);
			}
			else if(pieza.equals("2")){
				piezaCoronada = new Caballo(fila,columna,jugadorEnTurno);
			}
			else if(pieza.equals("3")){
				piezaCoronada = new Dama(fila,columna,jugadorEnTurno);
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