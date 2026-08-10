/**
 * Questão 05 - Inverter Encadeamento.
 *
 * Inverte as referências de uma lista simplesmente encadeada sem criar novos
 * nodos.
 */
public class Questao05InverterEncadeamento {

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

        @Override
        public String toString() {
            StringBuilder texto = new StringBuilder();
            Nodo atual = inicio;
            while (atual != null) {
                texto.append(atual.valor).append(" -> ");
                atual = atual.proximo;
            }
            return texto.append("null").toString();
        }
    }

    public static void inverterEncadeamento(ListaSimples lista) {
        ListaSimples.Nodo anterior = null;
        ListaSimples.Nodo atual = lista.inicio;
        lista.fim = lista.inicio;

        while (atual != null) {
            ListaSimples.Nodo proximo = atual.proximo;
            atual.proximo = anterior;
            anterior = atual;
            atual = proximo;
        }
        lista.inicio = anterior;
    }

    public static void main(String[] args) {
        ListaSimples lista = new ListaSimples();
        lista.inserirFinal(10);
        lista.inserirFinal(20);
        lista.inserirFinal(30);
        lista.inserirFinal(40);

        System.out.println("Antes: " + lista);
        inverterEncadeamento(lista);
        System.out.println("Depois: " + lista);
        System.out.println("Resultado esperado: 40 -> 30 -> 20 -> 10 -> null");
    }
}
