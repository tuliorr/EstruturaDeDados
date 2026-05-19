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

5. `ArvoreAVLComFrequencia.java`

   Variação da AVL que aceita valores repetidos. Em vez de criar vários nodos
   com o mesmo elemento, cada nodo guarda uma `frequencia`. Assim, a árvore
   continua tendo um único caminho de busca para cada valor distinto, mas também
   consegue representar quantas vezes aquele valor foi inserido.

6. `TesteComparativoArvores.java`

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

Essa altura não é o nível em que o nodo está na árvore principal. Para calcular
a altura de um nodo, tratamos esse nodo como a raiz da sua própria subárvore e
olhamos para baixo. A altura será baseada na maior subárvore abaixo dele, seja a
esquerda ou a direita.

Separar a altura esquerda e a altura direita em variáveis ajuda a visualizar o
raciocínio:

```text
alturaEsquerda = altura(filhoEsquerdo)
alturaDireita  = altura(filhoDireito)
alturaDoNodo   = 1 + maior(alturaEsquerda, alturaDireita)
```

Exemplo:

```text
        30
       /  \
     20    40
    /
  10
```

Nesse desenho, o nodo `20` é a raiz da subárvore formada por `20` e `10`, então
sua altura é `1`. O nodo `30` é a raiz da árvore inteira; sua maior subárvore é
a esquerda, então sua altura é `2`. Já os níveis seriam contados de cima para
baixo: `30` no nível `0`, `20` e `40` no nível `1`, e `10` no nível `2`.

## Rotações AVL

Rotações mudam a forma da árvore sem quebrar a regra da árvore de busca. O
objetivo é reduzir a altura do lado pesado.

Nos métodos de rotação simples, `p` representa o nodo desbalanceado e `u`
representa o filho que sobe. A subárvore intermediária é aquela que fica entre
`p` e `u` na ordem da árvore de busca, por isso precisa ser religada durante a
rotação. No diagrama da rotação à direita ela é `t3`; no diagrama da rotação à
esquerda ela é `t2`.

### Rotação Simples à Direita

Usada no caso esquerda-esquerda, quando o nodo `p` fica pesado à esquerda e o
filho esquerdo `u` também pende para a esquerda. Na rotação, `u` sobe, `p`
desce para a direita de `u`, e a subárvore `t3` continua entre os dois.

Neste desenho:

- `p` é o nodo desbalanceado;
- `u` é o filho esquerdo de `p`;
- `t4` é a subárvore direita de `p`;
- `v` é o filho esquerdo de `u`;
- `t3` é a subárvore direita de `u`;
- `t1` e `t2` são as subárvores esquerda e direita de `v`.

```text
rotacaoDireita(p)

Antes:

        p
       / \
      u   t4
     / \
    v   t3
   / \
 t1   t2

Ligações feitas no código:

u.direito = p
p.esquerdo = t3

Depois:

        u
       / \
      v   p
     / \ / \
   t1 t2 t3 t4
```

### Rotação Simples à Esquerda

Usada no caso direita-direita, quando o nodo `p` fica pesado à direita e o filho
direito `u` também pende para a direita. Na rotação, `u` sobe, `p` desce para a
esquerda de `u`, e a subárvore `t2` continua entre os dois.

Neste desenho:

- `p` é o nodo desbalanceado;
- `u` é o filho direito de `p`;
- `t1` é a subárvore esquerda de `p`;
- `v` é o filho direito de `u`;
- `t2` é a subárvore esquerda de `u`;
- `t3` e `t4` são as subárvores esquerda e direita de `v`.

```text
rotacaoEsquerda(p)

Antes:

        p
       / \
      t1  u
         / \
        t2  v
           / \
          t3 t4

Ligações feitas no código:

u.esquerdo = p
p.direito = t2

Depois:

        u
       / \
      p   v
     / \ / \
   t1 t2 t3 t4
```

### Rotação Dupla à Direita

Usada no caso esquerda-direita. Primeiro fazemos uma rotação à esquerda no filho
esquerdo de `p`, depois uma rotação à direita em `p`.

