package grafos;

import java.util.List;

/**
 * Contrato comun para las dos representaciones del grafo (no dirigido y ponderado).
 * Los vertices se identifican con enteros 0..n-1.
 */
public interface Grafo {

    /** Cantidad de vertices. */
    int numVertices();

    /** Agrega una arista no dirigida u-v con el peso dado. */
    void agregarArista(int u, int v, int peso);

    /** Vecinos de u, ordenados por indice de vertice. */
    List<Arista> vecinos(int u);

    /** Todas las aristas del grafo, cada una una sola vez (origen < destino). */
    List<Arista> aristas();
}
