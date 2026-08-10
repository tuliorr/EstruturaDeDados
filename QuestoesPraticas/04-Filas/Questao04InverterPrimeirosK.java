import Unidade2.P02Pilhas.PilhaDinamica;
import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 04 - Inverter os primeiros K elementos.
 *
 * Uma pilha inverte apenas o prefixo escolhido. Depois, a parte que não foi
 * invertida gira pela fila para voltar a ficar depois desse prefixo.
 */
public class Questao04InverterPrimeirosK {

    /**
     * Inverte os primeiros k elementos da própria fila.
     */
    public static <T> void inverter(FilaDinamica<T> fila, int k) {
        int tamanhoOriginal = fila.tamanho();
        PilhaDinamica<T> pilha = new PilhaDinamica<>();

        for (int i = 0; i < k; i++) {
            pilha.empilhar(fila.desenfileirar());
        }
        while (!pilha.estaVazia()) {
            fila.enfileirar(pilha.desempilhar());
        }

        for (int i = 0; i < tamanhoOriginal - k; i++) {
            fila.enfileirar(fila.desenfileirar());
        }
    }

    public static void main(String[] args) {
        FilaDinamica<Integer> fila = new FilaDinamica<>();
        for (int valor = 1; valor <= 5; valor++) {
            fila.enfileirar(valor);
        }

        inverter(fila, 3);
        System.out.println("Fila depois de inverter os 3 primeiros: " + fila);
        System.out.println("Resultado esperado: 3, 2, 1, 4, 5");

        // Experimente k igual a zero e k igual ao tamanho da fila.
    }
}
