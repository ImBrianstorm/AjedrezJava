public class PruebaTablero{
	public static void main(String[] args) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion{
		JuegoDeExtincion prueba = new JuegoDeExtincion(2,1,"Meme","Momo");
		prueba.iniciarJuego();
		prueba.turno();
		//System.out.println();	
	}
}

