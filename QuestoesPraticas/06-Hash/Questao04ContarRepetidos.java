/**
 * Questao 04 - Contar Valores Repetidos.
 *
 * Conta quantos valores diferentes aparecem mais de uma vez em um vetor.
 */
public class Questao04ContarRepetidos {

    private static class Nodo<K, V> {
        K chave;
        V valor;
        Nodo<K, V> proximo;

        Nodo(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private static class TabelaHash<K, V> {
        private Nodo<K, V>[] tabela;
        private int nElementos;

        @SuppressWarnings("unchecked")
        TabelaHash(int capacidade) {
            if (capacidade <= 0) {
                throw new IllegalArgumentException("A capacidade deve ser positiva.");
            }
            tabela = (Nodo<K, V>[]) new Nodo<?, ?>[capacidade];
        }

        int tamanho() {
            return nElementos;
        }

        double fatorDeCarga() {
            return (double) nElementos / tabela.length;
        }

        private int hash(K chave) {
            if (chave == null) {
                throw new IllegalArgumentException("A chave nao pode ser null.");
            }
            return Math.floorMod(chave.hashCode(), tabela.length);
        }

        void inserir(K chave, V valor) {
            int indice = hash(chave);
            Nodo<K, V> atual = tabela[indice];

            // Se a chave ja existe, apenas atualizamos o valor associado.
            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    atual.valor = valor;
                    return;
                }
                atual = atual.proximo;
            }

            Nodo<K, V> novo = new Nodo<>(chave, valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        V buscar(K chave) {
            Nodo<K, V> atual = tabela[hash(chave)];
            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    return atual.valor;
                }
                atual = atual.proximo;
            }
            return null;
        }

        boolean remover(K chave) {
            int indice = hash(chave);
            Nodo<K, V> atual = tabela[indice];
            Nodo<K, V> anterior = null;

            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    if (anterior == null) {
                        tabela[indice] = atual.proximo;
                    } else {
                        anterior.proximo = atual.proximo;
                    }
                    nElementos--;
                    return true;
                }
                anterior = atual;
                atual = atual.proximo;
            }
            return false;
        }

        int contarValoresInteirosMaioresQue(int limite) {
            int quantidade = 0;

            for (Nodo<K, V> inicio : tabela) {
                Nodo<K, V> atual = inicio;
                while (atual != null) {
                    // Nesta questao o valor associado e a frequencia, por isso e Integer.
                    if (((Integer) atual.valor) > limite) {
                        quantidade++;
                    }
                    atual = atual.proximo;
                }
            }

            return quantidade;
        }
    }

    public static int contarValoresRepetidos(int[] valores) {
        TabelaHash<Integer, Integer> frequencias = new TabelaHash<>(valores.length * 2 + 1);

        for (int valor : valores) {
            Integer frequenciaAtual = frequencias.buscar(valor);
            if (frequenciaAtual == null) {
                frequencias.inserir(valor, 1);
            } else {
                frequencias.inserir(valor, frequenciaAtual + 1);
            }
        }

        return frequencias.contarValoresInteirosMaioresQue(1);
    }

    public static void main(String[] args) {
        int[] valores = { 4, 7, 4, 2, 7, 9, 7 };

        System.out.println("Quantidade de valores repetidos: " + contarValoresRepetidos(valores));
        System.out.println("Resultado esperado: 2");
    }
}
