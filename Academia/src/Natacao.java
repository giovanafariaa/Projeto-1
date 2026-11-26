import java.util.ArrayList;
import java.util.List;

public class Natacao extends Atividade implements Certificavel, ControladorPresenca {
    private double profundidadePiscina;
    private List<String> presencas;

    public Natacao(String nome, String horario, int duracaoMinutos, int maxParticipantes, double profundidadePiscina) {
        super(nome, horario, duracaoMinutos, maxParticipantes, TipoAtividade.INDIVIDUAL);
        this.profundidadePiscina = profundidadePiscina;
        this.presencas = new ArrayList<>();
    }

    public double getProfundidadePiscina() { return profundidadePiscina; }

    @Override
    public void registrarPresenca(String nomeParticipante) {
        presencas.add(nomeParticipante);
        System.out.println("Presença de " + nomeParticipante + " registrada na aula de " + getNome() + ".");
    }

    @Override
    public void emitirCertificado(String nomeParticipante) {
        int aulas = contarPresencas(nomeParticipante);
        System.out.println("=========== CERTIFICADO ===========");
        System.out.println("Certificamos que " + nomeParticipante);
        System.out.println("participou da atividade " + getNome());
        System.out.println("com " + aulas + " presença(s) registrada(s).");
        System.out.println("===================================");
    }

    private int contarPresencas(String nomeParticipante) {
        int total = 0;
        for (String p : presencas) {
            if (p.equalsIgnoreCase(nomeParticipante)) total++;
        }
        return total;
    }

    @Override
    protected String detalhesEspecificos() {
        return String.format("Profundidade: %.1f m | Presenças registradas: %d", profundidadePiscina, presencas.size());
    }
}
