/**
 * Questao 09 - Classificar Fator de Carga.
 *
 * Calcula o fator de carga de uma tabela hash e retorna uma classificacao
 * textual.
 */
public class Questao09ClassificarFatorCarga {

    private static class Nodo<T> {
        T valor;
        Nodo<T> proximo;

        Nodo(T valor) {
            this.valor = valor;
        }
    }

    private static class TabelaHash<T> {
        private Nodo<T>[] tabela;
        private int nElementos;

        @SuppressWarnings("unchecked")
        TabelaHash(int capacidade) {
            if (capacidade <= 0) {
                throw new IllegalArgumentException("A capacidade deve ser positiva.");
            }
            tabela = (Nodo<T>[]) new Nodo<?>[capacidade];
        }

        int tamanho() {
            return nElementos;
        }

        double fatorDeCarga() {
            return (double) nElementos / tabela.length;
        }

        private int hash(T valor) {
            if (valor == null) {
                throw new IllegalArgumentException("O valor nao pode ser null.");
            }
            return Math.floorMod(valor.hashCode(), tabela.length);
        }

        void inserir(T valor) {
            int indice = hash(valor);
            Nodo<T> novo = new Nodo<>(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        boolean buscar(T valor) {
            Nodo<T> atual = tabela[hash(valor)];
            while (atual != null) {
                if (atual.valor.equals(valor)) {
                    return true;
                }
                atual = atual.proximo;
            }
            return false;
        }

        boolean remover(T valor) {
            int indice = hash(valor);
            Nodo<T> atual = tabela[indice];
            Nodo<T> anterior = null;

            while (atual != null) {
                if (atual.valor.equals(valor)) {
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

        String classificarFatorDeCarga() {
            double fator = fatorDeCarga();

            if (fator < 0.5) {
                return "leve";
            }
            if (fator <= 0.75) {
                return "moderada";
            }
            return "cheia";
        }
    }

    public static void main(String[] args) {
        TabelaHash<Integer> hash = new TabelaHash<>(10);
        int[] valores = { 1, 2, 3, 4, 5, 6 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        System.out.println("Fator de carga: " + hash.fatorDeCarga());
        System.out.println("Classificacao: " + hash.classificarFatorDeCarga());
        System.out.println("Resultado esperado: 0.6 / moderada");
    }
}
