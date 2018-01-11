import java.util.Scanner;

public class Menus extends Tablero{
	private Scanner io = new Scanner(System.in);
	private int filaInicial;
	private int filaFinal;
	private int columnaInicial;
	private int columnaFinal;

	public Menus(){

	}

	public void menuMovimiento(){//Agregar numero jugador
		String coordenadas;
		System.out.print("Jugador, ingresa las coordenadas de la pieza a mover (ejemplo:F5) → ");
		coordenadas = io.nextLine().trim().toUpperCase();
		//if()
	}
}