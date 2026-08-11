# Questionário Prático - Listas

Este questionário reúne exercícios de revisão sobre listas sequenciais,
simplesmente encadeadas, duplamente encadeadas e circulares. As questões partem
de percursos simples e avançam para alterações de referências e aplicações que
exploram as características de cada representação.

Quando a API pública da Unidade 2 é suficiente, a solução reutiliza a estrutura
correspondente. Nos exercícios cujo objetivo é manipular diretamente os nodos, o
arquivo inclui apenas a estrutura mínima necessária para tornar os encadeamentos
visíveis ao aluno.

As soluções priorizam a ideia central de cada algoritmo. Os exemplos usam
entradas válidas e deixam validações adicionais como extensão.

## Padrão das Soluções

Cada questão possui um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- construção da lista usada no teste;
- método principal que resolve a questão;
- métodos ou classes auxiliares, quando necessários;
- `main` com exemplos fixos, sem uso de `Scanner`;
- impressão da saída obtida e do resultado esperado.

## Níveis

- **Básico**: percurso ou alteração curta usando a API pública de uma lista.
- **Intermediário**: combina listas, ponteiros ou preservação de ordem.
- **Desafio**: exige controlar cuidadosamente referências ou uma estrutura
  circular.

## Questão 01 - Contar Ocorrências

**Nível:** Básico  
**Assunto:** lista sequencial  
**Arquivo:** `Questao01ContarOcorrencias.java`

### Enunciado

Implemente um método que receba uma `ListaVetor` e um valor inteiro e retorne
quantas vezes esse valor aparece na lista, sem modificá-la.

### Dica

Percorra as posições ocupadas usando `tamanho()` e consulte cada elemento com
`obtem(i)`.

### Objetivo de aprendizagem

Percorrer uma lista sequencial usando apenas sua interface pública.

### Exemplo de entrada e saída

```text
lista = [3, 5, 3, 7, 3]
valor = 3
Ocorrências de 3: 3
```

## Questão 02 - Remover Duplicados

**Nível:** Intermediário  
**Assunto:** lista sequencial dinâmica  
**Arquivo:** `Questao02RemoverDuplicados.java`

### Enunciado

Altere uma `ListaVetorGenericaDinamica<Integer>` para remover ocorrências
repetidas, preservando apenas a primeira aparição de cada valor e a ordem dessas
primeiras aparições.

### Dica

Para cada posição `i`, examine as posições posteriores. Quando remover a posição
`j`, não incremente `j`, pois o próximo elemento será deslocado para ela.

### Objetivo de aprendizagem

Controlar índices enquanto remoções alteram o tamanho e deslocam elementos.

### Exemplo de entrada e saída

```text
Antes: [4, 2, 4, 3, 2, 4]
Depois: [4, 2, 3]
```

## Questão 03 - Intercalar Listas Ordenadas

**Nível:** Intermediário  
**Assunto:** listas sequenciais ordenadas  
**Arquivo:** `Questao03IntercalarListasOrdenadas.java`

### Enunciado

Receba duas `ListaVetor` em ordem crescente e crie uma terceira lista com todos
os elementos também em ordem crescente. As listas de entrada não devem ser
alteradas.

### Dica

Mantenha um índice para cada entrada. Copie o menor elemento atual e avance
somente o índice da lista escolhida. Ao final, copie os elementos restantes.

### Objetivo de aprendizagem

Combinar dois percursos ordenados em tempo linear.

### Exemplo de entrada e saída

```text
primeira = [1, 4, 7]
segunda = [2, 3, 8, 9]
Intercalada: [1, 2, 3, 4, 7, 8, 9]
```

## Questão 04 - Particionar por Pivô

**Nível:** Intermediário  
**Assunto:** lista simplesmente encadeada  
**Arquivo:** `Questao04ParticionarPorPivo.java`

### Enunciado

Consuma uma `ListaSimplesmenteEncadeada` e produza outra lista na qual todos os
valores menores que um pivô apareçam antes dos valores maiores ou iguais. A
ordem relativa dentro de cada grupo deve ser preservada.

