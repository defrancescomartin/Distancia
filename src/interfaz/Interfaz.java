package interfaz;

import distancia.*;
import java.util.*;

/*
    Obligatorio 1 - Programación II 
    M2B Ingeniería en Sistemas ORT
    Martín De Francesco(273546)
    Nicolás Ruy López(256563)
 */

public class Interfaz {
    // Clase que despliega el menu de interaccion con el usuario (FrontEnd)

    public void imprimirMenu() {
        System.out.println("1) Registrar jugador \n"
                + "2) Establecer tablero \n"
                + "3) Jugar partida \n"
                + "4) Ranking \n"
                + "5) Fin \n");
    }

    public void menuPrincipal(Sistema s) {
        boolean fin = false;
        int tipoTablero = 1;
        while (!fin) {
            System.out.println("Seleccione una opcion(1-5)");
            imprimirMenu();
            Scanner lec = new Scanner(System.in);
            try {
                int opcion = lec.nextInt();
                switch (opcion) {
                    case 1:
                        registrarJugador(s);
                        break;
                    case 2:
                        tipoTablero = elegirTablero(s);
                        break;
                    case 3:
                        if (s.getListaJugadores().size() >= 2) {
                            System.out.println("Elige al primer jugador");
                            Jugador j1 = elegirJugadores(s);
                            System.out.println("Elige al segundo jugador");
                            Jugador j2 = elegirJugadores(s);
                            while (j1 == j2) {
                                System.out.println("\nPor favor elija jugadores distintos\n");
                                j2 = elegirJugadores(s);
                            }
                            jugarDistancia(s, tipoTablero, j1, j2);
                        } else {
                            System.out.println("\nPor favor registrar al menos dos jugadores\n");
                        }
                        break;

                    case 4:
                        verRanking(s);
                        break;
                    case 5:
                        fin = true;
                        break;
                    default:
                        System.out.println("\nIngrese una opcion valida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError, Ingrese un numero\n");
            }
        }
    }

    public void registrarJugador(Sistema s) { //Registra al Jugador ingresado
        Scanner lec = new Scanner(System.in);

        boolean existe = false;
        while (!existe) {
            System.out.println("Ingrese nombre");
            String nombre = lec.nextLine();
            System.out.println("Ingrese edad");
            int edad = lec.nextInt();
            lec.nextLine();
            System.out.println("Ingrese alias (debe ser unico)");
            String alias = lec.nextLine();
            Jugador j = new Jugador(nombre, edad, alias);
            if (s.existeJugador(j)) {
                System.out.println("\nYa existe un jugador con ese alias, ingrese los datos nuevamente\n");
            } else {
                existe = true;
                s.agregarJugador(j);
                System.out.println("\nJugador registrado!\n");
            }
        }
    }

    public Jugador elegirJugadores(Sistema s) {//Elige al jugador
        boolean valido = false;
        Jugador j = new Jugador();
        Scanner lec = new Scanner(System.in);
        System.out.println("\nQuien va a jugar? (ingrese numero del jugador)");
        do {
            imprimirListaJugadores(s);
            try {
                int jugador = lec.nextInt();
                if (jugador > 0 && jugador < s.getListaJugadores().size() + 1) {
                    j = s.getListaJugadores().get(jugador - 1);
                    valido = true;
                    System.out.println("\n" + s.getListaJugadores().get(jugador - 1) + "\n");
                    lec.nextLine();
                } else {
                    System.out.println("\nError, elija un número valido\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError, ingrese un número\n");
                lec.nextLine();
            }
        } while (!valido);
        return j;
    }

    public void jugarDistancia(Sistema s, int n, Jugador j1, Jugador j2) {
        Scanner lec = new Scanner(System.in);
        s.jugarConTablero(n);
        boolean turnoRojo = true;
        boolean fin = false;
        boolean posIniValida = false;
        boolean ingresos = false;
        boolean rendirse = false;
        int filaIni = -1;
        int colIni = -1;
        boolean posFinValida = false;
        int filaFin = -1;
        int colFin = -1;
        System.out.println(
                "Comienza el juego");
        while (!fin) {
            while (!ingresos) {
                try {
                    if (turnoRojo) {
                        System.out.println("Es turno del jugador Rojo");
                    } else {
                        System.out.println("Es turno del jugador Azul");
                    }
                    imprimirMatriz(s.getTablero());
                    while (!posIniValida) {
                        System.out.println("Ingrese posicion inicial");
                        String posIni = lec.nextLine().toUpperCase();
                        if (posIni.equalsIgnoreCase("x")) {
                            System.out.println("Juego finalizado");
                            fin = true;
                            posIniValida = true;
                            rendirse = true;
                            ingresos = true;
                        } else {
                            filaIni = s.leerFila(posIni);
                            colIni = s.leerColumna(posIni);
                            posIniValida = posicionInicialValidar(s, filaIni, colIni, turnoRojo);
                        }
                    }
                    if (!rendirse) {
                        String matrizMovPosibles[][] = s.matrizMovPosibles(filaIni, colIni, turnoRojo);
                        imprimirMatriz(matrizMovPosibles);
                        System.out.println("Ingrese posicion final");
                        String posFin = lec.nextLine().toUpperCase();
                        if (posFin.equalsIgnoreCase("x")) {
                            System.out.println("Juego finalizado");
                            fin = true;
                            rendirse = true;
                            ingresos = true;
                        } else {
                            filaFin = s.leerFila(posFin);
                            colFin = s.leerColumna(posFin);
                            if (s.validarMovimiento(matrizMovPosibles, filaFin, colFin)) {
                                ingresos = true;
                                s.realizarMovimiento(filaIni, colIni, filaFin, colFin, turnoRojo);
                                imprimirMatriz(s.getTablero());
                            } else {
                                System.out.println("Posicion no valida, porfavor ingrese nuevamente las posiciones");
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error, ingrese una posicion valida o X para finalizar el juego");
                }
            }
            if (s.hayGanador(s, turnoRojo)) {
                fin = true;
                ganador(turnoRojo, j1, j2);
            } else if (rendirse) {
                ganador(!turnoRojo, j1, j2);
                fin = true;
            } else if (s.tieneMovimientos(!turnoRojo)) {
                turnoRojo = !turnoRojo;
            } else {
                System.out.println("No hay movimientos disponibles para este jugador");
            }
            posIniValida = false;
            ingresos = false;
        }
    }

    public static boolean posicionInicialValidar(Sistema s, int fila, int col, boolean turno) { //Valida la posicion inicial
        boolean valido = true;
        if (fila == -1 && (col > 5 || col < 0)) {
            System.out.println("Posicion no valida, porfavor ingrese nuevamente");
            valido = false;
        } else if (!s.chequearTurno(turno, fila, col)) {
            System.out.println("Ingrese una posicion inicial correspondiente a su turno");
            valido = false;
        } else if (!s.hayMovPosibles(s.matrizMovPosibles(fila, col, turno))) {
            System.out.println("No hay movimientos disponibles para esta posicion, ingrese nuevamente");
            valido = false;
        }
        return valido;
    }

    public static void imprimirMatriz(String mat[][]) {//Imprimre la matriz
        String azul = "\033[34m";
        String rojo = "\033[31m";
        String negro = "\u001B[0m";
        String verde = "\u001B[32m";
        System.out.println("    1 2 3 4 5 6");
        for (int i = 0; i < mat.length; i++) {
            System.out.println("   +-+-+-+-+-+-+");
            switch (i) {
                case 0:
                    System.out.print(negro + "A  |");
                    break;
                case 1:
                    System.out.print(negro + "B  |");
                    break;
                case 2:
                    System.out.print(negro + "C  |");
                    break;
                case 3:
                    System.out.print(negro + "D  |");
                    break;
                case 4:
                    System.out.print(negro + "E  |");
                    break;
                case 5:
                    System.out.print(negro + "F  |");
                    break;
                default:
                    System.out.print(negro + "    |");
            }

            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == "A") {
                    System.out.print(azul + mat[i][j] + negro + "|");
                } else if (mat[i][j] == "R") {
                    System.out.print(rojo + mat[i][j] + negro + "|");
                } else if (mat[i][j] == "E") {
                    System.out.print(verde + mat[i][j] + negro + "|");
                } else if (mat[i][j] == "*") {
                    System.out.print(verde + mat[i][j] + negro + "|");
                } else if (mat[i][j] == "#") {
                    System.out.print(verde + mat[i][j] + negro + "|");
                } else if (mat[i][j] == "") {
                    System.out.print(negro + mat[i][j] + " " + "|");
                }
            }
            switch (i) {
                case 0:
                    System.out.print(negro + " A");
                    break;
                case 1:
                    System.out.print(negro + " B");
                    break;
                case 2:
                    System.out.print(negro + " C");
                    break;
                case 3:
                    System.out.print(negro + " D");
                    break;
                case 4:
                    System.out.print(negro + " E");
                    break;
                case 5:
                    System.out.print(negro + " F");
                    break;
                default:
                    System.out.print(negro + " |");
            }
            System.out.println("");
        }

        System.out.println(
                "   +-+-+-+-+-+-+"
                + "\n    1 2 3 4 5 6");
    }

    public void imprimirListaJugadores(Sistema s) { //Imprime lista de jugadores
        ArrayList<Jugador> lista = s.getListaJugadores();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(1 + i + ") " + lista.get(i));
        }
        System.out.println("");
    }

    public static void ganador(boolean turno, Jugador j1, Jugador j2) { //Devuelve el ganador
        if (turno) {
            System.out.println("Partida Finalizada el ganador es el jugador Rojo!");
            j1.aumentarGandas();
            j1.aumentarJugadas();
            j2.aumentarJugadas();
        } else {
            System.out.println("Partida Finalizada el ganador es el jugador Azul!");
            j2.aumentarGandas();
            j2.aumentarJugadas();
            j1.aumentarJugadas();
        }
        System.out.println("");
    }

    public void verRanking(Sistema s) {
        ArrayList<Jugador> listaJugadoresPorGanadas = s.sortListaJugadoresPorGanadas();
        System.out.println("RANKING: ");
        for (int i = 0; i < listaJugadoresPorGanadas.size(); i++) {
            if (i < 10) {
                System.out.println((i + 1)
                        + " " + listaJugadoresPorGanadas.get(i).getAlias()
                        + " (" + listaJugadoresPorGanadas.get(i).getGanadas() + " partidas ganadas)"
                        + " (" + listaJugadoresPorGanadas.get(i).getJugadas() + " partidas jugadas)");
            }
        }
        System.out.println("");
    }

    public int elegirTablero(Sistema s) {  //Metodo para elegir el tablero
        boolean inpVal = false;
        Scanner lec = new Scanner(System.in);
        int input = 0;
        while (!inpVal) {

            System.out.println("Seleccione el tablero a usar(Standard seleccionado por defecto)");
            System.out.println("1) Tablero Standard \n"
                    + "2) Precargado 1 para pruebas \n"
                    + "3) Precargado 2 para pruebas\n");
            try {
                input = lec.nextInt();
                inpVal = true;

            } catch (InputMismatchException e) {
                System.out.println("\nError, Ingrese un numero(1-3)\n");
                lec.nextLine();
                inpVal = false;
            }
        }
        if (input > 0 && input < 4) {
            System.out.println("Tablero seleccionado con exito\n");
            return input;
        } else {
            System.out.println("No ha seleccionado un tablero valido, jugara con el por defecto");
        }
        System.out.println("");
        return input;
    }
}
