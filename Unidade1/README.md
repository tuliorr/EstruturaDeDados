# Unidade 1 - Fundamentos de Programação em Java

Esta unidade reúne exemplos de base usados antes das estruturas de dados
propriamente ditas. O objetivo é reforçar dois temas que aparecem o tempo todo
nas unidades seguintes:

- recursividade, que será usada principalmente em árvores;
- passagem de parâmetros e referências, essencial para entender listas, pilhas,
  filas, tabelas hash e nós encadeados.

## Arquivos

1. `Recursividade.java`

   Contém exemplos de somatório, sequência crescente, divisão inteira,
   potência de dois, soma de vetor, inversão de vetor, máximo divisor comum e
   Torre de Hanoi.

   A intenção é comparar problemas diferentes que seguem o mesmo desenho:
   definir um caso base e reduzir o problema até chegar nele.

2. `PassagemParametros.java`

   Mostra a diferença entre copiar valores primitivos, copiar referências para
   objetos e usar esses valores como parâmetros em chamadas de métodos.

   O ponto mais importante é que Java sempre passa parâmetros por valor. Para
   objetos, o valor copiado é uma referência. Por isso um método consegue alterar
   o estado do objeto recebido, mas não consegue trocar a variável original que
   está fora dele.

## Ideias Importantes

- Uma variável primitiva guarda diretamente um valor, como `int`, `double`,
  `char` ou `boolean`.
- Uma variável de objeto guarda uma referência para um objeto criado na memória.
- Ao atribuir uma variável primitiva a outra, o valor é copiado.
- Ao atribuir uma variável de objeto a outra, a referência é copiada; as duas
  variáveis podem apontar para o mesmo objeto.
- Em chamadas de métodos, Java copia o valor do argumento para o parâmetro.
- Alterar um parâmetro primitivo não muda a variável original.
- Alterar o estado de um objeto recebido por parâmetro pode afetar o objeto
  observado fora do método.
- Reatribuir um parâmetro de objeto não troca a referência da variável original.
- Recursividade depende de um caso base e de uma chamada que aproxima o problema
  desse caso base.
- A pilha de chamadas guarda o estado de cada chamada recursiva ainda pendente.

## Ordem Sugerida de Estudo

1. `PassagemParametros.java`, para entender valores, referências e objetos antes
   de estudar nós encadeados.
2. Somatório e sequência crescente em `Recursividade.java`, por serem exemplos
   diretos de caso base e chamada recursiva.
3. Divisão inteira e potência de dois, para observar acúmulo de resultado.
4. Soma e inversão de vetor, para acompanhar recursão com índices.
5. MDC, para ver um algoritmo clássico baseado em redução.
6. Torre de Hanoi, para analisar uma recursão com duas chamadas internas.
