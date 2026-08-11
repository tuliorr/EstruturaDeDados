# Questionário Prático - Filas

Este questionário reúne exercícios de revisão sobre filas. As questões foram
pensadas para avaliação prática sem auxílio de IDE: cada uma possui uma ideia
principal clara, métodos pequenos e exemplos determinísticos no `main`.

As soluções reutilizam `FilaEstatica` ou `FilaDinamica`, apresentadas na Unidade
2. Algumas questões combinam filas com pilhas. O foco é reconhecer situações em
que o primeiro elemento inserido deve ser o primeiro removido, seguindo a regra
FIFO.

## Padrão para as soluções

Cada questão possui um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- método principal que resolve a questão;
- métodos auxiliares, quando necessários;
- validações simples para entradas inválidas;
- `main` com exemplos fixos e impressão do resultado esperado.

Os exemplos não usam `Scanner`, portanto podem ser executados diretamente.
Para manter o foco no algoritmo, eles partem de entradas válidas e não nulas:
tempos positivos, `k` entre zero e o tamanho da fila, janelas que cabem no vetor,
números não negativos e matrizes retangulares. O tratamento de entradas
incorretas pode ser acrescentado depois como exercício de melhoria.

## Níveis

- **Básico**: aplicação direta das operações de uma fila.
- **Intermediário**: combinação de fila com outra estrutura ou simulação.
- **Desafio**: algoritmo com vários baldes ou busca em largura.

## Questão 01 - Gerar Números Binários

**Nível:** Básico  
**Assunto:** geração em largura
**Arquivo:** `Questao01GerarNumerosBinarios.java`

### Enunciado

Gere, em ordem, as representações binárias dos números de `1` até uma quantidade
informada.

### Dica

Comece a fila com `"1"`. Para cada texto retirado, guarde-o na resposta e
enfileire duas continuações: uma com `0` e outra com `1` no final.

### Objetivo de aprendizagem

Perceber como uma fila gera resultados por níveis, preservando a ordem numérica.

### Exemplo de entrada e saída

Entrada:

```text
quantidade = 5
```

Saída esperada:

```text
Números binários: [1, 10, 11, 100, 101]
```

## Questão 02 - Intercalar Metades

**Nível:** Básico  
**Assunto:** reorganização de uma fila
**Arquivo:** `Questao02IntercalarMetades.java`

### Enunciado

Reorganize uma fila com quantidade par de elementos, alternando um valor da
primeira metade e um valor da segunda metade.

### Dica

Transfira a primeira metade para uma fila auxiliar. Depois, devolva
alternadamente um elemento da fila auxiliar e um elemento da fila original.

### Objetivo de aprendizagem

Usar uma fila auxiliar para combinar duas partes sem perder a ordem de cada uma.

### Exemplo de entrada e saída

Entrada:

```text
fila = 1, 2, 3, 4, 5, 6
```

Saída esperada:

```text
Fila intercalada: Frente -> [1] - [4] - [2] - [5] - [3] - [6] <- Fim
```

## Questão 03 - Média Móvel

**Nível:** Intermediário  
**Assunto:** janela deslizante  
**Arquivo:** `Questao03MediaMovel.java`

### Enunciado

Calcule a média de cada janela completa de tamanho `k` em um vetor. Use uma fila
estática para guardar apenas os valores da janela atual.

### Dica

Mantenha também a soma da janela. Quando a fila estiver cheia, subtraia o valor
que sai antes de adicionar o próximo.

### Objetivo de aprendizagem

Representar uma janela deslizante com fila e evitar somas repetidas.

### Exemplo de entrada e saída

Entrada:

```text
valores = [10, 20, 30, 40, 50]
tamanho da janela = 3
```

Saída esperada:

```text
Médias de tamanho 3: [20.0, 30.0, 40.0]
```

## Questão 04 - Inverter os Primeiros K Elementos

**Nível:** Intermediário  
**Assunto:** fila e pilha  
**Arquivo:** `Questao04InverterPrimeirosK.java`

### Enunciado

Inverta apenas os primeiros `k` elementos de uma fila, mantendo a ordem dos
elementos restantes.