Na rotação dupla, o desenho usa `v` para representar o neto que está no meio e
que sobe no final. Dentro de cada chamada de rotação simples, os nomes locais do
código continuam sendo `p`, `u` e uma subárvore intermediária, mas eles se
referem ao nodo recebido naquela chamada específica. Por isso, a intermediária
pode receber nomes diferentes no desenho completo.

```text
Antes:

        p
       / \
      u   t4
     / \
    t1  v
       / \
      t2  t3

Passo 1: rotação à esquerda no filho esquerdo de p

        p
       / \
      v   t4
      / \
     u   t3
    / \
   t1  t2

Passo 2: rotação à direita em p

        v
       / \
      u   p
     / \ / \
   t1 t2 t3 t4
```

### Rotação Dupla à Esquerda

Usada no caso direita-esquerda. Primeiro fazemos uma rotação à direita no filho
direito de `p`, depois uma rotação à esquerda em `p`.

Aqui também usamos `v` apenas no desenho para representar o neto central. Nas
chamadas reais do código, cada rotação simples ainda recebe seu próprio `p` e
calcula seu próprio `u`.

```text
Antes:

      p
     / \
    t1  u
       / \
      v   t4
     / \
    t2 t3

Passo 1: rotação à direita no filho direito de p

      p
     / \
    t1  v
       / \
      t2  u
         / \
        t3 t4

Passo 2: rotação à esquerda em p

      v
     / \
    p   u
   / \ / \
  t1 t2 t3 t4
```

## Simulação de Inserção e Remoção em AVL

Esta simulação usa a mesma sequência do método `main` de `ArvoreAVL.java`:

```text
30, 20, 10, 25, 40, 50, 45
```

Depois, removemos o elemento `20`.

Um ponto importante: depois de uma inserção ou remoção, o método `balancear` não
percorre a árvore inteira. Ele verifica apenas os nodos que ficaram no caminho
da recursão: o caminho que foi percorrido da raiz até o local alterado. Na volta
da recursão, cada ancestral desse ponto recalcula sua altura, calcula o fator de
balanço e, se necessário, rotaciona.

```text
desce procurando o local da alteração
insere ou remove
volta pela mesma trilha
balanceia os nodos dessa trilha
```

Se uma alteração aconteceu na subárvore esquerda de `20`, por exemplo, nodos em
outra subárvore, como um possível `40` à direita da raiz, não precisam ser
verificados porque sua altura não mudou.

### Inserindo 30

A árvore começa vazia. A chamada pública é:

```text
inserir(30)
```

Antes de inserir de fato, o código verifica se o valor já existe:

```text
contem(30)
buscar(30) -> null
```

Como a árvore está vazia, a chamada recursiva recebe `null`:

```text
inserir(null, 30) -> novo Nodo(30)
```

Árvore resultante:

```text
30(h=0)
```

### Inserindo 20

O valor `20` é menor que `30`, então ele entra à esquerda.

Pilha de chamadas:

```text
topo
inserir(null, 20)
inserir(30, 20)
inserir(20)
base
```

Retornos:

```text
inserir(null, 20) -> Nodo(20)
balancear(30)     -> Nodo(30)
inserir(20)       -> true
```

Árvore resultante:

```text
    30(h=1, fb=1)
   /
20(h=0, fb=0)
```

O fator de balanço de `30` é:

```text
altura(esquerda) - altura(direita)
0 - (-1) = 1
```

Como `1` ainda está dentro do intervalo permitido pela AVL, não há rotação.

### Inserindo 10

O valor `10` é menor que `30` e menor que `20`, então entra à esquerda de `20`.

Pilha de chamadas no ponto mais fundo:

```text
topo
inserir(null, 10)
inserir(20, 10)
inserir(30, 10)
inserir(10)
base
```

Antes do balanceamento final, a árvore fica assim:

```text
      30
     /
   20
  /
10
```

Os retornos começam de baixo para cima:

