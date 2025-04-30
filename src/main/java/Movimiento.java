public class Movimiento {
    private Pieza pieza;
    private int filaOrigen;
    private int columnaOrigen;
    private int filaDestino;
    private int columnaDestino;

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
                letra = "P";
                break;
            }case ALFIL:{
                letra = "A";
                break;
            }case CABALLO:{
                letra = "C";
                break;
            }case TORRE:{
                letra = "T";
                break;
            }case REINA:{
                letra = "R";
                break;
            }case REY:{
                letra = "K";
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
        return letra + destino + (8-filaDestino) + origen + (8 - filaOrigen);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
