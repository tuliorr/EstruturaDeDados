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

   Árvore em que cada nodo pode ter qualquer quantidade de filhos. É útil para
   representar hierarquias gerais, como uma disciplina com várias unidades e
   cada unidade com vários tópicos. A implementação usa a técnica "primeiro
   filho / próximo irmão", evitando uma lista pronta dentro do nodo.

2. `ArvoreBinaria.java`

   Árvore em que cada nodo tem no máximo dois filhos. Ela não possui regra de
   ordenação: quem usa a estrutura escolhe onde cada elemento será inserido.

3. `ArvoreBinariaBusca.java`

   Árvore binária com regra de busca: valores menores ficam à esquerda e valores
   maiores ficam à direita. Essa regra permite descartar uma subárvore inteira a
   cada passo da busca.

4. `ArvoreAVL.java`

   Árvore binária de busca balanceada. Depois de inserir ou remover, a AVL
   recalcula alturas e usa rotações para manter o fator de balanço entre -1 e 1.

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
- Uma árvore de busca pode ficar parecida com uma lista se os dados forem
  inseridos em ordem crescente ou decrescente.
- A AVL evita esse problema fazendo rotações após inserções e remoções.
- A árvore N-ária é melhor para hierarquias gerais, enquanto árvores de busca
  são melhores para dados comparáveis.
