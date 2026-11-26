public class AluguelPremium extends Aluguel {

    public AluguelPremium(int codigo, String cliente, int quantidadeDias) {
        super(codigo, cliente, quantidadeDias);
    }

    @Override
    public int getLimiteKmDia() {
        return -1; // ilimitada
    }

    @Override
    public boolean possuiSeguro() {
        return true;
    }
}
