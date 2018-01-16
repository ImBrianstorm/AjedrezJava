import java.io.Serializable;

public class Jugador implements Serializable{

	private int tipoJugador;
	private int numeroJugador;
	private int turnos;
	private String nombreJugador;

	public Jugador(int tipoJugador,int numeroJugador, String nombreJugador) throws TipoNoValidoExcepcion{
		if(tipoJugador<1||tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		this.tipoJugador = tipoJugador;
		this.turnos = 0;
		this.numeroJugador = numeroJugador;
		this.nombreJugador = nombreJugador;
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

	public int obtenerNumeroJugador(){
		return numeroJugador;
	}

	public int obtenerTipoJugador(){
		return tipoJugador;
	}

	public void asignarTipoJugador(int tipoJugador) throws TipoNoValidoExcepcion{
		if(tipoJugador<1||tipoJugador>2)
			throw new TipoNoValidoExcepcion("El jugador solo puede ser tipo 1 (humano) o tipo 2 (computadora");
		this.tipoJugador = tipoJugador;
	}


}