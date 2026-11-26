import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlanoBasico extends Plano implements ControladorPresenca {
    private Map<String, LocalDate> ultimoAcesso;

    public PlanoBasico(String nome, double valorMensal) {
        super(nome, valorMensal);
        this.ultimoAcesso = new HashMap<>();
    }

    @Override
    public boolean permiteAtividade(Atividade atividade) {
        return atividade.getTipo() == TipoAtividade.INDIVIDUAL;
    }

    // Controle de frequência do plano: limita a 1 acesso por dia
    @Override
    public void registrarPresenca(String nomeParticipante) {
        String chave = nomeParticipante.toLowerCase();
        LocalDate hoje = LocalDate.now();
        if (hoje.equals(ultimoAcesso.get(chave))) {
            System.out.println("Acesso negado: " + nomeParticipante + " já acessou a academia hoje.");
            return;
        }
        ultimoAcesso.put(chave, hoje);
        System.out.println("Acesso de " + nomeParticipante + " registrado no plano " + getNome() + ".");
    }
}
