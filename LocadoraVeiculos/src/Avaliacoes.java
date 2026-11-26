import java.util.ArrayList;
import java.util.List;

/**
 * Componente reutilizado por quem implementa Avaliavel (composição),
 * evitando duplicar a lógica de notas em Carro, Van e AluguelExecutivo.
 */
public class Avaliacoes {
    public static final int NOTA_MIN = 0;
    public static final int NOTA_MAX = 10;

    private List<Integer> notas = new ArrayList<>();

    public boolean adicionar(int nota) {
        if (nota < NOTA_MIN || nota > NOTA_MAX) {
            System.out.println("Nota inválida. Informe um valor entre " + NOTA_MIN + " e " + NOTA_MAX + ".");
            return false;
        }
        notas.add(nota);
        return true;
    }

    public double media() {
        if (notas.isEmpty()) return 0;
        int soma = 0;
        for (int n : notas) soma += n;
        return (double) soma / notas.size();
    }

    public int quantidade() {
        return notas.size();
    }

    @Override
    public String toString() {
        return notas.isEmpty() ? "Sem avaliações" : String.format("Média: %.1f (%d avaliações)", media(), notas.size());
    }
}
