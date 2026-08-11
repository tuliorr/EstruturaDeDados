# Questionário Prático - Pilhas

Este questionário reúne exercícios de revisão sobre pilhas. As questões foram
pensadas para avaliação prática sem auxílio de IDE: cada uma possui uma ideia
principal clara, métodos pequenos e exemplos determinísticos no `main`.

As soluções reutilizam `PilhaEstatica` ou `PilhaDinamica`, apresentadas na
Unidade 2. O foco é reconhecer situações em que o último elemento inserido deve
ser o primeiro removido, seguindo a regra LIFO.

## Padrão para as soluções

Cada questão possui um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- método principal que resolve a questão;
- métodos auxiliares, quando necessários;
- validações simples para entradas inválidas;
- `main` com exemplos fixos e impressão do resultado esperado.

Os exemplos não usam `Scanner`, portanto podem ser executados diretamente.
Para manter o foco no algoritmo, eles partem de entradas válidas e não nulas.
Validações de formatos incompletos podem ser acrescentadas depois como exercício
de melhoria.

## Níveis

- **Básico**: aplicação direta das operações de uma pilha.
- **Intermediário**: combinação de pilhas com validação ou simulação.
- **Desafio**: uso de duas pilhas ou de uma pilha monotônica.

## Questão 01 - Validar Delimitadores

**Nível:** Básico  
**Assunto:** balanceamento de delimitadores
**Arquivo:** `Questao01ValidarDelimitadores.java`

### Enunciado

Verifique se os delimitadores `()`, `[]` e `{}` de uma expressão estão
balanceados. Cada fechamento deve corresponder à última abertura que ainda não
foi fechada.

### Dica

Empilhe os delimitadores de abertura. Ao encontrar um fechamento, compare-o com
a abertura retirada do topo. Ao final, a pilha também deve estar vazia.

### Objetivo de aprendizagem

Usar a regra LIFO para conferir a ordem de fechamento de delimitadores aninhados.

### Exemplo de entrada e saída

Entrada:

```text
{[()]}
```

Saída esperada:

```text
Delimitadores válidos: true
```

## Questão 02 - Desfazer Operações

**Nível:** Básico  
**Assunto:** histórico de alterações  
**Arquivo:** `Questao02DesfazerOperacoes.java`

### Enunciado

Processe uma sequência de números inteiros e comandos `desfazer`. Cada número é
somado ao total. O comando remove o efeito do último número ainda registrado.

### Dica

Além de atualizar o total, empilhe cada número. Para desfazer, desempilhe e
subtraia o valor removido.

### Objetivo de aprendizagem

Usar uma pilha para guardar um histórico simples e reversível.

### Exemplo de entrada e saída

Entrada:

```text
10, -3, 5, desfazer, 2
```

Saída esperada:

```text
Total após os comandos: 9
```

## Questão 03 - Remover Pares Adjacentes

**Nível:** Intermediário  
**Assunto:** comparação com o topo  
**Arquivo:** `Questao03RemoverParesAdjacentes.java`

### Enunciado

Remova repetidamente pares de caracteres iguais e adjacentes. Uma remoção pode
aproximar caracteres que formam um novo par.

### Dica

Compare o próximo caractere com o topo. Se forem iguais, desempilhe; caso
contrário, empilhe o novo caractere.

### Objetivo de aprendizagem

Perceber que o topo representa o último caractere que ainda não foi eliminado.

### Exemplo de entrada e saída

Entrada:

```text
abbaca
```

Saída esperada:

```text
Após remover os pares: ca
```

## Questão 04 - Verificar Sequência de Desempilhamento

**Nível:** Intermediário  
**Assunto:** simulação de operações  
**Arquivo:** `Questao04VerificarSequenciaDesempilhamento.java`

### Enunciado

Receba uma ordem de entrada e uma ordem de saída. Verifique se a saída pode ser
obtida empilhando os valores na ordem informada e desempilhando quando desejado.

### Dica

