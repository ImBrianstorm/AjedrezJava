public class PruebaTablero{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion{
		JuegoDeExtincion prueba = null;
		try{
			prueba = new JuegoDeExtincion(2,"Meme","IA");
		}catch(TipoNoValidoExcepcion e){}
		prueba.iniciarJuego();
		System.out.println("\n" + prueba.toString());
		System.out.println("\n" + prueba.toString());	
		//System.out.println();	
	}
}

