import java.util.Random;

public class AlgoritmoRabin {
    private static int R = 256;
    private static  int Q = 2147483647;

    private static long hash(String s, int M, Counter counter){
        long h = 0;
        counter.operations += 3; 
        for (int j = 0; j < M; j++) {
            counter.iterations++; 
            counter.operations++; 
            counter.operations++; 
            counter.operations += 2; 
            h = (h * R + s.charAt(j)) % Q;
            counter.operations++; 
        }
        return h;
    }

    private static int search(String pat, String txt, Counter counter) {
        int M = pat.length();
        int N = txt.length();
        counter.operations += 2; 
        long patHash = hash(pat, M, new Counter());

        for (int i = 0; i <= N - M; i++) {
            counter.iterations++; 
            counter.operations++; 
            counter.operations++; 
            String substring = txt.substring(i, i + M);
            counter.operations++;
            long txtHash = hash(substring, M, counter);
            counter.operations++; 
            counter.operations++; 
            if (patHash == txtHash) {
                int j;
                for (j = 0; j < M; j++) {
                    counter.operations++;
                    if (pat.charAt(j) != txt.charAt(i + j)) {
                        break;
                    }
                }

                counter.operations++; 
                if (j == M) {
                    return i;
                }
            }
        }
        counter.operations++;
        return -1;
    }

    public static void main(String[] args) {
        String text = "ABCDCBDCBDACBDABDCBADF";
        String pattern = "ADF";
        Counter counterShort = new Counter();
        long startShort = System.currentTimeMillis();
        int resultShort = search(pattern, text, counterShort);
        long endShort = System.currentTimeMillis();
        long timeShortMs = (endShort - startShort);
        System.out.println("=== Resultado para texto curto ===");
        System.out.println("Índice encontrado: " + resultShort);
        System.out.println("Total de operações: " + counterShort.operations);
        System.out.println("Total de iterações: " + counterShort.iterations);
        System.out.println("Tempo de execução: " + timeShortMs + " ms");
        System.out.println();

        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 1000000; i++) {
            sb.append((char) ('A' + rand.nextInt(26))); 
        }
        String bigText = sb.toString();
        String bigPattern = "ESTEVAMCABRALPACHECO"; 
        Counter counterBig = new Counter();
        long startBig = System.currentTimeMillis();
        int resultBig = search(bigPattern, bigText, counterBig);
        long endBig = System.currentTimeMillis();
        long timeBigMs = (endBig - startBig);
        System.out.println("=== Resultado para texto grande (1.000.000 caracteres) ===");
        System.out.println("Índice encontrado: " + resultBig);
        System.out.println("Total de operações: " + counterBig.operations);
        System.out.println("Total de iterações: " + counterBig.iterations);
        System.out.println("Tempo de execução: " + timeBigMs + " ms");
    }

    static class Counter {
        int operations = 0;
        int iterations = 0;
    }
}
