package grafos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * Recorridos de grafos: anchura (BFS) y profundidad (DFS).
 * Devuelven el orden en que se visitan los vertices alcanzables desde el inicio.
 */
public class Recorridos {

    /**
     * BFS con cola.
     * Complejidad: O(V + E) con listas de adyacencia, O(V^2) con matriz.
     */
    public static List<Integer> bfs(Grafo g, int inicio) {
        boolean[] visitado = new boolean[g.numVertices()];
        List<Integer> orden = new ArrayList<>();
        Queue<Integer> cola = new ArrayDeque<>();

        visitado[inicio] = true;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int u = cola.poll();
            orden.add(u);
            for (Arista a : g.vecinos(u)) {
                if (!visitado[a.destino()]) {
                    visitado[a.destino()] = true;
                    cola.add(a.destino());
                }
            }
        }
        return orden;
    }

    /**
     * DFS recursivo.
     * Complejidad: O(V + E) con listas de adyacencia, O(V^2) con matriz.
     */
    public static List<Integer> dfs(Grafo g, int inicio) {
        boolean[] visitado = new boolean[g.numVertices()];
        List<Integer> orden = new ArrayList<>();
        dfsRec(g, inicio, visitado, orden);
        return orden;
    }

    private static void dfsRec(Grafo g, int u, boolean[] visitado, List<Integer> orden) {
        visitado[u] = true;
        orden.add(u);
        for (Arista a : g.vecinos(u)) {
            if (!visitado[a.destino()]) {
                dfsRec(g, a.destino(), visitado, orden);
            }
        }
    }

    /**
     * DFS iterativo con pila explicita (evita desbordar la pila de llamadas
     * en grafos muy profundos). Produce el mismo orden que la version recursiva.
     */
    public static List<Integer> dfsIterativo(Grafo g, int inicio) {
        boolean[] visitado = new boolean[g.numVertices()];
        List<Integer> orden = new ArrayList<>();
        Deque<Integer> pila = new ArrayDeque<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            int u = pila.pop();
            if (visitado[u]) {
                continue;
            }
            visitado[u] = true;
            orden.add(u);
            // Se apilan en orden inverso para visitar primero el vecino de menor indice
            List<Arista> vecinos = g.vecinos(u);
            for (int i = vecinos.size() - 1; i >= 0; i--) {
                int v = vecinos.get(i).destino();
                if (!visitado[v]) {
                    pila.push(v);
                }
            }
        }
        return orden;
    }
}
