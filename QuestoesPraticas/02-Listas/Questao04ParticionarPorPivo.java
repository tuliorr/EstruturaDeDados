import Unidade2.P01Listas.ListaSimplesmenteEncadeada;

/**
 * Questão 04 - Particionar por Pivô.
 *
 * Consome uma lista e cria outra com os valores menores que o pivô antes dos
 * demais. A ordem relativa de cada grupo é preservada.
 */
public class Questao04ParticionarPorPivo {

    public static ListaSimplesmenteEncadeada particionarPorPivo(
            ListaSimplesmenteEncadeada lista, int pivo) {
        ListaSimplesmenteEncadeada menores = new ListaSimplesmenteEncadeada();
        ListaSimplesmenteEncadeada maioresOuIguais = new ListaSimplesmenteEncadeada();

        while (!lista.estaVazia()) {
            int valor = lista.removeInicio();
            if (valor < pivo) {
                menores.insereFinal(valor);
            } else {
                maioresOuIguais.insereFinal(valor);
            }
        }

        ListaSimplesmenteEncadeada resultado = new ListaSimplesmenteEncadeada();
        transferir(menores, resultado);
        transferir(maioresOuIguais, resultado);
        return resultado;
    }

    private static void transferir(
            ListaSimplesmenteEncadeada origem, ListaSimplesmenteEncadeada destino) {
        while (!origem.estaVazia()) {
            destino.insereFinal(origem.removeInicio());
        }
    }

    public static void main(String[] args) {
        ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();
        int[] valores = {5, 1, 8, 3, 5, 2};
        for (int valor : valores) {
            lista.insereFinal(valor);
        }

        ListaSimplesmenteEncadeada resultado = particionarPorPivo(lista, 5);
        System.out.println("Lista particionada: " + resultado);
        System.out.println("Lista original após o consumo: " + lista);
        System.out.println("Resultado esperado: 1 -> 3 -> 2 -> 5 -> 8 -> 5 -> null");
    }
}
