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

## Política Para `null`

As árvores desta unidade não armazenam elementos `null`. Essa regra evita dois
problemas didáticos comuns: retorno ambíguo em buscas, já que `null` também
representa "não encontrado", e erro em árvores ordenadas, que dependem de
`compareTo`.

Nos métodos que retornam `boolean`, receber `null` faz a operação falhar com
`false`. Nos métodos de consulta que retornam um elemento, buscar `null` retorna
`null`. Em consultas numéricas, como `quantidadeFilhos`, `null` recebe o mesmo
tratamento de elemento inexistente.

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

Neste material, o fator de balanço é calculado como:

```text
fatorBalanco(nodo) = altura(filhoDireito) - altura(filhoEsquerdo)
```

Com essa convenção, fator positivo indica peso à direita, fator negativo indica
peso à esquerda, e uma AVL aceita apenas os valores `-1`, `0` e `1`.

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

Usaremos a convenção do código:

```text
fb = altura(direita) - altura(esquerda)
```

Assim:

```text
fb > 1   -> peso excessivo à direita
fb < -1  -> peso excessivo à esquerda
```

### Inserindo 30

A árvore começa vazia. A chamada pública é:

```text
inserir(30)
```

Antes de inserir de fato, o código verifica se o valor é válido e se já existe:

```text
30 != null
contem(30)
buscar(30) -> null
```

Como a árvore está vazia, a chamada recursiva recebe `null`:

```text
inserir(null, 30) -> novo Nodo(30)
```

Árvore resultante:

```text
30(h=0, fb=0)
```

### Inserindo 20

O valor `20` é menor que `30`, então ele entra à esquerda.

```text
inserir(20)
inserir(30, 20)
  20 < 30
  inserir(null, 20) -> novo Nodo(20)
```

Retorno da recursão:

```text
balancear(30)
  altura(esquerda)=0
  altura(direita)=-1
  fb(30) = -1 - 0 = -1
  retorna 30
```

O fator `-1` ainda está dentro do intervalo permitido. Não há rotação.

```text
    30(h=1, fb=-1)
   /
20(h=0, fb=0)
```

### Inserindo 10

O valor `10` é menor que `30` e menor que `20`, então entra à esquerda de `20`.

```text
inserir(10)
inserir(30, 10)
  10 < 30
  inserir(20, 10)
    10 < 20
    inserir(null, 10) -> novo Nodo(10)
```

Antes do balanceamento final:

```text
      30
     /
   20
  /
10
```

Retorno da recursão:

```text
balancear(20)
  altura(esquerda)=0, altura(direita)=-1
  fb(20) = -1
  retorna 20

balancear(30)
  altura(esquerda)=1, altura(direita)=-1
  fb(30) = -2
  peso excessivo à esquerda
  filho esquerdo 20 tem fb=-1
  caso esquerda-esquerda
  retorna rotacaoDireita(30)
```

Rotação feita:

```text
rotacaoDireita(30)
  p = 30
  u = 20
  t3 = null
```

Depois da rotação:

```text
    20(h=1, fb=0)
   /  \
 10    30
```

### Inserindo 25

O caminho é `20 -> 30 -> null`, porque `25` é maior que `20` e menor que `30`.

```text
inserir(25)
inserir(20, 25)
  25 > 20
  inserir(30, 25)
    25 < 30
    inserir(null, 25) -> novo Nodo(25)
```

Retorno:

```text
balancear(30)
  altura(esquerda)=0, altura(direita)=-1
  fb(30) = -1
  retorna 30

balancear(20)
  altura(esquerda)=0, altura(direita)=1
  fb(20) = 1
  retorna 20
```

Não há rotação.

```text
    20
   /  \
 10    30
       /
     25
```

### Inserindo 40

O caminho é `20 -> 30 -> null`, porque `40` é maior que `20` e maior que `30`.

```text
inserir(40)
inserir(20, 40)
  40 > 20
  inserir(30, 40)
    40 > 30
    inserir(null, 40) -> novo Nodo(40)
```

Retorno:

