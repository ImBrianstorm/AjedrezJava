public class Juego extends Tablero{
	
	public Juego(){
		super();
	}

	private Pieza[][] tablero = super.obtenerArregloPiezas();

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(1,1,6);
		tablero[5][1] = new Caballo(1,2,6);
		tablero[5][2] = new Dama(1,3,6);
		tablero[5][3] = new Rey(1,4,6);
		tablero[5][4] = new Caballo(1,5,6);
		tablero[5][5] = new Torre(1,6,6);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(1,i+1,5);
		
		//JUGADOR 2

		tablero[0][0] = new Torre(2,1,1);
		tablero[0][1] = new Caballo(2,2,1);
		tablero[0][2] = new Dama(2,3,1);
		tablero[0][3] = new Rey(2,4,1);
		tablero[0][4] = new Caballo(2,5,1);
		tablero[0][5] = new Torre(2,6,1);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,i+1,2);
	}
}