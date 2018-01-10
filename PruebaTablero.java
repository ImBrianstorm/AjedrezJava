public class PruebaTablero{
	public static void main(String[] args){
		try{
			JuegoDeExtincion prueba = new JuegoDeExtincion();
			prueba.iniciarJuego();
			System.out.println("\n\n" + prueba.toString());
			prueba.moverPieza(prueba.obtenerPieza(1,5),1,3);
			System.out.println("\n" + prueba.toString(2));

		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		//System.out.println();		
	}
}

