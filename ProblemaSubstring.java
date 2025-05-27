public class ProblemaSubstring {
    public static int naiveSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int operations = 0;
        int iterations = 0;

        operations += 2;
        for (int i = 0; i <= n - m; i++) {
            iterations++;
            operations++;
            operations++;
            for (int j = 0; j < m; j++) {
                iterations++;
                operations++;
                operations++;
                operations += 2;
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    operations++;
                    break;
                }
                operations++;
                if (j == m - 1) {
                    operations++;
                    System.out.println("Total de operações: " + operations);
                    System.out.println("Total de iterações: " + iterations);
                    return i;
                }
            }
        }

        operations++;
        System.out.println("Total de operações: " + operations);
        System.out.println("Total de iterações: " + iterations);
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("===== Caso de Teste Original =====");
        String text1 = "ABCDCBDCBDACBDABDCBADF";
        String pattern1 = "ADF";

        long startTime1 = System.currentTimeMillis();
        int result1 = naiveSearch(text1, pattern1);
        long endTime1 = System.currentTimeMillis();

        System.out.println("Resultado: " + result1);
        System.out.println("Tempo total (ms): " + (endTime1 - startTime1));

        System.out.println("\n===== Caso de Teste com 1.000.000 caracteres =====");
        int tamanhoTexto = 1_000_000;
        StringBuilder sb = new StringBuilder(tamanhoTexto);
        for (int i = 0; i < tamanhoTexto - 3; i++) {
            sb.append('A');
        }
        sb.append("XYZ");

        String text2 = sb.toString();
        String pattern2 = "XYZ";

        long startTime2 = System.currentTimeMillis();
        int result2 = naiveSearch(text2, pattern2);
        long endTime2 = System.currentTimeMillis();

        System.out.println("Resultado: " + result2);
        System.out.println("Tempo total (ms): " + (endTime2 - startTime2));
    }
}
