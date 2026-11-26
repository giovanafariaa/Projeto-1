import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Locadora locadora = new Locadora();

    public static void main(String[] args) {
        criarDadosIniciais();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            System.out.println();
            switch (opcao) {
                case 1: associarVeiculo(); break;
                case 2: locadora.exibirVeiculos(); break;
                case 3: locadora.exibirAlugueis(); break;
                case 4: locadora.exibirVeiculosDisponiveis(); break;
                case 5: avaliar(); break;
                case 6: inspecionar(); break;
                case 7:
                    int qtd = locadora.removerVeiculosEmManutencao();
                    System.out.println(qtd + " veículo(s) em manutenção removido(s).");
                    break;
                case 8: alterarStatusVeiculo(); break;
                case 0: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private static void criarDadosIniciais() {
        Carro carro = new Carro("ABC1D23", "Chevrolet Onix", 2022, 150.0, 25000, 4);
        Moto moto = new Moto("XYZ4E56", "Honda CB 500", 2021, 120.0, 18000, 500);
        Van van = new Van("JKL7F89", "Renault Master", 2020, 320.0, 60000, 15);

        locadora.cadastrarVeiculo(carro);
        locadora.cadastrarVeiculo(moto);
        locadora.cadastrarVeiculo(van);

        AluguelBasico basico = new AluguelBasico(1, "Maria Silva", 3);
        AluguelExecutivo executivo = new AluguelExecutivo(2, "João Souza", 5, "Carlos Pereira");

        locadora.cadastrarAluguel(basico);
        locadora.cadastrarAluguel(executivo);

        locadora.associarVeiculoAoAluguel(executivo, van);
    }

    private static void exibirMenu() {
        System.out.println("======= LOCADORA DE VEÍCULOS =======");
        System.out.println("1 - Associar veículo a um aluguel");
        System.out.println("2 - Exibir todos os veículos");
        System.out.println("3 - Exibir todos os aluguéis");
        System.out.println("4 - Exibir veículos disponíveis");
        System.out.println("5 - Avaliar");
        System.out.println("6 - Realizar inspeção");
        System.out.println("7 - Remover veículos em manutenção");
        System.out.println("8 - Alterar status de um veículo");
        System.out.println("0 - Sair");
    }

    private static void associarVeiculo() {
        List<Aluguel> alugueis = locadora.getAlugueis();
        List<Veiculo> disponiveis = locadora.getVeiculosDisponiveis();
        if (alugueis.isEmpty()) { System.out.println("Nenhum aluguel cadastrado."); return; }
        if (disponiveis.isEmpty()) { System.out.println("Nenhum veículo disponível."); return; }

        locadora.exibirAlugueis();
        int a = lerInt("Número do aluguel: ") - 1;
        if (a < 0 || a >= alugueis.size()) { System.out.println("Aluguel inválido."); return; }

        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + disponiveis.get(i));
        }
        int v = lerInt("Número do veículo: ") - 1;
        if (v < 0 || v >= disponiveis.size()) { System.out.println("Veículo inválido."); return; }

        if (locadora.associarVeiculoAoAluguel(alugueis.get(a), disponiveis.get(v))) {
            System.out.println("Veículo associado com sucesso!");
        }
    }

    private static void avaliar() {
        List<Avaliavel> lista = locadora.getAvaliaveis();
        if (lista.isEmpty()) { System.out.println("Nada a avaliar."); return; }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        int op = lerInt("Escolha o item: ") - 1;
        if (op < 0 || op >= lista.size()) { System.out.println("Opção inválida."); return; }
        int nota = lerInt("Nota (" + Avaliacoes.NOTA_MIN + " a " + Avaliacoes.NOTA_MAX + "): ");
        lista.get(op).avaliar(nota);
    }

    private static void inspecionar() {
        List<Inspecionavel> lista = locadora.getInspecionaveis();
        if (lista.isEmpty()) { System.out.println("Nenhum veículo inspecionável."); return; }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        int op = lerInt("Escolha o veículo: ") - 1;
        if (op < 0 || op >= lista.size()) { System.out.println("Opção inválida."); return; }
        lista.get(op).realizarInspecao();
    }

    private static void alterarStatusVeiculo() {
        List<Veiculo> veiculos = locadora.getVeiculos();
        if (veiculos.isEmpty()) { System.out.println("Nenhum veículo cadastrado."); return; }
        locadora.exibirVeiculos();
        int op = lerInt("Número do veículo: ") - 1;
        if (op < 0 || op >= veiculos.size()) { System.out.println("Veículo inválido."); return; }

        StatusVeiculo[] status = StatusVeiculo.values();
        for (int i = 0; i < status.length; i++) {
            System.out.println((i + 1) + " - " + status[i]);
        }
        int s = lerInt("Novo status: ") - 1;
        if (s < 0 || s >= status.length) { System.out.println("Status inválido."); return; }
        veiculos.get(op).setStatus(status[s]);
        System.out.println("Status atualizado para " + status[s] + ".");
    }

    private static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
