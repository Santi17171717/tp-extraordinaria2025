import java.io.PrintStream;
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
            case PEON:
                return pieza.getColor() == Color.BLANCO ? PEON_BLANCO : PEON_NEGRO;
            case ALFIL:
                return pieza.getColor() == Color.BLANCO ? ALFIL_BLANCO : ALFIL_NEGRO;
            case TORRE:
                return pieza.getColor() == Color.BLANCO ? TORRE_BLANCA : TORRE_NEGRA;
            case CABALLO:
                return pieza.getColor() == Color.BLANCO ? CABALLO_BLANCO : CABALLO_NEGRO;
            case REINA:
                return pieza.getColor() == Color.BLANCO ? REINA_BLANCA : REINA_NEGRA;
            case REY:
                return pieza.getColor() == Color.BLANCO ? REY_BLANCO : REY_NEGRO;
            default:
                return " ";
        }
    }

    public static boolean movimientoValidoPieza(Tablero t, Movimiento mov) {
        Pieza pieza = mov.pieza;
        if (pieza == null) return false;

        int filaO = mov.filaOrigen;
        int colO = mov.columnaOrigen;
        int filaD = mov.filaDestino;
        int colD = mov.columnaDestino;

        Pieza destino = t.getPieza(filaD, colD);
        if (destino != null && destino.getColor() == pieza.getColor()) return false;

        int dFila = filaD - filaO;
        int dCol = colD - colO;

        switch (pieza.getTipoPieza()) {
            case PEON:
                if (pieza.getColor() == Color.BLANCO) {
                    if (colO == colD && t.getPieza(filaD, colD) == null) {
                        if (dFila == -1) return true;
                        if (filaO == 6 && dFila == -2 && t.getPieza(filaO - 1, colO) == null) return true;
                    } else if (Math.abs(dCol) == 1 && dFila == -1 && destino != null && destino.getColor() == Color.NEGRO) {
                        return true;
                    }
                } else {
                    if (colO == colD && t.getPieza(filaD, colD) == null) {
                        if (dFila == 1) return true;
                        if (filaO == 1 && dFila == 2 && t.getPieza(filaO + 1, colO) == null) return true;
                    } else if (Math.abs(dCol) == 1 && dFila == 1 && destino != null && destino.getColor() == Color.BLANCO) {
                        return true;
                    }
                }
                return false;

            case TORRE:
                return (filaO == filaD || colO == colD) && t.comprobarPiezasEnCamino(mov);

            case ALFIL:
                return Math.abs(dFila) == Math.abs(dCol) && t.comprobarPiezasEnCamino(mov);

            case REINA:
                return (filaO == filaD || colO == colD || Math.abs(dFila) == Math.abs(dCol)) &&
                        t.comprobarPiezasEnCamino(mov);

            case CABALLO:
                return (Math.abs(dFila) == 2 && Math.abs(dCol) == 1) ||
                        (Math.abs(dFila) == 1 && Math.abs(dCol) == 2);

            case REY:
                return Math.abs(dFila) <= 1 && Math.abs(dCol) <= 1 && (dFila != 0 || dCol != 0);

            default:
                return false;
        }
    }

    public static boolean finalDePartida(Tablero tablero, Color turno) {
        return tablero.reyMuerto(turno);
    }

    public static Movimiento solicitarMovimiento(Tablero tablero, Color turno, PrintStream pantalla, Scanner teclado) {
        while (true) {
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

            int colO = columnaAIndice(colOText, pantalla);
            int filaO = filaAIndice(filaOText, pantalla);
            int colD = columnaAIndice(colDText, pantalla);
            int filaD = filaAIndice(filaDText, pantalla);

            if (colO == -1 || filaO == -1 || colD == -1 || filaD == -1) {
                pantalla.println("Movimiento fuera de los límites del tablero.");
                continue;
            }


            Pieza pieza = tablero.getPieza(filaO, colO);
            if (pieza == null || pieza.getColor() != turno) {
                pantalla.println("No hay una pieza del color correcto en la posición de origen.");
                continue;
            }

            Movimiento movimiento = new Movimiento(pieza, filaO, colO, filaD, colD);
            if (!movimientoValidoPieza(tablero, movimiento)) {
                pantalla.println("Movimiento no válido.");
                continue;
            }

            return movimiento;
        }
    }

    public static int filaAIndice(String fila, PrintStream pantalla) {
        try {
            int i = Integer.parseInt(fila);
            if (i >= 1 && i <= 8) {
                return 8 - i;
            } else {
                pantalla.println("La fila " + i + " está fuera de rango (número del 1 al 8)");
                return -1;
            }
        } catch (NumberFormatException e) {
            pantalla.println("La fila " + fila + " no tiene el formato correcto (número del 1 al 8)");
            return -1;
        }
    }

    public static int columnaAIndice(String columna, PrintStream pantalla) {
        columna = columna.toLowerCase();
        if (columna.length() != 1) {
            pantalla.println("La columna " + columna + " no tiene el formato correcto (letra de 'a' a 'h')");
            return -1;
        }

        char c = columna.charAt(0);
        if (c < 'a' || c > 'h') {
            if (Character.isLetter(c)) {
                pantalla.println("La columna " + columna + " está fuera de rango (letra de 'a' a 'h')");
            } else {
                pantalla.println("La columna " + columna + " no tiene el formato correcto (letra de 'a' a 'h')");
            }
            return -1;
        }

        return c - 'a';
    }

    public static String indiceAFila(int indice) {
        if (indice < 0 || indice > 7) {
            throw new IllegalArgumentException("Índice de fila fuera de rango");
        }
        return Integer.toString(8 - indice);
    }

    public static String indiceAColumna(int indice) {
        if (indice < 0 || indice > 7) {
            throw new IllegalArgumentException("Índice de columna fuera de rango");
        }
        return Character.toString((char) ('a' + indice));
    }

}
