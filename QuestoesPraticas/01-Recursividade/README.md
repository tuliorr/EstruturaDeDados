# Questionário Prático - Recursividade e Parâmetros

Este questionário reúne exercícios curtos sobre chamadas recursivas e passagem
de parâmetros em Java. As seis primeiras questões apresentam novos problemas de
recursividade. As duas últimas comparam parâmetros primitivos e referências para
objetos.

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

## Questão 06 - Calcular Fibonacci

**Nível:** Básico  
**Assunto:** recursividade com duas chamadas  
**Arquivo:** `Questao06CalcularFibonacci.java`

### Enunciado

Implemente `long fibonacci(int n)`, considerando `fibonacci(0) = 0`,
`fibonacci(1) = 1` e, para as demais posições, a soma dos dois termos anteriores.
Use valores pequenos, pois esta versão didática repete cálculos.

### Dica

Existem dois casos base. Fora deles, faça chamadas para `n - 1` e `n - 2`.

### Objetivo de aprendizagem

Observar uma recursão que se divide em dois ramos e reconhecer a repetição de
cálculos.

### Exemplo de entrada e saída

```text
n = 7
Fibonacci de 7: 13
```

## Questão 07 - Atualizar Valor com Retorno

**Nível:** Básico  
**Assunto:** passagem de parâmetro primitivo  
**Arquivo:** `Questao07AtualizarValorComRetorno.java`

### Enunciado

Implemente `double aplicarAcrescimo(double valor, double percentual)`. Demonstre
que a variável original não é modificada pela chamada e que o resultado precisa
ser atribuído a uma variável.

### Dica

Parâmetros primitivos recebem uma cópia do valor. Retorne o novo valor em vez de
esperar que o argumento original seja alterado.

### Objetivo de aprendizagem

Diferenciar a variável usada na chamada da cópia recebida pelo método.

### Exemplo de entrada e saída

```text
valor = 100.0
percentual = 10.0
Valor original: 100.0
Valor atualizado: 110.0
```

## Questão 08 - Aplicar Desconto em Produto

**Nível:** Básico  
**Assunto:** referência de objeto como parâmetro  
**Arquivo:** `Questao08AplicarDescontoProduto.java`

### Enunciado

Crie um objeto `Produto` com um preço e implemente
`void aplicarDesconto(Produto produto, double percentual)`. Use duas variáveis
apontando para o mesmo produto e mostre que ambas observam o preço alterado.

### Dica

O método recebe uma cópia da referência. Essa cópia ainda aponta para o mesmo
objeto, portanto seus atributos podem ser modificados.

### Objetivo de aprendizagem

Entender que Java passa tanto primitivos quanto referências por valor, mas que
uma referência copiada continua permitindo alterar o objeto compartilhado.

### Exemplo de entrada e saída

```text
preço inicial = 200.0
desconto = 10.0
Preço pela primeira referência: 180.0
Preço pela segunda referência: 180.0
```

## Questões Mais Indicadas para Prova

Para avaliação sem auxílio de IDE, as questões mais diretas são:

1. contar vogais;
2. somar dígitos;
3. verificar número primo;
4. verificar palíndromo;
5. converter decimal para binário;
7. atualizar valor com retorno.
