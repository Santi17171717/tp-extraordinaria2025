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
        boolean caminoLibre = true;
        int i, j;

        switch (movimiento.toString().charAt(0)){
            case 'P':{

            }case 'A':{

            }case 'C':{
                return true;
            }case 'T':{

            }case 'R':{
                if (movimiento.toString().charAt(1) == movimiento.toString().charAt(3)){ //vertical

                }else if (movimiento.toString().charAt(2) == movimiento.toString().charAt(4)){ // horizontal

                }else {//diagonal

                    do {
                        if (tablero[i][j] != null){
                            caminoLibre = false;
                        }
                        if (){ // arriba izq

                        } else if () { // arriba der

                        } else if () { // abajo izq

                        }else { // abajo der

                        }
                    }while ((movimiento.toString().charAt(2) != (movimiento.toString().charAt(4));
                }
            }case 'K':{

            }
        }
        return caminoLibre;
    }

    public void agregarPieza(int fila, int columna, Pieza pieza) {
        if (fila < 8 && columna < 8 && tablero[fila][columna] == null){
            tablero[fila][columna] = pieza.toString();
        }
        else if (fila >= 8 || columna >= 8){
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está fuera de rango con " +
                    "las coordenadas correspondientes");
        }else {
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está ocupada por la pieza "
                    + pieza);
        }
    }

    public Pieza getPieza(int fila, int columna) {
        if (fila < 8 && columna < 8){
            String posicion = tablero[fila][columna];

            return
        }
        else {
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está fuera de rango con " +
                    "las coordenadas correspondientes");
        }
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

