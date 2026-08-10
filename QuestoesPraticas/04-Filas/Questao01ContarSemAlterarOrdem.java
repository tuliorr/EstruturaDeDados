import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 01 - Contar sem alterar a ordem.
 *
 * Conta as ocorrências de um valor em uma fila. Cada elemento é retirado da
 * frente e recolocado no fim, preservando a ordem depois de uma volta completa.
 */
public class Questao01ContarSemAlterarOrdem {

    /**
     * Conta o valor procurado e deixa a fila na mesma ordem inicial.
     */
    public static <T> int contar(FilaDinamica<T> fila, T procurado) {
        int quantidadeOriginal = fila.tamanho();
        int ocorrencias = 0;

        for (int i = 0; i < quantidadeOriginal; i++) {
            T elemento = fila.desenfileirar();
            if (procurado.equals(elemento)) {
                ocorrencias++;
            }
            fila.enfileirar(elemento);
        }
        return ocorrencias;
    }

    public static void main(String[] args) {
        FilaDinamica<Integer> fila = new FilaDinamica<>();
        int[] valores = { 4, 7, 4, 2, 4 };
        for (int valor : valores) {
            fila.enfileirar(valor);
        }

        System.out.println("Quantidade de valores 4: " + contar(fila, 4));
        System.out.println("Fila preservada: " + fila);
        System.out.println("Resultado esperado: 3 e ordem 4, 7, 4, 2, 4");

        // Experimente procurar um valor que não está na fila.
    }
}
