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
               this.forToString(0) +
               "    + — + — + — + — + — + — +\n" +
               " 2  | " + tablero[1][0].toString() + " | " + tablero[1][1].toString() + " | " + tablero[1][2].toString() + " | " + tablero[1][3].toString() + " | " + tablero[1][4].toString() + " | " + tablero[1][5].toString() + " |\n" +
               "    + — + — + — + — + — + — +\n" +
               " 3  |   |   |   |   |   |   |\n" +
               "    + — + — + — + — + — + — +\n" +
               " 4  |   |   |   |   |   |   |\n" +
               "    + — + — + — + — + — + — +\n" +
               " 5  | " + tablero[4][0].toString() + " | " + tablero[4][1].toString() + " | " + tablero[4][2].toString() + " | " + tablero[4][3].toString() + " | " + tablero[4][4].toString() + " | " + tablero[4][5].toString() + " |\n" +
               "    + — + — + — + — + — + — +\n" +
               " 6  | " + tablero[5][0].toString() + " | " + tablero[5][1].toString() + " | " + tablero[5][2].toString() + " | " + tablero[5][3].toString() + " | " + tablero[5][4].toString() + " | " + tablero[5][5].toString() + " |\n" +
               "    + — + — + — + — + — + — +";
     }

	private String forToString(int fixed){
	     String string = " " + (fixed+1) + "  | ";
	     for(int i=0;i<=5;i++)
	          string += tablero[fixed][i].toString() + " | ";
	     string += "\n";

	     return string;
}

}