import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaSuporte {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Chamado> chamados = new ArrayList<>();
    private static Funcionario funcionarioLogado;

    public static void main(String[] args) {
        // Inicializar lista de funcionários e chamados (exemplo)
        Funcionario J = new Funcionario(1, "João", "RH");
        funcionarios.add(J);
        funcionarios.add(new Funcionario(2, "Maria", "Suporte"));
        funcionarios.add(new Funcionario(3, "Pedro", "Financeiro"));
        chamados.add(new Chamado(1, J, "martelo", "cabo removido"));
        chamados.get(0).setFuncionarioAtendimento(funcionarios.get(1));
        // Selecionar funcionário
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() +
                    ", Nome: " + funcionario.getNome() +
                    ", Departamento: " + funcionario.getDepartamento());
        }
        System.out.print("Digite o ID do funcionário que está usando o sistema: ");
        int idFuncionario = scanner.nextInt();

        funcionarioLogado = buscarFuncionarioPorId(idFuncionario);
        if (funcionarioLogado != null) {
            System.out.println("Funcionário logado: " + funcionarioLogado.getNome());

            for (Chamado chamado : chamados) {
                System.out.println("Lista de chamados:");
                if (chamado.getFuncionarioAbertura().equals(funcionarioLogado)) {
                    System.out.println(" ID: " + chamado.getId() +
                            ", data de abertura: " + chamado.getDataAbertura() + ", status: " + chamado.getStatus());
                }
            }
            if(funcionarioLogado.getDepartamento() == "Suporte") {
                System.out.println("Chamados em andamento: ");
                for (Chamado chamado : chamados) {
                    if (chamado.getFuncionarioAtendimento().equals(funcionarioLogado) && chamado.getStatus() == "Em andamento") {
                        System.out.println(" ID: " + chamado.getId() +
                                ", data de abertura: " + chamado.getDataAbertura() + ", status: " + chamado.getStatus());
                    }
                }
                System.out.println("Digite qual chamado deseja atualizar: ");
                int idChamado = scanner.nextInt();
                scanner.nextLine();
                Chamado chamadoAtualizado = buscarChamadoPorId(idChamado); // Alterado a ordem do if abaixo para só pedir resolução se o ID existir.
                if(chamadoAtualizado == null) {
                    System.out.println("ID não encontrado");
                }
                else {
                    System.out.println("Digite a resolução");
                    String resolucao = scanner.nextLine();
                    chamadoAtualizado.setStatusConcluido(resolucao);
                    System.out.println(" ID: " + chamadoAtualizado.getId() +
                    ", data de abertura: " + chamadoAtualizado.getDataAbertura() + ", status: " + chamadoAtualizado.getStatus() + ", resolucao: " + chamadoAtualizado.getResolucao());
                }
            }
            System.out.println();
            // Exibir menu de opções
            /*
             * boolean sair = false;
             * while (!sair) {
             * System.out.println("----------- MENU -----------");
             * }
             */

        }
    }

    // Método para buscar funcionário por ID
    private static Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }
    private static Chamado buscarChamadoPorId (int id) {
        for (Chamado chamado: chamados) {
            if (chamado.getId() == id) {
                return chamado;
            }
        }
        return null;
    }
}