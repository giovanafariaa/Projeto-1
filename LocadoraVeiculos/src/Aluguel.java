import java.util.ArrayList;
import java.util.List;

public abstract class Aluguel {
    private int codigo;
    private String cliente;
    private int quantidadeDias;
    private List<Veiculo> veiculos;

    public Aluguel(int codigo, String cliente, int quantidadeDias) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.quantidadeDias = quantidadeDias;
        this.veiculos = new ArrayList<>();
    }

    public int getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public int getQuantidadeDias() { return quantidadeDias; }
    public List<Veiculo> getVeiculos() { return veiculos; }

    /** Limite de km por dia; valor negativo indica quilometragem ilimitada. */
    public abstract int getLimiteKmDia();
    public abstract boolean possuiSeguro();

    public boolean isKmIlimitada() {
        return getLimiteKmDia() < 0;
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (!veiculo.isDisponivel()) {
            System.out.println("O veículo " + veiculo.getPlaca() + " não está disponível.");
            return false;
        }
        veiculos.add(veiculo);
        veiculo.setStatus(StatusVeiculo.ALUGADO);
        return true;
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Veiculo v : veiculos) total += v.getValorDiaria() * quantidadeDias;
        return total;
    }

    protected String detalhesEspecificos() {
        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s | Código: %d | Cliente: %s | %d dia(s) | Km: %s | Seguro: %s | Total: R$ %.2f",
                getClass().getSimpleName(), codigo, cliente, quantidadeDias,
                isKmIlimitada() ? "ilimitada" : getLimiteKmDia() + " km/dia",
                possuiSeguro() ? "sim" : "não", calcularValorTotal()));
        String extra = detalhesEspecificos();
        if (!extra.isEmpty()) sb.append(" | ").append(extra);
        sb.append("\n   Veículos: ");
        if (veiculos.isEmpty()) {
            sb.append("nenhum");
        } else {
            for (Veiculo v : veiculos) sb.append(v.getPlaca()).append(" (").append(v.getModelo()).append("); ");
        }
        return sb.toString();
    }
}
