import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        PessoaController controller = PessoaController.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Buscar registro por id");
            System.out.println("2 - Listar todas os registros");
            System.out.println("3 - Criar nova registro");
            System.out.println("4 - Atualizar um registro");
            System.out.println("5 - Remover um registro");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
            case 1:
                boolean exibirTodas = false;
                while (true) {
                    System.out.println("Digite o id do registro:");
                    int id = scanner.nextInt();
                    Pessoa pessoa = controller.getById(id);
                    if (pessoa != null) {
                        System.out.println("Registro encontrado: " + pessoa);
                        break;
                    } else {
                        System.out.println("Registro não encontrado.");
                        System.out.println("Deseja exibir todos os registros? (s/n)");
                        String escolheropcao = scanner.next();
                        if (escolheropcao.equalsIgnoreCase("s")) {
                            exibirTodas = true;
                            break;
                        }
                    }
                }
                if (exibirTodas) {
                    System.out.println("Listando todos os registros:");
                    List<Pessoa> pessoas = controller.findAll();
                    for (Pessoa p : pessoas) {
                        System.out.println(p);
                    }
                }
                break;
                case 2:
                    System.out.println("Listando todos os registros:");
                    List<Pessoa> pessoas = controller.findAll();
                    for (Pessoa p : pessoas) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.println("Novo registro:");
                    Pessoa novaPessoa = new Pessoa();
                    System.out.println("Digite o nome:");
                    novaPessoa.setNome(scanner.next());
                    System.out.println("Digite o email:");
                    novaPessoa.setEmail(scanner.next());
                    System.out.println("Digite o cargo:");
                    novaPessoa.setCargo(scanner.next());
                    controller.persist(novaPessoa);
                    System.out.println("Registro criado com sucesso.");
                    break;
                case 4:
                    System.out.println("Atualizar registro:");
                    int idAtualizar;
                    Pessoa pessoaAtualizar;

                    do {
                        System.out.println("Digite o id do registro:");
                        idAtualizar = scanner.nextInt();
                        pessoaAtualizar = controller.getById(idAtualizar);
                        if (pessoaAtualizar == null) {
                            System.out.println("Registro não encontrado.");
                            System.out.println("Deseja exibir todos os registros? (s/n)");
                            String escolheropcao = scanner.next();
                            if (escolheropcao.equalsIgnoreCase("s")) {
                                List<Pessoa> pessoasatualizar = controller.findAll();
                                for (Pessoa p : pessoasatualizar) {
                                    System.out.println(p);
                                }
                            }
                        }
                    } while (pessoaAtualizar == null);

                    System.out.println("Digite o novo nome:");
                    pessoaAtualizar.setNome(scanner.next());
                    System.out.println("Digite o novo email:");
                    pessoaAtualizar.setEmail(scanner.next());
                    System.out.println("Digite o novo cargo:");
                    pessoaAtualizar.setCargo(scanner.next());
                    controller.merge(pessoaAtualizar);
                    System.out.println("Registro atualizada com sucesso.");
                    break;
                case 5:
                    boolean exibirTodasRemover = false;
                    while (true) {
                        System.out.println("Digite o id do registro:");
                        int id = scanner.nextInt();
                        Pessoa pessoa = controller.getById(id);
                        if (pessoa != null) {
                            System.out.println("Registro encontrado: " + pessoa);
                            controller.remove(pessoa);
                            System.out.println("Registro removido com sucesso.");
                            break;
                        } else {
                            System.out.println("Registro não encontrado.");
                            System.out.println("Deseja exibir todos os registros? (s/n)");
                            String escolheropcao = scanner.next();
                            if (escolheropcao.equalsIgnoreCase("s")) {
                                exibirTodasRemover = true;
                                break;
                            }
                        }
                    }
                    if (exibirTodasRemover) {
                        System.out.println("Listando todos os registros:");
                        List<Pessoa> pessoasremover = controller.findAll();
                        for (Pessoa p : pessoasremover) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 0:
                    continuar = false;
                    System.out.println("Sessão encerrada!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}