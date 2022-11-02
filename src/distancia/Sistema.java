package distancia;

import java.util.*;

    /*
    Obligatorio 1 - Programación II 
    M2B Ingeniería en Sistemas ORT
    Martín De Francesco(273546)
    Nicolás Ruy López(256563)
    */


public class Sistema {
    // Clase Sistema con metodos de Jugador, Matrices de tablero, Movimientos posibles, Turno, Ganador, etc

    private ArrayList<Jugador> listaJugadores;
    private String[][] tablero;

    public Sistema() {
        listaJugadores = new ArrayList();
        String[][] mat = new String[6][6];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    mat[i][j] = "A";
                } else {
                    mat[i][j] = "R";
                }
            }
        }
        setTablero(mat);
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] unTablero) {
        tablero = unTablero;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    public void agregarJugador(Jugador j) {
        listaJugadores.add(j);
    }

    public boolean existeJugador(Jugador j) {
        boolean esta = false;
        for (int i = 0; i < listaJugadores.size() && !esta; i++) {
            if (listaJugadores.get(i).equals(j)) {
                esta = true;
            }
        }
        return esta;
    }

    public void setTableroStandard() {
        String[][] tableroStandard = new String[6][6];
        for (int i = 0; i < tableroStandard.length; i++) {
            for (int j = 0; j < tableroStandard[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    tableroStandard[i][j] = "A";
                } else {
                    tableroStandard[i][j] = "R";
                }
            }
        }
        setTablero(tableroStandard);
    }

    public void setTableroPruebas1() {
        String[][] tableroPruebas1 = new String[6][6];
        for (int i = 0; i < tableroPruebas1.length; i++) {
            for (int j = 0; j < tableroPruebas1[0].length; j++) {
                tableroPruebas1[i][j] = "";
                if (i == 0 && j == 0) {
                    tableroPruebas1[i][j] = "R";
                }
                if (i == 2 && j == 2) {
                    tableroPruebas1[i][j] = "A";
                }
                if (i == 3 && j == 5) {
                    tableroPruebas1[i][j] = "A";
                }
                if (i == 4 && j == 1) {
                    tableroPruebas1[i][j] = "A";
                }
                if (i == 5 && j == 3) {
                    tableroPruebas1[i][j] = "R";
                }
            }
        }
        setTablero(tableroPruebas1);
    }

    public void setTableroPruebas2() {
        String[][] tableroPruebas2 = new String[6][6];
        for (int i = 0; i < tableroPruebas2.length; i++) {
            for (int j = 0; j < tableroPruebas2[0].length; j++) {
                tableroPruebas2[i][j] = "";
                if (i == 0 && j == 0) {
                    tableroPruebas2[i][j] = "R";
                }
                if (i == 5 && j == 4) {
                    tableroPruebas2[i][j] = "A";
                }
                if (i == 5 && j == 5) {
                    tableroPruebas2[i][j] = "A";
                }
            }
        }
        setTablero(tableroPruebas2);
    }

    public void jugarConTablero(int n) {//Selecciona el tablero deseado
        if (n == 2) {
            setTableroPruebas1();
        } else if (n == 3) {
            setTableroPruebas2();
        } else {
            setTableroStandard();
        }

    } 

    public String[][] matrizMovPosibles(int fila, int col, boolean turno) {
        // Matriz que contiene los numeros del 1 al 6 para poder verificar si un movimiento puede realizarse
        // y si el mismo tiene captura posible

        String[][] matMovPosibles = new String[6][6];
        int[][] matDistancias = new int[6][6];
        String turnoDe = "N";
        String captura = "M";
        if (turno) {
            turnoDe = "R";
            captura = "A";
        } else {
            turnoDe = "A";
            captura = "R";
        }
        for (int i = 0; i < matDistancias.length; i++) {
            for (int j = 0; j < matDistancias[0].length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 5)) {
                    matDistancias[i][j] = 6;
                    matDistancias[i + 5][j] = 6;
                }
                if ((i == 0 && j == 1) || (i == 0 && j == 4) || (i == 1 && j == 0) || (i == 1 && j == 5) || (i == 4 && j == 0) || (i == 5 && j == 1) || (i == 5 && j == 4) || (i == 4 && j == 5)) {
                    matDistancias[i][j] = 5;
                }
                if ((i == 0 && j == 2) || (i == 0 && j == 3) || (i == 2 && j == 0) || (i == 3 && j == 0) || (i == 5 && j == 2) || (i == 5 && j == 3) || (i == 2 && j == 5) || (i == 3 && j == 5)) {
                    matDistancias[i][j] = 4;
                }
                if ((i == 1 && j == 1) || (i == 1 && j == 4)) {
                    matDistancias[i][j] = 3;
                    matDistancias[i + 3][j] = 3;
                }
                if ((i == 1 && j == 2) || (i == 1 && j == 3) || (i == 2 && j == 1) || (i == 3 && j == 1) || (i == 4 && j == 2) || (i == 4 && j == 3) || (i == 2 && j == 4) || (i == 3 && j == 4)) {
                    matDistancias[i][j] = 2;
                }
                if ((i == 2 && j == 2) || (i == 2 && j == 3)) {
                    matDistancias[i][j] = 1;
                    matDistancias[i + 1][j] = 1;
                }
            }
        }

        for (int i = 0; i < getTablero().length; i++) {
            for (int j = 0; j < getTablero()[0].length; j++) {
                matMovPosibles[i][j] = getTablero()[i][j];
            }
        }
        matMovPosibles[fila][col] = "E";

        if ((fila + 1) < matDistancias.length && (matDistancias[fila][col] < matDistancias[fila + 1][col])) { //Abajo
            if (getTablero()[fila + 1][col] == "") {
                matMovPosibles[fila + 1][col] = "*";
            }
        }

        if ((fila - 1) < matDistancias.length && (fila - 1) >= 0 && (matDistancias[fila][col] < matDistancias[fila - 1][col])) { // Arriba
            if (getTablero()[fila - 1][col] == "") {
                matMovPosibles[fila - 1][col] = "*";
            }
        }
        if ((col + 1) < matDistancias[0].length && (matDistancias[fila][col] < matDistancias[fila][col + 1])) {   //Derecha
            if (getTablero()[fila][col + 1] == "") {
                matMovPosibles[fila][col + 1] = "*";
            }
        }
        if ((col - 1) < matDistancias[0].length && (col - 1) >= 0 && (matDistancias[fila][col] < matDistancias[fila][col - 1])) { // Izquierda
            if (getTablero()[fila][col - 1] == "") {
                matMovPosibles[fila][col - 1] = "*";
            }
        }
        if ((fila - 1) < getTablero().length && (col - 1) < matDistancias[0].length && (fila - 1) >= 0 && (col - 1) >= 0 && (matDistancias[fila][col] < matDistancias[fila - 1][col - 1])) { //Diagonal arriba izquierda
            if (getTablero()[fila - 1][col - 1] == "") {
                matMovPosibles[fila - 1][col - 1] = "*";
            }
        }
        if ((fila + 1) < getTablero().length && (col + 1) < matDistancias[0].length && (matDistancias[fila][col] < matDistancias[fila + 1][col + 1])) { //Diagonal abajo derecha
            if (getTablero()[fila + 1][col + 1] == "") {
                matMovPosibles[fila + 1][col + 1] = "*";
            }
        }
        if ((fila - 1) < getTablero().length && (col + 1) < getTablero()[0].length && (fila - 1) >= 0 && (matDistancias[fila][col] < matDistancias[fila - 1][col + 1])) { //Diagonal arriba derecha
            if (getTablero()[fila - 1][col + 1] == "") {
                matMovPosibles[fila - 1][col + 1] = "*";
            }
        }
        if ((fila + 1) < getTablero().length && (col - 1) < getTablero()[0].length && (col - 1) >= 0 && (matDistancias[fila][col] < matDistancias[fila + 1][col - 1])) { //Diagonal abajo izquierda
            if (getTablero()[fila + 1][col - 1] == "") {
                matMovPosibles[fila + 1][col - 1] = "*";
            }
        }
        boolean encontroAbajo = false;
        boolean encontroArriba = false;
        boolean encontroDerecha = false;
        boolean encontroIzquierda = false;
        boolean encontroDiagArrIzq = false;
        boolean encontroDiagAbaDer = false;
        boolean encontroDiagArrDer = false;
        boolean encontroDiagAbaIzq = false;
        for (int i = 1; i < 6; i++) {
            if ((fila + i) < matDistancias.length && (matDistancias[fila][col] >= matDistancias[fila + i][col]) && !encontroAbajo) { //Abajo
                if (getTablero()[fila + i][col] == captura) {
                    matMovPosibles[fila + i][col] = "#";
                    encontroAbajo = true;
                } else if (getTablero()[fila + i][col] == turnoDe) {
                    encontroAbajo = true;
                }
            }
            if ((fila - i) < matDistancias.length && (fila - i) >= 0 && (matDistancias[fila][col] >= matDistancias[fila - i][col]) && !encontroArriba) { //Arriba
                if (getTablero()[fila - i][col] == captura) {
                    matMovPosibles[fila - i][col] = "#";
                    encontroArriba = true;
                } else if (getTablero()[fila - i][col] == turnoDe) {
                    encontroArriba = true;
                }
            }
            if ((col + i) < matDistancias[0].length && (matDistancias[fila][col] >= matDistancias[fila][col + i]) && !encontroDerecha) {   //Derecha
                if (getTablero()[fila][col + i] == captura) {
                    matMovPosibles[fila][col + i] = "#";
                    encontroDerecha = true;
                } else if (getTablero()[fila][col + i] == turnoDe) {
                    encontroDerecha = true;
                }
            }
            if ((col - i) < matDistancias[0].length && (col - i) >= 0 && (matDistancias[fila][col] >= matDistancias[fila][col - i]) && !encontroIzquierda) {   //Izquierda
                if (getTablero()[fila][col - i] == captura) {
                    matMovPosibles[fila][col - i] = "#";
                    encontroIzquierda = true;
                } else if (getTablero()[fila][col - i] == turnoDe) {
                    encontroIzquierda = true;
                }
            }
            if ((fila - i) < getTablero().length && (col - i) < matDistancias[0].length && (fila - i) >= 0 && (col - i) >= 0 && (matDistancias[fila][col] >= matDistancias[fila - i][col - i]) && !encontroDiagArrIzq) { //Diagonal arriba izquierda
                if (getTablero()[fila - i][col - i] == captura) {
                    matMovPosibles[fila - i][col - i] = "#";
                    encontroDiagArrIzq = true;
                } else if (getTablero()[fila - i][col - i] == turnoDe) {
                    encontroDiagArrIzq = true;
                }
            }
            if ((fila + i) < getTablero().length && (col + i) < matDistancias[0].length && (matDistancias[fila][col] >= matDistancias[fila + i][col + i]) && !encontroDiagAbaDer) { //Diagonal abajo derecha
                if (getTablero()[fila + i][col + i] == captura) {
                    matMovPosibles[fila + i][col + i] = "#";
                    encontroDiagAbaDer = true;
                } else if (getTablero()[fila + i][col + i] == turnoDe) {
                    encontroDiagAbaDer = true;
                }
            }
            if ((fila - i) < getTablero().length && (col + i) < getTablero()[0].length && (fila - i) >= 0 && (matDistancias[fila][col] >= matDistancias[fila - i][col + i]) && !encontroDiagArrDer) { //Diagonal arriba derecha
                if (getTablero()[fila - i][col + i] == captura) {
                    matMovPosibles[fila - i][col + i] = "#";
                    encontroDiagArrDer = true;

                } else if (getTablero()[fila - i][col + i] == turnoDe) {
                    encontroDiagArrDer = true;
                }
            }
            if ((fila + 1) < getTablero().length && (col - i) < getTablero()[0].length && (col - i) >= 0 && (matDistancias[fila][col] >= matDistancias[fila + 1][col - 1]) && !encontroDiagAbaIzq) { //Diagonal abajo izquierda
                if (getTablero()[fila + i][col - i] == captura) {
                    matMovPosibles[fila + i][col - i] = "#";
                    encontroDiagAbaIzq = true;

                } else if (getTablero()[fila + i][col - i] == turnoDe) {
                    encontroDiagAbaIzq = true;
                }
            }

        }

        return matMovPosibles;
    }

    public static boolean validarMovimiento(String mat[][], int fila, int col) {//Valida el movimiento a realizar
        boolean valido = false;
        if (mat[fila][col] == "*" || mat[fila][col] == "#") {
            valido = true;
        }
        return valido;
    } 

    public static boolean hayMovPosibles(String mat[][]) {//Chequea si hay movimientos disponibles
        boolean hay = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == "*" || mat[i][j] == "#") {
                    hay = true;
                }
            }
        }
        return hay;
    } 

    public void realizarMovimiento(int filaIn, int colIn, int filaFin, int colFin, boolean turno) { //Efectua el movimiento
        String ficha = turnoDe(turno);
        getTablero()[filaIn][colIn] = "";
        getTablero()[filaFin][colFin] = ficha;
    }

    public static int leerFila(String string) { //Maneja valor de fila ingresada
        int fila = -1;
        String filaIn = string.substring(0, 1);
        switch (filaIn) {
            case "A":
                fila = 0;
                break;
            case "B":
                fila = 1;
                break;
            case "C":
                fila = 2;
                break;
            case "D":
                fila = 3;
                break;
            case "E":
                fila = 4;
                break;
            case "F":
                fila = 5;
                break;
        }
        return fila;
    } 

    public static int leerColumna(String string) { //Maneja valor de fila ingresada
        int col = Integer.parseInt(string.substring(1)) - 1;
        return col;
    }

    public static boolean finalizarPartida(String string) { //Verifica si el jugador se rinde
        boolean fin = false;
        if (string.equalsIgnoreCase("x")) {
            System.out.println("Juego finalizado");
            fin = true;
        }
        return fin;
    }

    public boolean chequearTurno(boolean turno, int fila, int col) { //Chequea el turno
        boolean valido = true;
        String ficha = turnoDe(turno);
        if (getTablero()[fila][col] == ficha) {
            valido = true;
        } else {
            valido = false;
        }

        return valido;
    }

    public boolean tieneMovimientos(boolean turno) {//Verifica si hay movimientos posibles para ese turno
        boolean tiene = false;
        String[][] mat = new String[6][6];
        for (int i = 0; i < getTablero().length && !tiene; i++) {
            for (int j = 0; j < getTablero()[0].length && !tiene; j++) {
                if (chequearTurno(turno, i, j)) {
                    mat = matrizMovPosibles(i, j, turno);
                    for (int k = 0; k < mat.length && !tiene; k++) {
                        for (int l = 0; l < mat[0].length && !tiene; l++) {
                            if (mat[k][l] == "*" || mat[k][l] == "#") {
                                tiene = true;
                            }
                        }
                    }
                }
            }
        }
        return tiene;
    }

    public static boolean hayGanador(Sistema s, boolean turno) { //Verifica si hay ganador
        boolean hay = true;
        String ficha = turnoDe(!turno);
        for (int i = 0; i < s.getTablero().length && hay; i++) {
            for (int j = 0; j < s.getTablero()[0].length && hay; j++) {
                if (s.getTablero()[i][j] == ficha) {
                    hay = false;
                }
            }
        }
        return hay;
    }

    public static String turnoDe(boolean turno) { //Asigna un string dependiendo del turno
        String turnoDe = "N";
        if (turno == true) {
            turnoDe = "R";
        } else {
            turnoDe = "A";
        }
        return turnoDe;
    }

    public ArrayList<Jugador> sortListaJugadoresPorGanadas() {
        Collections.sort(getListaJugadores(), new CriterioOrdGanadasDesc());
        return getListaJugadores();
    }

}
