package Unidade1;

import java.util.Arrays;

/**
 * Reune exemplos classicos de recursividade usados para observar o mesmo
 * padrao: definir um caso base simples e reduzir o problema ate chegar nele.
 * Como cada chamada ocupa espaco na pilha, os exemplos foram pensados para
 * entradas pequenas, que permitem acompanhar a execucao com facilidade.
 *
 * Como melhoria, o aluno pode validar casos extremos, como Integer.MIN_VALUE,
 * antes dos metodos que usam Math.abs. Aqui priorizamos a ideia recursiva.
 */
public class Recursividade {

    // =========================
    // Somatorio
    // =========================

    /**
     * Soma os inteiros de 1 ate n. A intuicao e separar o problema em "n" mais a
     * soma dos valores menores que n.
     */
    public static int somarAteN(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + somarAteN(n - 1);
    }

    // =========================
    // Sequencia
    // =========================

    /**
     * Imprime a sequencia de 0 ate n. A posicao da escrita em relacao a chamada
     * recursiva define se a ordem sera crescente ou decrescente.
     */
    public static void imprimirSequencia(int n) {
        if (n < 0) {
            return;
        }

        // Para imprimir em ordem decrescente, comente a escrita depois da
        // recursao e descomente a linha abaixo:
        // System.out.print(n + " ");

        imprimirSequencia(n - 1);

        // Como a escrita esta depois da recursao, a ordem atual e crescente.
        System.out.print(n + " ");
    }

    // =========================
    // Divisao inteira
    // =========================

    /**
     * Calcula a divisao inteira por subtracoes sucessivas. A parte recursiva
     * trabalha com valores positivos; o metodo publico trata o sinal antes.
     */
    public static int divisaoInteira(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("O divisor nao pode ser zero.");
        }

        int sinal = (dividendo < 0) ^ (divisor < 0) ? -1 : 1;
        int quociente = divisaoInteiraPositiva(Math.abs(dividendo), Math.abs(divisor));
        return sinal * quociente;
    }

    private static int divisaoInteiraPositiva(int dividendo, int divisor) {
        if (dividendo < divisor) {
            return 0;
        }
        return 1 + divisaoInteiraPositiva(dividendo - divisor, divisor);
    }

    // =========================
    // Potencia de dois
    // =========================

    /**
     * Calcula 2 elevado ao expoente. O intervalo de 0 a 30 evita overflow no
     * tipo int. A cada chamada reduzimos o expoente em uma unidade.
     */
    public static int potenciaDeDois(int expoente) {
        if (expoente < 0 || expoente > 30) {
            throw new IllegalArgumentException("O expoente deve estar entre 0 e 30.");
        }
        if (expoente == 0) {
            return 1;
        }
        return 2 * potenciaDeDois(expoente - 1);
    }

    // =========================
    // Vetores
    // =========================

    /**
     * Soma um vetor considerando que a soma a partir de uma posicao e o valor
     * daquela posicao mais a soma do restante do vetor.
     */
    public static int somaVetor(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            return 0;
        }
        return somaVetor(vetor, 0);
    }

    private static int somaVetor(int[] vetor, int posicao) {
        if (posicao == vetor.length) {
            return 0;
        }
        return vetor[posicao] + somaVetor(vetor, posicao + 1);
    }

    /**
     * Inverte o vetor trocando as extremidades. Depois da troca, o problema
     * restante e inverter o miolo do vetor.
     */
    public static void inverterVetor(int[] vetor) {
        if (vetor == null) {
            return;
        }
        inverterVetor(vetor, 0, vetor.length - 1);
    }

    private static void inverterVetor(int[] vetor, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }

        int auxiliar = vetor[inicio];
        vetor[inicio] = vetor[fim];
        vetor[fim] = auxiliar;

        inverterVetor(vetor, inicio + 1, fim - 1);
    }

    // =========================
    // MDC
    // =========================

    /**
     * Calcula o maximo divisor comum pelo algoritmo de Euclides. A intuicao e
     * substituir o par (a, b) por (b, resto), preservando o MDC.
     */
    public static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0) {
            return a;
        }
        return mdc(b, a % b);
    }

    // =========================
    // Torre de Hanoi
    // =========================

    /**
     * Exibe os movimentos da Torre de Hanoi.
     *
     * Logica da solucao: para mover n discos, primeiro movemos os n-1 menores
     * para a haste auxiliar, depois movemos o maior para o destino e, por fim,
     * movemos os n-1 discos da haste auxiliar para o destino. Cada etapa com
     * n-1 discos repete exatamente a mesma ideia.
     */
    public static void torreHanoi(int discos, char origem, char auxiliar, char destino) {
        if (discos <= 0) {
            return;
        }

        torreHanoi(discos - 1, origem, destino, auxiliar);
        System.out.println("Mover disco de " + origem + " para " + destino);
        torreHanoi(discos - 1, auxiliar, origem, destino);
    }

    // =========================
    // Teste dos exemplos
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Exemplos de Recursividade ===");

        // Somatorio: mostra a reducao n + soma(n - 1).
        System.out.println("Somatorio de 1 ate 5: " + somarAteN(5));

        // Sequencia: escrever depois da recursao produz a ordem crescente.
        System.out.print("Sequencia crescente ate 6: ");
        imprimirSequencia(6);
        System.out.println();
        // Se a escrita for movida para antes da recursao, a saida sera:
        // 6 5 4 3 2 1 0

        // Divisao: 17 / 3 cabe cinco vezes quando subtraimos 3 repetidamente.
        System.out.println("Divisao inteira 17 / 3: " + divisaoInteira(17, 3));
        System.out.println("Divisao inteira -17 / 3: " + divisaoInteira(-17, 3));

        // Potencia: cada nivel da pilha de chamadas representa uma multiplicacao por 2.
        System.out.println("2 elevado a 6: " + potenciaDeDois(6));

        int[] valores = { 3, 2, 5, 4, 6 };
        System.out.println("Vetor original: " + Arrays.toString(valores));
        System.out.println("Soma do vetor: " + somaVetor(valores));

        // Inversao: as extremidades se encontram ate nao restar nada para trocar.
        inverterVetor(valores);
        System.out.println("Vetor invertido: " + Arrays.toString(valores));

        // MDC: exemplo classico do algoritmo de Euclides.
        System.out.println("MDC de 48 e 18: " + mdc(48, 18));

        // Hanoi: tres discos produzem poucos movimentos e deixam a estrategia visivel.
        System.out.println("Movimentos da Torre de Hanoi com 3 discos:");
        torreHanoi(3, 'A', 'B', 'C');
    }
}
