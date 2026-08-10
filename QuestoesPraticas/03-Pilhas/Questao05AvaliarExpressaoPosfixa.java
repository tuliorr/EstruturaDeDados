import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 05 - Avaliar expressão pós-fixa.
 *
 * Em uma expressão pós-fixa, cada operador é aplicado aos dois últimos valores
 * ainda não resolvidos. Os tokens devem ser separados por espaços.
 */
public class Questao05AvaliarExpressaoPosfixa {

    /**
     * Avalia uma expressão válida com inteiros e os operadores +, -, * e /.
     */
    public static int avaliar(String expressao) {
        PilhaDinamica<Integer> operandos = new PilhaDinamica<>();
        String[] tokens = expressao.trim().split("\\s+");

        for (String token : tokens) {
            if (ehOperador(token)) {
                int segundo = operandos.desempilhar();
                int primeiro = operandos.desempilhar();
                operandos.empilhar(calcular(primeiro, segundo, token.charAt(0)));
            } else {
                operandos.empilhar(Integer.parseInt(token));
            }
        }

        return operandos.desempilhar();
    }

    private static boolean ehOperador(String token) {
        return token.length() == 1 && "+-*/".indexOf(token.charAt(0)) >= 0;
    }

    private static int calcular(int primeiro, int segundo, char operador) {
        switch (operador) {
            case '+':
                return primeiro + segundo;
            case '-':
                return primeiro - segundo;
            case '*':
                return primeiro * segundo;
            case '/':
                return primeiro / segundo;
            default:
                throw new IllegalArgumentException("Operador desconhecido.");
        }
    }

    public static void main(String[] args) {
        String expressao = "5 2 + 3 * 4 -";

        System.out.println("Expressão: " + expressao);
        System.out.println("Resultado: " + avaliar(expressao));
        System.out.println("Resultado esperado: 17");

        // Como melhoria, valide expressões incompletas ou com divisão por zero.
    }
}
