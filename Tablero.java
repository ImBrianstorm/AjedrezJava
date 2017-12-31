public class Tablero{

	Pieza[][] tablero;

	public Tablero(){
		tablero = new Pieza[6][6];
	}

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(1,1,1);
		tablero[5][1] = new Caballo(1,2,1);
		tablero[5][2] = new Dama(1,3,1);
		tablero[5][3] = new Rey(1,4,1);
		tablero[5][4] = new Caballo(1,5,1);
		tablero[5][5] = new Torre(1,6,1);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(1,i+1,2);
		
		//JUGADOR 2

		tablero[0][0] = new Torre(2,1,6);
		tablero[0][1] = new Caballo(2,2,6);
		tablero[0][2] = new Dama(2,3,6);
		tablero[0][3] = new Rey(2,4,6);
		tablero[0][4] = new Caballo(2,5,6);
		tablero[0][5] = new Torre(2,6,6);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,i+1,5);
	}

	public String toString(){
        return "      A   B   C   D   E   F  \n" +
               "    + — + — + — + — + — + — +\n" +
                             this.forToString(1) +
               "    + — + — + — + — + — + — +\n" +
                             this.forToString(2) +
               "    + — + — + — + — + — + — +\n" +
               " 3  |   |   |   |   |   |   |\n" +
               "    + — + — + — + — + — + — +\n" +
               " 4  |   |   |   |   |   |   |\n" +
               "    + — + — + — + — + — + — +\n" +
                             this.forToString(5) +
               "    + — + — + — + — + — + — +\n" +
                             this.forToString(6) +
               "    + — + — + — + — + — + — +";
     }

	private String forToString(int renglon){
	    String string = " " + (renglon) + "  | ";
	    for(int i=0;i<=5;i++) string += tablero[renglon-1][i].toString() + " | ";
	    string += "\n";

	    return string;
}

}