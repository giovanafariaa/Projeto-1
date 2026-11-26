import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Academia academia = new Academia();

    public static void main(String[] args) {
        criarDadosIniciais();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            System.out.println();
            switch (opcao) {
                case 1: associarAtividade(); break;
                case 2: academia.exibirAtividades(); break;
                case 3: academia.exibirPlanos(); break;
                case 4: academia.exibirPlanosAtivos(); break;
                case 5: emitirCertificado(); break;
                case 6: registrarPresenca(); break;
                case 7:
                    int qtd = academia.removerAtividadesInativas();
                    System.out.println(qtd + " atividade(s) inativa(s) removida(s).");
                    break;
                case 8: registrarAcessoPlano(); break;
                case 9: alterarStatusAtividade(); break;
                case 10: alterarStatusPlano(); break;
                case 0: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private static void criarDadosIniciais() {
        Musculacao musc = new Musculacao("Musculação Manhã", "07:00", 60, 30, 25);
        Natacao nat = new Natacao("Natação Adulto", "09:00", 50, 15, 1.8);
        Spinning spin = new Spinning("Spinning Noite", "19:00", 45, 20, 20);

        academia.cadastrarAtividade(musc);
        academia.cadastrarAtividade(nat);
        academia.cadastrarAtividade(spin);

        PlanoBasico basico = new PlanoBasico("Plano Básico", 89.90);
        PlanoPremium premium = new PlanoPremium("Plano Premium", 159.90);

        academia.cadastrarPlano(basico);
        academia.cadastrarPlano(premium);

        academia.associarAtividadeAoPlano(basico, musc);
        academia.associarAtividadeAoPlano(premium, musc);
        academia.associarAtividadeAoPlano(premium, spin);
    }

    private static void exibirMenu() {
        System.out.println("========= ACADEMIA =========");
        System.out.println("1 - Associar atividade a um plano");
        System.out.println("2 - Exibir todas as atividades");
        System.out.println("3 - Exibir todos os planos");
        System.out.println("4 - Exibir planos ativos");
        System.out.println("5 - Emitir certificado");
        System.out.println("6 - Registrar presença em atividade");
        System.out.println("7 - Remover atividades inativas");
        System.out.println("8 - Registrar acesso de cliente (planos com controle de frequência)");
        System.out.println("9 - Ativar/inativar atividade");
        System.out.println("10 - Ativar/inativar plano");
        System.out.println("0 - Sair");
    }

    private static void registrarAcessoPlano() {
        List<Plano> planos = academia.getPlanos();
        List<ControladorPresenca> controlados = new java.util.ArrayList<>();
        for (Plano p : planos) {
            if (p instanceof ControladorPresenca && p.isAtivo()) controlados.add((ControladorPresenca) p);
        }
        if (controlados.isEmpty()) { System.out.println("Nenhum plano ativo com controle de frequência."); return; }
        for (int i = 0; i < controlados.size(); i++) {
            System.out.println((i + 1) + " - " + ((Plano) controlados.get(i)).getNome());
        }
        int op = lerInt("Escolha o plano: ") - 1;
        if (op < 0 || op >= controlados.size()) { System.out.println("Opção inválida."); return; }
        String nome = lerTexto("Nome do cliente: ");
        controlados.get(op).registrarPresenca(nome);
    }

    private static void alterarStatusAtividade() {
        List<Atividade> atividades = academia.getAtividades();
        if (atividades.isEmpty()) { System.out.println("Nenhuma atividade cadastrada."); return; }
        academia.exibirAtividades();
        int op = lerInt("Número da atividade: ") - 1;
        if (op < 0 || op >= atividades.size()) { System.out.println("Atividade inválida."); return; }
        Atividade a = atividades.get(op);
        a.setStatus(a.isAtiva() ? StatusAtividade.INATIVA : StatusAtividade.ATIVA);
        System.out.println("Atividade " + a.getNome() + " agora está " + a.getStatus() + ".");
    }

    private static void alterarStatusPlano() {
        List<Plano> planos = academia.getPlanos();
        if (planos.isEmpty()) { System.out.println("Nenhum plano cadastrado."); return; }
        academia.exibirPlanos();
        int op = lerInt("Número do plano: ") - 1;
        if (op < 0 || op >= planos.size()) { System.out.println("Plano inválido."); return; }
        Plano p = planos.get(op);
        p.setStatus(p.isAtivo() ? StatusPlano.INATIVO : StatusPlano.ATIVO);
        System.out.println("Plano " + p.getNome() + " agora está " + p.getStatus() + ".");
    }

    private static void associarAtividade() {
        List<Plano> planos = academia.getPlanos();
        List<Atividade> atividades = academia.getAtividades();
        if (planos.isEmpty() || atividades.isEmpty()) {
            System.out.println("É necessário ter planos e atividades cadastrados.");
            return;
        }
        academia.exibirPlanos();
        int p = lerInt("Número do plano: ") - 1;
        if (p < 0 || p >= planos.size()) { System.out.println("Plano inválido."); return; }

        academia.exibirAtividades();
        int a = lerInt("Número da atividade: ") - 1;
        if (a < 0 || a >= atividades.size()) { System.out.println("Atividade inválida."); return; }

        if (academia.associarAtividadeAoPlano(planos.get(p), atividades.get(a))) {
            System.out.println("Atividade associada com sucesso!");
        }
    }

    private static void emitirCertificado() {
        List<Certificavel> lista = academia.getCertificaveis();
        if (lista.isEmpty()) { System.out.println("Nenhuma atividade emite certificado."); return; }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        int op = lerInt("Escolha a atividade: ") - 1;
        if (op < 0 || op >= lista.size()) { System.out.println("Opção inválida."); return; }
        String nome = lerTexto("Nome do participante: ");
        lista.get(op).emitirCertificado(nome);
    }

    private static void registrarPresenca() {
        List<ControladorPresenca> lista = academia.getAtividadesComPresenca();
        if (lista.isEmpty()) { System.out.println("Nenhuma atividade controla presença."); return; }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        int op = lerInt("Escolha a atividade: ") - 1;
        if (op < 0 || op >= lista.size()) { System.out.println("Opção inválida."); return; }
        String nome = lerTexto("Nome do participante: ");
        lista.get(op).registrarPresenca(nome);
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

    private static String lerTexto(String msg) {
        String texto;
        do {
            System.out.print(msg);
            texto = sc.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }
}
