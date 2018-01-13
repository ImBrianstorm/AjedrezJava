public class Jugador{

	private int tipo;
	private int numeroJugador;
	private int turnos;
	private String nombreJugador;

	public Jugador(int tipo,int numeroJugador, String nombreJugador) throws TipoNoValidoExcepcion{
		if(tipo<1||tipo>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		this.tipo = tipo;
		this.turnos = 0;
		this.numeroJugador = numeroJugador;
	}

	public void sumarTurno(){
		turnos++;
	}

	public int obtenerTurnos(){
		return turnos;
	}

	public String obtenerNombreJugador(){
		return nombreJugador;
	}

	public void jugar(){

	}
}