### Dica

Distribua os valores removidos da entrada em duas listas auxiliares. Depois
transfira primeiro os menores e, em seguida, os maiores ou iguais para o
resultado.

### Objetivo de aprendizagem

Construir uma partição estável e perceber quando uma operação consome a entrada.

### Exemplo de entrada e saída

```text
lista = 5 -> 1 -> 8 -> 3 -> 5 -> 2 -> null
pivô = 5
Lista particionada: 1 -> 3 -> 2 -> 5 -> 8 -> 5 -> null
Lista original após o consumo: null
```

## Questão 05 - Inverter Encadeamento

**Nível:** Intermediário  
**Assunto:** referências em lista simplesmente encadeada  
**Arquivo:** `Questao05InverterEncadeamento.java`

### Enunciado

Inverta uma lista simplesmente encadeada alterando apenas as referências entre
os nodos. Não crie uma segunda lista nem novos nodos.

### Dica

Mantenha referências para o nodo anterior, o atual e o próximo. Salve o próximo
antes de alterar `atual.proximo`.

### Objetivo de aprendizagem

Modificar um encadeamento sem perder o trecho da lista que ainda não foi
percorrido.

### Exemplo de entrada e saída

```text
Antes: 10 -> 20 -> 30 -> 40 -> null
Depois: 40 -> 30 -> 20 -> 10 -> null
```

## Questão 06 - Encontrar Elemento Central

**Nível:** Intermediário  
**Assunto:** ponteiros lento e rápido  
**Arquivo:** `Questao06EncontrarElementoCentral.java`

### Enunciado

Encontre o elemento central de uma lista simplesmente encadeada em uma única
passagem. Quando a quantidade de elementos for par, retorne o segundo dos dois
elementos centrais. Considere que a lista possui pelo menos um elemento.

### Dica

Avance um ponteiro uma posição por vez e outro duas posições por vez. Quando o
segundo chegar ao fim, o primeiro estará no centro.

### Objetivo de aprendizagem

Usar velocidades diferentes de percurso sem conhecer previamente o tamanho da
lista.

### Exemplo de entrada e saída

```text
lista = 10 -> 20 -> 30 -> 40 -> null
Elemento central: 30
```

## Questão 07 - Palíndromo em Lista Dupla

**Nível:** Intermediário  
**Assunto:** lista duplamente encadeada  
**Arquivo:** `Questao07PalindromoListaDupla.java`

### Enunciado

Verifique se os valores de uma lista duplamente encadeada formam um palíndromo.
Compare simultaneamente a lista a partir do início e do fim.

### Dica

Faça no máximo `tamanho / 2` comparações. Depois de cada comparação, avance a
referência esquerda e recue a referência direita.

### Objetivo de aprendizagem

Aproveitar os dois sentidos de navegação oferecidos pelo encadeamento duplo.

### Exemplo de entrada e saída

```text
lista = 1 <-> 2 <-> 3 <-> 2 <-> 1
A lista é palíndromo: true
```

## Questão 08 - Problema de Josephus

**Nível:** Desafio  
**Assunto:** lista circular  
**Arquivo:** `Questao08ProblemaJosephus.java`

### Enunciado

No Problema de Josephus, participantes ficam organizados em uma roda. A partir
do primeiro, conte repetidamente um passo positivo e elimine quem ocupar a
posição contada. Continue a partir do participante seguinte até restar apenas o
sobrevivente. Considere que existe pelo menos um participante.

### Dica

Ligue o último nodo ao primeiro. Mantenha referências para o participante atual
e para o anterior, pois o anterior deve ser ligado ao sucessor quando o atual
for eliminado.

### Objetivo de aprendizagem

Representar o Problema de Josephus com uma lista circular e remover nodos sem
interromper o ciclo.

### Exemplo de entrada e saída

```text
participantes = Ana, Beto, Carlos, Dora, Eva
passo = 3
Eliminado: Carlos
Eliminado: Ana
Eliminado: Eva
Eliminado: Beto
Sobrevivente: Dora
```