```text
balancear(30)
  altura(esquerda)=0, altura(direita)=0
  fb(30) = 0
  retorna 30

balancear(20)
  altura(esquerda)=0, altura(direita)=1
  fb(20) = 1
  retorna 20
```

Também não há rotação.

```text
    20
   /  \
 10    30
       / \
     25   40
```

### Inserindo 50

O caminho é `20 -> 30 -> 40 -> null`.

```text
inserir(50)
inserir(20, 50)
  50 > 20
  inserir(30, 50)
    50 > 30
    inserir(40, 50)
      50 > 40
      inserir(null, 50) -> novo Nodo(50)
```

Antes do último balanceamento:

```text
    20
   /  \
 10    30
       / \
     25   40
            \
             50
```

Retorno:

```text
balancear(40)
  altura(esquerda)=-1, altura(direita)=0
  fb(40) = 1
  retorna 40

balancear(30)
  altura(esquerda)=0, altura(direita)=1
  fb(30) = 1
  retorna 30

balancear(20)
  altura(esquerda)=0, altura(direita)=2
  fb(20) = 2
  peso excessivo à direita
  filho direito 30 tem fb=1
  caso direita-direita
  retorna rotacaoEsquerda(20)
```

Rotação feita:

```text
rotacaoEsquerda(20)
  p = 20
  u = 30
  t2 = 25
```

Depois da rotação:

```text
        30
       /  \
     20    40
    / \      \
  10  25      50
```

### Inserindo 45

O caminho é `30 -> 40 -> 50 -> null`.

```text
inserir(45)
inserir(30, 45)
  45 > 30
  inserir(40, 45)
    45 > 40
    inserir(50, 45)
      45 < 50
      inserir(null, 45) -> novo Nodo(45)
```

Antes da correção local:

```text
40
  \
   50
  /
45
```

Retorno:

```text
balancear(50)
  altura(esquerda)=0, altura(direita)=-1
  fb(50) = -1
  retorna 50

balancear(40)
  altura(esquerda)=-1, altura(direita)=1
  fb(40) = 2
  peso excessivo à direita

  filho direito 50 tem fb=-1
  caso direita-esquerda

  nodo.direito = rotacaoDireita(50)
  retorna rotacaoEsquerda(40)

balancear(30)
  altura(esquerda)=1, altura(direita)=1
  fb(30) = 0
  retorna 30
```

Primeiro corrigimos o filho direito:

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
        30(h=2, fb=0)
       /              \
   20(h=1, fb=0)      45(h=1, fb=0)
   /   \              /   \
10     25           40     50
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

A chamada pública começa validando a entrada:

```text
20 != null
contem(20)
buscar(20) -> 20
```

Nesta remoção, o caminho principal é `30 -> 20`. Como o nodo `20` tem dois
filhos, a implementação também entra na subárvore direita dele para remover o
sucessor.

```text
remover(30, 20)
  20 < 30
  nodo.esquerdo = remover(20, 20)

remover(20, 20)
  comparacao == 0
  nodo encontrado
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

Retorno:

```text
balancear(25)
  altura(esquerda)=0, altura(direita)=-1
  fb(25) = -1
  retorna 25

balancear(30)
  altura(esquerda)=1, altura(direita)=1
  fb(30) = 0
  retorna 30

remover(20) -> true
```

Árvore final:

```text
        30(h=2, fb=0)
       /              \
   25(h=1, fb=-1)     45(h=1, fb=0)
   /                  /   \
10                  40     50
```

Observe que a remoção também chama `balancear` no caminho de volta da recursão.
Neste exemplo, nenhuma rotação foi necessária depois de remover `20`, porque os
fatores de balanço permaneceram dentro do intervalo `-1`, `0` e `1`.

### O Que Aparece nos Prints

Como `ArvoreAVL.java` imprime as rotações dentro de `balancear`, essa sequência
mostra no console os momentos em que a estrutura realmente girou:

```text
Balanceando 30: rotacao simples a direita
Balanceando 20: rotacao simples a esquerda
Balanceando 40: rotacao dupla direita-esquerda
```

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
