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
        try (PrintWriter log = new PrintWriter(new OutputStreamWriter(new FileOutputStream(registro), "UTF-8"))) {
            while (!Reglas.finalDePartida(tablero, turno)) {
                pantalla.println(tablero);
                pantalla.print("Introduce movimiento (ej. e2e4): ");
                String linea = teclado.nextLine();

                if (linea.length() != 4) {
                    pantalla.println("Formato de movimiento incorrecto. Debe ser de 4 caracteres (ej. e2e4).");
                    continue;
                }

                String colOText = linea.substring(0, 1);
                String filaOText = linea.substring(1, 2);
                String colDText = linea.substring(2, 3);
                String filaDText = linea.substring(3, 4);

                int colO = Reglas.columnaAIndice(colOText, pantalla);
                int filaO = Reglas.filaAIndice(filaOText, pantalla);
                int colD = Reglas.columnaAIndice(colDText, pantalla);
                int filaD = Reglas.filaAIndice(filaDText, pantalla);

                if (colO == -1 || filaO == -1 || colD == -1 || filaD == -1) {
                    pantalla.println("Movimiento fuera de los límites del tablero.");
                    continue;
                }

                Pieza pieza = tablero.getPieza(filaO, colO);
                if (pieza == null || pieza.getColor() != turno) {
                    pantalla.println("No hay una pieza del color correcto en la posición de origen.");
                    continue;
                }

                Movimiento mov = new Movimiento(pieza, filaO, colO, filaD, colD);
                if (!Reglas.movimientoValidoPieza(tablero, mov)) {
                    pantalla.println("Movimiento no válido.");
                    continue;
                }

                tablero.realizarMovimiento(mov);
                log.write(linea + "\n");
                if (turno == Color.BLANCO) {
                    turno = Color.NEGRO;
                } else {
                    turno = Color.BLANCO;
                }
            }

            pantalla.println(tablero);
            if (tablero.reyMuerto(Color.BLANCO)) {
                pantalla.println("Fin de la partida. Ganan las negras");
            } else if (tablero.reyMuerto(Color.NEGRO)) {
                pantalla.println("Fin de la partida. Ganan las blancas");
            }
        } catch (IOException e) {
            pantalla.println("Error al abrir el fichero de registro");
            throw new RuntimeException(e);
        }
    }


    static void reproducir(String fichero, PrintStream pantalla, Scanner teclado) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            Tablero tablero = new Tablero("tcadract/pppppppp/8/8/8/8/PPPPPPPP/TCADRACT");
            pantalla.println(tablero);
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
                pantalla.println(tablero);
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
