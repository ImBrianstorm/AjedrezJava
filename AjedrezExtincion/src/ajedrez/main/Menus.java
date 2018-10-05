/**
 * Clase que imprime menus y obtiene datos
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.main;

import java.util.Scanner;
import java.util.InputMismatchException;
import ajedrez.juego.pieza.Pieza;
import ajedrez.juego.pieza.Torre;
import ajedrez.juego.pieza.Caballo;
import ajedrez.juego.pieza.Dama;
import ajedrez.juego.tablero.Tablero;
import ajedrez.juego.jugador.Jugador;
import ajedrez.excepciones.CoordenadaNoValidaExcepcion;
import ajedrez.excepciones.MovimientoNoValidoExcepcion;

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
	private boolean repetir;

	/**
	* Constructor que crea un objeto Menus
	*/
	public Menus(){
		this.filaInicial = -1;
		this.filaFinal = -1;
		this.columnaInicial = -1;
	    this.columnaFinal = -1;
	    this.piezaCoronada = null;
	    this.tipoJuego = -1;
	}

	/**
	* Metodo que obtiene la fila inicial que se consigue del usuario a traves del metodo menuMovimiento1
	* @return filaInicial
	*/
	public int obtenerFilaInicial(){
		return filaInicial;
	}

	/**
	* Metodo que obtiene la fila final que se consigue del usuario a traves del metodo menuMovimiento2
	* @return filaFinal
	*/
	public int obtenerFilaFinal(){
		return filaFinal;
	}

	/**
	* Metodo que obtiene la columna inicial que se consigue del usuario a traves del metodo menuMovimiento1
	* @return columnaInicial
	*/
	public int obtenerColumnaInicial(){
		return columnaInicial;
	}

	/**
	* Metodo que obtiene la columna final que se consigue del usuario a traves del metodo menuMovimiento2
	* @return columnaFinal
	*/
	public int obtenerColumnaFinal(){
		return columnaFinal;
	}

	/**
	* Metodo que obtiene la pieza a coronar, que se consigue del usuario a traves del metodo menuCoronacion
	* @return piezaCoronada
	*/
	public Pieza obtenerPiezaCoronada(){
		return piezaCoronada;
	}

	/**
	* Metodo que obtiene el tipo de juego, que se consigue del usuario a traves del metodo menuTipoJuego
	* @return tipoJuego
	*/
	public int obtenerTipoJuego(){
		return tipoJuego;
	}

	/**
	* Metodo que obtiene la opcion de inicio, que se consigue del usuario a traves del metodo menuOpcionesInicio
	* @return tipoJuego
	*/
	public int obtenerOpcionInicio(){
		return opcionInicio;
	}

	/**
	* Metodo que obtiene el nivel de juego, que se consigue del usuario a traves del metodo menuNivelJuego
	* @return nivelJuego
	*/
	public int obtenerNivelJuego(){
		return nivelJuego;
	}

	/**
	* Metodo que obtiene el nombre del primer jugador, que se consigue del usuario a traves del metodo menuNombreJ1
	* @return nombrePrimerJugador
	*/
	public String obtenerNombrePrimerJugador(){
		return nombrePrimerJugador;
	}

	/**
	* Metodo que obtiene el nombre del segundo jugador, que se consigue del usuario a traves del metodo menuNombreJ2
	* @return nombreSegundoJugador
	*/
	public String obtenerNombreSegundoJugador(){
		return nombreSegundoJugador;
	}

	/**
	* Metodo que obtiene un booleano verdadero si el usuario quiere repetir, falso en caso contrario
	* @return repetir -- true si se busca repetir
	*/
	public boolean obtenerRepetir(){
		return repetir;
	}

	/**
	* Metodo que imprime en pantallas las instrucciones del juego
	*/
	public static void instrucciones(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
    	System.out.println((char)27 + "[35m══════════════════════════════════════════════════════════════════");
		System.out.println((char)27 + "[32m  Esta modalidad del ajedrez se jugara en un tablero de 6x6. Las");
		System.out.println((char)27 + "[32m  reglas son las mismas que en el ajedrez tradicional, con la   ");
		System.out.println((char)27 + "[32m  unica excepcion de que no hay jaque ni jaque mate. En su lugar");
		System.out.println((char)27 + "[32m  gana el que elimine a un grupo completo de piezas, es decir,  ");
		System.out.println((char)27 + "[32m  todas las torres, todos los caballos, todos los peones, al    ");
		System.out.println((char)27 + "[32m  rey, etcetera. Los peones pueden coronarse, lo que conlleva   ");
		System.out.println((char)27 + "[32m  que si asciende, por ejemplo, a una dama, en el juego habra   ");
		System.out.println((char)27 + "[32m  una dama mas, pero un peon menos. Ojo con esto, porque si     ");
		System.out.println((char)27 + "[32m  promueves a tu ultimo peon, perderas el juego.                ");
		System.out.println((char)27 + "[31m                                                                ");
		System.out.println((char)27 + "[31m  Como no hay jaque, se permite enrocar mientras el rey este    ");
		System.out.println((char)27 + "[31m  \"en jaque\" o peligre de estarlo.                            ");
		System.out.println((char)27 + "[31m                                                                ");
		System.out.println((char)27 + "[33m  Para enrocar, solo basta con mover a tu rey dos escaques      ");
		System.out.println((char)27 + "[33m  hacia la torre que enrocaras y la torre se movera             ");
		System.out.println((char)27 + "[33m  automaticamente. Tambien la captura al paso es valida.        ");
		System.out.println((char)27 + "[31m                                                                ");
		System.out.println((char)27 + "[36m  Para mover tus piezas, solo basta con ingresar sus coordenadas");
		System.out.println((char)27 + "[36m  en la forma columna-fila. Primero la letra, luego el numero   ");
		System.out.println((char)27 + "[36m  (F5, por ejemplo). No te preocupes por mover una pieza a una  ");
		System.out.println((char)27 + "[36m  posicion no valida. El juego no te lo permitira, te dira la   ");
		System.out.println((char)27 + "[36m  razon y te permitira hacer tu seleccion de nuevo.             ");
		System.out.println((char)27 + "[31m                                                                ");
		System.out.println((char)27 + "[34m  Si juegas contra un humano, el tablero girara para tu propia  ");
		System.out.println((char)27 + "[34m  comodidad.                                                    ");
		System.out.println((char)27 + "[31m                                                                ");
		System.out.println((char)27 + "[32m  Se te preguntara el nivel del juego, esto va relacionado con  ");
		System.out.println((char)27 + "[32m  el numero de turnos. En el caso de que juegues contra un      ");
		System.out.println((char)27 + "[32m  humano, se declarara empate si nadie gano despues de los      ");
		System.out.println((char)27 + "[32m  turnos establecidos, y si se juega contra la computadora, al  ");
		System.out.println((char)27 + "[32m  terminar todos turnos, la computadora habra ganado.           ");
		System.out.println((char)27 + "[32m                                                                ");
		System.out.println((char)27 + "[37m                 ¡SAL A JUGAR Y DIVIERTETE!                   \n");
		System.out.println((char)27 + "[35m══════════════════════════════════════════════════════════════════");
		System.out.println((char)27 + "[37m");
	}

	/**
	* Metodo que pregunta al usuario si desea repetir una accion y lo asigna a su variable correspondiente
	*/
	public void menuRepeticion(){
		String opcion;
		System.out.println((char)27 + "[37m\n" + nombrePrimerJugador + ", ¿deseas volver al menu de inicio?: Ingresa SI para regresar, ingresa cualquier otro dato para salir:");
		opcion = io.nextLine().trim().toLowerCase();

		if(opcion.equals("si")||opcion.equals("sí"))
			repetir = true;
		else
		 repetir=false;
	}

	/**
	* Metodo que pregunta al usuario el nivel de juego y lo asigna a su respectiva variable en la clase
	*/
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
			System.out.print((char)27 + "[37m");
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

	/**
	* Metodo que pregunta al usuario el tipo de juego y lo asigna a su respectiva variable en la clase
	*/
	public void menuTipoJuego(){
		boolean opcionInvalida;
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.println((char)27 + "[37m" + nombrePrimerJugador + ", ¿jugaras contra otro jugador o contra la computadora?\n");
		do{
			opcionInvalida=false;
			System.out.println((char)27 + "[36m1 = Humano");
			System.out.println((char)27 + "[32m2 = Computadora\n");
			System.out.print((char)27 + "[37m");
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

	/**
	* Metodo que pregunta al usuario su nombre y lo asigna a su respectiva variable en la clase
	*/
	public void menuNombreJ1(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.print((char)27 + "[37m¡Bienvenido! Ingresa tu nombre para continuar: ");
		this.nombrePrimerJugador = io.nextLine().trim();
	}

	/**
	* Metodo que pregunta al usuario el nombre del segundo jugador y lo asigna a su respectiva variable en la clase
	*/
	public void menuNombreJ2(){
		System.out.print("\033[H\033[2J");
    	System.out.flush();
		System.out.print((char)27 + "[37mIngresa el nombre del segundo jugador: ");
		this.nombreSegundoJugador = io.nextLine().trim();
	}

	/**
	* Metodo que pide al usuario una opcion de inicio y lo asigna a su respectiva variable en la clase
	*/
	public void menuOpcionesInicio(){
		System.out.print("\033[H\033[2J");
    System.out.flush();
		System.out.println((char)27 + "[37mHola " + nombrePrimerJugador + ", selecciona que deseas hacer a continuacion:\n");
		System.out.println((char)27 + "[31m1 = Jugar");
		System.out.println((char)27 + "[32m2 = Ver las instrucciones");
		System.out.println((char)27 + "[33m3 = Ver los registros de partidas");
		//Aqui metere los diseños de tablero, incluir alfil, diseños pieza, que no se voltee el tablero
		//System.out.println((char)27 + "[36m4 = Opciones\n");
		System.out.print((char)27 + "[37m\nIngrese cualquier otro dato para salir: ");
		try{
			this.opcionInicio = in.nextInt();
			if(this.opcionInicio<1||this.opcionInicio>3) throw new InputMismatchException();
		}catch(InputMismatchException e){
			System.out.println((char)27 + "[32m\n¡Hasta luego, " + nombrePrimerJugador + "!\n");
			System.exit(0);
		}
	}

	/**
	* Metodo que pregunta al usuario coordenadas para obtener una pieza en un Tablero para moverla, y las asigna a su respectiva variable en la clase, arrojando excepciones si no es posible hacer el movimiento o ingresar las coordenadas
	* @see Tablero
	* @param numeroJugador -- El numero del jugador en turno
	* @param nombreJugador -- El nombre del jugador en turno
	* @param tablero -- el Tablero donde se movera la pieza
	* @throws CoordenadaNoValidaExcepcion -- Se arroja si las coordenadas de la pieza no existe o esta mal ingresada
	* @throws MovimientoNoValidoExcepcion -- Se arroja si el movimiento de la Pieza sobre el tablero no es valido
	*/
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

	/**
	* Metodo que pregunta al usuario coordenadas para mover una pieza en un Tablero para moverla, y las asigna a su respectiva variable en la clase, arrojando excepciones si no es posible hacer el movimiento o ingresar las coordenadas
	* @see Tablero
	* @param tablero -- el Tablero donde se movera la pieza
	* @throws CoordenadaNoValidaExcepcion -- Se arroja si las coordenadas a donde se piensa mover la Pieza no existe o esta mal ingresada
	*/
	public void menuMovimiento2(Tablero tablero) throws CoordenadaNoValidaExcepcion{
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

	/**
	* Metodo que pregunta al usuario si quiere coronar a su Peon, y le da opciones de como hacerlo
	* @see Jugador
	* @param fila -- la fila donde se encuentra el Peon a coronar
	* @param columna -- la columna donde se encuentra el Peon a coronar
	* @param jugadorEnTurno -- el jugador que coronara a su Peon
	*/
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

	/**
	*Metodo privado que convierte un entero decimal ASCII a su respectiva columna
	*/
	private static int asciiToColumna(int ascii){
		return ascii-64;
	}
}
