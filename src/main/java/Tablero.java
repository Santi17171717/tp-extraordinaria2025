public class Tablero{
    private Pieza[][] tablero;

    public Tablero(String notacionFEN) throws IllegalArgumentException {
        tablero[0][0] = new Pieza('t');
        tablero[0][1] = new Pieza('c');
        tablero[0][2] = new Pieza('a');
        tablero[0][3] = new Pieza('r');
        tablero[0][4] = new Pieza('k');
        tablero[0][5] = new Pieza('a');
        tablero[0][6] = new Pieza('c');
        tablero[0][7] = new Pieza('t');

        tablero[1][0] = new Pieza('p');
        tablero[1][1] = new Pieza('p');
        tablero[1][2] = new Pieza('p');
        tablero[1][3] = new Pieza('p');
        tablero[1][4] = new Pieza('p');
        tablero[1][5] = new Pieza('p');
        tablero[1][6] = new Pieza('p');
        tablero[1][7] = new Pieza('p');

        tablero[6][0] = new Pieza('P');
        tablero[6][1] = new Pieza('P');
        tablero[6][2] = new Pieza('P');
        tablero[6][3] = new Pieza('P');
        tablero[6][4] = new Pieza('P');
        tablero[6][5] = new Pieza('P');
        tablero[6][6] = new Pieza('P');
        tablero[6][7] = new Pieza('P');

        tablero[7][0] = new Pieza('T');
        tablero[7][1] = new Pieza('C');
        tablero[7][2] = new Pieza('A');;
        tablero[7][3] = new Pieza('R');
        tablero[7][4] = new Pieza('K');
        tablero[7][5] = new Pieza('A');
        tablero[7][6] = new Pieza('C');
        tablero[7][7] = new Pieza('T');
    }

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

    public void agregarPieza(int fila, int columna, Pieza pieza) {
        tablero[fila][columna] = pieza;
    }

    public Pieza getPieza(int fila, int columna) {
        return tablero[fila][columna];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < 8; fila++) {
            sb.append(8 - fila).append(" ");
            for (int col = 0; col < 8; col++) {
                sb.append("|").append(Reglas.simbolo(tablero[fila][col]));
            }
            sb.append("|\n");
        }
        sb.append("   a b c d e f g h\n");
        return sb.toString();
    }

    public boolean reyMuerto(Color turno) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza p = tablero[fila][col];
                if (p != null && p.getTipoPieza() == TipoPieza.REY && p.getColor() == turno) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getNumColumnas() {
        return 8;
    }

    public int getNumFilas() {
        return 8;
    }

    public void realizarMovimiento(Movimiento mov) {
        int columnaOrigen = mov.toString().charAt(4);
        int filaOrigen = Character.getNumericValue(mov.toString().charAt(3));
        int columnaDestino = mov.toString().charAt(2);
        int filaDestino = Character.getNumericValue(mov.toString().charAt(1));

        tablero[filaDestino][columnaDestino] = tablero[filaOrigen][columnaOrigen];
        tablero[filaOrigen][columnaOrigen] = null;
    }
}