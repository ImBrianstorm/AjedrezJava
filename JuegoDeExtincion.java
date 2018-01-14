import java.util.Random;

public class JuegoDeExtincion extends Tablero{

	private int tipo;
	private int turnos;
	private Jugador primerJugador;
	private Jugador segundoJugador;
	private boolean noEsPosibleMover;

	public JuegoDeExtincion(int tipo, String nombrePrimerJugador, String nombreSegundoJugador) throws TamañoNoSoportadoExcepcion,TipoNoValidoExcepcion{
		super(6,6);
		this.turnos = 0;
		this.tipo = tipo;
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
	}

	public void iniciarJuego(){
		
		//JUGADOR 1
		tablero[5][0] = new Torre(1,6,1);
		tablero[5][1] = new Caballo(1,6,2);
		tablero[5][2] = new Dama(1,6,3);
		tablero[5][3] = new Rey(1,6,4);
		tablero[5][4] = new Caballo(1,6,5);
		tablero[5][5] = new Torre(1,6,6);

		for(int i=0;i<=5;i++) tablero[4][i] = new Peon(1,5,i+1);
		
		//JUGADOR 2
		tablero[0][0] = new Torre(2,1,1);
		tablero[0][1] = new Caballo(2,1,2);
		tablero[0][2] = new Dama(2,1,3);
		tablero[0][3] = new Rey(2,1,4);
		tablero[0][4] = new Caballo(2,1,5);
		tablero[0][5] = new Torre(2,1,6);

		for(int i=0;i<=5;i++) tablero[1][i] = new Peon(2,2,i+1);
	}

	public void capturarPieza(Pieza pieza,Pieza piezaEliminada,int tipoJugador) throws TipoNoValidoExcepcion,EliminacionInvalidaExcepcion{
		if(tipoJugador<1&&tipoJugador>2)
			throw new TipoNoValidoExcepcion("Este constructor solo admite tipo 1 (humano) o tipo 2 (computadora");
		try{
			pieza.validarEliminar(piezaEliminada);
			tablero[piezaEliminada.obtenerFila()-1][piezaEliminada.obtenerColumna()-1] = null; 
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

	private void capturaAlPaso(Pieza peonAtacado){
		super.quitarPiezaTablero(peonAtacado);
	}

	public void moverPieza(Pieza pieza,int fila,int columna,int tipoJugador) throws MovimientoNoValidoExcepcion,EliminacionInvalidaExcepcion,TipoNoValidoExcepcion{
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
				capturaAlPaso(pieza.obtenerPeonEliminado());
				pieza.deshabilitarCapturaAlPaso();
				pieza.eliminarPeonEliminado();
			}
			if(obtenerPieza(fila,columna)!=null){
				capturarPieza(pieza,obtenerPieza(fila,columna),tipoJugador);				
			}
			super.quitarPiezaTablero(pieza);
			tablero[fila-1][columna-1] = pieza;			
			tablero[fila-1][columna-1].asignarPosicion(fila,columna);
			tablero[fila-1][columna-1].sumarMovimiento();
		}catch(MovimientoNoValidoExcepcion e){
			if(tipoJugador==1)
				System.out.println(e);
			noEsPosibleMover = true;
			throw new MovimientoNoValidoExcepcion();//INTENTO
		}catch(ArrayIndexOutOfBoundsException e){
			if(tipoJugador==1)
				System.out.println("Movimiento no permitido: está fuera del tablero");
			noEsPosibleMover = true;
			throw new ArrayIndexOutOfBoundsException();
		}catch(NullPointerException e){
			if(tipoJugador==1)
				System.out.println("Movimiento no permitido: no puedes mover una pieza que no existe");
			noEsPosibleMover = true;
			throw new NullPointerException();
		}

		//Coronacion del peon
		if(pieza.obtenerNombre().equals("Peon") && noEsPosibleMover==false){
			if(pieza.obtenerNumeroJugador()==1){
				if(fila==1){
					if(tipoJugador==1){
						Menus menu = new Menus();
						menu.menuCoronacion(1,fila,columna);
						if(menu.obtenerPiezaCoronada()!=null){
							this.quitarPiezaTablero(pieza);
							this.agregarPieza(menu.obtenerPiezaCoronada(),fila,columna);
						}
					}else if(tipoJugador==2){
						Random rnd = new Random();
						int nuevaPiezaInt = rnd.nextInt(3);
						Pieza piezaCoronada;
						if(nuevaPiezaInt==0){
							piezaCoronada = new Torre(1,fila,columna);
						}
						else if(nuevaPiezaInt==1){
							piezaCoronada = new Caballo(1,fila,columna);
						}
						else if(nuevaPiezaInt==2){
							piezaCoronada = new Dama(1,fila,columna);
						}
					}
				}
			}else if(pieza.obtenerNumeroJugador()==2){
				if(fila==this.obtenerNumeroFilas()){
					if(tipoJugador==1){
						Menus menu = new Menus();
						menu.menuCoronacion(2,fila,columna);
						if(menu.obtenerPiezaCoronada()!=null){
							this.quitarPiezaTablero(pieza);
							this.agregarPieza(menu.obtenerPiezaCoronada(),fila,columna);
						}
					}else if(tipoJugador==2){
						Random rnd = new Random();
						int nuevaPiezaInt = rnd.nextInt(3);
						Pieza piezaCoronada;
						if(nuevaPiezaInt==0){
							piezaCoronada = new Torre(2,fila,columna);
						}
						else if(nuevaPiezaInt==1){
							piezaCoronada = new Caballo(2,fila,columna);
						}
						else if(nuevaPiezaInt==2){
							piezaCoronada = new Dama(2,fila,columna);
						}
					}
				}
			}
		}
	}

	public void turnoHumano(int numeroJugador,String nombreJugador){
		Menus menu = new Menus();
		boolean coordenadaNoValida;
		boolean movimientoNoValido;
		do{
			do{
				coordenadaNoValida = false;
				movimientoNoValido = false;
				try{
					menu.menuMovimiento1(numeroJugador,nombreJugador,this);
				}catch(CoordenadaNoValidaExcepcion e){
					System.out.println(e + "\nIntentalo de nuevo");
					coordenadaNoValida = true;
				}catch(MovimientoNoValidoExcepcion e){
					System.out.println(e + "\nIntentalo de nuevo");
					movimientoNoValido = true;
				}catch(NullPointerException e){
					System.out.println("No puedes tomar una pieza inexistente\nIntentalo de nuevo");
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
				this.moverPieza(super.obtenerPieza(menu.obtenerFilaInicial(),menu.obtenerColumnaInicial()),menu.obtenerFilaFinal(),menu.obtenerColumnaFinal(),1);
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

	public void turnoComputadora(int numeroJugador){
		Random rdm = new Random();
		int filaInicial;
		int filaFinal;
		int columnaInicial;
		int columnaFinal;
		boolean piezaInamovible;

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
				this.moverPieza(super.obtenerPieza(filaInicial,columnaInicial),filaFinal,columnaFinal,2);
			}catch(MovimientoNoValidoExcepcion e){
				noEsPosibleMover = true;
			}catch(EliminacionInvalidaExcepcion e){
				noEsPosibleMover = true;
			}catch(TipoNoValidoExcepcion e){
			}
		}while(noEsPosibleMover);
		turnos++;
	}

}