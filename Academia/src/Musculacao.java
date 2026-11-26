public class Musculacao extends Atividade {
    private int quantidadeAparelhos;

    public Musculacao(String nome, String horario, int duracaoMinutos, int maxParticipantes, int quantidadeAparelhos) {
        super(nome, horario, duracaoMinutos, maxParticipantes, TipoAtividade.INDIVIDUAL);
        this.quantidadeAparelhos = quantidadeAparelhos;
    }

    public int getQuantidadeAparelhos() { return quantidadeAparelhos; }

    @Override
    protected String detalhesEspecificos() {
        return "Aparelhos: " + quantidadeAparelhos;
    }
}
