import Unidade2.P01Listas.ListaVetor;

/**
 * Questão 01 - Contar Ocorrências.
 *
 * Conta quantas vezes um valor aparece em uma lista baseada em vetor.
 */
public class Questao01ContarOcorrencias {

    public static int contarOcorrencias(ListaVetor lista, int valor) {
        int quantidade = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            if (lista.obtem(i) == valor) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public static void main(String[] args) {
        ListaVetor lista = new ListaVetor(5);
        lista.insereFinal(3);
        lista.insereFinal(5);
        lista.insereFinal(3);
        lista.insereFinal(7);
        lista.insereFinal(3);

        System.out.println("Lista: " + lista);
        System.out.println("Ocorrências de 3: " + contarOcorrencias(lista, 3));
        System.out.println("Resultado esperado: 3");
    }
}
