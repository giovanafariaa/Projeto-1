import java.util.ArrayList;
import java.util.List;

public abstract class Plano {
    private String nome;
    private double valorMensal;
    private StatusPlano status;
    private List<Atividade> atividades;

    public Plano(String nome, double valorMensal) {
        this.nome = nome;
        this.valorMensal = valorMensal;
        this.status = StatusPlano.ATIVO;
        this.atividades = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public double getValorMensal() { return valorMensal; }
    public StatusPlano getStatus() { return status; }
    public List<Atividade> getAtividades() { return atividades; }

    public void setStatus(StatusPlano status) { this.status = status; }

    public boolean isAtivo() {
        return status == StatusPlano.ATIVO;
    }

    public abstract boolean permiteAtividade(Atividade atividade);

    public boolean adicionarAtividade(Atividade atividade) {
        if (atividades.contains(atividade)) {
            System.out.println("A atividade já está associada a este plano.");
            return false;
        }
        if (!permiteAtividade(atividade)) {
            System.out.println("O plano " + nome + " não permite a atividade " + atividade.getNome() + ".");
            return false;
        }
        atividades.add(atividade);
        return true;
    }

    public void removerAtividade(Atividade atividade) {
        atividades.remove(atividade);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s | %s | R$ %.2f | %s", getClass().getSimpleName(), nome, valorMensal, status));
        sb.append("\n   Atividades: ");
        if (atividades.isEmpty()) {
            sb.append("nenhuma");
        } else {
            for (Atividade a : atividades) sb.append(a.getNome()).append("; ");
        }
        return sb.toString();
    }
}
