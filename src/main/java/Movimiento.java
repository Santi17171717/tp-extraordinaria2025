/**
 * Representa un movimiento de ajedrez. La pieza que se mueve,
 * su posición de origen y su posición de destino.
 */
public class Movimiento {
    /** La pieza que se mueve */
    public Pieza pieza;
    /** Fila de origen del movimiento (0-7) */
    public int filaOrigen;
    /** Columna de origen del movimiento (0-7) */
    public int columnaOrigen;
    /** Fila de destino del movimiento (0-7) */
    public int filaDestino;
    /** Columna de destino del movimiento (0-7) */
    public int columnaDestino;

    /**
     * Constructor para un movimiento.
     * @param pieza La pieza que se moverá
     * @param filaOrigen Fila de origen (0-7)
     * @param columnaOrigen Columna de origen (0-7)
     * @param filaDestino Fila de destino (0-7)
     * @param columnaDestino Columna de destino (0-7)
     */
    public Movimiento(Pieza pieza, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        this.pieza = pieza;
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.filaDestino = filaDestino;
        this.columnaDestino = columnaDestino;
    }

    /**
     * Genera una representación en notación algebraica del movimiento.
     * @return String que representa el movimiento abreviado
     */
    @Override
    public String toString() {
        String letra = "";
        String origen = "";
        String destino = "";

        switch (pieza.getTipoPieza()) {
            case PEON:
                letra = (pieza.getColor() == Color.BLANCO) ? "P" : "p";
                break;
            case ALFIL:
                letra = (pieza.getColor() == Color.BLANCO) ? "A" : "a";
                break;
            case CABALLO:
                letra = (pieza.getColor() == Color.BLANCO) ? "C" : "c";
                break;
            case TORRE:
                letra = (pieza.getColor() == Color.BLANCO) ? "T" : "t";
                break;
            case REINA:
                letra = (pieza.getColor() == Color.BLANCO) ? "D" : "d";
                break;
            case REY:
                letra = (pieza.getColor() == Color.BLANCO) ? "R" : "r";
                break;
        }

        origen = convertirColumnaALetra(columnaOrigen);
        destino = convertirColumnaALetra(columnaDestino);

        return letra + origen + (8 - filaOrigen) + destino + (8 - filaDestino);
    }

    /**
     * Compara si el movimiento es igual a otro objeto.
     * @param obj Objeto a comparar
     * @return Si son movimientos iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Movimiento)) return false;
        Movimiento otro = (Movimiento) obj;
        return filaOrigen == otro.filaOrigen &&
                columnaOrigen == otro.columnaOrigen &&
                filaDestino == otro.filaDestino &&
                columnaDestino == otro.columnaDestino &&
                pieza != null && otro.pieza != null &&
                pieza.getTipoPieza() == otro.pieza.getTipoPieza() &&
                pieza.getColor() == otro.pieza.getColor();
    }

    /**
     * Convierte un índice de columna (0-7) a letra (a-h).
     * @param columna Índice numérico de la columna (0-7)
     * @return Letra correspondiente a la columna (a-h)
     */
    private String convertirColumnaALetra(int columna) {
        switch (columna) {
            case 0: return "a";
            case 1: return "b";
            case 2: return "c";
            case 3: return "d";
            case 4: return "e";
            case 5: return "f";
            case 6: return "g";
            case 7: return "h";
            default: return "";
        }
    }
}