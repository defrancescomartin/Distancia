package interfaz;

    /*
    Obligatorio 1 - Programación II 
    M2B Ingeniería en Sistemas ORT
    Martín De Francesco(273546)
    Nicolás Ruy López(256563)
    */

import distancia.Sistema;

public class Prueba {
    // Main, inicializar todo desde aca
    
    public static void main(String[] args) {
        Sistema s = new Sistema();
        Interfaz imp = new Interfaz();
        imp.menuPrincipal(s);
        
    }
    
}
