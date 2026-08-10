# Unidade 3 - Estruturas Dispersas

Estruturas dispersas usam uma função de hashing para transformar uma chave em
um índice de tabela. A vantagem buscada é acesso rápido; o desafio é tratar
colisões, que acontecem quando chaves diferentes produzem o mesmo índice.

## Ordem Sugerida

1. `HashSimples.java`

   Mostra a ideia básica da função de hashing usando inteiros. Ainda não trata
   colisões de forma completa; quando há colisão, o valor antigo é substituído.

2. `HashSimplesGenerico.java`

   Usa `hashCode()` para calcular índices de objetos genéricos. É útil para
   discutir por que objetos precisam de uma representação numérica para entrar
   em uma tabela hash.

3. `HashSondagemLinear.java`

   Resolve colisões por endereçamento aberto. Quando uma posição está ocupada,
   a estrutura tenta a próxima posição. Também apresenta o conceito de lápide
   para remoção.

4. `HashSondagemQuadratica.java`

   Também usa endereçamento aberto, mas a distância da tentativa cresce ao
   quadrado. O exemplo usa capacidade prima e fator de carga máximo `0,5` para
   garantir que a sequência de tentativas encontre uma posição disponível.
   Para a tentativa `i`, calcula `h(i) = (floorMod(chave, m) + i²) % m`, com
   `i` variando de `0` a `m/2` e `i²` calculado em `long`.

5. `HashComEncadeamento.java`

   Resolve colisões com listas encadeadas em cada índice. Em vez de procurar
   outra posição, todos os elementos que colidem ficam na lista daquele índice.

6. `HashMapGenerico.java`

   Implementa um mapa chave-valor com encadeamento e redimensionamento. É o
   exemplo mais próximo do uso cotidiano de uma tabela hash.

## Ideias Importantes

- Uma boa função hash espalha bem as chaves.
- Colisão é inevitável em tabelas finitas.
- Endereçamento aberto guarda tudo na própria tabela.
- Encadeamento separado usa uma estrutura auxiliar em cada posição.
- Redimensionar exige rehashing: todos os elementos precisam ter seus índices
  recalculados na nova tabela, porque o tamanho participa do cálculo do índice.
  Por exemplo, se `hash(18) = 18`, então em uma tabela de tamanho `10` o índice
  é `18 % 10 = 8`; depois de redimensionar para tamanho `20`, o mesmo elemento
  passa para o índice `18 % 20 = 18`.

## Complexidades

| Estrutura/operação | Caso médio | Pior caso | Observação |
|---|---:|---:|---|
| Hash simples | `O(1)` | `O(1)` | uma colisão substitui o valor anterior |
| Sondagem linear | `O(1)` | `O(m)` | pode formar agrupamentos consecutivos |
| Sondagem quadrática | `O(1)` | `O(m)` | exige as regras de capacidade e carga do exemplo |
| Encadeamento | `O(1)` | `O(n)` | o pior caso concentra tudo em um balde |
| Mapa: buscar/remover | `O(1)` | `O(n)` | depende da distribuição das chaves |
| Mapa: inserir | `O(1)` amortizado | `O(n)` | o rehash ocasional percorre todas as entradas |

As tabelas hash usam espaço `O(m + n)` com encadeamento e `O(m)` com
endereçamento aberto. O caso médio pressupõe uma função hash que distribua bem
as chaves.
