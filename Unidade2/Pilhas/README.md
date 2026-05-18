# Pilhas

Pilhas seguem a regra LIFO: o último elemento inserido é o primeiro removido.
Essa restrição parece simples, mas aparece em muitos contextos: chamadas de
função, recursividade, algoritmos de backtracking, navegação com voltar e
validação de expressões.

## Ordem Sugerida

1. `PilhaEstatica.java`

   Implementa uma pilha com vetor de caracteres. O atributo `topo` indica a
   posição do último elemento inserido. O arquivo também usa a pilha para
   verificar balanceamento de parênteses, colchetes e chaves.

2. `PilhaDinamica.java`

   Implementa uma pilha genérica com nodos encadeados. Cada novo nodo aponta
   para o antigo topo, e depois passa a ser o novo topo.

## Ideias Importantes

- Empilhar altera apenas o topo.
- Desempilhar remove apenas o topo.
- Em uma pilha dinâmica, não existe capacidade fixa definida no construtor.
- A pilha é uma estrutura excelente para mostrar como a ordem de remoção pode
  ser diferente da ordem de inserção.
