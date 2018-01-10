public class PruebaTablero{
	public static void main(String[] args){
		try{
			Juego prueba = new Juego();
			prueba.iniciarJuego();
			System.out.println("\n\n" + prueba.toString());
			prueba.moverPieza(prueba.obtenerPieza(7,6),5,6);
			prueba.moverPieza(prueba.obtenerPieza(5,6),4,6);
			System.out.println("\n\n" + prueba.toString());
			prueba.moverPieza(prueba.obtenerPieza(2,7),4,7);
			System.out.println("\n\n" + prueba.toString());
			prueba.moverPieza(prueba.obtenerPieza(4,9),3,7);
			System.out.println("\n" + prueba.toString());
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		//System.out.println();		
	}
}

