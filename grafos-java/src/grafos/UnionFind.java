package grafos;

/**
 * Estructura Union-Find (conjuntos disjuntos) con compresion de caminos
 * y union por rango. Cada operacion cuesta O(alfa(n)), practicamente constante.
 */
public class UnionFind {

    private final int[] padre;
    private final int[] rango;

    public UnionFind(int n) {
        padre = new int[n];
        rango = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
        }
    }

    public int find(int x) {
        if (padre[x] != x) {
            padre[x] = find(padre[x]); // compresion de caminos
        }
        return padre[x];
    }

    /** Une los conjuntos de a y b. Devuelve false si ya estaban unidos (formaria ciclo). */
    public boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return false;
        }
        if (rango[ra] < rango[rb]) {
            padre[ra] = rb;
        } else if (rango[ra] > rango[rb]) {
            padre[rb] = ra;
        } else {
            padre[rb] = ra;
            rango[ra]++;
        }
        return true;
    }
}
