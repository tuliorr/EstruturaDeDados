# Listas

Listas armazenam elementos em uma ordem lógica. Elas são uma boa primeira
família de estruturas porque permitem comparar claramente duas formas de
organização: vetor e encadeamento.

Em listas com vetor, os elementos ficam em posições contíguas de memória. Isso
facilita o acesso por índice, mas exige deslocamentos quando inserimos ou
removemos no início ou no meio. Em listas encadeadas, cada elemento fica em um
nodo, e cada nodo aponta para o próximo. Isso evita deslocamentos, mas exige
percorrer referências até encontrar a posição desejada.

## Ordem Sugerida

1. `ListaVetor.java`

   Lista estática de inteiros. É o ponto de partida para entender capacidade,
   posição lógica e deslocamento de elementos.

2. `ListaVetorGenerica.java`

   Generaliza a lista em vetor para qualquer tipo comparável. Mostra por que
   genéricos tornam a estrutura reutilizável.

3. `ListaVetorGenericaDinamica.java`

   Acrescenta redimensionamento. Quando o vetor lota, a estrutura cria um vetor
   maior e copia os elementos antigos.

4. `ListaSimplesmenteEncadeada.java`

   Apresenta nodos com referência para o próximo elemento. É a base para
   entender encadeamento.

5. `ListaSimplesmenteEncadeadaGenerica.java`

   Aplica genéricos à lista simplesmente encadeada.

6. `ListaDuplamenteEncadeada.java`

   Cada nodo conhece o anterior e o próximo. Isso facilita operações no fim da
   lista e permite navegação nos dois sentidos.

7. `ListaCircularDuplamenteEncadeada.java`

   Fecha a lista em ciclo: o fim aponta para o início, e o início aponta para o
   fim. É útil para entender estruturas circulares e cuidados para evitar laços
   infinitos.

## Pontos de Atenção

- Em vetor, `nElementos` indica a primeira posição livre.
- Em lista encadeada, `inicio` e `fim` reduzem casos especiais.
- Em listas circulares, não se deve usar `while (atual != null)`, porque os
  nodos nunca chegam a `null`.

`insereOrdenado` pressupõe que os elementos já existentes estejam em ordem
crescente. Misturar essa operação com inserções livres pode deixar o resultado
fora de ordem; a estrutura não faz uma ordenação automática.

## Complexidades

| Implementação | Acesso por posição | Busca | Inserção no início | Inserção no fim | Remoção no início | Remoção no fim |
|---|---:|---:|---:|---:|---:|---:|
| Vetor estático | `O(1)` | `O(n)` | `O(n)` | `O(1)` | `O(n)` | `O(1)` |
| Vetor dinâmico | `O(1)` | `O(n)` | `O(n)` | `O(1)` amortizado | `O(n)` | `O(1)` |
| Simplesmente encadeada | `O(n)` | `O(n)` | `O(1)` | `O(1)` | `O(1)` | `O(n)` |
| Duplamente encadeada | `O(n)` | `O(n)` | `O(1)` | `O(1)` | `O(1)` | `O(1)` |
| Circular dupla | `O(n)` | `O(n)` | `O(1)` | `O(1)` | `O(1)` | `O(1)` |

Inserir ou remover em uma posição intermediária custa `O(n)` em todas as versões:
o vetor desloca elementos e as listas encadeadas precisam localizar a posição.
Uma inserção ordenada também custa `O(n)` para encontrar o ponto correto.
