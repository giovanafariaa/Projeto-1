import java.util.ArrayList;
import java.util.List;

public class Spinning extends Atividade implements ControladorPresenca {
    private int quantidadeBicicletas;
    private List<String> presencas;

    public Spinning(String nome, String horario, int duracaoMinutos, int maxParticipantes, int quantidadeBicicletas) {
        super(nome, horario, duracaoMinutos, maxParticipantes, TipoAtividade.COLETIVA);
        this.quantidadeBicicletas = quantidadeBicicletas;
        this.presencas = new ArrayList<>();
    }

    public int getQuantidadeBicicletas() { return quantidadeBicicletas; }

    @Override
    public void registrarPresenca(String nomeParticipante) {
        if (presencas.size() >= quantidadeBicicletas) {
            System.out.println("Não há bicicletas disponíveis na aula de " + getNome() + ".");
            return;
        }
        presencas.add(nomeParticipante);
        System.out.println("Presença de " + nomeParticipante + " registrada na aula de " + getNome() + ".");
    }

    @Override
    protected String detalhesEspecificos() {
        return "Bicicletas: " + quantidadeBicicletas + " | Presenças registradas: " + presencas.size();
    }
}
