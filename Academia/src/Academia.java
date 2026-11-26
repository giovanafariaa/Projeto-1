import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Academia {
    private List<Atividade> atividades;
    private List<Plano> planos;

    public Academia() {
        this.atividades = new ArrayList<>();
        this.planos = new ArrayList<>();
    }

    public void cadastrarAtividade(Atividade atividade) { atividades.add(atividade); }
    public void cadastrarPlano(Plano plano) { planos.add(plano); }

    public List<Atividade> getAtividades() { return atividades; }
    public List<Plano> getPlanos() { return planos; }

    public boolean associarAtividadeAoPlano(Plano plano, Atividade atividade) {
        if (!atividade.isAtiva()) {
            System.out.println("Não é possível associar uma atividade inativa.");
            return false;
        }
        return plano.adicionarAtividade(atividade);
    }

    public void exibirAtividades() {
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        for (int i = 0; i < atividades.size(); i++) {
            System.out.println((i + 1) + " - " + atividades.get(i));
        }
    }

    public void exibirPlanos() {
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }
        for (int i = 0; i < planos.size(); i++) {
            System.out.println((i + 1) + " - " + planos.get(i));
        }
    }

    public void exibirPlanosAtivos() {
        boolean encontrou = false;
        for (Plano p : planos) {
            if (p.isAtivo()) {
                System.out.println("- " + p);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum plano ativo.");
    }

    public List<Certificavel> getCertificaveis() {
        List<Certificavel> lista = new ArrayList<>();
        for (Atividade a : atividades) {
            if (a instanceof Certificavel) lista.add((Certificavel) a);
        }
        return lista;
    }

    public List<ControladorPresenca> getAtividadesComPresenca() {
        List<ControladorPresenca> lista = new ArrayList<>();
        for (Atividade a : atividades) {
            if (a instanceof ControladorPresenca) lista.add((ControladorPresenca) a);
        }
        return lista;
    }

    public int removerAtividadesInativas() {
        int removidas = 0;
        Iterator<Atividade> it = atividades.iterator();
        while (it.hasNext()) {
            Atividade a = it.next();
            if (!a.isAtiva()) {
                for (Plano p : planos) p.removerAtividade(a);
                it.remove();
                removidas++;
            }
        }
        return removidas;
    }
}
