public class Movimiento {
    public Pieza pieza;
    public int filaOrigen;
    public int columnaOrigen;
    public int filaDestino;
    public int columnaDestino;

    public Movimiento(Pieza pieza, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        this.pieza = pieza;
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.filaDestino = filaDestino;
        this.columnaDestino = columnaDestino;
    }

    public String toString() {
        String letra = "";
        String origen = "";
        String destino = "";

        switch (pieza.getTipoPieza()){
            case PEON:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "P";
                }else {
                    letra = "p";
                }

                break;
            }case ALFIL:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "A";
                }else {
                    letra = "a";
                }
                break;
            }case CABALLO:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "C";
                }else {
                    letra = "c";
                }

                break;
            }case TORRE:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "T";
                }else {
                    letra = "t";
                }

                break;
            }case REINA:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "D";
                }else {
                    letra = "d";
                }
                break;
            }case REY:{
                if (pieza.getColor() == Color.BLANCO){
                    letra = "R";
                }else {
                    letra = "r";
                }

                break;
            }
        }
        switch (columnaOrigen){
            case 0:{
                origen = "a";
                break;
            }case 1:{
                origen = "b";
                break;
            }case 2:{
                origen = "c";
                break;
            }case 3:{
                origen = "d";
                break;
            }case 4:{
                origen = "e";
                break;
            }case 5:{
                origen = "f";
                break;
            }case 6:{
                origen = "g";
                break;
            }case 7:{
                origen = "h";
                break;
            }
        }switch (columnaDestino){
            case 0:{
                destino = "a";
                break;
            }case 1:{
                destino = "b";
                break;
            }case 2:{
                destino = "c";
                break;
            }case 3:{
                destino = "d";
                break;
            }case 4:{
                destino = "e";
                break;
            }case 5:{
                destino = "f";
                break;
            }case 6:{
                destino = "g";
                break;
            }case 7:{
                destino = "h";
                break;
            }
        }
        return letra + origen + (8 - filaOrigen) + destino + (8 - filaDestino);
    }

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
}
