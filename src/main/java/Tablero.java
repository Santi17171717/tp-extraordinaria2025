public class Tablero {
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
        int columnaOrigen = movimiento.toString().charAt(4);
        int filaOrigen = Character.getNumericValue(movimiento.toString().charAt(3));
        int columnaDestino = movimiento.toString().charAt(2);
        int filaDestino = Character.getNumericValue(movimiento.toString().charAt(1));

        System.out.println(filaOrigen);

        switch (movimiento.toString().charAt(0)){
            case 'P':{
                break;
            }case 'A':{
                do {
                    if (tablero[filaOrigen][columnaOrigen] != null){
                        caminoLibre = false;
                    }
                    if (filaOrigen > filaDestino && columnaOrigen > columnaDestino){ // arriba izq

                        filaOrigen--;
                        columnaOrigen--;
                    } else if (filaOrigen > filaDestino && columnaOrigen < columnaDestino) { // arriba der

                        filaOrigen--;
                        columnaOrigen++;
                    } else if (filaOrigen < filaDestino && columnaOrigen > columnaDestino) { // abajo izq

                        filaOrigen++;
                        columnaOrigen--;
                    }else { // abajo der
                        filaOrigen++;
                        columnaOrigen++;
                    }
                }while (columnaDestino != columnaOrigen);
                return caminoLibre;

            }case 'C':{//Como el caballo salta casillas no hay que comprobar
                break;
            }case 'T':{
                if (columnaOrigen == columnaDestino){ //vertical
                    do {
                        if (filaOrigen > filaDestino){
                            filaOrigen--;
                        }else{
                            filaOrigen++;
                        }
                    }while (filaDestino != filaOrigen);

                }else { // horizontal
                    do {
                        if (columnaOrigen > columnaDestino){
                            columnaOrigen--;
                        }else{
                            columnaOrigen--;
                        }
                    }while (columnaDestino != columnaOrigen);
                }
                break;

            }case 'R':{
                if (columnaOrigen == columnaDestino){ //vertical
                    do {
                        if (filaOrigen > filaDestino){
                            filaOrigen--;
                        }else{
                            filaOrigen++;
                        }
                    }while (filaDestino != filaOrigen);

                } else if (filaOrigen == filaDestino){ // horizontal
                    do {
                        if (columnaOrigen > columnaDestino){
                            columnaOrigen--;
                        }else{
                            columnaOrigen--;
                        }
                    }while (columnaDestino != columnaOrigen);

                }else {//diagonal

                    do {
                        if (tablero[filaOrigen][columnaOrigen] != null){
                            caminoLibre = false;
                        }
                        if (filaOrigen > filaDestino && columnaOrigen > columnaDestino){ // arriba izq

                            filaOrigen--;
                            columnaOrigen--;
                        } else if (filaOrigen > filaDestino && columnaOrigen < columnaDestino) { // arriba der

                            filaOrigen--;
                            columnaOrigen++;
                        } else if (filaOrigen < filaDestino && columnaOrigen > columnaDestino) { // abajo izq

                            filaOrigen++;
                            columnaOrigen--;
                        }else { // abajo der
                            filaOrigen++;
                            columnaOrigen++;
                        }
                    }while (columnaDestino != columnaOrigen);
                }
                break;
            }case 'K':{

                break;
            }
        }
        return caminoLibre;
    }

    public void agregarPieza(int fila, int columna, Pieza pieza) {
        if (fila < 8  && fila >= 0 && columna < 8 && columna >= 0 && tablero[fila][columna] == null){
            tablero[fila][columna] = pieza;
        }
        else if (fila >= 8 || columna >= 8 || columna < 0 || fila < 0){
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está fuera de rango con " +
                    "las coordenadas correspondientes");
        }else {
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está ocupada por la pieza "
                    + pieza);
        }
    }

    public Pieza getPieza(int fila, int columna) {
        if (fila < 8  && fila >= 0 && columna < 8 && columna >= 0){
            return tablero[fila][columna];
        }
        else {
            throw new IllegalArgumentException("La casilla (" + fila + ", "+ columna + ") está fuera de rango con " +
                    "las coordenadas correspondientes");
        }
    }

    public String toString() {

    }

    public boolean reyMuerto(Color turno) {
        boolean muerto = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Color.NEGRO == turno && muerto) {
                     muerto = !(tablero[i][j].toString().equals("♚"));
                }
                if (Color.BLANCO == turno && muerto) {
                    muerto = !(tablero[i][j].toString().equals("♔"));
                }
            }
        }
        return muerto;
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

