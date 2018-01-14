import java.util.Random;

public class JuegoDeExtincion extends Tablero{

	private int tipo;
	private int turnos;
	private int nivel;
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private boolean noEsPosibleMover;
	private boolean coronacionPeon;
	private boolean leyendaCapturado;
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

	public JuegoDeExtincion(int tipo,int nivel,String nombrePrimerJugador,String nombreSegundoJugador) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion,NivelNoValidoExcepcion{
		super(6,6);
		this.turnos = 0;
		this.tipo = tipo;
		this.coronacionPeon = false;
		this.nivel = nivel;
		this.leyendaCapturado = false;
		if(tipo==1){
			primerJugador = new Jugador(1,1,nombrePrimerJugador);
			segundoJugador = new Jugador(1,2,nombreSegundoJugador);
		}
		else if(tipo==2){
			primerJugador = new Jugador(1,1,nombrePrimerJugador);
			segundoJugador = new Jugador(2,2,"IA");
		}
		if(tipo<1||tipo>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		if(nivel<1||nivel>3)
			throw new NivelNoValidoExcepcion("Este constructor solo admite del nivel 1 al nivel 3");
	}

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(1,6,1,primerJugador);
		tablero[5][1] = new Caballo(1,6,2,primerJugador);
		tablero[5][2] = new Dama(1,6,3,primerJugador);
		tablero[5][3] = new Rey(1,6,4,primerJugador);
		tablero[5][4] = new Caballo(1,6,5,primerJugador);
		tablero[5][5] = new Torre(1,6,6,primerJugador);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(1,5,i+1,primerJugador);

		numeroPeonesJ1 = 6;
		numeroTorresJ1 = 2;
		numeroCaballosJ1 = 2;
		numeroDamasJ1 = 1;
		numeroReyesJ1 = 1;
		
		//JUGADOR 2
		tablero[0][0] = new Torre(2,1,1,segundoJugador);
		tablero[0][1] = new Caballo(2,1,2,segundoJugador);
		tablero[0][2] = new Dama(2,1,3,segundoJugador);
		tablero[0][3] = new Rey(2,1,4,segundoJugador);
		tablero[0][4] = new Caballo(2,1,5,segundoJugador);
		tablero[0][5] = new Torre(2,1,6,segundoJugador);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,2,i+1,segundoJugador);

		numeroPeonesJ2 = 6;
		numeroTorresJ2 = 2;
		numeroCaballosJ2 = 2;
		numeroDamasJ2 = 1;
		numeroReyesJ2 = 1;

