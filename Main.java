import java.util.Scanner;

/**
 * Clase main de un Juego de Extincion
 * @author Mauricio Chávez
 * @version 15012018
 */
public class Main{

	/**
	* Metodo principal
	* @param args -- argumentos de ejecucion, en este main, son inutiles
	*/
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion{
		Scanner io = new Scanner(System.in);
		RegistroJuegos registro = new RegistroJuegos();
		Menus menu = new Menus();
		String nombreJ1 = "";
		String nombreJ2 = "";
		int opcionInicio;
		int tipoJuego = 0;
		int nivelJuego;
		boolean repetir;

		menu.menuNombreJ1();
		nombreJ1 = menu.obtenerNombrePrimerJugador();
		
		do{
			repetir=false;
			menu.menuOpcionesInicio();

			switch(menu.obtenerOpcionInicio()){
				case 1:
				menu.menuTipoJuego();
				tipoJuego = menu.obtenerTipoJuego();
				if(tipoJuego==1){
					menu.menuNombreJ2();
					nombreJ2 = menu.obtenerNombreSegundoJugador();
				}else{
					nombreJ2 = "Maquina";
				}
				menu.menuNivelJuego();
				nivelJuego = menu.obtenerNivelJuego();

				JuegoDeExtincion juego = new JuegoDeExtincion(tipoJuego,nivelJuego,nombreJ1,nombreJ2);
				juego.iniciarJuego();
				juego.jugar();
				try{
					registro.guardarRegistroJuego();
					registro.leerRegistroJuegos();
					registro.registrarJuego(new RegistroGanador(juego.obtenerPrimerJugador(),juego.obtenerSegundoJugador(),juego.obtenerGanador()));
					registro.sumarRegistro();
					registro.guardarRegistroJuego();
				}catch(LimiteRegistrosAlcanzadoExcepcion e){
					System.out.println(e);
				}
				break;

				case 2:
				menu.instrucciones();
				break;

				case 3:
				registro.leerRegistroJuegos();
				registro.imprimirRegistros();
				break;
			}
			
			menu.menuRepeticion();
			repetir = menu.obtenerRepetir();

		}while(repetir);

		System.out.println((char)27 + "[32m\n¡Hasta luego, " + nombreJ1 + "!\n");
	}
}