public class PruebaTablero{
	public static void main(String[] args){
		Juego prueba = new Juego();
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(6,1),4,1);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(2,1),3,1);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(4,1),2,1);
		System.out.println("\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(4,1),4,6);
		prueba.moverPieza(prueba.obtenerPieza(4,6),5,6);
		prueba.moverPieza(prueba.obtenerPieza(5,6),3,6);
		prueba.moverPieza(prueba.obtenerPieza(3,6),3,1);
		System.out.println("\n" + prueba.toString());

		//System.out.println();		
	}
}

