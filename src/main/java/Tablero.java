public class Tablero {
    private String[][] tablero;
    public Tablero(String notacionFEN) throws IllegalArgumentException {
        tablero[0][0] = "♜";
        tablero[0][1] = "♞";
        tablero[0][2] = "♝";
        tablero[0][3] = "♛";
        tablero[0][4] = "♚";
        tablero[0][5] = "♝";
        tablero[0][6] = "♞";
        tablero[0][7] = "♜";

        tablero[1][0] = "♟";
        tablero[1][1] = "♟";
        tablero[1][2] = "♟";
        tablero[1][3] = "♟";
        tablero[1][4] = "♟";
        tablero[1][5] = "♟";
        tablero[1][6] = "♟";
        tablero[1][7] = "♟";

        tablero[6][0] = "♙";
        tablero[6][1] = "♙";
        tablero[6][2] = "♙";
        tablero[6][3] = "♙";
        tablero[6][4] = "♙";
        tablero[6][5] = "♙";
        tablero[6][6] = "♙";
        tablero[6][7] = "♙";

        tablero[7][0] = "♖";
        tablero[7][1] = "♘";
        tablero[7][2] = "♗";
        tablero[7][3] = "♕";
        tablero[7][4] = "♔";
        tablero[7][5] = "♗";
        tablero[7][6] = "♘";
        tablero[7][7] = "♖";
    }

    boolean comprobarPiezasEnCamino(Movimiento movimiento) {

    }

    public void agregarPieza(int fila, int columna, Pieza pieza) {

    }

    public Pieza getPieza(int fila, int columna) {

    }

    public String toString() {

    }

    public boolean reyMuerto(Color turno) {

    }

    public int getNumColumnas() {

    }

    public int getNumFilas() {

    }

    public void realizarMovimiento(Movimiento mov) {

    }
}

