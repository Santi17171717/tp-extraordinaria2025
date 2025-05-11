import java.io.*;
import java.util.Scanner;

public class Ajedrez {

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                Tablero tablero = new Tablero(args[0]);
                Color turno;
                if (args[1].equalsIgnoreCase("negro")) {
                    turno = Color.NEGRO;
                } else if (args[1].equalsIgnoreCase("blanco")) {
                    turno = Color.BLANCO;
                } else {
                    System.out.println("El argumento TURNO debe ser BLANCO o NEGRO");
                    return;
                }
                jugar(tablero, turno, System.out, new Scanner(System.in), args[2]);
            } catch (IllegalArgumentException e) {
                System.out.println("Error al crear el tablero");
            }
        } else if (args.length == 1) {
            System.out.println("Tablero inicial:");
            reproducir(args[0], System.out, new Scanner(System.in));
        } else {
            System.out.println("Número incorrecto de argumentos");
        }
    }

    static void jugar(Tablero tablero, Color turno, PrintStream pantalla, Scanner teclado, String registro) {
        try (PrintWriter log = new PrintWriter(new FileWriter(registro))) {
            while (!Reglas.finalDePartida(tablero, turno)) {
                pantalla.println(tablero.toString());
                Movimiento mov = Reglas.solicitarMovimiento(tablero, turno, pantalla, teclado);
                tablero.realizarMovimiento(mov);
                log.println(mov.toString());
                turno = (turno == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
            }
            pantalla.println(tablero.toString());
            String ganador = (turno == Color.BLANCO) ? "negras" : "blancas";
            pantalla.println("Fin de la partida. Ganan las " + ganador);
        } catch (IOException e) {
            pantalla.println("Error al abrir el fichero de registro");
            throw new RuntimeException(e);
        }
    }

    static void reproducir(String fichero, PrintStream pantalla, Scanner teclado) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            Tablero tablero = new Tablero("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
            pantalla.println(tablero.toString());
            String line;
            while ((line = reader.readLine()) != null) {
                String colO = line.substring(0,1);
                String filaO = line.substring(1,2);
                String colD = line.substring(2,3);
                String filaD = line.substring(3,4);
                int cO = Reglas.columnaAIndice(colO, pantalla);
                int fO = Reglas.filaAIndice(filaO, pantalla);
                int cD = Reglas.columnaAIndice(colD, pantalla);
                int fD = Reglas.filaAIndice(filaD, pantalla);
                Pieza pieza = tablero.getPieza(fO, cO);
                Movimiento mov = new Movimiento(pieza, fO, cO, fD, cD);
                tablero.realizarMovimiento(mov);
                pantalla.println(tablero.toString());
                pantalla.print("Pulsa INTRO para continuar");
                if (teclado.hasNextLine()) {
                    teclado.nextLine();
                }
            }
            if (tablero.reyMuerto(Color.BLANCO)) {
                pantalla.println("Fin de la partida. Ganan las negras");
            } else if (tablero.reyMuerto(Color.NEGRO)) {
                pantalla.println("Fin de la partida. Ganan las blancas");
            }
        } catch (IOException e) {
            pantalla.println("Error al abrir o leer el archivo: " + e.getMessage());
        }
    }
}
