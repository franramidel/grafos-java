package grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Grafo con listas de adyacencia.
 * Espacio: O(V + E).
 */
public class GrafoLista implements Grafo {

    private final List<List<Arista>> adyacencia;
    private final int n;

    public GrafoLista(int n) {
        this.n = n;
        this.adyacencia = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    @Override
    public int numVertices() {
        return n;
    }

    /** O(1) */
    @Override
    public void agregarArista(int u, int v, int peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        adyacencia.get(u).add(new Arista(u, v, peso));
        adyacencia.get(v).add(new Arista(v, u, peso));
    }

    /** Recorrer los vecinos de u cuesta O(grado(u)). */
    @Override
    public List<Arista> vecinos(int u) {
        List<Arista> copia = new ArrayList<>(adyacencia.get(u));
        copia.sort(Comparator.comparingInt(Arista::destino));
        return copia;
    }

    @Override
    public List<Arista> aristas() {
        List<Arista> resultado = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            for (Arista a : adyacencia.get(u)) {
                if (a.origen() < a.destino()) {
                    resultado.add(a);
                }
            }
        }
        return resultado;
    }
}
