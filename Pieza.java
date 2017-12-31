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

	public void actualizarCoordenadas(int x, int y){
		this.x = x;
		this.y = y;
	}

	public abstract void mover(int x, int y);

	public abstract void eliminar();
}
