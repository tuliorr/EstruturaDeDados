# Estrutura de Dados

Repositório didático da disciplina de Estrutura de Dados (T390), voltado para
alunos de graduação em Computação. A ideia central é implementar as estruturas
do zero, de forma simples, legível e comentada, para que seja possível estudar
não apenas como usar cada estrutura, mas também como ela funciona internamente.

Os códigos evitam bibliotecas prontas de estruturas de dados sempre que isso
ajuda o aprendizado. Em vez de esconder ponteiros, vetores, colisões ou
rotações, o repositório expõe essas operações para que elas possam ser
analisadas em aula, depuradas e modificadas pelos alunos.

## Organização Geral

```text
Unidade1/
Unidade2/
  Listas/
  Pilhas/
  Filas/
  FilasPrioridade/
Unidade3/
Unidade4/
```

Cada unidade possui um README próprio com a ordem sugerida de estudo. As pastas
da Unidade 2 também possuem READMEs específicos, porque essa unidade concentra
muitas estruturas lineares.

## Unidade 1 - Técnicas de Programação

Esta unidade apresenta técnicas que aparecem ao longo de toda a disciplina. O
foco atual está em recursividade, que é essencial para compreender árvores,
algoritmos de busca e várias soluções que reduzem um problema a versões menores
dele mesmo.

Conteúdos previstos:

1. Recursividade.
2. Tipos abstratos de dados.
3. Referências e alias.
4. Alocação de memória estática e dinâmica.

## Unidade 2 - Estruturas Lineares

Estruturas lineares organizam elementos em sequência. A diferença entre elas
está no modo de acesso e no custo das operações. Listas são úteis quando se
quer inserir, remover ou consultar por posição; pilhas restringem o acesso ao
topo; filas preservam a ordem de chegada; filas de prioridade removem elementos
de acordo com uma prioridade.

Conteúdos:

1. Listas estáticas e dinâmicas.
2. Pilhas estáticas e dinâmicas.
3. Filas estáticas e dinâmicas.
4. Filas de prioridade não ordenadas, ordenadas e baseadas em heap.

## Unidade 3 - Estruturas Dispersas

Estruturas dispersas usam funções de espalhamento para transformar chaves em
posições de uma tabela. O principal desafio é lidar com colisões, isto é,
quando duas chaves diferentes chegam ao mesmo índice.

Conteúdos:

1. Tabelas hash simples.
2. Funções de hashing.
3. Endereçamento aberto com sondagem.
4. Encadeamento separado.
5. Mapa chave-valor com redimensionamento.

## Unidade 4 - Estruturas Hierárquicas

Estruturas hierárquicas organizam dados em relações de pai e filho. Árvores são
importantes para representar hierarquias, acelerar buscas e manter dados
ordenados. Nesta unidade, a atenção deve ficar tanto na regra de organização dos
nós quanto nos custos de manter a árvore equilibrada.

Conteúdos:

1. Árvore binária.
2. Árvore binária de busca.
3. Árvore balanceada AVL.

## Padrão dos Códigos

Os arquivos seguem um padrão didático:

- comentário inicial explicando a intuição da estrutura;
- seções para atributos, construtor, métodos básicos, inserção, remoção, busca
  e representação;
- comentários objetivos nos métodos, focados na interpretação da operação;
- `main` com exemplo determinístico e comentado;
- `nElementos` como contador lógico de itens;
- nomes de métodos em português, mantendo consistência entre estruturas.
