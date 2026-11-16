import grafos.*;

import java.util.List;

/** Pruebas simples sin dependencias externas. Ejecutar con: java -cp out:out-test PruebasGrafos */
public class PruebasGrafos {

    static int fallos = 0;

    static void verificar(String nombre, boolean ok) {
        System.out.println((ok ? "[OK]    " : "[FALLO] ") + nombre);
        if (!ok) {
            fallos++;
        }
    }

    static void cargar(Grafo g) {
        g.agregarArista(0, 1, 7);
        g.agregarArista(0, 2, 3);
        g.agregarArista(1, 2, 1);
        g.agregarArista(1, 3, 5);
        g.agregarArista(2, 4, 6);
        g.agregarArista(3, 4, 2);
        g.agregarArista(3, 5, 4);
        g.agregarArista(4, 5, 8);
    }

    public static void main(String[] args) {
        Grafo[] grafos = {new GrafoMatriz(6), new GrafoLista(6)};
        String[] tipos = {"matriz", "lista"};

        for (int i = 0; i < grafos.length; i++) {
            Grafo g = grafos[i];
            cargar(g);
            String t = " (" + tipos[i] + ")";
            verificar("BFS" + t, Recorridos.bfs(g, 0).equals(List.of(0, 1, 2, 3, 4, 5)));
            verificar("DFS" + t, Recorridos.dfs(g, 0).equals(List.of(0, 1, 2, 4, 3, 5)));
            verificar("DFS iterativo = recursivo" + t,
                    Recorridos.dfsIterativo(g, 0).equals(Recorridos.dfs(g, 0)));
            List<Arista> prim = ArbolExpansionMinima.prim(g, 0);
            List<Arista> kruskal = ArbolExpansionMinima.kruskal(g);
            verificar("Prim peso 15 y 5 aristas" + t,
                    ArbolExpansionMinima.pesoTotal(prim) == 15 && prim.size() == 5);
            verificar("Kruskal peso 15 y 5 aristas" + t,
                    ArbolExpansionMinima.pesoTotal(kruskal) == 15 && kruskal.size() == 5);
        }

        // Grafo desconectado: BFS solo alcanza su componente
        GrafoLista d = new GrafoLista(4);
        d.agregarArista(0, 1, 1);
        d.agregarArista(2, 3, 1);
        verificar("BFS en grafo desconectado", Recorridos.bfs(d, 0).equals(List.of(0, 1)));
        verificar("Kruskal en bosque devuelve V-c aristas", ArbolExpansionMinima.kruskal(d).size() == 2);

        System.out.println(fallos == 0 ? "\nTodas las pruebas pasaron" : "\nFallos: " + fallos);
        System.exit(fallos == 0 ? 0 : 1);
    }
}
