public class Carro extends Veiculo implements Avaliavel {
    private int quantidadePortas;
    private Avaliacoes avaliacoes;

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        this.quantidadePortas = quantidadePortas;
        this.avaliacoes = new Avaliacoes();
    }

    public int getQuantidadePortas() { return quantidadePortas; }

    @Override
    public void avaliar(int nota) {
        if (avaliacoes.adicionar(nota)) {
            System.out.println("Carro " + getPlaca() + " avaliado com nota " + nota + ".");
        }
    }

    @Override
    protected String detalhesEspecificos() {
        return "Portas: " + quantidadePortas + " | " + avaliacoes;
    }
}
