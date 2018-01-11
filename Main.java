import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion{
		Scanner io = new Scanner(System.in);
		int opcion = -1;
	
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
		if(opcion==0){
			JuegoDeExtincion humano = new JuegoDeExtincion(0);
		}else{
			JuegoDeExtincion computadora = new JuegoDeExtincion(1);		
		}
	}
}