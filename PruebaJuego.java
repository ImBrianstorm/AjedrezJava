public class PruebaJuego{
	public static void main(String[] args){
		try{
			Menus menu = new Menus();
			menu.menuMovimiento();
		}catch(CoordenadaNoValidaExcepcion e){
			System.out.println(e);
		}
	}
}