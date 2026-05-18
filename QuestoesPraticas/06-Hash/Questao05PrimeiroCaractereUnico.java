import java.util.HashMap;
import java.util.Map;

/**
 * Questao 05 - Primeiro Caractere Nao Repetido.
 *
 * Retorna o primeiro caractere que aparece apenas uma vez em uma palavra.
 */
public class Questao05PrimeiroCaractereUnico {

    public static Character primeiroCaractereUnico(String palavra) {
        Map<Character, Integer> frequencias = new HashMap<>();

        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            int frequenciaAtual = frequencias.getOrDefault(caractere, 0);
            frequencias.put(caractere, frequenciaAtual + 1);
        }

        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            if (frequencias.get(caractere) == 1) {
                return caractere;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String palavra = "abacaxi";
        Character resposta = primeiroCaractereUnico(palavra);

        System.out.println("Primeiro caractere unico: " + resposta);
        System.out.println("Resultado esperado: b");
    }
}
