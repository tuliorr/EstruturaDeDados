# Questionário Prático - Árvores

Este questionário reúne exercícios de revisão sobre árvores. O foco não é
repetir exatamente os métodos das implementações principais, mas propor pequenas
variações e aplicações que obriguem o aluno a percorrer, consultar, modificar ou
interpretar uma árvore.

As questões foram pensadas para avaliação prática sem auxílio de IDE. Por isso,
a maior parte delas pede a implementação de um método pequeno, com uma ideia
principal clara e exemplos que possam ser desenhados no papel.

## Padrão Para as Soluções

Cada questão deverá ter um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- construção da árvore usada no teste;
- método principal que resolve a questão;
- métodos auxiliares privados, se forem necessários;
- `main` com exemplos fixos de teste;
- impressão da saída obtida para conferência.

## Níveis

- **Básico**: questões curtas, normalmente resolvidas com um percurso simples.
- **Intermediário**: questões que combinam percurso com condição, intervalo,
  comparação, alteração ou caminho.
- **Desafio**: questões boas para treino, mas mais longas para prova sem auxílio
  de IDE.

## Questão 01 - Contar Nós com Dois Filhos

**Nível:** Básico  
**Assunto:** árvore binária  
**Arquivo:** `Questao01ContarNosComDoisFilhos.java`

### Enunciado

Em uma árvore binária, cada nó pode ter filho esquerdo, filho direito, os dois ou
nenhum. Implemente um método que retorne quantos nós possuem exatamente dois
filhos.

### Dica

Em cada nó, verifique se `esquerda != null` e `direita != null`. Depois continue
o percurso nas duas subárvores e some os resultados.

### Objetivo de aprendizagem

Identificar casos locais de um nó e combinar essa verificação com percurso
recursivo.

### Exemplo de entrada e saída

Entrada:

```text
        10
       /  \
      5    20
     /
    3
```

Saída esperada:

```text
Nós com dois filhos: 1
```

## Questão 02 - Contar Nós com Apenas Um Filho

**Nível:** Básico  
**Assunto:** árvore binária  
**Arquivo:** `Questao02ContarNosComUmFilho.java`

### Enunciado

Implemente um método que conte quantos nós possuem exatamente um filho. O nó deve
ser contado quando possui apenas filho esquerdo ou apenas filho direito.

### Dica

Uma forma simples é testar as duas situações separadamente:
`esquerda != null && direita == null` ou `esquerda == null && direita != null`.

### Objetivo de aprendizagem

Praticar condições sobre a estrutura de cada nó e reforçar o significado de
filho ausente.

### Exemplo de entrada e saída

Entrada:

```text
        10
       /  \
      5    20
     /       \
    3         30
```

Saída esperada:

```text
Nós com apenas um filho: 2
```

## Questão 03 - Somar Valores Pares

**Nível:** Básico  
**Assunto:** árvore binária de inteiros  
**Arquivo:** `Questao03SomarPares.java`

### Enunciado

Considere uma árvore binária que armazena números inteiros. Implemente um método
que percorra a árvore e retorne a soma de todos os valores pares.

### Dica

Percorra todos os nós. Em cada nó, verifique se `valor % 2 == 0`. Se for par,
inclua o valor na soma; se for ímpar, some apenas os resultados das subárvores.

### Objetivo de aprendizagem

Combinar percurso completo de árvore com filtragem de valores.

### Exemplo de entrada e saída

Entrada:

```text
        8
       / \
      3   10
     /   /  \
    1   6    14
```

Saída esperada:

```text
Soma dos pares: 38
```

## Questão 04 - Contar Valores Maiores que X

**Nível:** Básico  
**Assunto:** árvore binária ou árvore binária de busca  
**Arquivo:** `Questao04ContarMaioresQueX.java`

### Enunciado

Implemente um método que receba um valor `x` e conte quantos elementos da árvore
são maiores que `x`.

Essa questão pode ser resolvida percorrendo toda a árvore. Se a estrutura usada
for uma BST, também e possível aproveitar a ordenação para evitar alguns ramos.

### Dica

Comece pela versão simples: visite todos os nós e conte aqueles cujo valor é
maior que `x`.

### Objetivo de aprendizagem

Usar um parametro externo em uma consulta recursiva e acumular uma contagem.

### Exemplo de entrada e saída

Entrada:

```text
valores na árvore = 8, 3, 10, 1, 6, 14
x = 6
```

Saída esperada:

```text
Quantidade de valores maiores que 6: 3
```

## Questão 05 - Imprimir Folhas

**Nível:** Básico  
**Assunto:** árvore binária  
**Arquivo:** `Questao05ImprimirFolhas.java`

### Enunciado

