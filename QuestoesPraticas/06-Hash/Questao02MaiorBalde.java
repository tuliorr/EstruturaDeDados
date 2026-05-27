/**
 * Questao 02 - Maior Balde.
 *
 * Retorna o tamanho da maior lista de colisao de uma tabela hash com
 * encadeamento externo.
 */
public class Questao02MaiorBalde {

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

            // Valores que caem no mesmo indice ficam ligados por proximo.
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

        int tamanhoMaiorBalde() {
            int maior = 0;

            for (Nodo<T> inicio : tabela) {
                int tamanho = contarElementos(inicio);
                if (tamanho > maior) {
                    maior = tamanho;
                }
            }

            return maior;
        }

        private int contarElementos(Nodo<T> inicio) {
            int quantidade = 0;
            Nodo<T> atual = inicio;
            while (atual != null) {
                quantidade++;
                atual = atual.proximo;
            }
            return quantidade;
        }
    }

    public static void main(String[] args) {
        TabelaHash<Integer> hash = new TabelaHash<>(10);
        int[] valores = { 12, 22, 32, 5, 18, 28 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        System.out.println("Tamanho do maior balde: " + hash.tamanhoMaiorBalde());
        System.out.println("Fator de carga: " + hash.fatorDeCarga());
        System.out.println("Resultado esperado: 3");
    }
}
