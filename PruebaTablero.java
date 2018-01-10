public class PruebaTablero{
	public static void main(String[] args){
		Juego prueba = new Juego();
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(2,4),3,4);
		System.out.println("\n\n" + prueba.toString(2));
		prueba.moverPieza(prueba.obtenerPieza(5,3),4,3);
		prueba.moverPieza(prueba.obtenerPieza(4,3),3,3);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(3,4),4,3);
		System.out.println("\n" + prueba.toString());
		
		//System.out.println();		
	}
}

