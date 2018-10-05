/**
 * Clase que registra juegos en un arreglo y los guarda en un archivo
 * @author Mauricio Chávez
 * @version 15012018
 */

package ajedrez.registro;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import ajedrez.excepciones.LimiteRegistrosAlcanzadoExcepcion;

public class RegistroJuegos implements Serializable{

	private RegistroGanador[] arregloRegistros;
	private int capacidadRegistros;
	private int numeroRegistros;

	/**
	* Constructor que crea un registro vacio, con capacidad de 100
	*/
	public RegistroJuegos(){
		this.arregloRegistros = new RegistroGanador[100];
		this.numeroRegistros = 0;
		this.capacidadRegistros = 100;
	}

	/**
	* Constructor que crea un registro de juegos dado un arreglo de registro de ganadores, un numero de registros y la capacidad de registros
	* @param registrosGanador
	* @param numeroRegistros
	* @param capacidadRegistros
	*/
	public RegistroJuegos(RegistroGanador[] registrosGanador,int numeroRegistros,int capacidadRegistros){
		this.arregloRegistros = registrosGanador;
		this.numeroRegistros = numeroRegistros;
		this.capacidadRegistros = capacidadRegistros;
	}

	/**
	* Constructor de copia
	* @param registroJuegos
	*/
	public RegistroJuegos(RegistroJuegos registroJuegos){
		this.arregloRegistros = registroJuegos.obtenerArregloRegistroGanador();
		this.numeroRegistros = registroJuegos.obtenerNumeroRegistros();
		this.capacidadRegistros = registroJuegos.obtenerCapacidadRegistros();
	}

	/**
	* Metodo que obtiene un arreglo de registros de ganador
	* @see RegistroGanador
	* @return Arreglo de registros de ganador
	*/
	public RegistroGanador[] obtenerArregloRegistroGanador(){
		return arregloRegistros;
	}

	/**
	* Metodo que obtiene el Registro de ganador especificado
	* @see RegistroGanador
	* @return numero de Registro
	*/
	public RegistroGanador obtenerRegistroGanador(int numeroRegistro){
		return arregloRegistros[numeroRegistro-1];
	}

	/**
	* Metodo que copia y devuelve el registro de juegos
	* @return RegistroJuegos
	*/
	public RegistroJuegos obtenerRegistroJuegos(){
		return this;
	}

	/**
	* Metodo que obtiene el numero de registros actual
	* @return numero de Registros
	*/
	public int obtenerNumeroRegistros(){
		return numeroRegistros;
	}

	/**
	*Metodo que suma un registro
	*/
	public void sumarRegistro(){
		numeroRegistros++;
	}

	/**
	* Metodo que obtiene la capacidad de registros
	* @return capacidad de registros
	*/
	public int obtenerCapacidadRegistros(){
		return capacidadRegistros;
	}

	/**
	* Metodo que guarda un Registro de ganador en un arreglo, arrojando una excepcion si ya no es posible guardar otro registro
	* @see RegistroGanador
	* @param juego -- Registro para registrar
	* @throws LimiteRegistrosAlcanzadoExcepcion -- Se arroja si ya no es posible guardar un nuevo registro
	*/
	public void registrarJuego(RegistroGanador juego) throws LimiteRegistrosAlcanzadoExcepcion{
		if(numeroRegistros==capacidadRegistros){
			throw new LimiteRegistrosAlcanzadoExcepcion("Ya no puedes registrar mas juegos");
		}
		arregloRegistros[numeroRegistros++] = juego;
	}

	/**
	* Metodo que guarda un Registro de juego en un archivo
	* @see RegistroGanador
	*/
	public void guardarRegistroJuego(){
		ObjectOutputStream escritor = null;
		try{
			escritor = new ObjectOutputStream(new FileOutputStream("../../data/registroJuego.dat"));
			for(int i=0;i<numeroRegistros;i++){
				escritor.writeObject(arregloRegistros[i]);
			}
		}catch(NotSerializableException e){
			System.out.println((char)27 + "[31mError en la grabacion. Objeto no serializable");
		}catch(IOException e){
			System.out.println((char)27 + "[31mError en la grabacion: " + e);
		}finally{
			if(escritor!=null){
				try{
					escritor.close();
				}catch(IOException e){
					System.out.println((char)27 + "[31mHubo un error al cerrar el archivo");
				}
			}else{
				System.out.println((char)27 + "[31mNo hay ningún archivo abierto");
			}
		}
	}


	/**
	* Metodo que lee un registro de juegos de un archivo
	*/
	public void leerRegistroJuegos(){
		ObjectInputStream lector = null;
		try{
			lector = new ObjectInputStream(new FileInputStream("../../data/registroJuego.dat"));
			numeroRegistros = 0;
			Object objeto;
			do{
				objeto = lector.readObject();
				if(objeto!=null)
					arregloRegistros[numeroRegistros++] = (RegistroGanador) objeto;
			}while(objeto!=null);
		}catch(EOFException e){}
		catch(ClassNotFoundException e){
			System.out.println((char)27 + "[31mEl objeto recuperado no es de la clase RegistroJuegos");
		}catch(FileNotFoundException e){
			System.out.println((char)27 + "[31mEl archivo registroJuego.dat no existe");
		}catch(IOException e){
			System.out.println((char)27 + "[31mError de lectura: " + e);
		}finally{
			if(lector!=null){
				try{
					lector.close();
				}catch(IOException e){
					System.out.println((char)27 + "[31mHubo un error al cerrar el archivo");
				}
			}else{
				System.out.println((char)27 + "[31mNo hay ningun abierto");
			}
		}
	}

	/**
	* Metodo que imprime en pantalla todos los registros del arreglo
	*/
	public void imprimirRegistros(){
		int numeroColor = 1;
		System.out.print("\033[H\033[2J");
    	System.out.flush();

    	System.out.println((char)27 + "[37m--REGISTROS--");

		if(arregloRegistros[0]==null){
	   		System.out.println((char)27 + "[32mAun no hay registros de juego :/\n\nDeberias jugar antes de usar esta opcion");
	   	}else{
			for(int i=0;i<numeroRegistros;i++){
				System.out.println((char)27 + "[3" + numeroColor++ +"m\n" + arregloRegistros[i].toString());
				if(numeroColor==7)
					numeroColor=1;
			}
		}
	}

}
