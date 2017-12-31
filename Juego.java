public class Juego{

		/**
		*Constructor que crea un objeto tipo Juego para usar en el ajedrez
		**/
		public Juego(int tipo) throws OpcionInvalidaExcepcion {
			if(tipo!=1&&tipo!=2) throw new OpcionInvalidaExcepcion();


		}
}