import java.util.Scanner;
public class Main{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion{
		Scanner io = new Scanner(System.in);
		String nombreJ1;
		String nombreJ2;
		int opcionInicio;
		int tipoJuego;
		int nivelJuego;

		Menus menu = new Menus();
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
				nombreJ2 = "Computadora";
			}
			menu.menuNivelJuego();
			nivelJuego = menu.obtenerNivelJuego();

			JuegoDeExtincion juego = new JuegoDeExtincion(tipoJuego,nivelJuego,nombreJ1,nombreJ2);
			juego.iniciarJuego();
			juego.jugar();
		}
	}
}