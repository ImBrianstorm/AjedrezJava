import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
	public static void main(String[] pps){
		Scanner io = new Scanner(System.in);
		int opcion;

	
		System.out.println("Bienvenido, ¿jugarás contra otro jugador o contra la máquina?");
		System.out.println("1. Humano");
		System.out.println("Ingrese cualquier otro dato para salir.\n");
		System.out.println("2. Máquina");
		try{
			opcion = io.nextInt();
			if(opcion!=1&&opcion!=2) throw new InputMismatchException();
		}catch(InputMismatchException e){
			System.out.println("Hasta luego.");
			System.exit(0);
		}

		if(opcion==1){
			Juego humano = new Juego(0);
		}else{
			Juego computadora = new Juego(1);
		}
	}
}