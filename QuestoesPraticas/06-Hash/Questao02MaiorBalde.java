/**
 * Questao 02 - Maior Balde.
 *
 * Retorna o tamanho da maior lista de colisao de uma tabela hash com
 * encadeamento separado.
 */
public class Questao02MaiorBalde {

    private static class Nodo {
        int valor;
        Nodo proximo;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class TabelaHash {
        private Nodo[] tabela;

        TabelaHash(int capacidade) {
            tabela = new Nodo[capacidade];
        }

        private int hash(int valor) {
            return Math.floorMod(valor, tabela.length);
        }

        void inserir(int valor) {
            int indice = hash(valor);
            Nodo novo = new Nodo(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
        }

        int tamanhoMaiorBalde() {
            int maior = 0;

            for (Nodo inicio : tabela) {
                int tamanho = contarElementos(inicio);
                if (tamanho > maior) {
                    maior = tamanho;
                }
            }

            return maior;
        }

        private int contarElementos(Nodo inicio) {
            int quantidade = 0;
            Nodo atual = inicio;
            while (atual != null) {
                quantidade++;
                atual = atual.proximo;
            }
            return quantidade;
        }
    }

    public static void main(String[] args) {
        TabelaHash hash = new TabelaHash(10);
        int[] valores = { 12, 22, 32, 5, 18, 28 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        System.out.println("Tamanho do maior balde: " + hash.tamanhoMaiorBalde());
        System.out.println("Resultado esperado: 3");
    }
}