### Dica

Coloque os primeiros `k` valores em uma pilha e devolva-os à fila. Depois,
rotacione os elementos que não participaram da inversão.

### Objetivo de aprendizagem

Combinar a ordem FIFO da fila com a ordem LIFO da pilha.

### Exemplo de entrada e saída

Entrada:

```text
fila = 1, 2, 3, 4, 5
k = 3
```

Saída esperada:

```text
Fila depois de inverter os 3 primeiros: Frente -> [3] - [2] - [1] - [4] - [5] <- Fim
```

## Questão 05 - Escalonamento Round Robin

**Nível:** Intermediário  
**Assunto:** fila circular de processos  
**Arquivo:** `Questao05EscalonamentoRoundRobin.java`

### Enunciado

Simule um escalonador Round Robin. Cada processo recebe no máximo um `quantum`
de tempo. Se não terminar, volta ao fim da fila.

### Dica

Desenfileire o processo, reduza seu tempo restante e volte a enfileirá-lo apenas
quando ainda houver trabalho.

### Objetivo de aprendizagem

Modelar atendimento justo e repetido com uma fila.

### Exemplo de entrada e saída

Entrada:

```text
P1 = 5, P2 = 3, P3 = 1
quantum = 2
```

Saída esperada:

```text
P1(2) -> P2(2) -> P3(1) -> P1(2) -> P2(1) -> P1(1)
```

## Questão 06 - Fila com Duas Pilhas

**Nível:** Intermediário  
**Assunto:** implementação alternativa  
**Arquivo:** `Questao06FilaComDuasPilhas.java`

### Enunciado

Implemente as operações de uma fila usando duas pilhas. A transferência da pilha
de entrada para a pilha de saída deve ocorrer somente quando a segunda estiver
vazia.

### Dica

Novos elementos entram sempre na primeira pilha. Ao transferir todos para a
segunda, o elemento mais antigo passa a ocupar o topo.

### Objetivo de aprendizagem

Construir comportamento FIFO a partir de duas estruturas LIFO.

### Exemplo de entrada e saída

Entrada:

```text
enfileirar Ana, Bruno, Carla
desenfileirar
enfileirar Daniel
consultar frente
```

Saída esperada:

```text
Primeiro removido: Ana
Nova frente: Bruno
```

## Questão 07 - Ordenação Radix

**Nível:** Desafio  
**Assunto:** dez filas de algarismos  
**Arquivo:** `Questao07OrdenacaoRadix.java`

### Enunciado

Ordene inteiros não negativos pelo algoritmo Radix Sort decimal. Use dez filas,
uma para cada algarismo de `0` a `9`.

### Dica

Comece pelas unidades. Distribua os números conforme o algarismo atual e colete
as filas da posição zero até a nove. Repita para dezenas, centenas e assim por
diante.

### Objetivo de aprendizagem

Usar a estabilidade das filas em um algoritmo de ordenação sem comparações.

### Exemplo de entrada e saída

Entrada:

```text
[170, 45, 75, 90, 802, 24, 2, 66]
```

Saída esperada:

```text
Ordenados: [2, 24, 45, 66, 75, 90, 170, 802]
```

## Questão 08 - Menor Caminho no Labirinto

**Nível:** Desafio  
**Assunto:** busca em largura  
**Arquivo:** `Questao08MenorCaminhoLabirinto.java`

### Enunciado

Em uma matriz, `0` representa passagem livre e `1` representa parede. Encontre a
menor quantidade de passos entre duas posições, movendo apenas para cima, baixo,
esquerda ou direita. Retorne `-1` quando não houver caminho.

### Dica

Use busca em largura. Enfileire a origem, marque sua distância e visite apenas
vizinhos livres que ainda não receberam distância.

### Objetivo de aprendizagem

Aplicar uma fila para explorar posições em ordem crescente de distância.

### Exemplo de entrada e saída

Entrada:

```text
0 1 0 0
0 0 0 1
1 1 0 0
0 0 0 0

origem = (0, 0)
destino = (3, 3)
```

Saída esperada:

```text
Menor quantidade de passos: 6
```