Implemente um método que imprima apenas os valores armazenados em nós folha. Um
nó folha é aquele que não possui filho esquerdo nem filho direito.

### Dica

Ao visitar um nó, teste se os dois filhos são `null`. Se forem, imprima o valor.
Caso contrário, continue o percurso nas subárvores.

### Objetivo de aprendizagem

Reconhecer folhas e diferenciar nós terminais de nós internos.

### Exemplo de entrada e saída

Entrada:

```text
        10
       /  \
      5    20
     /    /  \
    3    15   30
```

Saída esperada:

```text
Folhas: 3 15 30
```

## Questão 06 - Somar Intervalo na BST

**Nível:** Intermediário  
**Assunto:** árvore binária de busca  
**Arquivo:** `Questao06SomarIntervaloBST.java`

### Enunciado

Dada uma árvore binária de busca de inteiros é um intervalo `[min, max]`,
implemente um método que retorne a soma dos valores que estão dentro desse
intervalo.

### Dica

Use a propriedade da BST. Se o valor atual for menor que `min`, não é necessário
visitar a subárvore esquerda. Se for maior que `max`, não é necessário visitar a
subárvore direita.

### Objetivo de aprendizagem

Aplicar a ordenação da BST para reduzir o número de nós visitados.

### Exemplo de entrada e saída

Entrada:

```text
valores inseridos na BST = 20, 10, 30, 5, 15, 25, 40
intervalo = [12, 30]
```

Saída esperada:

```text
Soma no intervalo: 90
```

## Questão 07 - Imprimir em Ordem Decrescente

**Nível:** Intermediário  
**Assunto:** árvore binária de busca  
**Arquivo:** `Questao07OrdemDecrescente.java`

### Enunciado

Implemente um percurso que imprima os valores de uma BST em ordem decrescente.

### Dica

Na BST, o percurso em ordem crescente visita esquerda, raiz e direita. Para a
ordem decrescente, inverta a ordem: direita, raiz e esquerda.

### Objetivo de aprendizagem

Entender como a ordem do percurso muda a ordem dos valores impressos.

### Exemplo de entrada e saída

Entrada:

```text
valores inseridos na BST = 20, 10, 30, 5, 15, 25, 40
```

Saída esperada:

```text
Ordem decrescente: 40 30 25 20 15 10 5
```

## Questão 08 - Caminho até um Valor

**Nível:** Intermediário  
**Assunto:** árvore binária de busca  
**Arquivo:** `Questao08CaminhoAteValor.java`

### Enunciado

Em uma BST, implemente um método que imprima o caminho percorrido da raiz até um
valor buscado. O caminho deve mostrar todos os nós visitados durante a busca.

Se o valor não existir, imprima o caminho percorrido até a busca falhar e informe
que o valor não foi encontrado.

### Dica

Em cada nó visitado, imprima ou armazene o valor atual. Depois compare o valor
buscado com o valor do nó para decidir se a busca continua pela esquerda ou pela
direita.

### Objetivo de aprendizagem

Acompanhar as decisões tomadas em uma busca de BST.

### Exemplo de entrada e saída

Entrada:

```text
valores inseridos na BST = 40, 20, 60, 10, 30, 25
valor buscado = 25
```

Saída esperada:

```text
Caminho: 40 20 30 25
Encontrado: true
```

## Questão 09 - Inverter os Filhos de Cada No

**Nível:** Intermediário  
**Assunto:** árvore binária  
**Arquivo:** `Questao09InverterFilhos.java`

### Enunciado

Implemente um método que inverta os filhos de todos os nós de uma árvore
binária. Em cada nó, quem era filho esquerdo deve virar filho direito, e quem era
filho direito deve virar filho esquerdo.

Ao final, a árvore resultante será o espelho da árvore original.

### Dica

Em cada nó, use uma variável auxiliar para trocar as referências `esquerda` e
`direita`. Depois aplique o mesmo processo recursivamente nos filhos.

### Objetivo de aprendizagem

Praticar alteração estrutural em árvore usando recursão e referências.

### Exemplo de entrada e saída

Entrada:

```text
Antes:

        10
       /  \
      5    20
     /    /  \
    3    15   30
```

Saída esperada:

```text
Depois:

        10
       /  \
      20   5
     /  \   \
    30  15   3
```

## Questão 10 - Comparar Formato de Duas Árvores

**Nível:** Intermediário  
**Assunto:** árvore binária  
**Arquivo:** `Questao10CompararFormato.java`

### Enunciado

Implemente um método que receba duas árvores binárias e retorne se elas possuem
o mesmo formato, ignorando os valores armazenados.

Duas árvores possuem o mesmo formato quando os nós aparecem nas mesmas posições.

### Dica

