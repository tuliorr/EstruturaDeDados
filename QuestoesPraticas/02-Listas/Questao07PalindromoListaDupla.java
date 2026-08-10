/**
 * Questão 07 - Palíndromo em Lista Dupla.
 *
 * Compara ao mesmo tempo os valores do início e do fim de uma lista duplamente
 * encadeada.
 */
public class Questao07PalindromoListaDupla {

    public static class ListaDupla {
        private static class Nodo {
            int valor;
            Nodo anterior;
            Nodo proximo;

            Nodo(int valor) {
                this.valor = valor;
            }
        }

        private Nodo inicio;
        private Nodo fim;
        private int tamanho;

        public void inserirFinal(int valor) {
            Nodo novo = new Nodo(valor);
            novo.anterior = fim;
            if (fim == null) {
                inicio = novo;
            } else {
                fim.proximo = novo;
            }
            fim = novo;
            tamanho++;
        }
    }

    public static boolean ehPalindromo(ListaDupla lista) {
        ListaDupla.Nodo esquerda = lista.inicio;
        ListaDupla.Nodo direita = lista.fim;

        for (int i = 0; i < lista.tamanho / 2; i++) {
            if (esquerda.valor != direita.valor) {
                return false;
            }
            esquerda = esquerda.proximo;
            direita = direita.anterior;
        }
        return true;
    }

    public static void main(String[] args) {
        ListaDupla lista = new ListaDupla();
        int[] valores = {1, 2, 3, 2, 1};
        for (int valor : valores) {
            lista.inserirFinal(valor);
        }

        System.out.println("A lista é palíndromo: " + ehPalindromo(lista));
        System.out.println("Resultado esperado: true");
    }
}
