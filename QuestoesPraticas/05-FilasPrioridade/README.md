# Questionário Prático - Filas de Prioridade

Este questionário reúne operações e aplicações clássicas de filas de prioridade
não ordenadas, ordenadas, com heap e com heap estável.

## Padrão para as soluções

Cada questão possui um arquivo Java sem `package`, com:

- comentário inicial com o enunciado resumido;
- método ou classe principal que resolve a questão;
- uso das estruturas da `Unidade2/P04FilasPrioridade` quando apropriado;
- `main` determinístico, sem `Scanner`;
- impressão da saída e do resultado esperado.

Para manter o foco nos algoritmos, as soluções assumem entradas válidas. Como
exercício adicional, valide referências nulas e valores de `k` fora do intervalo.

## Níveis

- **Básico**: aplica diretamente uma prioridade ou a propriedade do heap.
- **Intermediário**: combina a estrutura com tipos comparáveis ou seleção parcial.
- **Desafio**: adapta comparações ou combina o heap com outro algoritmo.

## Questão 01 - Encontrar o Maior Elemento

**Nível:** Básico  
**Assunto:** fila de prioridade não ordenada  
**Arquivo:** `Questao01EncontrarMaiorElemento.java`

### Enunciado

Dado um vetor de inteiros, encontre o maior elemento usando uma fila de
prioridade não ordenada.

### Dica

Insira todos os valores na fila e remova uma única vez.

### Objetivo de aprendizagem

Observar que a fila não ordenada procura o maior elemento no momento da remoção.

### Exemplo de entrada e saída

```text
valores = [4, 1, 7, 3]
Maior elemento: 7
```

## Questão 02 - Ordenar com Fila de Prioridade

**Nível:** Básico  
**Assunto:** fila de prioridade ordenada  
**Arquivo:** `Questao02OrdenarComFilaPrioridade.java`

### Enunciado

Dado um vetor de inteiros, use uma fila de prioridade ordenada para devolver os
valores em ordem decrescente.

### Dica

Insira todos os valores e remova até a fila ficar vazia.

### Objetivo de aprendizagem

Usar remoções sucessivas para percorrer os elementos por prioridade.

### Exemplo de entrada e saída

```text
valores = [4, 1, 5, 2, 3]
Ordem decrescente: [5, 4, 3, 2, 1]
```

## Questão 03 - Verificar um max-heap

**Nível:** Básico  
**Assunto:** representação de um heap em vetor  
**Arquivo:** `Questao03VerificarMaxHeap.java`

### Enunciado

Implemente um método que informe se um vetor satisfaz a propriedade de max-heap:
todo pai deve ser maior ou igual a cada filho existente.

### Dica

Para o índice `i`, os filhos ficam em `2 * i + 1` e `2 * i + 2`. As folhas não
precisam ser verificadas.

### Objetivo de aprendizagem

Relacionar a árvore conceitual aos índices usados pela implementação em vetor.

### Exemplo de entrada e saída

```text
[20, 15, 18, 7, 9, 10] -> true
[20, 22, 18] -> false
```

## Questão 04 - Selecionar as k maiores prioridades

**Nível:** Intermediário  
**Assunto:** remoções em max-heap  
**Arquivo:** `Questao04SelecionarMaioresPrioridades.java`

### Enunciado

Dado um vetor e um valor `k`, devolva apenas os `k` maiores elementos em ordem
decrescente usando uma fila de prioridade com heap.

### Dica

Insira todos os valores e remova a raiz exatamente `k` vezes.

### Objetivo de aprendizagem

Usar seleção parcial sem implementar uma ordenação completa manualmente.

### Exemplo de entrada e saída

```text
valores = [8, 3, 10, 1, 7], k = 3
[10, 8, 7]
```

## Questão 05 - Triagem Estável de Pacientes

**Nível:** Intermediário  
**Assunto:** estabilidade em heap  
**Arquivo:** `Questao05TriagemEstavel.java`

### Enunciado

Pacientes com maior urgência devem ser atendidos primeiro. Quando dois pacientes
têm a mesma urgência, aquele que chegou primeiro deve ser atendido antes.

### Dica

O `compareTo` de `Paciente` compara somente a urgência. A estrutura estável cuida
da ordem de chegada.

### Objetivo de aprendizagem

Distinguir prioridade de estabilidade e tornar um empate observável por nomes.

### Exemplo de entrada e saída

```text
Ana=4, Bruno=5, Carla=4, Diego=5
[Bruno, Diego, Ana, Carla]
```

## Questão 06 - Selecionar os k pontos mais próximos

**Nível:** Intermediário  
**Assunto:** heap máximo limitado  
**Arquivo:** `Questao06PontosMaisProximos.java`

### Enunciado

Selecione os `k` pontos mais próximos da origem. Mantenha no heap somente os
melhores candidatos vistos até o momento.

### Dica

Compare pela distância ao quadrado, calculada em `long`. A raiz guarda o mais
distante entre os pontos atualmente selecionados.

### Objetivo de aprendizagem

Usar um heap de tamanho limitado para evitar guardar candidatos desnecessários.

### Exemplo de entrada e saída

```text
pontos = (1,1), (4,0), (2,2), (0,3), (1,2), k = 3
[(1,1), (1,2), (2,2)]
```

## Questão 07 - Mesclar k sequências ordenadas

**Nível:** Desafio  
**Assunto:** adaptação de prioridade  
**Arquivo:** `Questao07MesclarSequenciasOrdenadas.java`

### Enunciado

Receba várias sequências crescentes e produza uma única sequência crescente,
mantendo no heap apenas o próximo candidato de cada sequência.

### Dica

Como a implementação disponível é um max-heap, inverta a comparação do item para
que o menor valor seja considerado o mais prioritário.

### Objetivo de aprendizagem

Adaptar a comparação sem modificar a implementação da estrutura.

### Exemplo de entrada e saída

```text
[[1, 4, 7], [2, 5], [3, 6, 8]]
[1, 2, 3, 4, 5, 6, 7, 8]
```

## Questão 08 - Heap Sort

**Nível:** Desafio  
**Assunto:** ordenação com heap
**Arquivo:** `Questao08HeapSort.java`

### Enunciado

Ordene um vetor em ordem crescente usando uma fila de prioridade com max-heap.

### Dica

Como o heap remove primeiro o maior valor, preencha o vetor de resultado de trás
para frente.

### Objetivo de aprendizagem

Aplicar a propriedade do max-heap em um algoritmo clássico de ordenação.

### Exemplo de entrada e saída

```text
valores = [4, 1, 5, 2, 3]
Ordem crescente: [1, 2, 3, 4, 5]
```
