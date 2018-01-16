import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.EOFException;
import java.io.FileNotFoundException;

public class RegistroJuegos implements Serializable{

	private RegistroGanador[] arregloRegistros;
	private final int capacidadRegistros;
	private static int numeroRegistros;

	public RegistroJuegos(int capacidadRegistros){
		this.arregloRegistros = new RegistroGanador[capacidadRegistros];
		this.numeroRegistros = 0;
		this.capacidadRegistros = capacidadRegistros;
	}

	public RegistroGanador[] obtenerArregloRegistroGanador(){
		return arregloRegistros;
	}

	public RegistroGanador obtenerRegistroGanador(int numeroRegistro){
		return arregloRegistros[numeroRegistro-1];
	}

	public int obtenerNumeroRegistros(){
		return numeroRegistros;
	}

	public int obtenerCapacidadRegistros(){
		return capacidadRegistros;
	}

	public void registrarJuego(RegistroGanador juego) throws LimiteRegistrosAlcanzadoExcepcion{
		if(numeroRegistros==capacidadRegistros){
			throw new LimiteRegistrosAlcanzadoExcepcion("Ya no puedes registrar mas juegos");
		}
		this.arregloRegistros[numeroRegistros++] = juego;
	}

	public void guardarRegistroJuego(){
		ObjectOutputStream escritor = null;
		try{
			escritor = new ObjectOutputStream(new FileOutputStream("registroJuego.dat"));
			escritor.writeObject(this);
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

	public void leerRegistroJuegos(){
		ObjectInputStream lector = null;
		RegistroJuegos registro = null;
		try{
			lector = new ObjectInputStream(new FileInputStream("registroJuego.dat"));
			do{
				registro = (RegistroJuegos) lector.readObject();
			}while(registro!=null);
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
		this.arregloRegistros = registro.obtenerArregloRegistroGanador();
		this.numeroRegistros = this.obtenerNumeroRegistros();
	}

	public void imprimirRegistros(){
		int numeroColor = 1;
		System.out.print("\033[H\033[2J");
    	System.out.flush();

    	if(numeroRegistros==0){
    		System.out.println((char)27 + "[37mAun no hay registros de juego :/\nDeberias jugar antes de usar esta opcion");
    	}else{
    		System.out.println((char)27 + "[37m--REGISTROS--\n");
			for(int i=0;i<numeroRegistros;i++){
				System.out.println((char)27 + "[3" + numeroColor++ +"m" +arregloRegistros[i].toString());
				if(numeroColor==7)
					numeroColor=1;
			}
		}
	}

}