import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistroGanador implements Serializable{
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private Jugador ganador;
	private int dia;
	private int mes;
	private int anio;
	private int hora;
	private int minuto;
	private int amPm;

	public RegistroGanador(Jugador primerJugador,Jugador segundoJugador,Jugador ganador){
		this.primerJugador = primerJugador;
		this.segundoJugador = segundoJugador;
		this.ganador = ganador;

		Calendar fecha = new GregorianCalendar();
		this.dia = fecha.get(Calendar.DAY_OF_MONTH);
		this.mes = fecha.get(Calendar.MONTH)  + 1;
		this.anio = fecha.get(Calendar.YEAR);
		this.hora = fecha.get(Calendar.HOUR);
		this.minuto = fecha.get(Calendar.MINUTE);
		this.amPm = fecha.get(Calendar.AM_PM);

	}

	public Jugador obtenerPrimerJugador(){
		return primerJugador;
	}

	public Jugador obtenerSegundoJugador(){
		return segundoJugador;
	}

	public Jugador obtenerGanador(){
		return ganador;
	}

	public void asignarPrimerJugador(Jugador primerJugador){
		this.primerJugador = primerJugador;
	}

	public void asignarSegundoJugador(Jugador segundoJugador){
		this.segundoJugador = segundoJugador;
	}

	public void asignarGanador(Jugador ganador){
		this.ganador = ganador;
	}

	public String toString(){
		String ganadorJuego;
		String jugadores;
		String amPmString;
		if(ganador==null){
			ganadorJuego = "\n¡Hubo un empate!";
		}else{
			if(segundoJugador.obtenerTipoJugador()==1){
				ganadorJuego = "\nGanador: " + ganador.obtenerNombreJugador();
			}else{
				ganadorJuego = "\nGano la computadora";
			}
		}

		if(segundoJugador.obtenerTipoJugador()==1){
			jugadores = "\nJugadores: " + primerJugador.obtenerNombreJugador() + " y " + segundoJugador.obtenerNombreJugador();
		}else{
			jugadores = "\nJugador: " + primerJugador.obtenerNombreJugador();
		}if(amPm==0){
			amPmString = "AM";
		}else{
			amPmString = "PM";
		}
		return "Fecha: " + dia + "/"  + mes + "/"  + anio + "\nHora: " + hora + ":" + minuto + " " + amPmString + jugadores + ganadorJuego;
	}
}