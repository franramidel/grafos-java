package grafos;

import java.util.List;

/**
 * Demo: construye el grafo base de la investigacion con ambas representaciones
 * y ejecuta BFS, DFS, Prim y Kruskal.
 *
 * Grafo base (6 vertices A..F, no dirigido y ponderado):
 *   A-B 7, A-C 3, B-C 1, B-D 5, C-E 6, D-E 2, D-F 4, E-F 8
 */
public class Main {

    static final char[] NOMBRES = {'A', 'B', 'C', 'D', 'E', 'F'};

    /** Carga el grafo base en cualquier implementacion de Grafo. */
    static void cargarGrafoBase(Grafo g) {
        g.agregarArista(0, 1, 7); // A-B
        g.agregarArista(0, 2, 3); // A-C
        g.agregarArista(1, 2, 1); // B-C
        g.agregarArista(1, 3, 5); // B-D
        g.agregarArista(2, 4, 6); // C-E
        g.agregarArista(3, 4, 2); // D-E
        g.agregarArista(3, 5, 4); // D-F
        g.agregarArista(4, 5, 8); // E-F
    }

    static String nombres(List<Integer> vertices) {
        StringBuilder sb = new StringBuilder();
        for (int v : vertices) {
            sb.append(NOMBRES[v]).append(' ');
        }
        return sb.toString().trim();
    }

    static void imprimirArbol(String titulo, List<Arista> arbol) {
        System.out.println(titulo);
        for (Arista a : arbol) {
            System.out.println("  " + NOMBRES[a.origen()] + " - " + NOMBRES[a.destino()] + " (" + a.peso() + ")");
        }
        System.out.println("  Peso total: " + ArbolExpansionMinima.pesoTotal(arbol));
    }

    public static void main(String[] args) {
        GrafoMatriz matriz = new GrafoMatriz(6);
        GrafoLista lista = new GrafoLista(6);
        cargarGrafoBase(matriz);
        cargarGrafoBase(lista);

        System.out.println("=== Matriz de adyacencia ===");
        System.out.print("   ");
        for (char c : NOMBRES) {
            System.out.print(c + "  ");
        }
        System.out.println();
        int[][] m = matriz.getMatriz();
        for (int i = 0; i < m.length; i++) {
            System.out.print(NOMBRES[i] + "  ");
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("\n=== Listas de adyacencia ===");
        for (int u = 0; u < lista.numVertices(); u++) {
            StringBuilder sb = new StringBuilder(NOMBRES[u] + " -> ");
            for (Arista a : lista.vecinos(u)) {
                sb.append("(").append(NOMBRES[a.destino()]).append(",").append(a.peso()).append(") ");
            }
            System.out.println(sb);
        }

        System.out.println("\n=== Recorridos desde A ===");
        System.out.println("BFS (lista):  " + nombres(Recorridos.bfs(lista, 0)));
        System.out.println("BFS (matriz): " + nombres(Recorridos.bfs(matriz, 0)));
        System.out.println("DFS (lista):  " + nombres(Recorridos.dfs(lista, 0)));
        System.out.println("DFS (matriz): " + nombres(Recorridos.dfs(matriz, 0)));
        System.out.println("DFS iterativo: " + nombres(Recorridos.dfsIterativo(lista, 0)));

        System.out.println("\n=== Arbol de expansion minima ===");
        imprimirArbol("Prim (desde A):", ArbolExpansionMinima.prim(lista, 0));
        imprimirArbol("Kruskal:", ArbolExpansionMinima.kruskal(lista));
    }
}
