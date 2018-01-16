import java.util.Random;

/**
 * Clase que se encarga de ajustar distintas clases para jugar JuegoDeExtincion
 * @see Tablero
 * @see Jugador
 * @see Pieza
 * @see Menus
 * @author Mauricio Chávez
 * @version 15012018
 */
public class JuegoDeExtincion extends Tablero{

	private int tipo;
	private int turnos;
	private int nivel;
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private Jugador ganador;
	private boolean noEsPosibleMover;
	private boolean coronacionPeon;
	private boolean leyendaCapturadoHumano;
	private boolean leyendaCapturadoComputadora;
	private Pieza piezaACoronar;
	private int filaCoronacion;
	private int columnaCoronacion;
	
	private int numeroPeonesJ1;
	private int numeroTorresJ1;
	private int numeroCaballosJ1;
	private int numeroDamasJ1;
	private int numeroReyesJ1;

	private int numeroPeonesJ2;
	private int numeroTorresJ2;
	private int numeroCaballosJ2;
	private int numeroDamasJ2;
	private int numeroReyesJ2;

	/**
	* Constructor que crea un Juego de Extincion con los parametros especificados
	* @param tipoJuego -- 1 si es humano contra humano o 2 si es humano contra maquina
	* @param nivel -- 1 maximo 20 turnos, 2 maximo 40 turnos y 3 maximo 100 turnos
	* @param nombrePrimerJugador -- nombre del primer jugador
	* @param nombreSegundoJugador -- nombre del segundo jugador
	* @throws TamañoNoSoportado -- Se arrojaria si el tamaño de tablero no fuera valido
	* @throws TipoNoValido -- Se arroja si el tipo de juego no es 1 ni 2
	* @throws NivelNoValido -- Se arroja si el nivel de juego no es 1, 2 o 3
	*/
	public JuegoDeExtincion(int tipoJuego,int nivel,String nombrePrimerJugador,String nombreSegundoJugador) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion{
		super(6,6);
		this.turnos = 0;
		this.tipo = tipoJuego;
		this.coronacionPeon = false;
		this.nivel = nivel;
		this.leyendaCapturadoHumano = false;
		this.leyendaCapturadoComputadora = false;
		this.ganador = null;
		if(tipo==1){
			primerJugador = new Jugador(1,1,nombrePrimerJugador);
			segundoJugador = new Jugador(1,2,nombreSegundoJugador);
		}
		else if(tipo==2){
			primerJugador = new Jugador(1,1,nombrePrimerJugador);
			segundoJugador = new Jugador(2,2,"Computadora");
		}
		if(tipoJuego<1||tipoJuego>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		if(nivel<1||nivel>3)
			throw new NivelNoValidoExcepcion("Este constructor solo admite del nivel 1 al nivel 3");
	}

	/**
	* Metodo que obtiene al primer jugador del JuegoDeExtincion
	* @return primerJugador
	*/
	public Jugador obtenerPrimerJugador(){
		return primerJugador;
	}

	/**
	* Metodo que obtiene al segundo jugador del JuegoDeExtincion
	* @return segundoJugador
	*/
	public Jugador obtenerSegundoJugador(){
		return segundoJugador;
	}

	/**
	* Metodo que obtiene al ganador del JuegoDeExtincion
	* @return ganador
	*/
	public Jugador obtenerGanador(){
		return ganador;
	}

	/**
	*Metodo que asigna como primer jugador el parametro especificado
	* @param primerJugador -- jugador a asignar
	*/
	public void asignarPrimerJugador(Jugador primerJugador){
		this.primerJugador = primerJugador;
	}

	/**
	*Metodo que asigna como segundo jugador el parametro especificado
	* @param segundoJugador -- jugador a asignar
	*/
	public void asignarSegundoJugador(Jugador segundoJugador){
		this.segundoJugador = segundoJugador;
	}

	/**
	*Metodo que asigna como ganador el parametro especificado
	* @param ganador -- jugador a asignar
	*/
	public void asignarGanador(Jugador ganador){
		this.ganador = ganador;
	}

	/**
	*Metodo que inicia el juego, preparandolo para jugarlo
	*/
	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(6,1,primerJugador);
		tablero[5][1] = new Caballo(6,2,primerJugador);
		tablero[5][2] = new Dama(6,3,primerJugador);
		tablero[5][3] = new Rey(6,4,primerJugador);
		tablero[5][4] = new Caballo(6,5,primerJugador);
		tablero[5][5] = new Torre(6,6,primerJugador);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(5,i+1,primerJugador);

		numeroPeonesJ1 = 6;
		numeroTorresJ1 = 2;
		numeroCaballosJ1 = 2;
		numeroDamasJ1 = 1;
		numeroReyesJ1 = 1;
		
		//JUGADOR 2
		tablero[0][0] = new Torre(1,1,segundoJugador);
		tablero[0][1] = new Caballo(1,2,segundoJugador);
		tablero[0][2] = new Dama(1,3,segundoJugador);
		tablero[0][3] = new Rey(1,4,segundoJugador);
		tablero[0][4] = new Caballo(1,5,segundoJugador);
		tablero[0][5] = new Torre(1,6,segundoJugador);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,i+1,segundoJugador);