		System.out.print("\033[H\033[2J");
    	System.out.flush();
	}

	public void capturarPieza(Pieza pieza,Pieza piezaEliminada,int tipoJugador) throws TipoNoValidoExcepcion,EliminacionInvalidaExcepcion{
		if(tipoJugador<1&&tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este metodo solo admite tipo 1 (humano) o tipo 2 (computadora");
		try{
			pieza.validarEliminar(piezaEliminada);
		}catch(EliminacionInvalidaExcepcion e){
			if(tipoJugador==1)
				System.out.println(e);
			throw new EliminacionInvalidaExcepcion();
		}	
	}

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

	private void capturaAlPaso(Pieza peonAtacante,Pieza peonAtacado) throws CapturaNoValidaExcepcion{
		if(peonAtacado.obtenerTurnoPeonDosEscaques()+1!=peonAtacante.obtenerJugadorDeLaPieza().obtenerTurnos())
			throw new CapturaNoValidaExcepcion("La captura solo puede realizarse en la jugada inmediatamente siguiente al avance de salida del peon de dos escaques hacia delante");
		super.quitarPiezaTablero(peonAtacado);
	}

	public void moverPieza(Pieza pieza,int fila,int columna,Jugador jugadorEnTurno) throws MovimientoNoValidoExcepcion,EliminacionInvalidaExcepcion,TipoNoValidoExcepcion{
		int tipoJugador = jugadorEnTurno.obtenerTipoJugador();
		if(tipoJugador<1&&tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
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
						System.out.println("\n" + e);
					noEsPosibleMover = true;
					throw new EliminacionInvalidaExcepcion();
				}
			}
			if(obtenerPieza(fila,columna)!=null){
				capturarPieza(pieza,obtenerPieza(fila,columna),tipoJugador);
				restarCantidadPiezas(obtenerPieza(fila,columna));
				tablero[obtenerPieza(fila,columna).obtenerFila()-1][obtenerPieza(fila,columna).obtenerColumna()-1] = null;
				activarLeyendaCapturado();
			}
			super.quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarPosicion(fila,columna);
			tablero[fila-1][columna-1].sumarMovimiento();
		}catch(MovimientoNoValidoExcepcion e){
			if(tipoJugador==1)
				System.out.println("\n" + e);
			noEsPosibleMover = true;
			throw new MovimientoNoValidoExcepcion();
		}catch(ArrayIndexOutOfBoundsException e){
			if(tipoJugador==1)
				System.out.println("\nMovimiento no permitido: está fuera del tablero");
			noEsPosibleMover = true;
			throw new ArrayIndexOutOfBoundsException();
		}catch(NullPointerException e){
			if(tipoJugador==1)
				System.out.println("\nMovimiento no permitido: no puedes mover una pieza que no existe");
			noEsPosibleMover = true;
			throw new NullPointerException();
		}

		//Coronacion del peon
		if(pieza.obtenerNombre().equals("Peon") && noEsPosibleMover==false){
			activarCoronacionPeon(pieza,fila,columna);
		}
		jugadorEnTurno.sumarTurno();
	}

	public void turnoHumano(Jugador jugadorEnTurno){
		Menus menu = new Menus();
		boolean coordenadaNoValida;
		boolean movimientoNoValido;
		int numeroJugador = jugadorEnTurno.obtenerNumeroJugador();
		String nombreJugador = jugadorEnTurno.obtenerNombreJugador();

		if(jugadorEnTurno.obtenerNumeroJugador()==1)
			System.out.println("\n" + super.toString());
		else if(jugadorEnTurno.obtenerNumeroJugador()==2)
			System.out.println("\n" + super.toString(2));

		if(leyendaCapturado==true){
			System.out.println("¡Capturado!");
			desactivarLeyendaCapturado();
		}
		do{
			do{
				coordenadaNoValida = false;
				movimientoNoValido = false;
				try{
					menu.menuMovimiento1(numeroJugador,nombreJugador,this);
				}catch(CoordenadaNoValidaExcepcion e){
					System.out.println("\n" + e + "\nIntentalo de nuevo");
					coordenadaNoValida = true;
				}catch(MovimientoNoValidoExcepcion e){
					System.out.println("\n" + e + "\nIntentalo de nuevo");
					movimientoNoValido = true;
				}catch(NullPointerException e){
					System.out.println("\nNo puedes tomar una pieza inexistente\nIntentalo de nuevo");
					coordenadaNoValida = true;
				}
			}while(coordenadaNoValida||movimientoNoValido);

			coordenadaNoValida = false;
			movimientoNoValido = false;
			try{
				menu.menuMovimiento2(this);
			}catch(CoordenadaNoValidaExcepcion e){
				System.out.println(e + "\nIntentalo de nuevo");
				coordenadaNoValida = true;
			}catch(MovimientoNoValidoExcepcion e){
				System.out.println(e + "\nIntentalo de nuevo");
				movimientoNoValido = true;
			}catch(NullPointerException e){
				System.out.println("No existe esa coordenada\nIntentalo de nuevo");
				coordenadaNoValida = true;
			}

			try {
				this.moverPieza(super.obtenerPieza(menu.obtenerFilaInicial(),menu.obtenerColumnaInicial()),menu.obtenerFilaFinal(),menu.obtenerColumnaFinal(),jugadorEnTurno);
			}catch(MovimientoNoValidoExcepcion e){
				System.out.println("Intentalo de nuevo");
					movimientoNoValido = true;
			}catch(EliminacionInvalidaExcepcion e){
				System.out.println("Intentalo de nuevo");
					movimientoNoValido = true;
			}catch(TipoNoValidoExcepcion e){
			}
		}while(coordenadaNoValida||movimientoNoValido);
		
		turnos++;
	}

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

	public void turno(){
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
				System.out.println("¡Es un empate!");
			else
				System.out.println("¡El ganador es " + ganador.obtenerNombreJugador() + " :)");
			
		}
		else if(this.tipo==2){
			for(int i=0;i<turnosNivel;i++){
				this.turnoHumano(primerJugador);
				ganador = verificarTablero();
				if(ganador!=null)
					break;
				if(coronacionPeon==true){
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
				System.out.println("¡Ha ganado la computadora! :(");
			else{
				if(ganador==primerJugador)
					System.out.println("¡Ganaste! :)");
				else if(ganador==segundoJugador)
					System.out.println("¡Ha ganado la computadora! :(");
			}
		}
	}

	public Jugador verificarTablero(){
		if(numeroPeonesJ1==0||numeroTorresJ1==0||numeroCaballosJ1==0||numeroDamasJ1==0||numeroReyesJ1==0)
			return segundoJugador;
		else if(numeroPeonesJ2==0||numeroTorresJ2==0||numeroCaballosJ2==0||numeroDamasJ2==0||numeroReyesJ2==0)
			return primerJugador;
		else
			return null;
	}

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

	private void activarCoronacionPeon(Pieza piezaACoronar,int filaCoronacion,int columnaCoronacion){
		this.piezaACoronar = piezaACoronar;
		this.coronacionPeon = true;
		this.filaCoronacion = filaCoronacion;
		this.columnaCoronacion = columnaCoronacion;
	}

	private void desactivarCoronacionPeon(){
		this.piezaACoronar = null;
		this.coronacionPeon = false;
		this.filaCoronacion = -1;
		this.columnaCoronacion = -1;
	}

	private void activarLeyendaCapturado(){
		this.leyendaCapturado = true;
	}

	private void desactivarLeyendaCapturado(){
		this.leyendaCapturado = false;
	}

	private void coronacionPeon(int tipoJugador,Jugador jugadorEnTurno){
		if(piezaACoronar.obtenerNumeroJugador()==1){
			if(filaCoronacion==1){
				if(tipoJugador==1){
					Menus menu = new Menus();
					menu.menuCoronacion(1,filaCoronacion,columnaCoronacion,jugadorEnTurno);
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
						piezaCoronada = new Torre(1,filaCoronacion,columnaCoronacion,primerJugador);
					}
					else if(nuevaPiezaInt==1){
						piezaCoronada = new Caballo(1,filaCoronacion,columnaCoronacion,primerJugador);
					}
					else if(nuevaPiezaInt==2){
						piezaCoronada = new Dama(1,filaCoronacion,columnaCoronacion,primerJugador);
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
					menu.menuCoronacion(2,filaCoronacion,columnaCoronacion,jugadorEnTurno);
					if(menu.obtenerPiezaCoronada()!=null){
						this.quitarPiezaTablero(piezaACoronar);
						this.agregarPieza(menu.obtenerPiezaCoronada(),filaCoronacion,columnaCoronacion);
					}
				}else if(tipoJugador==2){
					Random rnd = new Random();
					int nuevaPiezaInt = rnd.nextInt(3);
					Pieza piezaCoronada;
					if(nuevaPiezaInt==0){
						piezaCoronada = new Torre(2,filaCoronacion,columnaCoronacion,segundoJugador);
					}
					else if(nuevaPiezaInt==1){
						piezaCoronada = new Caballo(2,filaCoronacion,columnaCoronacion,segundoJugador);
					}
					else if(nuevaPiezaInt==2){
						piezaCoronada = new Dama(2,filaCoronacion,columnaCoronacion,segundoJugador);
					}
				}
			}
		}
	}

}