Compare os dois nós atuais ao mesmo tempo. Se ambos forem `null`, o formato
daquele ramo e igual. Se apenas um for `null`, o formato e diferente. Caso os
dois existam, compare recursivamente esquerda com esquerda e direita com
direita.

### Objetivo de aprendizagem

Comparar duas estruturas recursivas sem depender dos valores armazenados.

### Exemplo de entrada e saída

Entrada:

```text
Árvore A:
    10
   /  \
  5    20

Árvore B:
    8
   / \
  2   9
```

Saída esperada:

```text
Mesmo formato: true
```

## Questão 11 - Contar Subcategorias em Árvore N-ária

**Nível:** Intermediário  
**Assunto:** árvore N-ária  
**Arquivo:** `Questao11ContarSubcategorias.java`

### Enunciado

Use uma árvore N-ária para representar categorias e subcategorias de uma loja.
Implemente um método que receba o nome de uma categoria e retorne quantas
subcategorias diretas ela possui.

### Dica

Primeiro encontre o nó da categoria buscada. Depois conte apenas seus filhos
diretos, sem contar netos ou descendentes mais profundos.

### Objetivo de aprendizagem

Aplicar árvore N-ária em uma hierarquia simples e diferenciar filhos diretos de
descendentes.

### Exemplo de entrada e saída

Entrada:

```text
Loja
+-- Informatica
|   +-- Notebooks
|   +-- Monitores
+-- Livros
+-- Jogos

categoria = Informatica
```

Saída esperada:

```text
Subcategorias diretas de Informatica: 2
```

## Questão 12 - Listar Pontuações em Ranking

**Nível:** Intermediário  
**Assunto:** aplicação com BST  
**Arquivo:** `Questao12RankingPontuacoes.java`

### Enunciado

Uma competição registra pontuações inteiras dos participantes. Insira essas
pontuações em uma BST e implemente um método que imprima o ranking da maior
pontuacao para a menor.

### Dica

Use o percurso decrescente da BST: visite primeiro a subárvore direita, depois a
raiz, depois a subárvore esquerda.

### Objetivo de aprendizagem

Usar uma BST como estrutura para manter dados ordenados e gerar uma listagem em
ordem inversa.

### Exemplo de entrada e saída

Entrada:

```text
pontuações = 80, 95, 70, 100, 85
```

Saída esperada:

```text
Ranking: 100 95 85 80 70
```

## Questão 13 - Verificar Balanceamento Simples

**Nível:** Desafio  
**Assunto:** balanceamento  
**Arquivo:** `Questao13BalanceamentoSimples.java`

### Enunciado

Implemente um método que verifique se uma árvore binária está balanceada de forma
simples. Para cada nó, a diferença entre a altura da subárvore esquerda e a
altura da subárvore direita deve ser no máximo `1`.

### Dica

Crie um método para calcular altura. Para cada nó, calcule a altura esquerda e a
altura direita, compare a diferença e depois verifique recursivamente se as
subárvores também estão balanceadas.

### Objetivo de aprendizagem

Relacionar altura, recursão e critério de balanceamento.

### Exemplo de entrada e saída

Entrada:

```text
        10
       /  \
      5    20
     /
    3
```

Saída esperada:

```text
Balanceada: true
```

## Questão 14 - Identificar Rotação AVL

**Nível:** Desafio  
**Assunto:** AVL  
**Arquivo:** `Questao14IdentificarRotacaoAVL.java`

### Enunciado

Dada uma sequência curta de inserção em uma AVL, identifique qual rotação deve
ser aplicada no primeiro desbalanceamento:

- rotação simples a direita;
- rotação simples a esquerda;
- rotação dupla esquerda-direita;
- rotação dupla direita-esquerda.

Não é necessário implementar a AVL inteira. O foco é reconhecer o caso de
desbalanceamento.

### Dica

Observe a direção seguida a partir do primeiro nó desbalanceado. Os casos são:
esquerda-esquerda, direita-direita, esquerda-direita e direita-esquerda.

### Objetivo de aprendizagem

Praticar o raciocínio das rotações AVL sem exigir a implementação completa da
estrutura.

### Exemplo de entrada e saída

Entrada:

```text
sequência = 30, 10, 20
```

Saída esperada:

```text
Caso: esquerda-direita
Rotação: dupla esquerda-direita
```

## Questões Mais Indicadas Para Prova

Para avaliação sem auxílio de IDE, as questões mais adequadas são:

1. contar nós com dois filhos;
2. contar nós com apenas um filho;
3. somar valores pares;
4. contar valores maiores que `x`;
5. imprimir folhas;
6. somar intervalo na BST;
7. imprimir em ordem decrescente;
8. caminho até um valor;
9. inverter os filhos de cada nó.

