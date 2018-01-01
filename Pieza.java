public abstract class Pieza{
	
	private String pieza;
	private int jugador;
	private int columna;
	private int fila;

	public Pieza(String pieza, int jugador, int columna, int fila){
		this.pieza = pieza;
		this.jugador = jugador ;
		this.columna = columna;
		this.fila =fila;
	}

	public void asignarJugador(int jugador){
		this.jugador = jugador;
	}

	public void asignarNombrePieza(String nombre){
		this.pieza = nombre ;
	}

	public void asignarCoordenadas(int columna, int fila){
		this.columna = columna;
		this.fila = fila;
	}

	public String obtenerNombrePieza(){
		return pieza;
	}

	public int obtenerNumeroJugador(){
		return jugador;
	}

	public int obtenerNumeroColumna(){
		return columna;
	}

	public int obtenerNumeroFila(){
		return fila;
	}

	public abstract void validarMovimiento(int columna, int fila,Pieza[][] arregloPiezas) throws MovimientoNoValidoExcepcion;

	public void validarEliminar(Pieza piezaEliminada)throws EliminacionInvalidaExcepcion{
		if(piezaEliminada==null){
			throw new EliminacionInvalidaExcepcion("No puedes eliminar una pieza que no existe");
		}
		else if(this.obtenerNumeroJugador()==piezaEliminada.obtenerNumeroJugador()){
			throw new EliminacionInvalidaExcepcion("No puedes eliminar esta pieza porque es tuya.");
		}
	}

	public String toString() {
		if(this==null) 
			return " ";
		else
			return (jugador==1) ? String.valueOf(pieza.charAt(0)) : String.valueOf(pieza.charAt(0)).toLowerCase();
	}

}
