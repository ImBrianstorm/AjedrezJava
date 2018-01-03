public class PruebaTablero{
	public static void main(String[] args){
		Juego prueba = new Juego();
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(2,6),1,4);
		prueba.moverPieza(prueba.obtenerPieza(1,4),2,2);
		System.out.println("\n\n" + prueba.toString());
		prueba.quitarPiezaTablero(prueba.obtenerPieza(3,5));
		prueba.moverPieza(prueba.obtenerPieza(3,6),3,4);
		System.out.println("\n\n" + prueba.toString());
		
	}
}