```text
inserir(null, 10) -> Nodo(10)
balancear(20)     -> Nodo(20)
balancear(30)     -> rotacaoDireita(30)
```

No nodo `30`, o fator de balanço fica:

```text
altura(esquerda) - altura(direita)
1 - (-1) = 2
```

O fator `2` indica excesso de altura à esquerda. Como o filho esquerdo `20`
também pende para a esquerda, é o caso esquerda-esquerda. A correção é uma
rotação simples à direita:

```text
rotacaoDireita(30)
  p = 30
  u = 20
  t3 = null
```

Depois da rotação:

```text
    20(h=1)
   /  \
 10    30
```

### Inserindo 25, 40, 50 e 45

Depois de inserir `25`, a árvore fica:

```text
    20
   /  \
 10    30
       /
     25
```

Ao inserir `40`, o caminho é `20 -> 30 -> null`. Na volta da recursão, o código
chama `balancear(30)` e depois `balancear(20)`. O nodo `20` fica pesado para a
direita, então a AVL faz uma rotação à esquerda e a árvore passa a ter `30` como
raiz:

```text
        30
       /  \
     20    40
    / \
  10  25
```

Depois de inserir `50`, não há rotação nova. A árvore fica:

```text
        30
       /  \
     20    40
    / \      \
  10  25      50
```

Agora inserimos `45`. O caminho percorrido e os retornos ficam assim:

```text
inserir(45)
inserir(30, 45)
  45 > 30
  inserir(40, 45)
    45 > 40
    inserir(50, 45)
      45 < 50
      inserir(null, 45) -> novo Nodo(45)

      balancear(50) -> retorna 50

    balancear(40)
      fatorBalanco(40) = -2
      pesado para direita

      fatorBalanco(50) = 1
      caso direita-esquerda

      nodo.direito = rotacaoDireita(50)
      retorna rotacaoEsquerda(40)

  balancear(30) -> retorna 30

raiz = 30
```

Observe quais nodos foram verificados pelo balanceamento após inserir `45`:

```text
50
40
30
```

Eles são exatamente os nodos do caminho de volta. Os nodos `20`, `10` e `25`
não são verificados nessa inserção, porque estão em outra parte da árvore e suas
alturas não mudaram.

Antes da correção local, a subárvore da direita fica assim:

```text
40
  \
   50
  /
45
```

Esse é o caso direita-esquerda. Primeiro corrigimos o filho direito:

```text
rotacaoDireita(50)
```

Resultado intermediário:

```text
40
  \
   45
     \
      50
```

Depois corrigimos o nodo desbalanceado:

```text
rotacaoEsquerda(40)
```

Resultado local:

```text
   45
  /  \
40    50
```

Árvore após todas as inserções:

```text
        30(h=2)
       /     \
   20(h=1)   45(h=1)
   /   \      /   \
10     25   40     50
```

### Removendo 20

Antes da remoção:

```text
        30
       /  \
     20    45
    / \    / \
  10  25  40 50
```

Nesta remoção, o caminho principal é `30 -> 20`. Como o nodo `20` tem dois
filhos, a implementação também entra na subárvore direita dele para remover o
sucessor. Portanto, o balanceamento verifica apenas os nodos afetados por esse
caminho, e não a árvore inteira.

Chamada pública:

```text
remover(20)
```

Antes de remover, o código confirma que o elemento existe:

```text
contem(20)
buscar(20) -> 20
```

Pilha de chamadas:

```text
topo
remover(20, 20)
remover(30, 20)
remover(20)
base
```

O primeiro passo é comparar `20` com a raiz `30`:

```text
remover(30, 20)
  20 < 30
  nodo.esquerdo = remover(20, 20)
```

Agora o nodo foi encontrado:

```text
remover(20, 20)
  comparacao == 0
```

O nodo `20` tem dois filhos:

```text
   20
  /  \
10    25
```

Nesse caso, a implementação usa o sucessor, ou seja, o menor nodo da subárvore
direita:

```text
sucessor = menorNodo(25)
sucessor = 25
```

O elemento do sucessor substitui o elemento do nodo removido:

