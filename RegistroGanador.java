import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase que registra un juego, su fecha y su respectivo ganador
 * @author Mauricio Chávez
 * @version 15012018
 */
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

	/**
	* Constructor que crea un nuevo registro, asignando a sus dos jugadores y su ganador
	* @param primerJugador -- primer jugador del registro
	* @param segundoJugador -- segundo jugador del registro, el cual puede ser una computadora
	* @param ganador -- ganador del juego, admitiendo un jugador null en caso de que no lo haya
	*/
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

	/**
	* Metodo que obtiene el primer Jugador del juego
	* @return primerJugador
	*/
	public Jugador obtenerPrimerJugador(){
		return primerJugador;
	}

	/**
	* Metodo que obtiene el segundo Jugador del juego
	* @return segundoJugador
	*/
	public Jugador obtenerSegundoJugador(){
		return segundoJugador;
	}

	/**
	* Metodo que obtiene el ganador del juego
	* @return ganador
	*/
	public Jugador obtenerGanador(){
		return ganador;
	}

	/**
	* Metodo que asigna el parametro al primer Jugador del registro
	* @param primerJugador -- Jugador a asignar
	*/
	public void asignarPrimerJugador(Jugador primerJugador){
		this.primerJugador = primerJugador;
	}

	/**
	* Metodo que asigna el parametro al segundo Jugador del registro
	* @param segundoJugador -- Jugador a asignar
	*/
	public void asignarSegundoJugador(Jugador segundoJugador){
		this.segundoJugador = segundoJugador;
	}

	/**
	* Metodo que asigna el parametro al ganador del registro
	* @param ganador -- Jugador a asignar
	*/
	public void asignarGanador(Jugador ganador){
		this.ganador = ganador;
	}

	@Override
	/**
	* Metodo que obtiene el registro como una cadena de texto
	* @return registro como cadena de texto
	*/
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
				if(ganador.obtenerNumeroJugador()==1){
					ganadorJuego = "\n¡Gano " + ganador.obtenerNombreJugador() + "!";
				}else{
					ganadorJuego = "\nGano la computadora";
				}
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
		return "Fecha: " + numeroToFecha(dia) + "/"  + numeroToFecha(mes) + "/"  + anio + "\nHora: " + numeroToFecha(hora) + ":" + numeroToFecha(minuto) + " " + amPmString + jugadores + ganadorJuego;
	}

	/**
	*Metodo privado auxiliar que convierte un numero entero a formato fecha
	*/
	private static String numeroToFecha(int numero){
		if(numero>9)
			return String.valueOf(numero);
		else
			return "0" + String.valueOf(numero);
	}
}