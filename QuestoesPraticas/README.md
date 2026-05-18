# Questões Práticas

Esta pasta reúne exercícios de revisão para praticar os conteúdos da disciplina
de Estrutura de Dados. A proposta é complementar as implementações das unidades:
primeiro o aluno estuda a estrutura no pacote principal, depois resolve questões
menores para fixar operações, percursos, casos especiais e aplicações.

As questões foram pensadas para estudo individual, revisão antes de prova e
atividades práticas em aula. Cada arquivo Java contém uma solução autocontida,
com `main` determinístico, para que o aluno consiga executar, observar a saída e
alterar os exemplos sem depender de entrada pelo teclado.

## Organização

```text
QuestoesPraticas/
  01-Recursividade/
  02-Listas/
  03-Pilhas/
  04-Filas/
  05-FilasPrioridade/
  06-Hash/
  07-Arvores/
```

Os prefixos numéricos indicam a relação com a sequência de conteúdos da
disciplina. As pastas `01-Recursividade/`, `02-Listas/`, `03-Pilhas/`,
`04-Filas/` e `05-FilasPrioridade/` estão reservadas para questionários futuros.
Neste momento, os questionários implementados estão em Hash e Árvores,
correspondentes às unidades de estruturas dispersas e estruturas hierárquicas.

1. `06-Hash/`

   Exercícios sobre tabelas hash, colisões, baldes, frequência, conjuntos,
   fator de carga, remoção e rehashing. É recomendado estudar depois de ler os
   exemplos da `Unidade3/`.

2. `07-Arvores/`

   Exercícios sobre árvores binárias, árvores de busca, árvores N-árias,
   percursos, altura, alteração de referências e noções de balanceamento. É
   recomendado estudar depois de ler os exemplos da `Unidade4/`.

## Como Estudar

Esta seção pressupõe que o aluno já estudou e entendeu a parte teórica do
conteúdo. Antes de usar estes exercícios como revisão, é primordial compreender
a estrutura estudada, ler o material teórico e consultar as referências
bibliográficas da disciplina. Depois disso, o repositório ajuda a revisar os
conceitos, praticar a implementação e resolver exercícios com exemplos
determinísticos.

Uma boa ordem de estudo é:

1. Ler o README da unidade correspondente.
2. Executar os exemplos principais da unidade.
3. Ler o enunciado da questão prática antes de abrir a solução.
4. Tentar resolver o método principal no papel ou em um arquivo separado.
5. Comparar sua solução com o arquivo Java da questão.
6. Alterar os dados do `main` para testar outros casos.

Ao revisar, observe principalmente a ideia usada em cada solução: qual estrutura
foi escolhida, qual percurso foi aplicado, quais casos precisam de cuidado e
como a saída confirma o comportamento esperado.

## Padrão das Questões

Cada questionário possui um README próprio com:

- enunciado da questão;
- nível de dificuldade;
- assunto trabalhado;
- objetivo de aprendizagem;
- dica de implementação;
- exemplo de entrada e saída;
- indicação do arquivo Java correspondente.

Cada arquivo Java segue o mesmo padrão didático:

- comentário inicial com o enunciado resumido;
- método principal que resolve a questão;
- métodos auxiliares, quando necessários;
- `main` com exemplo fixo e comentado;
- impressão da saída para conferência.

## Observações

Os arquivos desta pasta não usam `package`, porque os nomes das pastas possuem
prefixos numéricos e hifens para melhorar a organização visual. Assim, cada
arquivo pode ser compilado e executado individualmente como material de revisão.

As soluções servem como referência de estudo. Em atividades avaliativas, o mais
importante é compreender o raciocínio: saber quando percorrer tudo, quando
aproveitar uma regra da estrutura e como tratar casos como colisões, filhos
ausentes, remoções e redimensionamento.

