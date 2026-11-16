package grafos;

/**
 * Arista ponderada de un grafo. Para grafos no dirigidos se guarda una
 * Arista en cada extremo (u->v y v->u).
 */
public record Arista(int origen, int destino, int peso) implements Comparable<Arista> {

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }
}
