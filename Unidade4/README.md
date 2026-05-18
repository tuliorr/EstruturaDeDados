# Unidade 4 - Estruturas Hierárquicas

Estruturas hierárquicas organizam dados em relações de pai e filho. Elas são
adequadas para representar classificações, diretórios, árvores sintáticas,
índices de busca e qualquer cenário em que um elemento possa se desdobrar em
outros.

Ao estudar árvores, vale separar três ideias:

- a forma da árvore, isto é, quantos filhos cada nodo pode ter;
- a regra de organização dos elementos;
- o custo de manter a árvore eficiente depois de inserções e remoções.

## Ordem Sugerida

1. `ArvoreNaria.java`

   Modelo em que cada nodo pode ter qualquer quantidade de filhos. É indicado
   para hierarquias gerais, como disciplina, unidades e tópicos. No código, ela
   não usa um vetor ou lista de filhos dentro do nodo: cada nodo guarda
   `primeiroFilho` e `proximoIrmao`. Assim, os filhos de um mesmo pai formam uma
   cadeia de irmãos, e a descida para níveis inferiores acontece pelo primeiro
   filho. Inserir um filho significa encontrar o pai e ligar o novo nodo ao fim
   dessa cadeia.

2. `ArvoreBinaria.java`

   Modelo em que cada nodo tem no máximo dois filhos: `esquerdo` e `direito`.
   Ela não possui regra de ordenação; quem usa a estrutura escolhe onde cada
   elemento será inserido, ou usa a inserção em largura para preencher a primeira
   vaga disponível. Por isso, uma busca pode precisar visitar todos os nodos.

3. `ArvoreBinariaBusca.java`

   Modelo binário com regra de organização: valores menores ficam à esquerda e
   valores maiores ficam à direita. A implementação usa `Comparable<T>` para
   comparar os elementos e decidir o caminho. Essa regra permite descartar uma
   subárvore inteira a cada passo da busca, mas a árvore pode ficar muito
   inclinada dependendo da ordem de inserção.

4. `ArvoreAVL.java`

   Modelo de árvore binária de busca balanceada. Cada nodo guarda também sua
   altura, e a árvore calcula o fator de balanço após inserções e remoções. Se
   algum nodo fica fora do intervalo de -1 a 1, o código usa rotações simples ou
   duplas para recuperar o equilíbrio.

5. `TesteComparativoArvores.java`

   Classe de teste didática que insere a mesma sequência de valores em uma
   árvore binária comum, em uma árvore binária de busca e em uma AVL.

## Percursos

- Pré-ordem: visita o nodo antes dos filhos. Ajuda a enxergar a raiz antes das
  subárvores.
- Em ordem: em uma árvore binária de busca, mostra os elementos em ordem
  crescente.
- Pós-ordem: visita os filhos antes do nodo. É comum em remoções, liberações de
  memória e avaliação de expressões.
- Largura: visita nível por nível usando fila.

## Altura

A altura mede a maior distância entre um nodo e uma folha. Neste repositório,
uma árvore vazia tem altura `-1`, uma árvore com apenas a raiz tem altura `0`, e
cada novo nível aumenta a altura em uma unidade.

Separar a altura esquerda e a altura direita em variáveis ajuda a visualizar o
raciocínio:

```text
alturaEsquerda = altura(filhoEsquerdo)
alturaDireita  = altura(filhoDireito)
alturaDoNodo   = 1 + maior(alturaEsquerda, alturaDireita)
```

## Rotações AVL

Rotações mudam a forma da árvore sem quebrar a regra da árvore de busca. O
objetivo é reduzir a altura do lado pesado.

### Rotação Simples à Direita

Usada no caso esquerda-esquerda, quando o nodo `X` fica pesado à esquerda e o
filho esquerdo `Y` também pende para a esquerda.

```text
Antes:

        X
       /
      Y
     /
    A

Depois:

      Y
     / \
    A   X
```

### Rotação Simples à Esquerda

Usada no caso direita-direita, quando o nodo `X` fica pesado à direita e o filho
direito `Y` também pende para a direita.

```text
Antes:

    X
     \
      Y
       \
        A

Depois:

      Y
     / \
    X   A
```

### Rotação Dupla à Direita

Usada no caso esquerda-direita. Primeiro fazemos uma rotação à esquerda no filho
`Y`, depois uma rotação à direita em `X`.

```text
Antes:

        X
       /
      Y
       \
        Z

Passo 1: rotação à esquerda em Y

        X
       /
      Z
     /
    Y

Passo 2: rotação à direita em X

      Z
     / \
    Y   X
```

### Rotação Dupla à Esquerda

Usada no caso direita-esquerda. Primeiro fazemos uma rotação à direita no filho
`Y`, depois uma rotação à esquerda em `X`.

```text
Antes:

    X
     \
      Y
     /
    Z

Passo 1: rotação à direita em Y

    X
     \
      Z
       \
        Y

Passo 2: rotação à esquerda em X

      Z
     / \
    X   Y
```

## Pontos de Atenção

- Uma árvore binária comum não garante busca rápida.
- Remover em uma árvore binária comum é uma operação mais aberta do que em uma
  árvore de busca, porque não existe uma regra única de ordenação para preservar.
  A remoção pode substituir o nodo pelo último em largura, promover um filho,
  remover uma subárvore inteira ou seguir outra convenção definida pelo problema.
- Uma árvore de busca pode ficar parecida com uma lista se os dados forem
  inseridos em ordem crescente ou decrescente.
- A AVL evita esse problema fazendo rotações após inserções e remoções.
- A árvore N-ária é melhor para hierarquias gerais, enquanto árvores de busca
  são melhores para dados comparáveis.
