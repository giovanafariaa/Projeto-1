public class AluguelExecutivo extends Aluguel implements Avaliavel {
    private String motorista;
    private Avaliacoes avaliacoes;

    public AluguelExecutivo(int codigo, String cliente, int quantidadeDias, String motorista) {
        super(codigo, cliente, quantidadeDias);
        this.motorista = motorista;
        this.avaliacoes = new Avaliacoes();
    }

    public String getMotorista() { return motorista; }

    @Override
    public int getLimiteKmDia() {
        return -1; // ilimitada
    }

    @Override
    public boolean possuiSeguro() {
        return false;
    }

    @Override
    public void avaliar(int nota) {
        if (avaliacoes.adicionar(nota)) {
            System.out.println("Aluguel executivo " + getCodigo() + " avaliado com nota " + nota + ".");
        }
    }

    @Override
    protected String detalhesEspecificos() {
        return "Motorista: " + motorista + " | " + avaliacoes;
    }
}
