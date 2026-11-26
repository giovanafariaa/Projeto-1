import java.time.LocalDate;

public class Van extends Veiculo implements Avaliavel, Inspecionavel {
    private int maxPassageiros;
    private Avaliacoes avaliacoes;
    private LocalDate ultimaInspecao;

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, int maxPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.maxPassageiros = maxPassageiros;
        this.avaliacoes = new Avaliacoes();
    }

    public int getMaxPassageiros() { return maxPassageiros; }

    @Override
    public void avaliar(int nota) {
        if (avaliacoes.adicionar(nota)) {
            System.out.println("Van " + getPlaca() + " avaliada com nota " + nota + ".");
        }
    }

    @Override
    public void realizarInspecao() {
        ultimaInspecao = LocalDate.now();
        System.out.println("Inspeção realizada na van " + getPlaca() + " em " + ultimaInspecao + ".");
    }

    @Override
    protected String detalhesEspecificos() {
        return "Passageiros: " + maxPassageiros + " | " + avaliacoes + " | Última inspeção: "
                + (ultimaInspecao == null ? "nunca" : ultimaInspecao);
    }
}
