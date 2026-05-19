# Questionário Prático - Hash

Este questionário reúne exercícios de revisão sobre tabelas hash. As questões
foram pensadas para avaliação prática sem auxílio de IDE: cada uma deve ser
resolvida com um método pequeno, uma ideia principal clara e exemplos
determinísticos no `main`.

As questões não exigem implementar uma tabela hash completa do zero, exceto
quando isso for explicitamente indicado. O foco é usar hashing para resolver
problemas de busca, frequência, colisão e associação entre chave e valor.

## Padrão Para as Soluções

Cada questão deverá ter um arquivo Java próprio, com:

- comentário inicial com o enunciado resumido;
- método principal que resolve a questão;
- métodos auxiliares, se forem necessários;
- `main` com exemplos fixos de teste;
- impressão da saída obtida para conferência.

Os exemplos devem evitar `Scanner`, para que o arquivo possa ser executado
diretamente.

## Níveis

- **Básico**: questões curtas, normalmente resolvidas com uma única ideia.
- **Intermediário**: questões que combinam contagem, busca, colisão ou remoção.
- **Desafio**: questões boas para estudo, mas um pouco mais longas para prova sem
  auxílio de IDE.

## Questão 01 - Contar Colisões

**Nível:** Básico  
**Assunto:** hash com encadeamento  
**Arquivo:** `Questao01ContarColisoes.java`

### Enunciado

Uma tabela hash com encadeamento separado guarda, em cada posição, uma lista de
elementos que caíram naquele índice. Quando uma posição possui mais de um
elemento, significa que houve colisão naquela posição.

Implemente um método que percorra a tabela e retorne quantas posições possuem
mais de um elemento.

### Dica

Percorra todos os baldes da tabela. Para cada balde, conte quantos elementos ele
possui. Se a quantidade for maior que `1`, incremente o contador de colisões.

### Objetivo de aprendizagem

Entender que colisão não é um erro da tabela hash, mas uma situação esperada
quando chaves diferentes geram o mesmo índice.

### Exemplo de entrada e saída

Entrada:

```text
capacidade = 10
valores inseridos = 7, 17, 27, 3, 13
```

Saída esperada:

```text
Quantidade de posições com colisão: 2
```

## Questão 02 - Maior Balde

**Nível:** Básico  
**Assunto:** hash com encadeamento  
**Arquivo:** `Questao02MaiorBalde.java`

### Enunciado

Em uma tabela hash com encadeamento, algumas posições podem ficar vazias, outras
podem ter apenas um elemento e outras podem acumular vários elementos por causa
das colisões.

Implemente um método que retorne o tamanho do maior balde da tabela.

### Dica

Use uma variável para guardar o maior tamanho encontrado até o momento. Ao
percorrer cada balde, calcule sua quantidade de elementos e atualize o maior
valor quando necessário.

### Objetivo de aprendizagem

Analisar se os elementos estão bem distribuídos na tabela ou concentrados em
poucas posições.

### Exemplo de entrada e saída

Entrada:

```text
capacidade = 10
balde 2 = 12, 22, 32
balde 5 = 5
balde 8 = 18, 28
```

Saída esperada:

```text
Tamanho do maior balde: 3
```

## Questão 03 - Elementos de um Balde

**Nível:** Básico  
**Assunto:** hash com encadeamento  
**Arquivo:** `Questao03ElementosDoBalde.java`

### Enunciado

Considere uma tabela hash com encadeamento. Implemente um método que receba um
índice da tabela e imprima todos os elementos armazenados naquele balde.

Se o índice for inválido, o método deve informar que o índice não existe. Se o
balde estiver vazio, deve informar que não há elementos naquela posição.

### Dica

Antes de acessar o vetor da tabela, verifique se o índice está entre `0` e
`capacidade - 1`. Depois percorra a lista daquele balde.

### Objetivo de aprendizagem

Relacionar o índice calculado pela função hash com a lista de elementos que
ficou armazenada naquela posição.

### Exemplo de entrada e saída

Entrada:

```text
índice consultado = 7
balde 7 = 27, 17, 7
```

Saída esperada:

```text
Elementos do balde 7: 27 17 7
```

## Questão 04 - Contar Valores Repetidos

**Nível:** Intermediário  
**Assunto:** frequência com hash  
**Arquivo:** `Questao04ContarRepetidos.java`

### Enunciado

Dado um vetor de inteiros, implemente um método que conte quantos valores
diferentes aparecem mais de uma vez.

O resultado deve considerar o valor, não a quantidade total de repetições. Se o
numero `7` aparece três vezes, ele conta apenas como um valor repetido.

### Dica

Use uma estrutura de hash para guardar a frequência de cada numero. Depois
percorra as frequências e conte quantas são maiores que `1`.

### Objetivo de aprendizagem

Usar hash para transformar uma busca repetida em uma contagem eficiente por
chave.

### Exemplo de entrada e saída

Entrada:

```text
valores = 4, 7, 4, 2, 7, 9, 7
```

Saída esperada:

```text
Quantidade de valores repetidos: 2
```

## Questão 05 - Primeiro Caractere Não Repetido

**Nível:** Intermediário  
**Assunto:** frequência com hash  
**Arquivo:** `Questao05PrimeiroCaractereUnico.java`

### Enunciado

Dada uma palavra, implemente um método que retorne o primeiro caractere que
aparece apenas uma vez.

Se todos os caracteres forem repetidos, o método deve indicar que não existe
caractere único.

### Dica

Faça duas passagens pela palavra. Na primeira, conte a frequência de cada
caractere. Na segunda, percorra a palavra na ordem original e retorne o primeiro
caractere com frequência igual a `1`.

### Objetivo de aprendizagem

