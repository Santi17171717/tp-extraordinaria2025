import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reglas {
    private static final String PEON_BLANCO = "♙";
    private static final String PEON_NEGRO = "♟";
    private static final String ALFIL_BLANCO = "♗";
    private static final String ALFIL_NEGRO = "♝";
    private static final String TORRE_BLANCA = "♖";
    private static final String TORRE_NEGRA = "♜";
    private static final String CABALLO_BLANCO = "♘";
    private static final String CABALLO_NEGRO = "♞";
    private static final String REINA_BLANCA = "♕";
    private static final String REINA_NEGRA = "♛";
    private static final String REY_BLANCO = "♔";
    private static final String REY_NEGRO = "♚";

    public static String simbolo(Pieza pieza) {
        if (pieza == null) return " ";
        switch (pieza.getTipoPieza()) {
            case PEON: return pieza.getColor() == Color.BLANCO ? PEON_BLANCO : PEON_NEGRO;
            case ALFIL: return pieza.getColor() == Color.BLANCO ? ALFIL_BLANCO : ALFIL_NEGRO;
            case TORRE: return pieza.getColor() == Color.BLANCO ? TORRE_BLANCA : TORRE_NEGRA;
            case CABALLO: return pieza.getColor() == Color.BLANCO ? CABALLO_BLANCO : CABALLO_NEGRO;
            case REINA: return pieza.getColor() == Color.BLANCO ? REINA_BLANCA : REINA_NEGRA;
            case REY: return pieza.getColor() == Color.BLANCO ? REY_BLANCO : REY_NEGRO;
            default: return " ";
        }
    }

    public static int filaAIndice(String fila, PrintStream pantalla) {
        try {
            int f = Integer.parseInt(fila);
            if (f >= 1 && f <= 8) {
                return 8 - f;
            } else {
                pantalla.println("Fila fuera de rango (1-8)");
                return -1;
            }
        } catch (NumberFormatException e) {
            pantalla.println("Fila inválida");
            return -1;
        }
    }

    public static int columnaAIndice(String columna, PrintStream pantalla) {
        columna = columna.toLowerCase();
        if (columna.length() == 1 && columna.charAt(0) >= 'a' && columna.charAt(0) <= 'h') {
            return columna.charAt(0) - 'a';
        } else {
            pantalla.println("Columna inválida (a-h)");
            return -1;
        }
    }

    public static boolean movimientoValidoPieza(Tablero t, Movimiento mov) {
        Pieza pieza = new Pieza(mov.toString().charAt(0));
        if (pieza == null) return false;

        int f1 = Character.getNumericValue(mov.toString().charAt(3));
        int c1 = mov.toString().charAt(4);
        int f2 = Character.getNumericValue(mov.toString().charAt(1));
        int c2 = mov.toString().charAt(2);

        Pieza destino = t.getPieza(f2, c2);
        if (destino != null && destino.getColor() == pieza.getColor()) {
            return false; // no se puede capturar una pieza propia
        }

        switch (pieza.getTipoPieza()) {
            case PEON:
                int avance = pieza.getColor() == Color.BLANCO ? -1 : 1;
                int filaInicial = pieza.getColor() == Color.BLANCO ? 6 : 1;

                // avance simple
                if (c1 == c2 && f2 == f1 + avance && t.getPieza(f2, c2) == null) return true;

                // doble avance inicial
                if (c1 == c2 && f1 == filaInicial && f2 == f1 + 2 * avance &&
                        t.getPieza(f1 + avance, c2) == null && t.getPieza(f2, c2) == null) return true;

                // captura
                if (Math.abs(c1 - c2) == 1 && f2 == f1 + avance && t.getPieza(f2, c2) != null &&
                        t.getPieza(f2, c2).getColor() != pieza.getColor()) return true;

                return false;

            case TORRE:
                if (f1 == f2 || c1 == c2) {
                    return t.comprobarPiezasEnCamino(mov);
                }
                return false;

            case ALFIL:
                if (Math.abs(f1 - f2) == Math.abs(c1 - c2)) {
                    return t.comprobarPiezasEnCamino(mov);
                }
                return false;

            case REINA:
                if (f1 == f2 || c1 == c2 || Math.abs(f1 - f2) == Math.abs(c1 - c2)) {
                    return t.comprobarPiezasEnCamino(mov);
                }
                return false;

            case CABALLO:
                return (Math.abs(f1 - f2) == 2 && Math.abs(c1 - c2) == 1) ||
                        (Math.abs(f1 - f2) == 1 && Math.abs(c1 - c2) == 2);

            case REY:
                return Math.abs(f1 - f2) <= 1 && Math.abs(c1 - c2) <= 1;

            default:
                return false;
        }
    }


    public static boolean finalDePartida(Tablero tablero, Color turno) {
        return tablero.reyMuerto(turno);
    }

    public static Movimiento solicitarMovimiento(Tablero tablero, Color turno, PrintStream pantalla, Scanner teclado) {
        while (true) {
            pantalla.print("Columna de origen (a-h): ");
            int colO = columnaAIndice(teclado.nextLine(), pantalla);
            pantalla.print("Fila de origen (1-8): ");
            int filaO = filaAIndice(teclado.nextLine(), pantalla);
            pantalla.print("Columna de destino (a-h): ");
            int colD = columnaAIndice(teclado.nextLine(), pantalla);
            pantalla.print("Fila de destino (1-8): ");
            int filaD = filaAIndice(teclado.nextLine(), pantalla);

            if (colO == -1 || filaO == -1 || colD == -1 || filaD == -1) {
                pantalla.println("Entrada inválida. Intente nuevamente.");
                continue;
            }

            Pieza pieza = tablero.getPieza(filaO, colO);
            if (pieza == null || pieza.getColor() != turno) {
                pantalla.println("No hay pieza propia en la posición origen.");
                continue;
            }

            Movimiento mov = new Movimiento(pieza, filaO, colO, filaD, colD);
            if (movimientoValidoPieza(tablero, mov)) {
                return mov;
            } else {
                pantalla.println("Movimiento inválido según las reglas.");
            }
        }
    }

    public static String indiceAFila(int indice) {
        return Integer.toString(8 - indice);
    }

    public static String indiceAColumna(int indice) {
        return Character.toString((char) ('a' + indice));
    }
}
