/**
 * Questao 05 - Primeiro Caractere Nao Repetido.
 *
 * Retorna o primeiro caractere que aparece apenas uma vez em uma palavra.
 */
public class Questao05PrimeiroCaractereUnico {

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

            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    atual.valor = valor;
                    return;
                }
                atual = atual.proximo;
            }

            // Chaves diferentes que caem no mesmo indice ficam no mesmo balde.
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
    }

    public static Character primeiroCaractereUnico(String palavra) {
        TabelaHash<Character, Integer> frequencias = new TabelaHash<>(palavra.length() * 2 + 1);

        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            Integer frequenciaAtual = frequencias.buscar(caractere);
            if (frequenciaAtual == null) {
                frequencias.inserir(caractere, 1);
            } else {
                frequencias.inserir(caractere, frequenciaAtual + 1);
            }
        }

        // A segunda passada preserva a ordem original da palavra.
        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            if (frequencias.buscar(caractere) == 1) {
                return caractere;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String palavra = "abacaxi";
        Character resposta = primeiroCaractereUnico(palavra);

        System.out.println("Primeiro caractere unico: " + resposta);
        System.out.println("Resultado esperado: b");
    }
}
