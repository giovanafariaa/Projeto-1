public abstract class Atividade {
    private String nome;
    private String horario;
    private int duracaoMinutos;
    private int maxParticipantes;
    private StatusAtividade status;
    private TipoAtividade tipo;

    public Atividade(String nome, String horario, int duracaoMinutos, int maxParticipantes, TipoAtividade tipo) {
        this.nome = nome;
        this.horario = horario;
        this.duracaoMinutos = duracaoMinutos;
        this.maxParticipantes = maxParticipantes;
        this.tipo = tipo;
        this.status = StatusAtividade.ATIVA;
    }

    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public int getMaxParticipantes() { return maxParticipantes; }
    public StatusAtividade getStatus() { return status; }
    public TipoAtividade getTipo() { return tipo; }

    public void setStatus(StatusAtividade status) { this.status = status; }

    public boolean isAtiva() {
        return status == StatusAtividade.ATIVA;
    }

    protected abstract String detalhesEspecificos();

    @Override
    public String toString() {
        return String.format("%s | %s | Horário: %s | Duração: %d min | Máx.: %d participantes | %s | %s",
                getClass().getSimpleName(), nome, horario, duracaoMinutos, maxParticipantes, tipo, status)
                + " | " + detalhesEspecificos();
    }
}
