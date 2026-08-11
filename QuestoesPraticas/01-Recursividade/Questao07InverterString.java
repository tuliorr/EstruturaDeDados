/**
 * Questão 07 - Inverter String.
 *
 * Inverte recursivamente a ordem dos caracteres de um texto.
 */
public class Questao07InverterString {

    public static String inverter(String texto) {
        if (texto.length() <= 1) {
            return texto;
        }
        return inverter(texto.substring(1)) + texto.charAt(0);
    }

    public static void main(String[] args) {
        String texto = "dados";

        System.out.println("Texto invertido: " + inverter(texto));
        System.out.println("Resultado esperado: sodad");
    }
}
