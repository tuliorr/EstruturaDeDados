# Estrutura de Dados

Repositório didático da disciplina de Estrutura de Dados (T390), voltado para
alunos de graduação em Computação. A ideia central é implementar as estruturas
do zero, de forma simples, legível e comentada, para que seja possível estudar
não apenas como usar cada estrutura, mas também como ela funciona internamente.

Os códigos evitam bibliotecas prontas de estruturas de dados sempre que isso
ajuda o aprendizado. Em vez de esconder ponteiros, vetores, colisões ou
rotações, o repositório expõe essas operações para que elas possam ser
analisadas em aula, depuradas e modificadas pelos alunos.

## Organização Geral

```text
Unidade1/
Unidade2/
  P01Listas/
  P02Pilhas/
  P03Filas/
  P04FilasPrioridade/
Unidade3/
Unidade4/
QuestoesPraticas/
  01-Recursividade/
  02-Listas/
  03-Pilhas/
  04-Filas/
  05-FilasPrioridade/
  06-Hash/
  07-Arvores/
```

Cada unidade possui um README próprio com a ordem sugerida de estudo. As pastas
da Unidade 2 também possuem READMEs específicos, porque essa unidade concentra
muitas estruturas lineares.

## Como Ler as Complexidades

Complexidade descreve como o trabalho de um algoritmo cresce quando a entrada
aumenta. Ela não mede segundos exatos: permite comparar algoritmos sem depender
do computador usado. Neste material:

- `n` costuma representar a quantidade de elementos;
- `m` representa a capacidade de uma tabela;
- `h` representa a altura de uma árvore;
- complexidade de tempo conta o crescimento das operações realizadas;
- complexidade de espaço conta a memória auxiliar usada pelo algoritmo.

Quando uma tabela não indicar outro caso, o valor representa o **pior caso**. Em
hashing também mostramos o caso médio, que pressupõe boa distribuição das
chaves. Um custo **amortizado** distribui uma operação ocasionalmente cara, como
redimensionar um vetor, entre várias operações baratas.

| Ordem | Leitura intuitiva | Exemplo típico |
|---|---|---|
| `O(1)` | constante | consultar o topo de uma pilha |
| `O(log n)` | cresce lentamente | busca em árvore balanceada |
| `O(n)` | percorre os elementos uma vez | busca sequencial |
| `O(n log n)` | divide e processa os grupos | ordenações eficientes |
| `O(n²)` | compara muitos pares | dois laços aninhados |
| `O(2ⁿ)` | dobra a cada novo nível | recursão ingênua com duas chamadas |

## Conteúdo da Disciplina

### Unidade I - Técnicas de programação para implementação de estruturas de dados

Descrever as técnicas de programação usadas na implementação de estruturas de
dados.

Implementar algoritmos com uso de técnicas de programação.

Valorar o emprego das técnicas de programação na construção de estruturas de
dados.

Conteúdo:

1. 01.01 - Recursividade.
2. 01.02 - Tipos abstratos de dados.
3. 01.03 - Referências e alias.
4. 01.04 - Alocação de memória: estática e dinâmica.

### Unidade II - Estruturas de dados lineares

Diferenciar as estruturas de dados e suas complexidades na resolução de
problemas computacionais.

Implementar algoritmos com estruturas de dados e linguagens de programação.

Ser crítico no uso de estruturas de dados para resolução de problemas
computacionais.

Conteúdo:

1. 02.01 - Listas: estática e dinâmica (simplesmente encadeada, duplamente
   encadeada, não ordenada, ordenada e circulares).
2. 02.02 - Pilhas: estática e dinâmica.
3. 02.03 - Filas: estática e dinâmica.
4. 02.04 - Filas de prioridade: não ordenada, ordenada e heap.

### Unidade III - Estruturas de dados dispersas

Explicar o funcionamento de estruturas de dados dispersas na resolução de
problemas computacionais.

Implementar algoritmos com estruturas de dados dispersas.

Valorar a importância das estruturas de dados dispersas para organização e busca
de dados.

Conteúdo:

1. 03.01 - Tabelas hashing.
2. 03.02 - Função de hashing.
3. 03.03 - Endereçamento aberto: tentativa linear e tentativa quadrática.
4. 03.04 - Endereçamento fechado.

### Unidade IV - Estruturas de dados hierárquicas

Identificar os cenários propícios para o emprego de estruturas de dados
hierárquicas.

Aplicar estruturas de dados hierárquicas na resolução de problemas
computacionais.

Ser crítico no uso das estruturas de dados hierárquicas na resolução de
problemas computacionais.

Conteúdo:

1. 04.01 - Árvore N-ária.
2. 04.02 - Árvore binária.
3. 04.03 - Árvore de busca.
4. 04.04 - Árvore balanceada AVL.

## Padrão dos Códigos

Os arquivos seguem um padrão didático simples:

- seções para atributos, construtor, métodos básicos, inserção, remoção, busca
  e representação;
- `main` com exemplo determinístico e comentado.
