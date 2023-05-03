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
        chamados.add(new Chamado(2, J, "impressora", "maquina de impressao"));
        chamados.add(new Chamado(3, J, "caneta", "objeto de uso cotidiano"));
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
                System.out.println("Digite a resolução");
                String resolucao = scanner.nextLine();
                Chamado chamadoAtualizado = buscarChamadoPorId(idChamado);
                if(chamadoAtualizado != null) {
                    chamadoAtualizado.setStatusConcluido(resolucao);
                    System.out.println(" ID: " + chamadoAtualizado.getId() +
                    ", data de abertura: " + chamadoAtualizado.getDataAbertura() + ", status: " + chamadoAtualizado.getStatus() + ", resolucao: " + chamadoAtualizado.getResolucao());
                } else {
                    System.out.println("ID não encontrado");
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


    // Nao testado os dois metodos a baixo

    // falta conectar no menu (metodo da funcionalidade 5)
    private static Chamado buscarPorDescricao (String descricao) {
        for (Chamado chamado: chamados) {
            if (chamado.getDescricao() == descricao) {
                return chamado;
            }
        }
        return null;
    }

    // falta conectar ao menu (metodo da funcionalidade 4)
    public static void moverEquipamento() {

        Scanner scanner = new Scanner(System.in);
    
        if (funcionarioLogado.getDepartamento().equals("Suporte")) {
            System.out.println("Chamados em andamento: ");
            for (Chamado chamado : chamados) {
                if (chamado.getFuncionarioAtendimento().equals(funcionarioLogado) && chamado.getStatus().equals("Em andamento")) {
                    System.out.println(" ID: " + chamado.getId() +
                            ", data de abertura: " + chamado.getDataAbertura() + ", status: " + chamado.getStatus());
                }
            }
            System.out.println("Digite o ID do chamado que deseja mover: ");
            int idChamado = scanner.nextInt();
            scanner.nextLine();
    
            Chamado chamadoMovido = buscarChamadoPorId(idChamado);
            if (chamadoMovido == null) {
                System.out.println("ID do chamado não encontrado!");
                return;
            }
    
            if (!chamadoMovido.getStatus().equals("Aberto")) {
                System.out.println("Não é possível mover um chamado já resolvido!");
                return;
            }
    
            System.out.println("Digite o novo departamento do equipamento: ");
            String novoDepartamento = scanner.nextLine();
    
            chamadoMovido.getFuncionarioAtendimento().setDepartamento(novoDepartamento);
            System.out.println("Chamado movido com sucesso!");
            System.out.println(" ID: " + chamadoMovido.getId() +
                    ", equipamento: " + chamadoMovido.getEquipamento() +
                    ", data de abertura: " + chamadoMovido.getDataAbertura() +
                    ", departamento: " + chamadoMovido.getFuncionarioAtendimento().getDepartamento());
        }
    }
}