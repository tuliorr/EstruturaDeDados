# Filas

Filas seguem a regra FIFO: o primeiro elemento inserido é o primeiro removido.
Essa estrutura representa bem situações de atendimento, buffers, escalonamento
simples e processamento por ordem de chegada.

## Ordem Sugerida

1. `FilaEstatica.java`

   Implementa uma fila circular em vetor. Os índices `inicio` e `fim` avançam
   usando módulo, reaproveitando posições liberadas sem deslocar os elementos.

2. `FilaDinamica.java`

   Implementa uma fila genérica com nodos encadeados. As referências `inicio` e
   `fim` permitem desenfileirar e enfileirar em tempo constante.

## Ideias Importantes

- O elemento removido sempre vem da frente.
- O elemento inserido sempre entra no fim.
- Uma fila estática circular evita desperdício de posições no vetor.
- Uma fila dinâmica cresce criando nodos, sem precisar mover elementos.
