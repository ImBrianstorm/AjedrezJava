public class PruebaTablero{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion{
		JuegoDeExtincion prueba = new JuegoDeExtincion(2,"Meme","IA");
		prueba.iniciarJuego();
		System.out.println("\n" + prueba.toString());
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		prueba.turnoHumano(1,"Meme");
		System.out.println("\n" + prueba.toString());	
		//System.out.println();	
	}
}