		numeroPeonesJ2 = 6;
		numeroTorresJ2 = 2;
		numeroCaballosJ2 = 2;
		numeroDamasJ2 = 1;
		numeroReyesJ2 = 1;

		System.out.print("\033[H\033[2J");
    	System.out.flush();
	}

	/**
	* Metodo que captura una pieza, arrojando excepciones en caso de que no sea posible
	* @param pieza -- pieza que captura
	* @param piezaEliminada -- pieza que sera capturada
	* @param tipoJugador -- 1 si es humano o 2 si es una computadora
	* @throws TipoNoValidoExcepcion -- si el tipo de jugador es diferente a 1 y 2
	* @throws EliminacionInvalidaExcepcion -- si no es posible capturar la pieza
	*/
	public void capturarPieza(Pieza pieza,Pieza piezaEliminada,int tipoJugador) throws TipoNoValidoExcepcion,EliminacionInvalidaExcepcion{
		if(tipoJugador<1&&tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este metodo solo admite tipo 1 (humano) o tipo 2 (computadora");
		try{
			pieza.validarEliminar(piezaEliminada);
		}catch(EliminacionInvalidaExcepcion e){
			if(tipoJugador==1)
				System.out.println((char)27 + "[31m" + e);
			throw new EliminacionInvalidaExcepcion();
		}	
	}

	/**
	* Metodo que mueve una pieza en el Tablero y captura piezas si es posible, arrojando excepciones si no es posible
	* @param pieza -- pieza que se mueve y posiblemente captura
	* @param fila -- fila a la que se movera la pieza
	* @param columna -- columna a la que se movera la pieza
	* @param jugadorEnTurno -- Jugador que movera la pieza
	* @throws MovimientoNoValidoExcepcion -- Se arroja si no es posible mover la pieza a la posicion indicada
	* @throws TipoNoValidoExcepcion -- si el tipo de jugador es diferente a 1 y 2
	* @throws EliminacionInvalidaExcepcion -- si no es posible capturar la pieza
	*/
	public void moverPieza(Pieza pieza,int fila,int columna,Jugador jugadorEnTurno) throws MovimientoNoValidoExcepcion,EliminacionInvalidaExcepcion,TipoNoValidoExcepcion{
		int tipoJugador = jugadorEnTurno.obtenerTipoJugador();
		if(tipoJugador<1&&tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este metodo solo admite tipo 1 (humano) o tipo 2 (computadora");
		noEsPosibleMover = false;
		try{
			pieza.validarMovimiento(fila,columna,this);
			if(pieza.esPosibleEnrocar()){
				enroque(pieza,pieza.obtenerTorreAEnrocar(),pieza.obtenerTipoDeEnroque());
				pieza.deshabilitarEnroque();
				pieza.eliminarTorreAEnrocar();
			}
			if(pieza.esPosibleCapturaAlPaso()){
				try{
					capturaAlPaso(pieza,pieza.obtenerPeonEliminado());
					pieza.deshabilitarCapturaAlPaso();
					pieza.eliminarPeonEliminado();
				}catch(CapturaNoValidaExcepcion e){
					if(tipoJugador==1)
						System.out.println((char)27 + "[31m\n" + e);
					noEsPosibleMover = true;
					throw new EliminacionInvalidaExcepcion();
				}
			}
			if(obtenerPieza(fila,columna)!=null){
				capturarPieza(pieza,obtenerPieza(fila,columna),tipoJugador);
				restarCantidadPiezas(obtenerPieza(fila,columna));
				tablero[obtenerPieza(fila,columna).obtenerFila()-1][obtenerPieza(fila,columna).obtenerColumna()-1] = null;
				if(jugadorEnTurno.obtenerTipoJugador()==1)
					activarLeyendaCapturadoHumano();
				else if(jugadorEnTurno.obtenerTipoJugador()==2)
					activarLeyendaCapturadoComputadora();
			}
			super.quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarPosicion(fila,columna);
			tablero[fila-1][columna-1].sumarMovimiento();
		}catch(MovimientoNoValidoExcepcion e){
			if(tipoJugador==1)
				System.out.println((char)27 + "[31m\n" + e);
			noEsPosibleMover = true;
			throw new MovimientoNoValidoExcepcion();
		}catch(ArrayIndexOutOfBoundsException e){
			if(tipoJugador==1)
				System.out.println((char)27 + "[31m\nMovimiento no permitido: está fuera del tablero");
			noEsPosibleMover = true;
			throw new ArrayIndexOutOfBoundsException();
		}catch(NullPointerException e){
			if(tipoJugador==1)
				System.out.println((char)27 + "[31m\nMovimiento no permitido: no puedes mover una pieza que no existe");
			noEsPosibleMover = true;
			throw new NullPointerException();
		}

		//Coronacion del peon
		if(pieza.obtenerNombre().equals("Peon") && noEsPosibleMover==false){
			activarCoronacionPeon(pieza,fila,columna);
		}
		jugadorEnTurno.sumarTurno();
	}

	/**
	* Metodo que se encarga de ejecutar un turno humano
	* @param jugadorEnTurno -- jugador tipo 1 que movera pieza
	*/
	public void turnoHumano(Jugador jugadorEnTurno){
		Menus menu = new Menus();
		boolean coordenadaNoValida;
		boolean movimientoNoValido;
		int numeroJugador = jugadorEnTurno.obtenerNumeroJugador();
		String nombreJugador = jugadorEnTurno.obtenerNombreJugador();

		if(jugadorEnTurno.obtenerNumeroJugador()==1)
			System.out.println((char)27 + "[37m\n" + super.toString());
		else if(jugadorEnTurno.obtenerNumeroJugador()==2)
			System.out.println((char)27 + "[37m\n" + super.toString(2));

		if(leyendaCapturadoHumano==true){
			System.out.println((char)27 + "[32m¡Capturado!\n");
			desactivarLeyendaCapturadoHumano();
		}

		if(leyendaCapturadoComputadora==true){
			System.out.println((char)27 + "[31m¡Te han capturado!\n");
			desactivarLeyendaCapturadoComputadora();
		}

		do{
			do{
				coordenadaNoValida = false;
				movimientoNoValido = false;
				try{
					menu.menuMovimiento1(numeroJugador,nombreJugador,this);
				}catch(CoordenadaNoValidaExcepcion e){
					System.out.println((char)27 + "[31m\n" + e + ", intentalo de nuevo\n");
					coordenadaNoValida = true;
				}catch(MovimientoNoValidoExcepcion e){
					System.out.println((char)27 + "[31m\n" + e + ", intentalo de nuevo\n");
					movimientoNoValido = true;
				}catch(NullPointerException e){
					System.out.println((char)27 + "[31m\nNo puedes tomar una pieza inexistente, intentalo de nuevo\n");
					coordenadaNoValida = true;
				}
			}while(coordenadaNoValida||movimientoNoValido);

			coordenadaNoValida = false;
			movimientoNoValido = false;
			try{
				menu.menuMovimiento2(this);
			}catch(CoordenadaNoValidaExcepcion e){
				System.out.println((char)27 + "[31m" + e + ", intentalo de nuevo\n");
				coordenadaNoValida = true;
			}catch(NullPointerException e){
				System.out.println((char)27 + "[31mNo existe esta coordenada, intentalo de nuevo\n");
				coordenadaNoValida = true;
			}

			try {
				this.moverPieza(super.obtenerPieza(menu.obtenerFilaInicial(),menu.obtenerColumnaInicial()),menu.obtenerFilaFinal(),menu.obtenerColumnaFinal(),jugadorEnTurno);
			}catch(MovimientoNoValidoExcepcion e){
				System.out.println((char)27 + "[31mIntentalo de nuevo\n");
					movimientoNoValido = true;
			}catch(EliminacionInvalidaExcepcion e){
				System.out.println((char)27 + "[31mIntentalo de nuevo\n");
					movimientoNoValido = true;
			}catch(TipoNoValidoExcepcion e){
			}
		}while(coordenadaNoValida||movimientoNoValido);
		
		turnos++;
	}

	/**
	* Metodo que se encarga de ejecutar un turno de computadora
	* @param jugadorEnTurno -- jugador tipo 2 que movera pieza
	*/
	public void turnoComputadora(Jugador jugadorEnTurno){
		Random rdm = new Random();
		int filaInicial;
		int filaFinal;
		int columnaInicial;
		int columnaFinal;
		boolean piezaInamovible;
		int numeroJugador = jugadorEnTurno.obtenerNumeroJugador();

		do{
			noEsPosibleMover = false;
			do{
				piezaInamovible = false;
				filaInicial = rdm.nextInt(super.obtenerNumeroFilas())+1;
				columnaInicial = rdm.nextInt(super.obtenerNumeroColumnas())+1;

				if(super.obtenerPieza(filaInicial,columnaInicial)==null)
					piezaInamovible = true;
				else 
					if(super.obtenerPieza(filaInicial,columnaInicial).obtenerNumeroJugador()!=numeroJugador)
						piezaInamovible = true;
			}while(piezaInamovible);

			filaFinal = rdm.nextInt(super.obtenerNumeroFilas())+1;
			columnaFinal = rdm.nextInt(super.obtenerNumeroColumnas())+1;
			
			try{
				this.moverPieza(super.obtenerPieza(filaInicial,columnaInicial),filaFinal,columnaFinal,jugadorEnTurno);
			}catch(MovimientoNoValidoExcepcion e){
				noEsPosibleMover = true;
			}catch(EliminacionInvalidaExcepcion e){
				noEsPosibleMover = true;
			}catch(TipoNoValidoExcepcion e){
			}
		}while(noEsPosibleMover);
		turnos++;
	}

	/**
	* Metodo que se encarga de ejecutar el juego con el nivel, tipo y jugadores de la clase, asignando un ganador si lo hay
	*/
	public void jugar(){
		Jugador ganador = null;
		int turnosNivel = 0;

		switch(nivel){
			case 1:
			turnosNivel = 20;
			break;

			case 2:
			turnosNivel = 40;
			break;

			case 3:
			turnosNivel = 100;
			break;
		}

		if(this.tipo==1){
			for(int i=0;i<turnosNivel;i++){
				this.turnoHumano(primerJugador);
				ganador = verificarTablero();
				if(ganador!=null)
					break;
				if(coronacionPeon==true){
					System.out.print("\033[H\033[2J");
    				System.out.flush();
					System.out.println((char)27 + "[37m\n" + super.toString());
					coronacionPeon(1,primerJugador);
					desactivarCoronacionPeon();
				}
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				this.turnoHumano(segundoJugador);
				ganador = verificarTablero();
				if(ganador!=null)
					break;
				if(coronacionPeon==true){
					System.out.print("\033[H\033[2J");
    				System.out.flush();
					System.out.println((char)27 + "[37m\n" + super.toString());
					coronacionPeon(1,segundoJugador);
					desactivarCoronacionPeon();
				}
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				turnos++;
			}

			System.out.print("\033[H\033[2J");
    		System.out.flush();
			System.out.println("\n" + super.toString());
			System.out.println("JUEGO TERMINADO");
			
			if(ganador==null)
				System.out.println((char)27 + "[32m¡Es un empate!");
			else
				System.out.println((char)27 + "[32m¡El ganador es " + ganador.obtenerNombreJugador() + "! :)");
			
		}
		else if(this.tipo==2){
			for(int i=0;i<turnosNivel;i++){
				this.turnoHumano(primerJugador);
				ganador = verificarTablero();
				if(ganador!=null)
					break;
				if(coronacionPeon==true){
					System.out.print("\033[H\033[2J");
    				System.out.flush();
					System.out.println((char)27 + "[37m\n" + super.toString());
					coronacionPeon(1,primerJugador);
					desactivarCoronacionPeon();
				}
				System.out.print("\033[H\033[2J");
    			System.out.flush();
				this.turnoComputadora(segundoJugador);
				ganador = verificarTablero();
				if(ganador!=null)
					break;
				if(coronacionPeon==true){
					coronacionPeon(2,segundoJugador);
					desactivarCoronacionPeon();
				}
				turnos++;
			}

			System.out.print("\033[H\033[2J");
    		System.out.flush();
			System.out.println("\n" + super.toString());
			System.out.println("JUEGO TERMINADO");
			
			if(ganador==null)
				System.out.println((char)27 + "[31m¡Ha ganado la computadora! :(");
			else{
				if(ganador==primerJugador)
					System.out.println((char)27 + "[32m¡Ganaste! :)");
				else if(ganador==segundoJugador)
					System.out.println((char)27 + "[31m¡Ha ganado la computadora! :(");
			}
		}
		this.ganador = ganador;
	}

	/**
	* Metodo que se encarga de verificar el Tablero, buscando si alguno de los jugadores ya perdio un grupo de sus piezas, devolviendo al ganador
	* @return ganador del juego, null si nadie ha ganado
	*/
	public Jugador verificarTablero(){
		if(numeroPeonesJ1==0||numeroTorresJ1==0||numeroCaballosJ1==0||numeroDamasJ1==0||numeroReyesJ1==0)
			return segundoJugador;
		else if(numeroPeonesJ2==0||numeroTorresJ2==0||numeroCaballosJ2==0||numeroDamasJ2==0||numeroReyesJ2==0)
			return primerJugador;
		else
			return null;
	}

	/**
	* Metodo privado auxiliar que se encarga de restar la cantidad de piezas a medida que van siendo eliminadas
	*/
	private void restarCantidadPiezas(Pieza piezaEliminada){
		if(piezaEliminada.obtenerNumeroJugador()==1){
			if(piezaEliminada.obtenerNombre().equals("Peon"))
				numeroPeonesJ1--;
			else if(piezaEliminada.obtenerNombre().equals("Torre"))
				numeroTorresJ1--;
			else if(piezaEliminada.obtenerNombre().equals("Caballo"))
				numeroCaballosJ1--;
			else if(piezaEliminada.obtenerNombre().equals("Dama"))
				numeroDamasJ1--;
			else if(piezaEliminada.obtenerNombre().equals("Rey"))
				numeroReyesJ1--;
		}else if(piezaEliminada.obtenerNumeroJugador()==2){
			if(piezaEliminada.obtenerNombre().equals("Peon"))
				numeroPeonesJ2--;
			else if(piezaEliminada.obtenerNombre().equals("Torre"))
				numeroTorresJ2--;
			else if(piezaEliminada.obtenerNombre().equals("Caballo"))
				numeroCaballosJ2--;
			else if(piezaEliminada.obtenerNombre().equals("Dama"))
				numeroDamasJ2--;
			else if(piezaEliminada.obtenerNombre().equals("Rey"))
				numeroReyesJ2--;
		}
	}

	/**
	* Metodo privado auxiliar que se encarga de sumar la cantidad de piezas a medida que van siendo agregadas
	*/
	private void sumarCantidadPiezas(Pieza piezaAgregada){
		if(piezaAgregada.obtenerNumeroJugador()==1){
			if(piezaAgregada.obtenerNombre().equals("Peon"))
				numeroPeonesJ1++;
			else if(piezaAgregada.obtenerNombre().equals("Torre"))
				numeroTorresJ1++;
			else if(piezaAgregada.obtenerNombre().equals("Caballo"))
				numeroCaballosJ1++;
			else if(piezaAgregada.obtenerNombre().equals("Dama"))
				numeroDamasJ1++;
			else if(piezaAgregada.obtenerNombre().equals("Rey"))
				numeroReyesJ1++;
		}else if(piezaAgregada.obtenerNumeroJugador()==2){
			if(piezaAgregada.obtenerNombre().equals("Peon"))
				numeroPeonesJ2++;
			else if(piezaAgregada.obtenerNombre().equals("Torre"))
				numeroTorresJ2++;
			else if(piezaAgregada.obtenerNombre().equals("Caballo"))
				numeroCaballosJ2++;
			else if(piezaAgregada.obtenerNombre().equals("Dama"))
				numeroDamasJ2++;
			else if(piezaAgregada.obtenerNombre().equals("Rey"))
				numeroReyesJ2++;
		}
	}

	/**
	* Metodo privado auxiliar que se encarga de activar la coronacion del peon
	*/
	private void activarCoronacionPeon(Pieza piezaACoronar,int filaCoronacion,int columnaCoronacion){
		this.piezaACoronar = piezaACoronar;
		this.coronacionPeon = true;
		this.filaCoronacion = filaCoronacion;
		this.columnaCoronacion = columnaCoronacion;
	}

	/**
	* Metodo privado auxiliar que se encarga de desactivar la coronacion del peon
	*/
	private void desactivarCoronacionPeon(){
		this.piezaACoronar = null;
		this.coronacionPeon = false;
		this.filaCoronacion = -1;
		this.columnaCoronacion = -1;
	}

	/**
	* Metodo privado auxiliar que se encarga de activar la leyenda de capturado
	*/
	private void activarLeyendaCapturadoHumano(){
		this.leyendaCapturadoHumano = true;
	}

	/**
	* Metodo privado auxiliar que se encarga de desactivar la leyenda de capturado
	*/
	private void desactivarLeyendaCapturadoHumano(){
		this.leyendaCapturadoHumano = false;
	}

	/**
	* Metodo privado auxiliar que se encarga de activar la leyenda de capturado de la computadora
	*/
	private void activarLeyendaCapturadoComputadora(){
		this.leyendaCapturadoComputadora = true;
	}

	/**
	* Metodo privado auxiliar que se encarga de desactivar la leyenda de capturado de la computadora
	*/
	private void desactivarLeyendaCapturadoComputadora(){
		this.leyendaCapturadoComputadora = false;
	}

	/**
	* Metodo privado auxiliar que se encarga de ejecutar la coronacion del peon
	*/
	private void coronacionPeon(int tipoJugador,Jugador jugadorEnTurno){
		if(piezaACoronar.obtenerNumeroJugador()==1){
			if(filaCoronacion==1){
				if(tipoJugador==1){
					Menus menu = new Menus();
					menu.menuCoronacion(filaCoronacion,columnaCoronacion,jugadorEnTurno);
					if(menu.obtenerPiezaCoronada()!=null){
						restarCantidadPiezas(piezaACoronar);
						this.quitarPiezaTablero(piezaACoronar);
						sumarCantidadPiezas(menu.obtenerPiezaCoronada());
						this.agregarPieza(menu.obtenerPiezaCoronada(),filaCoronacion,columnaCoronacion);
					}
				}else if(tipoJugador==2){
					Random rnd = new Random();
					int nuevaPiezaInt = rnd.nextInt(3);
					Pieza piezaCoronada = null;
					if(nuevaPiezaInt==0){
						piezaCoronada = new Torre(filaCoronacion,columnaCoronacion,primerJugador);
					}
					else if(nuevaPiezaInt==1){
						piezaCoronada = new Caballo(filaCoronacion,columnaCoronacion,primerJugador);
					}
					else if(nuevaPiezaInt==2){
						piezaCoronada = new Dama(filaCoronacion,columnaCoronacion,primerJugador);
					}
					restarCantidadPiezas(piezaACoronar);
					this.quitarPiezaTablero(piezaACoronar);
					sumarCantidadPiezas(piezaCoronada);
					this.agregarPieza(piezaCoronada,filaCoronacion,columnaCoronacion);
				}
			}
		}else if(piezaACoronar.obtenerNumeroJugador()==2){
			if(filaCoronacion==this.obtenerNumeroFilas()){
				if(tipoJugador==1){
					Menus menu = new Menus();
					menu.menuCoronacion(filaCoronacion,columnaCoronacion,jugadorEnTurno);
					if(menu.obtenerPiezaCoronada()!=null){
						restarCantidadPiezas(piezaACoronar);
						this.quitarPiezaTablero(piezaACoronar);
						sumarCantidadPiezas(menu.obtenerPiezaCoronada());
						this.agregarPieza(menu.obtenerPiezaCoronada(),filaCoronacion,columnaCoronacion);
					}
				}else if(tipoJugador==2){
					Random rnd = new Random();
					int nuevaPiezaInt = rnd.nextInt(3);
					Pieza piezaCoronada = null;
					if(nuevaPiezaInt==0){
						piezaCoronada = new Torre(filaCoronacion,columnaCoronacion,segundoJugador);
					}
					else if(nuevaPiezaInt==1){
						piezaCoronada = new Caballo(filaCoronacion,columnaCoronacion,segundoJugador);
					}
					else if(nuevaPiezaInt==2){
						piezaCoronada = new Dama(filaCoronacion,columnaCoronacion,segundoJugador);
					}
					restarCantidadPiezas(piezaACoronar);
					this.quitarPiezaTablero(piezaACoronar);
					sumarCantidadPiezas(piezaCoronada);
					this.agregarPieza(piezaCoronada,filaCoronacion,columnaCoronacion);
				}
			}
		}
	}

	/**
	* Metodo privado auxiliar en el metodo moverPieza que se encarga de hacer el enroque
	*/
	private void enroque(Pieza rey, Pieza torre,int tipoEnroque){
		if(tipoEnroque==1){
			super.quitarPiezaTablero(torre);
			super.agregarPieza(torre,rey.obtenerFila(),rey.obtenerColumna()-1);
		}
		else if(tipoEnroque==2){
			super.quitarPiezaTablero(torre);
			super.agregarPieza(torre,rey.obtenerFila(),rey.obtenerColumna()+1);
		}
	}

	/**
	* Metodo privado auxiliar en el metodo moverPieza que se encarga de hacer la captura al paso
	*/
	private void capturaAlPaso(Pieza peonAtacante,Pieza peonAtacado) throws CapturaNoValidaExcepcion{
		if(peonAtacante.obtenerJugadorDeLaPieza().obtenerNumeroJugador()==1)
			if(peonAtacado.obtenerTurnoPeonDosEscaques()+1!=peonAtacante.obtenerJugadorDeLaPieza().obtenerTurnos())
				throw new CapturaNoValidaExcepcion("La captura solo puede realizarse en la jugada inmediatamente siguiente al avance de salida del peon de dos escaques hacia delante");
		else if(peonAtacante.obtenerJugadorDeLaPieza().obtenerNumeroJugador()==2)
			if(peonAtacado.obtenerTurnoPeonDosEscaques()!=peonAtacante.obtenerJugadorDeLaPieza().obtenerTurnos())
				throw new CapturaNoValidaExcepcion("La captura solo puede realizarse en la jugada inmediatamente siguiente al avance de salida del peon de dos escaques hacia delante");
		super.quitarPiezaTablero(peonAtacado);
	}
}


