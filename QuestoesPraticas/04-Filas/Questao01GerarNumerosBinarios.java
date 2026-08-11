import java.util.Arrays;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 01 - Gerar números binários.
 *
 * Gera as representações binárias de 1 até uma quantidade informada. Para cada
 * valor retirado da fila, são formados dois novos valores com os sufixos 0 e 1.
 */
public class Questao01GerarNumerosBinarios {

    /**
     * Retorna as primeiras representações binárias, começando por 1.
     */
    public static String[] gerar(int quantidade) {
        String[] binarios = new String[quantidade];
        FilaDinamica<String> fila = new FilaDinamica<>();
        fila.enfileirar("1");

        for (int i = 0; i < quantidade; i++) {
            String atual = fila.desenfileirar();
            binarios[i] = atual;

            fila.enfileirar(atual + "0");
            fila.enfileirar(atual + "1");
        }

        return binarios;
    }

    public static void main(String[] args) {
        System.out.println("Números binários: " + Arrays.toString(gerar(5)));
        System.out.println("Resultado esperado: [1, 10, 11, 100, 101]");

        // Experimente gerar os dez primeiros números binários.
    }
}
