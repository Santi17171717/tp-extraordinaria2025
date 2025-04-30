public class Pieza {
    private TipoPieza tipoPieza;
    private Color color;
    private boolean movida;


    public Pieza(char c) {
        this.movida = false;
        switch (c){
            case 'P':{
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.BLANCO;
                break;
            }

            case 'p':{
                this.tipoPieza = TipoPieza.PEON;
                this.color = Color.NEGRO;
                break;
            }

            case 'A':{
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.BLANCO;
                break;
            }

            case 'a':{
                this.tipoPieza = TipoPieza.ALFIL;
                this.color = Color.NEGRO;
                break;
            }

            case 'T':{
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.BLANCO;
                break;
            }

            case 't':{
                this.tipoPieza = TipoPieza.TORRE;
                this.color = Color.NEGRO;
                break;
            }

            case 'C':{
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.BLANCO;
                break;
            }

            case 'c':{
                this.tipoPieza = TipoPieza.CABALLO;
                this.color = Color.NEGRO;
                break;

            }

            case 'R':{
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.BLANCO;
                break;
            }

            case 'r':{
                this.tipoPieza = TipoPieza.REINA;
                this.color = Color.NEGRO;
                break;
            }

            case 'K':{
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.BLANCO;
                break;
            }

            case 'k':{
                this.tipoPieza = TipoPieza.REY;
                this.color = Color.NEGRO;
                break;
            }
            default:{
                throw new IllegalArgumentException("Caracter de pieza no reconocido");
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
        return this.movida;
    }

    public void marcarMovida() {
        this.movida = true;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public String toString() {

        String pieza = "";

       if (this.getColor() == Color.NEGRO){
           if (this.getTipoPieza() == TipoPieza.PEON){
               pieza = "♟";
           } else if (this.getTipoPieza() == TipoPieza.ALFIL){
               pieza = "♝";
           }
           else if (this.getTipoPieza() == TipoPieza.CABALLO){
               pieza = "♞";
           }
           else if (this.getTipoPieza() == TipoPieza.TORRE){
               pieza = "♜";
           }
           else if (this.getTipoPieza() == TipoPieza.REINA){
               pieza = "♛";
           }
           else if (this.getTipoPieza() == TipoPieza.REY){
               pieza = "♚";
           }
       } else if (this.getColor() == Color.BLANCO) {

           if (this.getTipoPieza() == TipoPieza.PEON){
               pieza = "♙";
           }
           else if (this.getTipoPieza() == TipoPieza.ALFIL){
               pieza = "♗";
           }
           else if (this.getTipoPieza() == TipoPieza.CABALLO){
               pieza = "♘";
           }
           else if (this.getTipoPieza() == TipoPieza.TORRE){
               pieza = "♖";
           }
           else if (this.getTipoPieza() == TipoPieza.REINA){
               pieza = "♕";
           }
           else if (this.getTipoPieza() == TipoPieza.REY){
               pieza = "♔";
           }
       }
       return  pieza;
    }
}
