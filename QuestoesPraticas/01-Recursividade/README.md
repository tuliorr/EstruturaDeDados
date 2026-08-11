# Questionário Prático - Recursividade

Este questionário reúne oito exercícios curtos sobre chamadas recursivas em
Java. Cada questão apresenta um problema diferente e destaca como o caso base e
a chamada recursiva trabalham juntos.

Os exercícios não repetem os exemplos de somatório, sequência crescente,
divisão inteira, potência de dois, soma ou inversão de vetor, MDC e Torres de
Hanoi apresentados na Unidade 1.

As soluções priorizam a ideia central de cada algoritmo. Por isso, os exemplos
usam entradas válidas e pequenas. Validações adicionais podem ser propostas
depois como extensão.

## Padrão das Soluções

Cada questão possui um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- método principal que resolve a questão;
- métodos auxiliares privados, quando necessários;
- `main` com exemplos fixos de teste;
- impressão da saída obtida e do resultado esperado.

## Níveis

- **Básico**: uma regra recursiva curta ou uma demonstração direta de parâmetro.
- **Intermediário**: exige mais de uma condição ou um parâmetro auxiliar.

## Questão 01 - Contar Vogais

**Nível:** Básico  
**Assunto:** recursividade em texto  
**Arquivo:** `Questao01ContarVogais.java`

### Enunciado

Implemente o método `int contarVogais(String texto)` para contar, de forma
recursiva, quantas vogais existem em um texto. Considere as letras `a`, `e`,
`i`, `o` e `u`, maiúsculas ou minúsculas.

### Dica

Use um índice para indicar o caractere atual. Some `1` quando ele for uma vogal
e `0` caso contrário. A chamada seguinte deve avançar o índice.

### Objetivo de aprendizagem

Percorrer um texto recursivamente e combinar o resultado atual com o restante
do texto.

### Exemplo de entrada e saída

```text
texto = estrutura
Vogais em "estrutura": 4
```

## Questão 02 - Somar Dígitos

**Nível:** Básico  
**Assunto:** recursividade com divisão inteira  
**Arquivo:** `Questao02SomarDigitos.java`

### Enunciado

Implemente `int somarDigitos(int numero)` para retornar a soma dos algarismos de
um número não negativo.

### Dica

O último dígito é obtido com `% 10`. A divisão por `10` remove esse dígito e
produz o argumento da próxima chamada.

### Objetivo de aprendizagem

Decompor um número em uma parte local e um problema menor.

### Exemplo de entrada e saída

```text
numero = 5072
Soma dos dígitos de 5072: 14
```

## Questão 03 - Verificar Número Primo

**Nível:** Intermediário  
**Assunto:** recursividade com método auxiliar  
**Arquivo:** `Questao03VerificarPrimo.java`

### Enunciado

Implemente `boolean ehPrimo(int numero)`. Um número primo é maior ou igual a
dois e não possui divisor além de `1` e dele mesmo.

### Dica

Crie um método auxiliar que receba o divisor atual. Se a divisão for exata, o
número não é primo. Se `divisor * divisor` ultrapassar o número, nenhum divisor
foi encontrado.

### Objetivo de aprendizagem

Usar um parâmetro auxiliar para representar o progresso de uma busca recursiva.

### Exemplo de entrada e saída

```text
numero = 29
29 é primo: true
```

## Questão 04 - Verificar Palíndromo

**Nível:** Intermediário  
**Assunto:** recursividade com dois índices  
**Arquivo:** `Questao04VerificarPalindromo.java`

### Enunciado

Implemente `boolean ehPalindromo(String texto)` para verificar se um texto é
igual quando lido da esquerda para a direita e da direita para a esquerda.
Considere os caracteres exatamente como foram informados.

### Dica

Compare as duas extremidades. Se forem iguais, aproxime os índices e repita. O
caso base ocorre quando os índices se encontram ou se cruzam.

### Objetivo de aprendizagem

Percorrer uma sequência pelas duas extremidades usando parâmetros recursivos.

### Exemplo de entrada e saída

```text
texto = arara
arara é palíndromo: true
```

## Questão 05 - Converter Decimal para Binário

**Nível:** Básico  
**Assunto:** recursividade e representação numérica  
**Arquivo:** `Questao05DecimalParaBinario.java`

### Enunciado

Implemente `String decimalParaBinario(int numero)` para converter um inteiro não
negativo em texto binário. O valor zero deve produzir `"0"`.

### Dica

Converta primeiro `numero / 2` e acrescente o resto `numero % 2` ao final. A
ordem das chamadas faz os bits mais significativos aparecerem primeiro.

### Objetivo de aprendizagem

Perceber como o retorno da chamada recursiva influencia a ordem do resultado.

### Exemplo de entrada e saída

```text
numero = 13
13 em binário: 1101
```

## Questão 06 - Busca Binária Recursiva

**Nível:** Intermediário
**Assunto:** busca binária em vetor crescente
**Arquivo:** `Questao06BuscaBinariaRecursiva.java`

### Enunciado

Implemente `int buscaBinaria(int[] vetor, int valor)` para buscar um valor em um
vetor crescente. Retorne o índice encontrado ou `-1` quando o valor não existir.

### Dica

Compare o valor com o elemento do meio. Depois continue somente na metade em que
o valor pode estar. A busca termina quando o início ultrapassa o fim.

### Objetivo de aprendizagem

Representar um intervalo com dois índices e reduzir esse intervalo pela metade a
cada chamada.

### Exemplo de entrada e saída

```text
vetor = [2, 4, 6, 8, 10]
valor = 8
Índice do valor 8: 3
```

## Questão 07 - Inverter String

**Nível:** Básico  
**Assunto:** recursividade em texto
**Arquivo:** `Questao07InverterString.java`

### Enunciado

Implemente `String inverter(String texto)` para devolver os caracteres de um
texto na ordem inversa.

### Dica

Inverta primeiro o texto sem o primeiro caractere. Depois acrescente esse
primeiro caractere ao final. Um texto com zero ou um caractere já está invertido.

### Objetivo de aprendizagem

Perceber como a ordem das concatenações pode inverter o resultado das chamadas
recursivas.

### Exemplo de entrada e saída

```text
texto = dados
Texto invertido: sodad
```

## Questão 08 - Encontrar Maior Recursivamente

**Nível:** Básico  
**Assunto:** recursividade em vetor
**Arquivo:** `Questao08EncontrarMaiorRecursivo.java`

### Enunciado

Implemente `int maior(int[] vetor)` para encontrar recursivamente o maior valor
de um vetor não vazio.

### Dica

Encontre o maior valor do restante do vetor e compare-o com o valor da posição
atual. A última posição fornece o caso base.

### Objetivo de aprendizagem

Combinar o resultado de uma chamada recursiva com o elemento da posição atual.

### Exemplo de entrada e saída

```text
vetor = [7, 2, 9, 4]
Maior valor: 9
```
