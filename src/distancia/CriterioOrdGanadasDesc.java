package distancia;

import java.util.Comparator;

    /*
    Obligatorio 1 - Programación II 
    M2B Ingeniería en Sistemas ORT
    Martín De Francesco(273546)
    Nicolás Ruy López(256563)
    */

public class CriterioOrdGanadasDesc implements Comparator<Jugador> {

    // Criterio de ordenacion por partidas ganadas de forma descendente
    public int compare(Jugador jugador1, Jugador jugador2) {
        int dif = jugador2.getGanadas() - jugador1.getGanadas();
        if (dif == 0) {
            dif = jugador1.getAlias().compareToIgnoreCase(jugador2.getAlias());
        }
        return dif;
    }
}