Combinar contagem por hash com preservação da ordem original dos dados.

### Exemplo de entrada e saída

Entrada:

```text
palavra = abacaxi
```

Saída esperada:

```text
Primeiro caractere único: b
```

## Questão 06 - Comparar Conjuntos

**Nível:** Intermediário  
**Assunto:** busca com hash  
**Arquivo:** `Questao06CompararConjuntos.java`

### Enunciado

Dados dois vetores de inteiros, implemente um método que verifique se eles
possuem os mesmos valores, ignorando a ordem e ignorando repetições.

Dois vetores devem ser considerados equivalentes quando todo valor que aparece
em um também aparece no outro.

### Dica

Insira os valores do primeiro vetor em uma estrutura de hash. Depois verifique se
todos os valores do segundo vetor aparecem nela. Repita a verificação no sentido
contrário para garantir que o primeiro vetor não possui valores ausentes no
segundo.

### Objetivo de aprendizagem

Usar hash como representacao simples de conjunto e práticar busca rapida por
pertencimento.

### Exemplo de entrada e saída

Entrada:

```text
vetorA = 3, 1, 2, 2
vetorB = 2, 3, 1
```

Saída esperada:

```text
Possuem os mesmos valores: true
```

## Questão 07 - Remover Valores Pares

**Nível:** Intermediário  
**Assunto:** remoção em hash  
**Arquivo:** `Questao07RemoverPares.java`

### Enunciado

Considere uma tabela hash de inteiros. Implemente um método que remova todos os
valores pares da tabela, mantendo os valores ímpares.

Ao final, a tabela deve continuar válida para novas buscas.

### Dica

Percorra cada balde da tabela. Ao remover elementos de uma lista encadeada,
cuidado para não perder a referência para o próximo nodo.

### Objetivo de aprendizagem

Praticar remoção em estruturas com colisão, observando que percorrer e remover
ao mesmo tempo exige cuidado com as referências.

### Exemplo de entrada e saída

Entrada:

```text
valores inseridos = 4, 7, 10, 13, 22, 31
```

Saída esperada:

```text
Tabela após remover pares: 31 13 7
```

Observação: a ordem de impressão depende da forma como a tabela percorre os
baldes. O ponto principal é que apenas os valores ímpares devem permanecer.

## Questão 08 - Cadastro por Matrícula

**Nível:** Intermediário  
**Assunto:** aplicação com chave e valor  
**Arquivo:** `Questao08CadastroMatricula.java`

### Enunciado

Crie uma pequena aplicação de cadastro de alunos usando hash. A chave deve ser a
matrícula do aluno e o valor deve ser o nome.

A estrutura deve permitir inserir aluno, buscar aluno pela matrícula e remover
aluno pela matrícula.

### Dica

Crie uma classe simples para representar a entrada da tabela, por exemplo
`Aluno`, com `matrícula` e `nome`. A função hash deve usar a matrícula para
calcular o índice.

### Objetivo de aprendizagem

Aplicar hash em um problema realista de acesso rapido por identificador.

### Exemplo de entrada e saída

Entrada:

```text
inserir: 2024001, Ana
inserir: 2024002, Bruno
buscar: 2024002
remover: 2024001
```

Saída esperada:

```text
Aluno encontrado: Bruno
Aluno 2024001 removido: true
```

## Questão 09 - Classificar Fator de Carga

**Nível:** Básico  
**Assunto:** fator de carga  
**Arquivo:** `Questao09ClassificarFatorCarga.java`

### Enunciado

Implemente um método que receba a quantidade de elementos e a capacidade de uma
tabela hash. O método deve calcular o fator de carga e retornar uma classificação
textual:

- `"leve"` quando o fator de carga for menor que `0.5`;
- `"moderada"` quando estiver entre `0.5` e `0.75`;
- `"cheia"` quando for maior que `0.75`.

### Dica

O fator de carga é calculado por `nElementos / capacidade`. Use conversão para
`double` antes da divisão para não perder as casas decimais.

### Objetivo de aprendizagem

Interpretar o fator de carga como medida simples de ocupação da tabela.

### Exemplo de entrada e saída

Entrada:

```text
nElementos = 6
capacidade = 10
```

Saída esperada:

```text
Fator de carga: 0.6
Classificação: moderada
```

## Questão 10 - Rehash Simplificado

**Nível:** Desafio  
**Assunto:** redimensionamento  
**Arquivo:** `Questao10RehashSimplificado.java`

### Enunciado

Em uma tabela hash, quando o fator de carga fica muito alto, a quantidade de
colisões tende a aumentar. Implemente um rehash simplificado: quando o fator de
carga passar de `0.75`, crie uma nova tabela com o dobro da capacidade e reinsira
todos os elementos.

### Dica

Não copie os baldes diretamente para os mesmos índices. Ao mudar a capacidade, o
índice gerado por `hashCode % capacidade` também muda. Por isso, cada elemento
deve ser inserido novamente na nova tabela.

### Objetivo de aprendizagem

Entender por que o redimensionamento de uma tabela hash exige recalcular os
índices das chaves.

### Exemplo de entrada e saída

Entrada:

```text
capacidade inicial = 4
valores inseridos = 1, 2, 3, 4
```

Saída esperada:

```text
Capacidade antes do rehash: 4
Capacidade depois do rehash: 8
Todos os valores continuam buscaveis: true
```

## Questões Mais Indicadas Para Prova

Para avaliação sem auxílio de IDE, as questões mais adequadas são:

1. contar colisões;
2. maior balde;
3. elementos de um balde;
4. contar valores repetidos;
5. primeiro caractere não repetido;
6. comparar conjuntos;
7. classificar fator de carga.


