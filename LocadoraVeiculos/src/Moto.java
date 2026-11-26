import java.time.LocalDate;

public class Moto extends Veiculo implements Inspecionavel {
    private int cilindrada;
    private LocalDate ultimaInspecao;

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }

    @Override
    public void realizarInspecao() {
        ultimaInspecao = LocalDate.now();
        System.out.println("Inspeção realizada na moto " + getPlaca() + " em " + ultimaInspecao + ".");
    }

    @Override
    protected String detalhesEspecificos() {
        return "Cilindrada: " + cilindrada + " cc | Última inspeção: "
                + (ultimaInspecao == null ? "nunca" : ultimaInspecao);
    }
}
