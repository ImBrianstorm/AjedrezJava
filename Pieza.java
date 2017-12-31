public abstract class Pieza{
	
	private String pieza;
	private int jugador;
	private int x;
	private int y;

	public Pieza(String pieza, int jugador, int x, int y){
		this.pieza = pieza;
		this.jugador = jugador ;
		this.x = x;
		this.y = y;
	}

	public void asignarJugador(int jugador){
		this.jugador = jugador;
	}

	public String obtenerNombrePieza(){
		return pieza;
	}

	public int obtenerNumeroJugador(){
		return jugador;
	}

	public void actualizarCoordenadas(int x, int y){
		this.x = x;
		this.y = y;
	}

	public abstract void mover(int x, int y);

	public abstract void eliminar();

	public String toString() {
		if(this==null) 
			return " ";
		else
			return (jugador==1) ? String.valueOf(pieza.charAt(0)) : String.valueOf(pieza.charAt(0)).toLowerCase();
	}

}
