/**
 * Tablero de ajedrez con sus piezas y algunas operaciones.
 * Crea tableros desde notación FEN, valida movimientos y realiza operaciones de piezas.
 */
public class Tablero {
    /** Matriz 8x8 que representa las casillas del tablero */
    private final Pieza[][] tablero;

    /**
     * Construye un tablero con String en notación FEN.
     * @param notacionFEN Cadena en formato FEN que describe la posición inicial
     * @throws IllegalArgumentException Excepción si el formato FEN es incorrecto
     */
    public Tablero(String notacionFEN) throws IllegalArgumentException {
        tablero = new Pieza[8][8];
        String[] filas = notacionFEN.split("/");
        if (filas.length != 8) throw new IllegalArgumentException("Formato FEN incorrecto: debe tener 8 filas");

        for (int i = 0; i < 8; i++) {
            int col = 0;
            String fila = filas[i];
            for (int j = 0; j < fila.length(); j++) {
                char c = fila.charAt(j);
                if (Character.isDigit(c)) {
                    col += c - '0';
                } else {
                    tablero[i][col++] = new Pieza(c);
                }
            }
        }
    }

    /**
     * Verifica si hay piezas el camino entre dos posiciones.
     * @param movimiento Movimiento a verificar
     * @return Si no hay piezas en el camino
     */
    boolean comprobarPiezasEnCamino(Movimiento movimiento) {
        boolean caminoLibre = true;
        int f1 = movimiento.filaOrigen;
        int c1 = movimiento.columnaOrigen;
        int f2 = movimiento.filaDestino;
        int c2 = movimiento.columnaDestino;

        int df = Integer.compare(f2, f1);
        int dc = Integer.compare(c2, c1);

        int f = f1 + df;
        int c = c1 + dc;

        while ((f != f2 || c != c2) && caminoLibre) {
            if (tablero[f][c] != null) caminoLibre = false;
            f += df;
            c += dc;
        }
        return caminoLibre;
    }

    /**
     * Pone una pieza en una posición específica del tablero.
     * @param fila Fila donde colocar la pieza (0-7)
     * @param columna Columna donde colocar la pieza (0-7)
     * @param pieza Pieza por colocar
     * @throws IllegalArgumentException Excepción si la posición está fuera de rango o ocupada
     */
    public void agregarPieza(int fila, int columna, Pieza pieza) {
        if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
            throw new IllegalArgumentException(String.format("La casilla (%d, %d) está fuera de rango%n",
                    fila, columna));
        }
        if (tablero[fila][columna] != null) {
            throw new IllegalArgumentException(String.format("La casilla (%d, %d) está ocupada por la pieza %s%n",
                    fila, columna, Reglas.simbolo(tablero[fila][columna])));
        }
        tablero[fila][columna] = pieza;
    }

    /**
     * Coloca la pieza en una posición del tablero.
     * @param fila Fila de la pieza (0-7)
     * @param columna Columna de la pieza (0-7)
     * @return Pieza en la posición especificada o null si está vacía
     * @throws IllegalArgumentException Excepción si la posición está fuera de rango
     */
    public Pieza getPieza(int fila, int columna) {
        if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
            throw new IllegalArgumentException(String.format("La casilla (%d, %d) está fuera de rango%n",
                    fila, columna));
        }
        return tablero[fila][columna];
    }

    /**
     * Genera una representación visual del tablero en texto.
     * @return String con el tablero representado en StringBuilder
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  a b c d e f g h\n");
        sb.append(" ┌─┬─┬─┬─┬─┬─┬─┬─┐\n");
        for (int fila = 0; fila < 8; fila++) {
            sb.append(8 - fila);
            for (int col = 0; col < 8; col++) {
                sb.append("│").append(Reglas.simbolo(tablero[fila][col]));
            }
            sb.append("│").append(8 - fila).append("\n");
            if (fila < 7) {
                sb.append(" ├─┼─┼─┼─┼─┼─┼─┼─┤\n");
            }
        }
        sb.append(" └─┴─┴─┴─┴─┴─┴─┴─┘\n");
        sb.append("  a b c d e f g h\n");
        return sb.toString();
    }

    /**
     * Verifica si el rey de uno de los colores ha sido capturado.
     * @param turno Color del rey por verificar
     * @return Si no hay rey de ese color en el tablero
     */
    public boolean reyMuerto(Color turno) {
        boolean muerto = true;
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza p = tablero[fila][col];
                if ((p != null && p.getTipoPieza() == TipoPieza.REY && p.getColor() == turno) && muerto) {
                    muerto = false;
                }
            }
        }
        return muerto;
    }

    /**
     * Devuelve el número de columnas del tablero.
     * @return 8 (columnas en un tablero)
     */
    public int getNumColumnas() {
        return 8;
    }

    /**
     * Devuelve el número de filas del tablero.
     * @return 8 (filas en un tablero)
     */
    public int getNumFilas() {
        return 8;
    }

    /**
     * Realiza un movimiento en el tablero.
     * @param mov Movimiento a realizar
     * @throws ArrayIndexOutOfBoundsException Excepción si el movimiento está fuera de los límites del tablero
     */
    public void realizarMovimiento(Movimiento mov) {
        if (mov.filaOrigen < 0 || mov.filaOrigen >= 8 || mov.columnaOrigen < 0 || mov.columnaOrigen >= 8 ||
                mov.filaDestino < 0 || mov.filaDestino >= 8 || mov.columnaDestino < 0 || mov.columnaDestino >= 8) {
            throw new ArrayIndexOutOfBoundsException("Movimiento fuera de los límites del tablero");
        }
        tablero[mov.filaDestino][mov.columnaDestino] = mov.pieza;
        tablero[mov.filaOrigen][mov.columnaOrigen] = null;
    }
}