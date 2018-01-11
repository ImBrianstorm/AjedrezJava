public class PruebaTablero{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion{
		JuegoDeExtincion prueba = new JuegoDeExtincion(1);
		prueba.iniciarJuego();
		System.out.println("\n\n" + prueba.toString());
		prueba.moverPieza(prueba.obtenerPieza(1,5),1,3);
		System.out.println("\n" + prueba.toString(2));	
		//System.out.println();		
	}
}

