public class PruebaTablero{
	public static void main(String[] args){
		Juego prueba = new Juego();
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(2,2),4,2);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(4,2),5,2);
		System.out.println("\n\n" + prueba.toString());
		

		
	}
}

