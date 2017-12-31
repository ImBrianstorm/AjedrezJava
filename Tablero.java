public class Tablero{

	Pieza[][] tablero;

	public Tablero(){
		tablero = new Pieza[6][6];
	}

	public void iniciarJuego(){
		tablero[5][0] = new Torre(1,1,1);
		tablero[5][1] = new Caballo(1,2,1);
		tablero[5][2] = new Dama(1,3,1);
		tablero[5][3] = new Rey(1,4,1);
		tablero[5][4] = new Caballo(1,5,1);
		tablero[5][5] = new Torre(1,6,1);
	}

	public String toString(){
		return "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +\n" +
			   "|   |   |   |   |   |   |\n" +
			   "+ — + — + — + — + — + — +";
	}
}