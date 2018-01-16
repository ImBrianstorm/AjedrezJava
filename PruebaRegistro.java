public class PruebaRegistro{
	public static void main(String[] args) throws TipoNoValidoExcepcion{
		Jugador jugador1 = new Jugador(1,1,"Meme");
		Jugador jugador2 = new Jugador(2,2,"Mauricio");
		Jugador ganador = jugador2;
		RegistroGanador registro = new RegistroGanador(jugador1,jugador2,ganador);
		System.out.println(registro.toString());
	}
}