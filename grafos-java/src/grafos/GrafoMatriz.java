package grafos;

import java.util.ArrayList;
import java.util.List;

/**
 * Grafo con matriz de adyacencia. peso 0 significa "no hay arista",
 * por lo que los pesos deben ser positivos.
 * Espacio: O(V^2).
 */
public class GrafoMatriz implements Grafo {

    private final int[][] matriz;
    private final int n;

    public GrafoMatriz(int n) {
        this.n = n;
        this.matriz = new int[n][n];
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
        matriz[u][v] = peso;
        matriz[v][u] = peso;
    }

    /** Consultar si existe la arista u-v: O(1). */
    public boolean existeArista(int u, int v) {
        return matriz[u][v] != 0;
    }

    /** Recorrer los vecinos de u cuesta O(V) aunque tenga pocos. */
    @Override
    public List<Arista> vecinos(int u) {
        List<Arista> resultado = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (matriz[u][v] != 0) {
                resultado.add(new Arista(u, v, matriz[u][v]));
            }
        }
        return resultado;
    }

    @Override
    public List<Arista> aristas() {
        List<Arista> resultado = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                if (matriz[u][v] != 0) {
                    resultado.add(new Arista(u, v, matriz[u][v]));
                }
            }
        }
        return resultado;
    }

    public int[][] getMatriz() {
        return matriz;
    }
}
