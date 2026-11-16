package grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Algoritmos de Arbol de Expansion Minima (MST): Prim y Kruskal.
 * Ambos devuelven la lista de aristas del arbol (V-1 aristas si el grafo es conexo).
 */
public class ArbolExpansionMinima {

    /** Suma de los pesos de una lista de aristas. */
    public static int pesoTotal(List<Arista> arbol) {
        int total = 0;
        for (Arista a : arbol) {
            total += a.peso();
        }
        return total;
    }

    /**
     * Prim con cola de prioridad (min-heap).
     * Complejidad: O(E log V) con listas de adyacencia.
     * Con matriz y busqueda lineal del minimo seria O(V^2).
     */
    public static List<Arista> prim(Grafo g, int inicio) {
        int n = g.numVertices();
        boolean[] enArbol = new boolean[n];
        List<Arista> arbol = new ArrayList<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>();

        enArbol[inicio] = true;
        cola.addAll(g.vecinos(inicio));

        while (!cola.isEmpty() && arbol.size() < n - 1) {
            Arista menor = cola.poll();
            if (enArbol[menor.destino()]) {
                continue; // formaria un ciclo
            }
            enArbol[menor.destino()] = true;
            arbol.add(menor);
            for (Arista a : g.vecinos(menor.destino())) {
                if (!enArbol[a.destino()]) {
                    cola.add(a);
                }
            }
        }
        return arbol;
    }

    /**
     * Kruskal: ordena las aristas por peso y agrega la menor que no forme ciclo,
     * usando Union-Find.
     * Complejidad: O(E log E) = O(E log V), dominada por el ordenamiento.
     */
    public static List<Arista> kruskal(Grafo g) {
        int n = g.numVertices();
        List<Arista> ordenadas = new ArrayList<>(g.aristas());
        Collections.sort(ordenadas);

        UnionFind uf = new UnionFind(n);
        List<Arista> arbol = new ArrayList<>();

        for (Arista a : ordenadas) {
            if (uf.union(a.origen(), a.destino())) {
                arbol.add(a);
                if (arbol.size() == n - 1) {
                    break;
                }
            }
        }
        return arbol;
    }
}
