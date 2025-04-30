import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Ajedrez {

    public static void main(String[] args) {
        if (args.length == 3){
            try {
                Tablero tablero = new Tablero(args[0]);
                if (args[1].contentEquals("negro") || args[1].contentEquals("blanco")){
                    Color turno;
                    if (args[1].contentEquals("negro")){
                        turno = Color.NEGRO;
                    }else {
                        turno = Color.BLANCO;
                    }
                    jugar(tablero, turno, System.out, new Scanner(System.in), args[2]);
                }else {
                    System.out.println(" El argumento TURNO debe ser BLANCO o NEGRO");
                }
            }catch (IllegalArgumentException exception){
                System.out.println("Error al crear el tablero");
            }
        } else if (args.length == 1) {
            reproducir(args[0], System.out, new Scanner(System.in));
        }else {
            System.out.println("Número incorrecto de argumentos");
        }
    }

    static void jugar(Tablero tablero, Color turno, PrintStream pantalla, Scanner teclado, String registro) {
        try (FileWriter fileWriter = new FileWriter(registro)) {
        } catch (IOException e) {
            pantalla.println("Error al abrir el fichero de registro");
            throw new RuntimeException();
        }
        do {
            pantalla.println(tablero.toString());


            if (turno.equals(Color.NEGRO)){
                turno = Color.BLANCO;
            }else {
                turno = Color.NEGRO;
            }
        } while (!tablero.reyMuerto(turno));
    }

    static void reproducir(String fichero_movimientos, PrintStream pantalla, Scanner teclado) {

    }
}
