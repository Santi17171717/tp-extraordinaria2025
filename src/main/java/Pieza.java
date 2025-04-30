public class Pieza {
    private TipoPieza tipoPieza;
    private Color color;
    private boolean movida;

    public Pieza(char c) {
        this.movida = false;
        switch (c){
            case '♙':{
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.BLANCO;
                break;
            }

            case '♟':{
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.NEGRO;
                break;
            }

            case '♗':{
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.BLANCO;
                break;
            }

            case '♝':{
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.NEGRO;
                break;
            }

            case '♖':{
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.BLANCO;
                break;
            }

            case '♜':{
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.NEGRO;
                break;
            }

            case '♘':{
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.BLANCO;
                break;
            }

            case '♞':{
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.NEGRO;
                break;

            }

            case '♕':{
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.BLANCO;
                break;
            }

            case '♛':{
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.NEGRO;
                break;
            }

            case '♔':{
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.BLANCO;
                break;
            }

            case '♚':{
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.NEGRO;
                break;
            }

        }
    }


    public TipoPieza getTipoPieza() {
        return tipoPieza;
    }

    public Color getColor() {
        return color;
    }

    public boolean getMovida() {
        return movida;
    }

    public void marcarMovida() {
        this.movida = true;
    }

    @Override
    public boolean equals(Object obj) {

    }

    @Override
    public String toString() {

    }
}
