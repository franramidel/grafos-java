# Grafos en Java

Algoritmos de la investigación "Grafos y Algoritmos Fundamentales de Recorrido y Optimización"
(Algoritmos y Estructuras de Datos I, TEC).

- Grafo no dirigido y ponderado con **matriz** (`GrafoMatriz`) y **listas** (`GrafoLista`) de adyacencia
- **BFS** y **DFS** (recursivo e iterativo) en `Recorridos`
- **Prim** y **Kruskal** (con Union-Find) en `ArbolExpansionMinima`

## Ejecutar (Java 17+)

```
javac -d out src/grafos/*.java
java -cp out grafos.Main

javac -cp out -d out-test test/PruebasGrafos.java
java -cp out:out-test PruebasGrafos
```

En Windows usar `;` en lugar de `:` en el classpath (`out;out-test`).

## Complejidades

| Algoritmo | Listas | Matriz |
|---|---|---|
| BFS / DFS | O(V+E) | O(V^2) |
| Prim (heap) | O(E log V) | O(V^2) con minimo lineal |
| Kruskal | O(E log E) | O(E log E) |

El informe IEEE (LaTeX) está en `informe/`.
