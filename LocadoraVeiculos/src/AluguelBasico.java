public class AluguelBasico extends Aluguel {
    private static final int LIMITE_KM_DIA = 100;

    public AluguelBasico(int codigo, String cliente, int quantidadeDias) {
        super(codigo, cliente, quantidadeDias);
    }

    @Override
    public int getLimiteKmDia() {
        return LIMITE_KM_DIA;
    }

    @Override
    public boolean possuiSeguro() {
        return false;
    }
}
