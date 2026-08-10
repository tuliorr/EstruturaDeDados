import Unidade2.P01Listas.ListaVetorGenericaDinamica;

/**
 * Questão 02 - Remover Duplicados.
 *
 * Remove valores repetidos de uma lista dinâmica e preserva a primeira
 * ocorrência de cada valor.
 */
public class Questao02RemoverDuplicados {

    public static void removerDuplicados(ListaVetorGenericaDinamica<Integer> lista) {
        for (int i = 0; i < lista.tamanho(); i++) {
            int j = i + 1;
            while (j < lista.tamanho()) {
                if (lista.obtem(i).equals(lista.obtem(j))) {
                    lista.removePosicao(j);
                } else {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        ListaVetorGenericaDinamica<Integer> lista = new ListaVetorGenericaDinamica<>(3);
        lista.insereFinal(4);
        lista.insereFinal(2);
        lista.insereFinal(4);
        lista.insereFinal(3);
        lista.insereFinal(2);
        lista.insereFinal(4);

        System.out.println("Antes: " + lista);
        removerDuplicados(lista);
        System.out.println("Depois: " + lista);
        System.out.println("Resultado esperado: [4, 2, 3]");
    }
}
