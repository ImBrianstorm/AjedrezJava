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
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion,LimiteRegistrosAlcanzadoExcepcion{
		Scanner io = new Scanner(System.in);
		RegistroJuegos registro = new RegistroJuegos(100);
		Menus menu = new Menus();
		String nombreJ1;
		String nombreJ2;
		int opcionInicio;
		int tipoJuego;
		int nivelJuego;

		menu.menuNombreJ1();
		nombreJ1 = menu.obtenerNombrePrimerJugador();
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
			registro.registrarJuego(new RegistroGanador(juego.obtenerPrimerJugador(),juego.obtenerSegundoJugador(),juego.obtenerGanador()));
			registro.guardarRegistroJuego();
			break;

			case 3:
			registro.leerRegistroJuegos();
			registro.imprimirRegistros();
			break;
		}
	}
}