Empilhe cada valor da entrada. Enquanto o topo for o próximo valor desejado na
saída, desempilhe e avance nessa sequência.

### Objetivo de aprendizagem

Simular uma sequência de operações sem precisar enumerar todas as possibilidades.

### Exemplo de entrada e saída

Entrada:

```text
entrada = 1, 2, 3, 4, 5
saída A = 4, 5, 3, 2, 1
saída B = 4, 3, 5, 1, 2
```

Saída esperada:

```text
Primeira sequência é possível: true
Segunda sequência é possível: false
```

## Questão 05 - Avaliar Expressão Pós-fixa

**Nível:** Intermediário  
**Assunto:** avaliação de expressão  
**Arquivo:** `Questao05AvaliarExpressaoPosfixa.java`

### Enunciado

Avalie uma expressão pós-fixa formada por inteiros e pelos operadores `+`, `-`,
`*` e `/`. Os tokens são separados por espaços.

### Dica

Empilhe números. Para cada operador, desempilhe primeiro o segundo operando e
depois o primeiro, calcule e empilhe o resultado.

### Objetivo de aprendizagem

Entender por que a notação pós-fixa dispensa regras de precedência e parênteses.

### Exemplo de entrada e saída

Entrada:

```text
5 2 + 3 * 4 -
```

Saída esperada:

```text
Resultado: 17
```

## Questão 06 - Histórico de Navegação

**Nível:** Intermediário  
**Assunto:** duas pilhas  
**Arquivo:** `Questao06HistoricoNavegacao.java`

### Enunciado

Implemente um histórico com as operações visitar, voltar e avançar. Uma nova
visita depois de voltar deve apagar as páginas que estavam disponíveis para
avançar.

### Dica

Use uma pilha para páginas anteriores e outra para páginas seguintes. Ao mover
em uma direção, coloque a página atual na pilha da direção oposta.

### Objetivo de aprendizagem

Combinar duas pilhas para navegar em duas direções.

### Exemplo de entrada e saída

Entrada:

```text
inicio -> aulas -> pilhas -> voltar -> avançar
```

Saída esperada:

```text
Depois de voltar: aulas
Depois de avançar: pilhas
```

## Questão 07 - Pilha com Mínimo

**Nível:** Desafio  
**Assunto:** consulta em O(1)  
**Arquivo:** `Questao07PilhaComMinimo.java`

### Enunciado

Implemente uma pilha de inteiros que, além das operações comuns, informe o menor
elemento atual em tempo constante. Valores mínimos repetidos devem funcionar.

### Dica

Use uma segunda pilha. Empilhe nela todo valor menor ou igual ao mínimo atual e
remova seu topo quando o mesmo valor sair da pilha principal.

### Objetivo de aprendizagem

Trocar um pouco de espaço adicional por uma consulta mais rápida.

### Exemplo de entrada e saída

Entrada:

```text
empilhar 5, 2, 2, 4
consultar mínimo
desempilhar 4 e 2
consultar mínimo
```

Saída esperada:

```text
Mínimo inicial: 2
Mínimo após remover 4 e um 2: 2
```

## Questão 08 - Próximo Elemento Maior

**Nível:** Desafio  
**Assunto:** pilha monotônica  
**Arquivo:** `Questao08ProximoElementoMaior.java`

### Enunciado

Para cada valor de um vetor, encontre o primeiro elemento maior localizado à sua
direita. Use `-1` quando esse elemento não existir.

### Dica

Guarde os índices que ainda esperam uma resposta. Enquanto o valor atual for
maior que o valor indicado pelo topo, desempilhe o índice e registre o valor
atual como resposta.

### Objetivo de aprendizagem

Usar uma pilha monotônica para evitar comparar cada valor com todos os valores à
sua direita.

### Exemplo de entrada e saída

Entrada:

```text
[4, 5, 2, 10, 8]
```

Saída esperada:

```text
Próximos maiores: [5, 10, 10, -1, -1]
```
