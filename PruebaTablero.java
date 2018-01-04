public class PruebaTablero{
	public static void main(String[] args){
		Juego prueba = new Juego();
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(6,1),5,1);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(5,1),6,1);
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(6,4),6,2);
		System.out.println("\n" + prueba.toString());
		
		//System.out.println();		
	}
}

