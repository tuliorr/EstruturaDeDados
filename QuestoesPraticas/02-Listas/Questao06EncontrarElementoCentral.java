/**
 * Questão 06 - Encontrar Elemento Central.
 *
 * Usa um ponteiro lento e outro rápido para encontrar o centro de uma lista
 * simplesmente encadeada em uma única passagem.
 */
public class Questao06EncontrarElementoCentral {

    public static class ListaSimples {
        private static class Nodo {
            int valor;
            Nodo proximo;

            Nodo(int valor) {
                this.valor = valor;
            }
        }

        private Nodo inicio;
        private Nodo fim;

        public void inserirFinal(int valor) {
            Nodo novo = new Nodo(valor);
            if (inicio == null) {
                inicio = novo;
            } else {
                fim.proximo = novo;
            }
            fim = novo;
        }
    }

    public static int encontrarElementoCentral(ListaSimples lista) {
        ListaSimples.Nodo lento = lista.inicio;
        ListaSimples.Nodo rapido = lista.inicio;
        while (rapido != null && rapido.proximo != null) {
            lento = lento.proximo;
            rapido = rapido.proximo.proximo;
        }
        return lento.valor;
    }

    public static void main(String[] args) {
        ListaSimples lista = new ListaSimples();
        lista.inserirFinal(10);
        lista.inserirFinal(20);
        lista.inserirFinal(30);
        lista.inserirFinal(40);

        System.out.println("Elemento central: " + encontrarElementoCentral(lista));
        System.out.println("Resultado esperado: 30 (o segundo central)");
    }
}