```text
nodo.elemento = 25
```

Depois, o sucessor antigo precisa sair da subárvore direita:

```text
nodo.direito = removerMenor(25)
removerMenor(25) -> null
```

A subárvore esquerda da raiz passa a ser:

```text
   25
  /
10
```

Retornos:

```text
removerMenor(25) -> null
balancear(25)    -> Nodo(25)
balancear(30)    -> Nodo(30)
remover(20)      -> true
```

Árvore final:

```text
        30(h=2, fb=0)
       /     \
   25(h=1)   45(h=1)
   /         /   \
10          40    50
```

Observe que a remoção também chama `balancear` no caminho de volta da recursão.
Neste exemplo, nenhuma rotação foi necessária depois de remover `20`, porque os
fatores de balanço permaneceram dentro do intervalo `-1`, `0` e `1`.

## AVL com Frequência

A classe `ArvoreAVL.java` não aceita valores repetidos: se o valor já existe,
`inserir` retorna `false`. Já a classe `ArvoreAVLComFrequencia.java` representa
duplicatas com um contador dentro do próprio nodo.

Em vez de criar isto:

```text
20
  \
   20
     \
      20
```

ela guarda isto:

```text
20(f=3)
```

Ou seja, o valor `20` aparece uma vez na estrutura da árvore, mas sua frequência
indica que existem três ocorrências dele.

### Inserção com Frequência

Ao inserir um valor novo, a classe cria um nodo normalmente e rebalanceia o
caminho de volta da recursão:

```text
inserir(25)
  valor ainda não existe
  cria novo nodo 25(f=1)
  atualiza alturas
  balanceia os ancestrais afetados
```

Ao inserir um valor repetido, a estrutura da árvore não muda:

```text
inserir(20)
  valor já existe
  20(f=1) vira 20(f=2)
```

Como nenhum filho foi criado ou removido, a altura da árvore não muda por causa
da duplicata. O que muda é apenas o campo `frequencia`.

### Remoção com Frequência

Ao remover um valor com frequência maior que 1, a classe apenas decrementa a
frequência:

```text
remover(20)
  20(f=3) vira 20(f=2)
```

Nesse caso, o nodo continua na árvore.

O nodo só é removido fisicamente quando a última ocorrência sai:

```text
remover(20)
  20(f=1)
  remove o nodo da árvore
  rebalanceia o caminho de volta
```

Se esse nodo tiver dois filhos, a remoção usa o sucessor, como na AVL comum. A
diferença é que o sucessor é copiado junto com sua frequência, e o nodo original
do sucessor é removido por completo da antiga posição.

### Contadores

`ArvoreAVLComFrequencia.java` mantém dois contadores:

```text
tamanho()  -> total de ocorrências, contando repetidos
distintos() -> quantidade de valores diferentes
```

Exemplo:

```text
inserções: 30, 20, 30, 30, 10

tamanho()  = 5
distintos() = 3
frequencia(30) = 3
frequencia(20) = 1
frequencia(10) = 1
```

As rotações continuam funcionando do mesmo jeito, porque a frequência não muda a
forma da árvore. Rotacionar muda ligações entre nodos; a frequência fica
armazenada junto com o elemento de cada nodo.

## Pontos de Atenção

- Uma árvore binária comum não garante busca rápida.
- Remover em uma árvore binária comum é uma operação mais aberta do que em uma
  árvore de busca, porque não existe uma regra única de ordenação para preservar.
  A remoção pode substituir o nodo pelo último em largura, promover um filho,
  remover uma subárvore inteira ou seguir outra convenção definida pelo problema.
- Uma árvore de busca pode ficar parecida com uma lista se os dados forem
  inseridos em ordem crescente ou decrescente.
- A AVL evita esse problema fazendo rotações após inserções e remoções.
- A AVL com frequência é útil quando valores repetidos importam, mas você não
  quer criar vários nodos com a mesma chave.
- A árvore N-ária é melhor para hierarquias gerais, enquanto árvores de busca
  são melhores para dados comparáveis.
