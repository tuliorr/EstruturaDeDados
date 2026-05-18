/**
 * Questao 14 - Identificar Rotacao AVL.
 *
 * Identifica qual rotacao corrige o primeiro desbalanceamento em uma sequencia
 * curta de insercao.
 */
public class Questao14IdentificarRotacaoAVL {

    public static String identificarRotacao(int primeiro, int segundo, int terceiro) {
        if (segundo < primeiro && terceiro < segundo) {
            return "simples a direita";
        }
        if (segundo > primeiro && terceiro > segundo) {
            return "simples a esquerda";
        }
        if (segundo < primeiro && terceiro > segundo && terceiro < primeiro) {
            return "dupla esquerda-direita";
        }
        if (segundo > primeiro && terceiro < segundo && terceiro > primeiro) {
            return "dupla direita-esquerda";
        }
        return "sem rotacao";
    }

    public static String identificarCaso(int primeiro, int segundo, int terceiro) {
        if (segundo < primeiro && terceiro < segundo) {
            return "esquerda-esquerda";
        }
        if (segundo > primeiro && terceiro > segundo) {
            return "direita-direita";
        }
        if (segundo < primeiro && terceiro > segundo && terceiro < primeiro) {
            return "esquerda-direita";
        }
        if (segundo > primeiro && terceiro < segundo && terceiro > primeiro) {
            return "direita-esquerda";
        }
        return "sem desbalanceamento";
    }

    public static void main(String[] args) {
        int primeiro = 30;
        int segundo = 10;
        int terceiro = 20;

        System.out.println("Caso: " + identificarCaso(primeiro, segundo, terceiro));
        System.out.println("Rotacao: " + identificarRotacao(primeiro, segundo, terceiro));
        System.out.println("Resultado esperado: esquerda-direita / dupla esquerda-direita");
    }
}
