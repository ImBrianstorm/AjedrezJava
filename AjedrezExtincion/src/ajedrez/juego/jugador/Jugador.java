/**
 *
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.juego.jugador;

import java.io.Serializable;
import ajedrez.excepciones.TipoNoValidoExcepcion;


public class Jugador implements Serializable{

	private int tipoJugador;
	private int numeroJugador;
	private int turnos;
	private String nombreJugador;

	/**
	* Constructor que crea un Jugador con su tipo(humano o maquina), le asigna su numero de Jugador y su nombre, arrojando una excepcion cuando el tipo es invalido
	* @param tipoJugador -- Existen dos tipos, humano o maquina, 1 y 2 respectivamente
	* @param numeroJugador -- El numero que se le asignara al Jugador
	* @param nombreJugador -- El nombre del Jugador
	* @throws TipoNoValidoExcepcion -- Se arroja cuando el tipo de Jugador es menor a 1 o mayor a 2
	*/
	public Jugador(int tipoJugador,int numeroJugador, String nombreJugador) throws TipoNoValidoExcepcion{
		if(tipoJugador<1||tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		this.tipoJugador = tipoJugador;
		this.turnos = 0;
		this.numeroJugador = numeroJugador;
		this.nombreJugador = nombreJugador;
	}

	/**
	* Metodo que suma un turno al Jugador
	*/
	public void sumarTurno(){
		turnos++;
	}

	/**
	* Metodo que obtiene los turnos del Jugador como un numero entero
	* @return Turnos del Jugador
	*/
	public int obtenerTurnos(){
		return turnos;
	}

	/**
	* Metodo que obtiene el nombre del Jugador como una cadena de texto
	* @return Cadena de texto con el nombre del Jugador
	*/
	public String obtenerNombreJugador(){
		return nombreJugador;
	}

	/**
	* Metodo que obtiene el numero del Jugador como numero entero
	* @return numero del Jugador
	*/
	public int obtenerNumeroJugador(){
		return numeroJugador;
	}

	/**
	* Metodo que obtiene el tipo del Jugador como numero entero
	* @return tipo del Jugador
	*/
	public int obtenerTipoJugador(){
		return tipoJugador;
	}

	/**
	* Metodo que asigna un numero entero al tipo del Jugador, lanzando una excepcion si el tipo no es valido (1 o 2)
	* @param tipoJugador -- tipoDelJugador (Humano o maquina)
	* @throws TipoNoValidoExcepcion -- Se arroja cuando el tipo no es valido
	*/
	public void asignarTipoJugador(int tipoJugador) throws TipoNoValidoExcepcion{
		if(tipoJugador<1||tipoJugador>2)
			throw new TipoNoValidoExcepcion("El jugador solo puede ser tipo 1 (humano) o tipo 2 (computadora");
		this.tipoJugador = tipoJugador;
	}


}
