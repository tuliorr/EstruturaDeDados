# Filas de Prioridade

Filas de prioridade removem o elemento mais prioritário, não necessariamente o
mais antigo. Elas são úteis quando alguns itens devem ser aténdidos antes de
outros, como em escalonamento, simulações, processamento de eventos e
algoritmos de grafos.

## Ordem Sugerida

1. `FilaPrioridadeNaoOrdenada.java`

   A inserção é simples, porque o novo elemento entra no fim. O custo aparece na
   remoção, quando a estrutura precisa procurar a maior prioridade.

2. `FilaPrioridadeOrdenada.java`

   Mantém os elementos ordenados desde a inserção. A remoção fica simples, pois
   o maior elemento já está no início.

3. `FilaPrioridadeHeap.java`

   Usa um Max-Heap. O maior elemento fica na raiz, e inserção/remoção exigem
   reorganizar apenas um caminho da árvore representada no vetor.

4. `FilaPrioridadeHeapEstavel.java`

   Acrescenta estabilidade ao heap. Quando dois elementos têm a mesma
   prioridade, o que chegou primeiro também sai primeiro.

## Pontos de Comparação

- Fila não ordenada: inserção barata, remoção cara.
- Fila ordenada: inserção mais cara, remoção barata.
- Heap: equilíbrio entre inserção e remoção.
- Heap estável: mantém prioridade e ordem de chegada em empates.
