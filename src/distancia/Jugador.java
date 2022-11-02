package distancia;

    /*
    Obligatorio 1 - Programación II 
    M2B Ingeniería en Sistemas ORT
    Martín De Francesco(273546)
    Nicolás Ruy López(256563)
    */

public class Jugador {
    // Clase con todos los atributos de la clase Jugador, con constructores y metodo toString equals y compareTo
    
    private String nombre;
    private int edad;
    private String alias;
    private int ganadas;
    private int jugadas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        edad = unaEdad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String unAlias) {
        alias = unAlias;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void setGanadas(int unNumero) {
        ganadas = unNumero;
    }

    public int getJugadas() {
        return jugadas;
    }

    public void setJugadas(int unNumero) {
        jugadas = unNumero;
    }

    public void aumentarGandas() {
        setGanadas(getGanadas() + 1);
    }
    public void aumentarJugadas() {
        setJugadas(getJugadas() + 1);
    }

    public Jugador(String unNombre, int unaEdad, String unAlias) {
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
        this.setAlias(unAlias);
        this.setGanadas(0);
        this.setJugadas(0);
    }

    public Jugador() {
        this.setAlias("Jugador Generico");
        this.setEdad(0);
        this.setGanadas(0);
        this.setJugadas(0);
    }

    public void setJugador(Jugador unJugador) {
        this.setNombre(unJugador.getNombre());
        this.setEdad(unJugador.getEdad());
        this.setAlias(unJugador.getAlias());
    }

    public String toString() {
        return "Nombre: " + this.getNombre() + ", Edad: " + this.getEdad()
                + ", Alias: " + this.getAlias() + ", Jugadas: " + this.getJugadas()
                + ", Ganadas: " + this.getGanadas();
    }

    @Override
    public boolean equals(Object obj) {
        Jugador o = (Jugador) obj;
        return this.getAlias().equalsIgnoreCase(o.getAlias());
    }

    public int compareTo(Jugador j) {
        return j.getGanadas() - this.getGanadas();
    }
}
