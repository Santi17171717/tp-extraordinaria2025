/**
 * Representa una pieza de ajedrez con su tipo, color y si ha sido movida.
 * Crea piezas a partir de caracteres FEN.
 * Verifica igualdad entre piezas y obtiene su representación visual.
 */
public class Pieza {
    /** Tipo de pieza  */
    private final TipoPieza tipoPieza;
    /** Color de la pieza */
    private final Color color;
    /** Indica si la pieza ya ha sido movida  */
    private boolean movida;

    /**
     * Construye una pieza a partir de su representación FEN.
     * @param c Carácter FEN que representa la pieza (ej. 'P' para peón blanco, 'p' para peón negro)
     * @throws IllegalArgumentException Si el carácter no corresponde a ninguna pieza válida
     */
    public Pieza(char c) {
        this.movida = false;
        switch (c) {
            case 'P':
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.BLANCO;
                break;
            case 'p':
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.NEGRO;
                break;
            case 'A':
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.BLANCO;
                break;
            case 'a':
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.NEGRO;
                break;
            case 'T':
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.BLANCO;
                break;
            case 't':
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.NEGRO;
                break;
            case 'C':
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.BLANCO;
                break;
            case 'c':
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.NEGRO;
                break;
            case 'D':
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.BLANCO;
                break;
            case 'd':
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.NEGRO;
                break;
            case 'R':
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.BLANCO;
                break;
            case 'r':
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.NEGRO;
                break;
            default:
                throw new IllegalArgumentException("Caracter de pieza no reconocido");
        }
    }

    /**
     * @return TipoPieza que representa el tipo de esta pieza
     */
    public TipoPieza getTipoPieza() {
        return tipoPieza;
    }

    /**
     * @return Color de la pieza
     */
    public Color getColor() {
        return color;
    }

    /**
     * Indica si la pieza ya ha sido movida.
     * @return Si la pieza ha sido movida, false en caso contrario
     */
    public boolean getMovida() {
        return this.movida;
    }

    /**
     * Marca la pieza como movida.
     */
    public void marcarMovida() {
        this.movida = true;
    }

    /**
     * Compara esta pieza con un objeto recibidod.
     * @param obj Objeto a comparar
     * @return si son piezas iguales
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pieza otraPieza = (Pieza) obj;
        return this.getColor() == otraPieza.getColor() && this.tipoPieza == otraPieza.tipoPieza;
    }

    /**
     * Devuelve la representación de la pieza como figura.
     * @return String con la figura correspondiente a la pieza
     */
    public String toString() {
        if (this.getColor() == Color.NEGRO) {
            switch (this.getTipoPieza()) {
                case PEON:    return "♟";
                case ALFIL:   return "♝";
                case CABALLO: return "♞";
                case TORRE:   return "♜";
                case REINA:   return "♛";
                case REY:     return "♚";
            }
        } else if (this.getColor() == Color.BLANCO) {
            switch (this.getTipoPieza()) {
                case PEON:    return "♙";
                case ALFIL:   return "♗";
                case CABALLO: return "♘";
                case TORRE:   return "♖";
                case REINA:   return "♕";
                case REY:     return "♔";
            }
        }
        return "";
    }
}