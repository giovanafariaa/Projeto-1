import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Locadora {
    private List<Veiculo> veiculos;
    private List<Aluguel> alugueis;

    public Locadora() {
        this.veiculos = new ArrayList<>();
        this.alugueis = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) { veiculos.add(veiculo); }
    public void cadastrarAluguel(Aluguel aluguel) { alugueis.add(aluguel); }

    public List<Veiculo> getVeiculos() { return veiculos; }
    public List<Aluguel> getAlugueis() { return alugueis; }

    public boolean associarVeiculoAoAluguel(Aluguel aluguel, Veiculo veiculo) {
        return aluguel.adicionarVeiculo(veiculo);
    }

    public void exibirVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println((i + 1) + " - " + veiculos.get(i));
        }
    }

    public void exibirAlugueis() {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (int i = 0; i < alugueis.size(); i++) {
            System.out.println((i + 1) + " - " + alugueis.get(i));
        }
    }

    public List<Veiculo> getVeiculosDisponiveis() {
        List<Veiculo> lista = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.isDisponivel()) lista.add(v);
        }
        return lista;
    }

    public void exibirVeiculosDisponiveis() {
        List<Veiculo> lista = getVeiculosDisponiveis();
        if (lista.isEmpty()) {
            System.out.println("Nenhum veículo disponível.");
            return;
        }
        for (Veiculo v : lista) System.out.println("- " + v);
    }

    public List<Avaliavel> getAvaliaveis() {
        List<Avaliavel> lista = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v instanceof Avaliavel) lista.add((Avaliavel) v);
        }
        for (Aluguel a : alugueis) {
            if (a instanceof Avaliavel) lista.add((Avaliavel) a);
        }
        return lista;
    }

    public List<Inspecionavel> getInspecionaveis() {
        List<Inspecionavel> lista = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v instanceof Inspecionavel) lista.add((Inspecionavel) v);
        }
        return lista;
    }

    public int removerVeiculosEmManutencao() {
        int removidos = 0;
        Iterator<Veiculo> it = veiculos.iterator();
        while (it.hasNext()) {
            Veiculo v = it.next();
            if (v.getStatus() == StatusVeiculo.MANUTENCAO) {
                for (Aluguel a : alugueis) a.removerVeiculo(v);
                it.remove();
                removidos++;
            }
        }
        return removidos;
    }
}
